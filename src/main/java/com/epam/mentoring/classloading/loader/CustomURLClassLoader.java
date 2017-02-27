package com.epam.mentoring.classloading.loader;

import java.net.URL;
import java.net.URLClassLoader;

import com.epam.mentoring.classloading.constant.ClassLoadingConstant;
import com.epam.mentoring.classloading.security.ClassPathPermission;

public class CustomURLClassLoader extends URLClassLoader {

	public CustomURLClassLoader(URL[] urls, ClassLoader parent) {
		super(urls, parent);
	}

	public CustomURLClassLoader(URL[] urls) {
		super(urls, CustomClassLoader.class.getClassLoader());
	}

	@Override
	public Class loadClass(String name) throws ClassNotFoundException {
		if (name.startsWith(ClassLoadingConstant.SEMAPHORE_PACKAGE_NAME)) {
			SecurityManager sm = System.getSecurityManager();
			if (sm != null) {
				String path = getURLs()[0].getPath();
				ClassPathPermission p = new ClassPathPermission(path, "load");
				sm.checkPermission(p);
			}
			super.loadClass(name);
		}
		return super.loadClass(name);
	}
}
