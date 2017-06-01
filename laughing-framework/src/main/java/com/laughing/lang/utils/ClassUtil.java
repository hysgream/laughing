package com.laughing.lang.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.Set;

import com.laughing.lang.exceptions.CanNotFindClassException;


public class ClassUtil {
	
	public static final char RESOURCE_SEPARATOR_CHAR = '/';

	public static final char PACKAGE_SEPARATOR_CHAR = '.';

	public static final String PACKAGE_SEPARATOR = String.valueOf(PACKAGE_SEPARATOR_CHAR);

	public static final char INNER_CLASS_SEPARATOR_CHAR = '$';

	public static final String INNER_CLASS_SEPARATOR = String.valueOf(INNER_CLASS_SEPARATOR_CHAR);
	
	private static final Map<Class<?>, Class<?>> primary2wrapper = MapUtil.newHashMap();

	private static final Map<Class<?>, Class<?>> wrapper2primary = MapUtil.newHashMap();

	private static final Set<Class<?>> simpleTypes = CollectionUtil.newHashSet();

    private static final Set<Class<?>> primaryNumericTypes = CollectionUtil.newHashSet();

	static {
		primary2wrapper.put(Byte.TYPE, Byte.class);
		primary2wrapper.put(Boolean.TYPE, Boolean.class);
		primary2wrapper.put(Short.TYPE, Short.class);
		primary2wrapper.put(Character.TYPE, Character.class);
		primary2wrapper.put(Integer.TYPE, Integer.class);
		primary2wrapper.put(Long.TYPE, Long.class);
		primary2wrapper.put(Float.TYPE, Float.class);
		primary2wrapper.put(Double.TYPE, Double.class);
		primary2wrapper.put(Void.TYPE, Void.class);

        wrapper2primary.put(Byte.class, Byte.TYPE);
        wrapper2primary.put(Boolean.class, Boolean.TYPE);
        wrapper2primary.put(Short.class, Short.TYPE);
        wrapper2primary.put(Character.class, Character.TYPE);
        wrapper2primary.put(Integer.class, Integer.TYPE);
        wrapper2primary.put(Long.class, Long.TYPE);
        wrapper2primary.put(Float.class, Float.TYPE);
        wrapper2primary.put(Double.class, Double.TYPE);
        wrapper2primary.put(Void.class, Void.TYPE);
		
		simpleTypes.add(Void.TYPE);
		simpleTypes.add(Void.class);
		simpleTypes.add(Byte.TYPE);
		simpleTypes.add(Byte.class);
		simpleTypes.add(Boolean.TYPE);
		simpleTypes.add(Boolean.class);
		simpleTypes.add(Short.TYPE);
		simpleTypes.add(Short.class);
		simpleTypes.add(Character.TYPE);
		simpleTypes.add(Character.class);
		simpleTypes.add(Integer.TYPE);
		simpleTypes.add(Integer.class);
		simpleTypes.add(Long.TYPE);
		simpleTypes.add(Long.class);
		simpleTypes.add(Float.TYPE);
		simpleTypes.add(Float.class);
		simpleTypes.add(Double.TYPE);
		simpleTypes.add(Double.class);

        primaryNumericTypes.add(Byte.TYPE);
        primaryNumericTypes.add(Short.TYPE);
        primaryNumericTypes.add(Integer.TYPE);
        primaryNumericTypes.add(Long.TYPE);
        primaryNumericTypes.add(Float.TYPE);
        primaryNumericTypes.add(Double.TYPE);

	}
	
	public static boolean isSimpleType(Class<?> clazz) {
		return simpleTypes.contains(clazz);
	}
	
	public static InputStream getResourceAsStream(String resourceName) {
		URL url = getResource(resourceName);
		if (url == null) {
			return null;
		}
		try {
			return url.openStream();
		} catch (IOException e) {
			throw new  RuntimeException(e);
		}
	}
	
	public static URL getResource(String resourceName) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		URL url = null;
        if (classLoader != null) {
            url = classLoader.getResource(resourceName);
            if (url != null) {
                return url;
            }
        }
        // 如果没找到，试着从装入自己的ClassLoader中查找。
        classLoader = ClassUtil.class.getClassLoader();
        if (classLoader != null) {
            url = classLoader.getResource(resourceName);
            if (url != null) {
                return url;
            }
        }

        // 最后的尝试: 在系统ClassLoader中查找(JDK1.2以上)，
        // 或者在JDK的内部ClassLoader中查找(JDK1.2以下)。
        return ClassLoader.getSystemResource(resourceName);
	}
	
	public static Class<?> getWrapperClass(Class<?> primaryClass) {
		Class<?> ret = primary2wrapper.get(primaryClass);
		if (ret == null) {
			return primaryClass;
		}
		return ret;
	}

    public static Class<?> getPrimary(Class<?> wrapperClass) {
        Class<?> ret = wrapper2primary.get(wrapperClass);
        if (ret == null) {
            return wrapperClass;
        }
        return ret;
    }

	public static InputStream getResourceAsStream(Class<?> clazz, String name) {
		InputStream is = clazz.getResourceAsStream(name);
		if (is != null) {
			return is;
		}
		is = clazz.getClassLoader().getResourceAsStream(name);
		if (is != null) {
			return is;
		}
		return null;
	}
	
	/**
	 * @param clazz
	 * @return
	 */
	public static Set<Class<?>> getAllInterfaces(Class<?> clazz) {
		Set<Class<?>> ret = CollectionUtil.newHashSet();
		getAllInterfacesImpl(clazz, ret);
		return ret;
	}
	
	public static void getAllInterfacesImpl(Class<?> clazz, Set<Class<?>> ret) {
		if (!clazz.isInterface()) {
			Class<?> superClass = clazz.getSuperclass();
			if (superClass == null) {
				return;
			}
			getAllInterfacesImpl(superClass, ret);
		}
		Class<?>[] interfaces = clazz.getInterfaces();
		for (Class<?> interf : interfaces) {
			ret.add(interf);
			getAllInterfacesImpl(interf, ret);
		}
	}
	
	public static ClassLoader getClassLoader() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if (classLoader != null) {
			return classLoader;
		}
		return ClassUtil.class.getClassLoader();
	}

	public static boolean classExist(String name) {
		try {
			forName(name);
			return true;
		} catch (CanNotFindClassException e) {
			return false;
		}
	}
	
	public static Class<?> forName(String name) {
		return forName(name, getClassLoader());
	}
	
	public static Class<?> forName(String name, ClassLoader classLoader) {
		try {
			return Class.forName(name, true, classLoader);
		} catch (ClassNotFoundException e) {
			throw new CanNotFindClassException(e);
		}
	}
	
	public static String getShortClassName(String className) {
		if (StringUtil.isEmpty(className)) {
			return className;
		}

		className = getClassName(className, false);

		char[] chars = className.toCharArray();
		int lastDot = 0;

		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == PACKAGE_SEPARATOR_CHAR) {
				lastDot = i + 1;
			} else if (chars[i] == INNER_CLASS_SEPARATOR_CHAR) {
				chars[i] = PACKAGE_SEPARATOR_CHAR;
			}
		}

		return new String(chars, lastDot, chars.length - lastDot);
	}

	public static String getShortClassName(Class<?> clazz) {
		if (clazz == null) {
			return null;
		}
		return getShortClassName(clazz.getName());
	}
	
	private static String getClassName(String className, boolean processInnerClass) {
		if (StringUtil.isEmpty(className)) {
			return className;
		}

		if (processInnerClass) {
			className = className.replace(INNER_CLASS_SEPARATOR_CHAR, PACKAGE_SEPARATOR_CHAR);
		}

		int length = className.length();
		int dimension = 0;

		for (int i = 0; i < length; i++, dimension++) {
			if (className.charAt(i) != '[') {
				break;
			}
		}
		if (dimension == 0) {
			return className;
		}

		if (length <= dimension) {
			return className;
		}
		StringBuilder componentTypeName = new StringBuilder();

		switch (className.charAt(dimension)) {
		case 'Z':
			componentTypeName.append("boolean");
			break;
		case 'B':
			componentTypeName.append("byte");
			break;
		case 'C':
			componentTypeName.append("char");
			break;
		case 'D':
			componentTypeName.append("double");
			break;
		case 'F':
			componentTypeName.append("float");
			break;
		case 'I':
			componentTypeName.append("int");
			break;
		case 'J':
			componentTypeName.append("long");
			break;
		case 'S':
			componentTypeName.append("short");
			break;
		case 'L':
			if ((className.charAt(length - 1) != ';') || (length <= (dimension + 2))) {
				return className;
			}
			componentTypeName.append(className.substring(dimension + 1, length - 1));
			break;

		default:
			return className;
		}
		for (int i = 0; i < dimension; i++) {
			componentTypeName.append("[]");
		}
		return componentTypeName.toString();
	}

	public static String getClassName(String className) {
		return getClassName(className, true);
	}

    public static boolean isPrimaryNumericClass(Class<?> clazz) {
        return primaryNumericTypes.contains(clazz);
    }
	
}
