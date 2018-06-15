package com.zq.soap_jaxws;

import org.springframework.stereotype.Component;

import javax.jws.WebService;

/**
 * Author zq
 * Created by CTSIG on 2018/6/13.
 * Email : qizhou1994@126.com
 */
@WebService
@Component
public class HelloServiceImpl implements HelloService {
    public String say(String name) {
        return "hello" + name ;
    }
}
