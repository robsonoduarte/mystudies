package br.com.mystudies.web.listener;

import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.commons.lang3.time.DateFormatUtils;


@WebListener
public class AppListener implements ServletContextListener {

	
    public AppListener() {
    }


    public void contextInitialized(ServletContextEvent servletContextEvent) {
    	ServletContext servletContext = servletContextEvent.getServletContext();
    	servletContext.setAttribute("appDeployTime", DateFormatUtils.format(new Date(), "dd/MM/yyyy HH:mm:ss"));
    }


    public void contextDestroyed(ServletContextEvent arg0) {
    }
	
}
