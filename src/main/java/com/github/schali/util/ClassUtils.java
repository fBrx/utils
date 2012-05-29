package com.github.schali.util;

import java.net.URL;
import java.security.ProtectionDomain;

/**
 * Utility methods to work with and get information for classes.
 *
 * @author Florian Müller
 */
public class ClassUtils {
	
	/**
	 * Returns the location (JAR-File) from where the supplied class was loaded from. Returns <code>null</code> in case
	 * of any error (for example when security restrictions apply).
	 * 
	 * @param clazz the class
	 * @return the location from where tha class was loaded or <code>null</code>
	 * @see Class#getProtectionDomain()
	 * @see ProtectionDomain#getCodeSource()
	 */
	public static URL which(Class<?> clazz) {
		try {
			return clazz.getProtectionDomain().getCodeSource().getLocation();	
		}catch (Throwable ex) {
			return null;
		}
	}
}
