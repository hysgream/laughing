package com.laughing.help.tools.gen.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import com.laughing.lang.utils.MapUtil;
import com.laughing.lang.utils.StringUtil;

public class DomainField {

    private static Map<Class<?>, String> class2type = MapUtil.newHashMap();

    static {
        class2type.put(Integer.class, "int");
        class2type.put(Integer.TYPE, "int");
        class2type.put(Short.class, "int");
        class2type.put(Short.TYPE, "int");
        class2type.put(Long.class, "long");
        class2type.put(Long.TYPE, "long");
        class2type.put(Float.class, "number");
        class2type.put(Float.TYPE, "number");
        class2type.put(Double.class, "number");
        class2type.put(Double.TYPE, "number");
        class2type.put(String.class, "string");
        class2type.put(Date.class, "date");
        class2type.put(Timestamp.class, "date");
    }

    private String name;

    private String type;

    private boolean booleanType = false;

    public DomainField(String name, Class<?> javaType) {
        this.name = name;
        String type = class2type.get(javaType);
        if (type == null) {
            type = "string";
        }
        this.type = type;
        if (javaType == Boolean.TYPE || javaType == Boolean.class) {
            booleanType = true;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFieldMessage() {
        return "#errorMessage($!form." + name + ".message)";
    }

    public String getGetter() {
        if (booleanType) {
            return "is" + StringUtil.uppercaseFirstLetter(name);
        } else {
            return "get" + StringUtil.uppercaseFirstLetter(name);
        }
    }

    public String getSetter() {
        return "set" + StringUtil.uppercaseFirstLetter(name);
    }


    public String getFieldValue() {
        return "$!form." + name + ".value";
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
