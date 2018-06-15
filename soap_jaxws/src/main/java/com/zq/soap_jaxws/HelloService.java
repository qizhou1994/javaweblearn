package com.zq.soap_jaxws;

import javax.jws.WebService;

/**
 * Author zq
 * Created by CTSIG on 2018/6/13.
 * Email : qizhou1994@126.com
 */
@WebService
public interface HelloService {
    String say(String name);
}
