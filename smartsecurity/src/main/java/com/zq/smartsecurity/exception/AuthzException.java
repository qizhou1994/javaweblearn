package com.zq.smartsecurity.exception;

/**
 * Author zq
 * Created by CTSIG on 2018/6/7.
 * Email : qizhou1994@126.com
 */
public class AuthzException extends RuntimeException {


    public AuthzException(){
        super();
    }

    public AuthzException(String message){
        super(message);
    }



    public AuthzException(String message,Throwable cause){
        super(message,cause);
    }

    public AuthzException(Throwable cause){
        super(cause);
    }
}
