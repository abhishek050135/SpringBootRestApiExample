package com.websystique.springboot.configuration;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	
	 private static final SessionFactory sessionFactory = buildSessionFactory();
	 
	    // Hibernate 5:
	    private static SessionFactory buildSessionFactory() {
	        try {
	            // Create the ServiceRegistry from hibernate.cfg.xml
	            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()//
	                    .configure("hibernate.cfg.xml").build();
	 
	            // Create a metadata sources using the specified service registry.
	            Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
	 
	            
	            System.out.println("Session Factory builded.");
	            return metadata.getSessionFactoryBuilder().build();
	        } catch (Throwable ex) {
	         
	            System.err.println("Initial SessionFactory creation failed." + ex);
	            throw new ExceptionInInitializerError(ex);
	        }
	    }
	 
	    public static SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }
	 
	    public static void shutdown() {
	        // Close caches and connection pools
	        getSessionFactory().close();
	    }

	/*
	private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/springtest?autoReconnect=true&useSSL=false");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "root");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "update");
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(UserServiceEntity.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
    */
    
}