package com.laughing.help.tools.gen.dao;

import java.util.Map;
import java.util.Set;

import com.laughing.lang.utils.CollectionUtil;
import com.laughing.lang.utils.MapUtil;
import com.laughing.lang.utils.StringUtil;

public class SqlTypes {

    static Map<Class<?>, String> types = MapUtil.newHashMap();
    static Set<Class<?>> defaultNullableTypes = CollectionUtil.newHashSet();

    static {
        types.put(Boolean.TYPE, "tinyint");
        types.put(Boolean.class, "tinyint");

        types.put(Byte.TYPE, "tinyint");
        types.put(Byte.class, "tinyint");

        types.put(Short.TYPE, "shortint");
        types.put(Short.class, "shortint");

        types.put(Character.TYPE, "char");
        types.put(Character.class, "char");

        types.put(Integer.TYPE, "int");
        types.put(Integer.class, "int");

        types.put(Long.TYPE, "bigint");
        types.put(Long.class, "bigint");

        types.put(Float.TYPE, "decimal(10, ?)");
        types.put(Float.class, "decimal(10, ?)");

        types.put(Double.TYPE, "decimal(20, ?)");
        types.put(Double.class, "decimal(20, ?)");

        types.put(String.class, "varchar(?)");

        types.put(java.sql.Date.class, "datetime");
        types.put(java.sql.Timestamp.class, "datetime");
        types.put(java.util.Date.class, "datetime");

        defaultNullableTypes.add(Boolean.class);
        defaultNullableTypes.add(Byte.class);
        defaultNullableTypes.add(Short.class);
        defaultNullableTypes.add(Character.class);
        defaultNullableTypes.add(Integer.class);
        defaultNullableTypes.add(Long.class);
        defaultNullableTypes.add(Float.class);
        defaultNullableTypes.add(Double.class);

    }

    public static boolean isDefaultNullable(Class<?> clazz) {
        return defaultNullableTypes.contains(clazz);
    }

    public static String getByClass(Class<?> clazz) {
        String ret = types.get(clazz);
        if (StringUtil.isEmpty(ret)) {
            return "?description?";
        }
        return ret;
    }

}
