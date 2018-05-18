package com.zq.smart_framework.proxy.test.aop.asp;

import com.zq.smart_framework.proxy.test.aop.Apology;

/**
 * Author zq
 * Created by CTSIG on 2018/5/18.
 * Email : qizhou1994@126.com
 */
public class ApologyImpl implements Apology {
    @Override
    public void saySorry(String name) {
        System.out.println("ApologyImpl saySory : " +name);
    }
}
