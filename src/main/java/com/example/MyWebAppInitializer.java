package com.example;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    // configure servlet context (view resolvers etc)
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { MyMvcConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    // configure root application context (middle tier services, data sources etc)
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }
}