package com.epam.mentoring.classloading.loader;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.epam.mentoring.classloading.constant.ClassLoadingConstant;

public class CustomClassLoader extends ClassLoader {
	
	private String classPath;

	public CustomClassLoader(String path) {
		super(CustomClassLoader.class.getClassLoader());
		this.classPath = path;
	}

	private Class getClass(String name) throws ClassNotFoundException {
		String className = name.substring(name.lastIndexOf(".") + 1, name.length()) + ".class";
		byte[] b = null;
		try {
			b = loadClassFileData(className);
			return defineClass(name, b, 0, b.length);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Class loadClass(String name) throws ClassNotFoundException {
		if (name.startsWith(ClassLoadingConstant.SEMAPHORE_PACKAGE_NAME)) {
			System.out.println("Loading " + name);
			return getClass(name);
		}
		return super.loadClass(name);
	}

	private byte[] loadClassFileData(String name) throws IOException {
		File file = new File(classPath + name);
	    InputStream stream = new FileInputStream(file);
		int size = stream.available();
		byte buff[] = new byte[size];
		DataInputStream in = new DataInputStream(stream);
		in.readFully(buff);
		in.close();
		return buff;
	}
}
