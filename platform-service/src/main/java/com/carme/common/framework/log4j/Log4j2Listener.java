package com.carme.common.framework.log4j;

import org.springframework.util.ResourceUtils;
import org.springframework.web.util.Log4jConfigListener;

import javax.servlet.ServletContextEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by admin on 2017/4/21.
 */
public class Log4j2Listener extends Log4jConfigListener {

    private final static String LOG4J_PROPERTIES_PATH = "log4jProperties";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        initLogProperty(servletContextEvent);
        super.contextInitialized(servletContextEvent);
    }

    private void initLogProperty(ServletContextEvent event) {
        String resourceLocation = event.getServletContext().getInitParameter(LOG4J_PROPERTIES_PATH);
        Properties prop = new Properties();
        try {
            File file = ResourceUtils.getFile(resourceLocation);
            prop.load(new FileInputStream(file));
        } catch (IOException e) {
            throw new IllegalArgumentException("日志加载配置文件失败！path= " + resourceLocation, e);
        }
        Enumeration<Object> en = prop.keys();
        while (en.hasMoreElements()) {
            String name = en.nextElement().toString();
            String value = prop.getProperty(name);
            System.setProperty(name, value);
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
