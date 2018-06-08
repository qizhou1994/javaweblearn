package com.zq.smartsecurity.exception;

/**
 * Author zq
 * Created by CTSIG on 2018/6/7.
 * Email : qizhou1994@126.com
 */
public class AuthcException extends Exception{

    public AuthcException(){
        super();
    }

    public AuthcException(String message){
        super(message);
    }



    public AuthcException(String message,Throwable cause){
        super(message,cause);
    }

    public AuthcException(Throwable cause){
        super(cause);
    }

}
