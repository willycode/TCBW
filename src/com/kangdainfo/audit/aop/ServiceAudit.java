package com.kangdainfo.audit.aop;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class ServiceAudit implements AfterReturningAdvice  {

    public void afterReturning(Object returnValue, Method m, Object[] args, Object target) throws Throwable {
    	System.out.println(m.getName());
    }

}
