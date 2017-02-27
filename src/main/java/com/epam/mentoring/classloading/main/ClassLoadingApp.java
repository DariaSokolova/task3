package com.epam.mentoring.classloading.main;

import java.io.Console;
import java.io.File;
import java.net.URL;

import com.epam.mentoring.classloading.constant.ClassLoadingConstant;
import com.epam.mentoring.classloading.loader.CustomClassLoader;
import com.epam.mentoring.classloading.loader.CustomURLClassLoader;

public class ClassLoadingApp {
	public static void main(String[] args) {
		Console console = System.console();
		SecurityManager security = System.getSecurityManager();
		if (args.length >= 1) {
			System.out.println("Task 3");
			String path = console.readLine("Enter a path to jar: ");
			try {
				URL[] urls = new URL[] { new File(path).toURI().toURL() };
				CustomURLClassLoader loader = new CustomURLClassLoader(urls);
				Class<?> cl = loader
						.loadClass(ClassLoadingConstant.SEMAPHORE_CLASS_NAME);
				cl.getMethod("lever").invoke(cl.newInstance());

				loader.close();
			} catch (Exception e) {
				System.err.println("Error is ocurred:");
				e.printStackTrace();
			}
		} else {
			System.out.println("Tasks 1 and 2");
			while (true) {
				String path = console.readLine("Enter a class path: ");
				try {

					CustomClassLoader loader = new CustomClassLoader(path);
					Class<?> cl = loader
							.loadClass(ClassLoadingConstant.SEMAPHORE_CLASS_NAME);
					cl.getMethod("lever").invoke(cl.newInstance());
					System.out.println("Class was loaded successfully");
				} catch (Exception e) {
					System.err.println("Error is ocurred:");
					e.printStackTrace();
				}
			}
		}
	}
}
