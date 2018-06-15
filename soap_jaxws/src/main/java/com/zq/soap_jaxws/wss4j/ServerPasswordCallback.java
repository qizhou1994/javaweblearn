package com.zq.soap_jaxws.wss4j;

import org.apache.wss4j.common.ext.WSPasswordCallback;
import org.springframework.stereotype.Component;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Author zq
 * Created by CTSIG on 2018/6/15.
 * Email : qizhou1994@126.com
 */
@Component
public class ServerPasswordCallback implements CallbackHandler {

    private static final Map<String,String> userMap = new HashMap<String, String>();

    static {
        userMap.put("client","clientpass");
        userMap.put("server","serverpass");
    }

    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {

        WSPasswordCallback callback = (WSPasswordCallback) callbacks[0];

        String clientUsername = callback.getIdentifier();
        String serverPassword = userMap.get(clientUsername);

        if(serverPassword != null){
            callback.setPassword(serverPassword);
        }
    }
}
