package com.soen.hasslefree.persistence;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
 
public class HibernateUtil {
 
	private static final SessionFactory sessionFactory = buildSessionFactory();
 
	private static SessionFactory buildSessionFactory() {
		try {
			// load from different directory
			SessionFactory sessionFactory = new Configuration().configure()
					.buildSessionFactory();
 
			return sessionFactory;
 
		} catch (HibernateException ex) {
			// Make sure you log the exception, as it might be swallowed
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
 
}

//public class HibernateUtil {
//
//    private static final SessionFactory sessionFactory;
//
//    static {
//        try {
//            sessionFactory = new Configuration().configure().buildSessionFactory();
//        } catch (HibernateException ex) {
//            // Log the exception. 
//            System.err.println("Initial SessionFactory creation failed." + ex);
//            throw new ExceptionInInitializerError(ex);
//        }
//    }
//
//    public static SessionFactory getSessionFactory() {
//        return sessionFactory;
//    }
//
//    public static void shutdown() {
//        // Close caches and connection pools
//        getSessionFactory().close();
//    }
//
//}
