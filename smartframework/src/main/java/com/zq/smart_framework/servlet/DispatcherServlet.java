package com.zq.smart_framework.servlet;

import com.zq.smart_framework.HelperLoader;
import com.zq.smart_framework.bean.Data;
import com.zq.smart_framework.bean.Handler;
import com.zq.smart_framework.bean.Param;
import com.zq.smart_framework.bean.View;
import com.zq.smart_framework.helper.*;
import com.zq.smart_framework.util.*;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Author zq
 * Created by CTSIG on 2018/5/11.
 * Email : qizhou1994@126.com
 */

@WebServlet("/*")
public class DispatcherServlet extends HttpServlet {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(DispatcherServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("init");
        //初始化相关Helper类
        HelperLoader.init();
        System.out.println("运行完？init");
        //获取ServletContext对象 （注册servlet）
        ServletContext servletContext = config.getServletContext();
        //注册处理JSP的Servlet
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");
        System.out.println("ConfigHelper.getAppJspPath() + \"* \" = " + ConfigHelper.getAppJspPath());
        //注册处理静态资源的默认Servlet
        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");

        defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");
        System.out.println("ConfigHelper.getAppAssetPath() + \"*\" =" + ConfigHelper.getAppAssetPath());

        UploadHelper.init(servletContext);
    }

    private void registerServlet(ServletContext servletContext) {
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping("/index.jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");

        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping("/favicon.ico");
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");
    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("输入网页");

        ServletHelper.init(req, resp);
        try {
            //获取请求方法与请求路径
            String requestMethod = req.getMethod().toLowerCase();
            String requestPath = req.getPathInfo();
            System.out.println("requestMethod = " + requestMethod);
            System.out.println("requestPath = " + requestPath);
         /*   if (requestPath.equals("/favicon.ico")) {
                return;
            }*/
            //获取Action处理器
            Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
            if (handler != null) {
                //获取Controller类及其bean实例
                Class<?> controllerClass = handler.getControllerClass();
                Object controllerBean = BeanHelper.getBean(controllerClass);
                //创建请求参数对象
                Param param;
                if (UploadHelper.isMultipart(req)) {
                    param = UploadHelper.createParam(req);
                } else {
                    param = RequestHelper.createParam(req);
                }
         /*
            Map<String, Object> paramMap = new HashMap<String, Object>();
            Enumeration<String> paramNames = req.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String paramName = paramNames.nextElement();
                String paramValue = req.getParameter(paramName);
                paramMap.put(paramName, paramValue);
            }
            String body = CodecUtil.decodeURL(StreamUtil.getString(req.getInputStream()));
            if (StringUtil.isNotEmpty(body)) {
                String[] params = StringUtil.splitString(body, "&");
                if (ArrayUtil.isNotEmpty(params)) {
                    for (String param : params) {
                        String[] array = StringUtil.splitString(param,"=");
                        if(ArrayUtil.isNotEmpty(array) && array.length == 2){
                            String paramName = array[0];
                            String paramValue = array[1];
                            paramMap.put(paramName,paramValue);
                        }
                    }
                }
            }
            //设置请求参数
            Param param = new Param(paramMap);*/
                //调用Action方法
                Method actionMethod = handler.getActionMethod();
                //请求参数为空与不为空 分别走两种
                Object result;
                System.out.println("param  = " + param);
                if (param.isEmpty()) {
                    result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, new Object[]{});
                } else {
                    result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, new Object[]{param});
                }

//              result = ReflectionUtil.invokeMethod(controllerBean,actionMethod,param);
                //处理Action方法返回值
                System.out.println("请求为" + result);
                if (result instanceof View) {
                    //返回JSP页面
                    View view = (View) result;
                    handleViewResult(view, req, resp);
                } else if (result instanceof Data) {
                    //返回JSON数据
                    Data data = (Data) result;
                    handleDataResult(data, resp);
                }
            }
        } catch (Exception e) {
            handleViewResult(new View("error.jsp"), req, resp);
            LOGGER.error("Servlet error:" + e);
        } finally {
            ServletHelper.destory();
        }
    }

    /**
     * 返回视图Jsp
     *
     * @param view
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void handleViewResult(View view, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = view.getPath();
        if (StringUtil.isNotEmpty(path)) {
            if (path.startsWith("/")) {
                response.sendRedirect(request.getContextPath() + path);
            } else {
                Map<String, Object> model = view.getModel();
                for (Map.Entry<String, Object> entry : model.entrySet()) {
                    request.setAttribute(entry.getKey(), entry.getValue());
                }
                request.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(request, response);
            }
        }
    }

    /**
     * 返回json数据
     *
     * @param data
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void handleDataResult(Data data, HttpServletResponse response) throws IOException {
        //返回JSON数据
        Object model = data.getModel();
        if (data != null) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            String json = JsonUtil.toJson(data);
            writer.write(json);
            writer.flush();
            writer.close();
        }
    }
}
