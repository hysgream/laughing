package com.laughing.lang.magic;

import com.laughing.lang.exceptions.PropertyAccessException;
import com.laughing.lang.utils.ArrayUtil;

/**
* @ClassName: Property 
* @Description:定义类的属性
* @author lifei.pan
* @email plfnet@163.com
* @date 2017年5月19日 下午6:18:03 
 */
public class Property {

	private String name;

	private MagicClass propertyClass;

	private MagicMethod readMethod;

	private MagicMethod writeMethod;

	public Property() {
		super();
	}

	public Property(String name, MagicClass propertyClass, MagicMethod readMethod, MagicMethod writeMethod) {
		super();
		this.name = name;
		this.propertyClass = propertyClass;
		this.readMethod = readMethod;
		this.writeMethod = writeMethod;
	}

	/**
	 * 是否可读
	 * 
	 * @return
	 */
	public boolean isReadable() {
		return readMethod != null;
	}

	/**
	 * 是否可写
	 * 
	 * @return
	 */
	public boolean isWritable() {
		return writeMethod != null;
	}

	/**
	 * 属性名
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 获取属性对应的class
	 * 
	 * @return
	 */
	public MagicClass getPropertyClass() {
		return propertyClass;
	}

	/**
	 * 获取目标对象的属性值
	 * 
	 * @param owner
	 * @return
	 */
	public Object getValue(Object owner) {
		if (!isReadable()) {
			throw new PropertyAccessException("can not read the property: " + name);
		}
		return readMethod.invoke(owner, ArrayUtil.EMPTY_OBJECT_ARRAY);
	}

	/**
	 * 设置目标对象的属性值
	 * 
	 * @param owner
	 * @param newValue
	 */
	public void setValue(Object owner, Object newValue) {
		if (!isWritable()) {
			throw new PropertyAccessException("can not write the property: " + name);
		}
		writeMethod.invoke(owner, new Object[] { newValue });
	}

	/**
	 * 设置目标对象的属性值，并做自动类型转换
	 * 
	 * @param owner
	 * @param newValue
	 */
	public void setValueExt(Object owner, Object newValue) {
		if (!isWritable()) {
			throw new PropertyAccessException("can not write the property: " + name);
		}
	}

	@Override
	public String toString() {
		return "Property [name=" + name + ", propertyClass=" + propertyClass + "]";
	}

}
