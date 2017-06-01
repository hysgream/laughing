package com.laughing2b.util.spring.conf;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.helpers.FileWatchdog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.config.TypedStringValue;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.ReflectionUtils;

import com.google.common.collect.Maps;

/**
* @ClassName: PropertyPlaceholderConf 
* @Description: 属性文件配置管理器
* @author lifei.pan
* @email plfnet@163.com
* @date 2015年8月7日 下午4:08:44 
*
 */
public class PropertyPlaceholderConf extends PropertyPlaceholderConfigurer implements ApplicationContextAware {
    private static Logger logger = LoggerFactory.getLogger(PropertyPlaceholderConf.class);
    private Properties props;
    private long refreshInterval = 30000;
    private Map<String, String> propertyValueMap = Maps.newHashMap();
    private Map<String, PropertyValue> propertyConfMap = Maps.newHashMap();
    private ConfigurableListableBeanFactory beanFactory;

    private ConfigTypeConvertEditor typeConverter = new ConfigTypeConvertEditor();

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
            throws BeansException {
        String[] beanNames = beanFactoryToProcess.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            BeanDefinition bd = beanFactoryToProcess.getBeanDefinition(beanName);
            MutablePropertyValues pvs = bd.getPropertyValues();
            for (PropertyValue pv : pvs.getPropertyValues()) {
                if (pv.getValue() instanceof TypedStringValue) {
                    TypedStringValue stringValue = (TypedStringValue) pv.getValue();
                    if (isConfProperty(stringValue)) {
                        String confKey = fetchConfKey(stringValue);
                        propertyValueMap.put(confKey, beanName);
                        propertyConfMap.put(confKey, pv);

                    }
                }
            }

        }

        logger.info("find {} property conf :{}", propertyValueMap.size(), propertyValueMap.keySet());
        super.processProperties(beanFactoryToProcess, props);
        this.props = props;
    }

    private boolean isConfProperty(TypedStringValue stringValue) {
        return StringUtil.startsWith(stringValue.getValue(), PropertyPlaceholderConfigurer.DEFAULT_PLACEHOLDER_PREFIX);
    }

    private String fetchConfKey(TypedStringValue stringValue) {
        return stringValue.getValue().replace(PropertyPlaceholderConfigurer.DEFAULT_PLACEHOLDER_PREFIX, "").
                replace(PropertyPlaceholderConfigurer.DEFAULT_PLACEHOLDER_SUFFIX, "");
    }

    public void refresh() throws Exception {
        for (String key : propertyValueMap.keySet()) {
            String beanName = propertyValueMap.get(key);
            PropertyValue pv = propertyConfMap.get(key);
            TypedStringValue strValue = (TypedStringValue) pv.getValue();
            String newValue = props.getProperty(key);
            if (!StringUtil.equals(strValue.getValue(), newValue)) {
                logger.info("conf key=[{}] has change from [{}] to [{}] ",new Object[]{ key,strValue.getValue(), newValue});
                Object bd = beanFactory.getBean(beanName);
                Field field = ReflectionUtils.findField(bd.getClass(), pv.getName());
                setFieldValue(bd, field, key);

            }
        }
    }

    public void setFieldValue(Object bd, Field field, String key) throws IllegalAccessException {
        Object property = getProperty(key);
        if (property != null) {
            Type type = field.getGenericType();
            Object _value = typeConverter.convertIfNecessary(property, type);
            ReflectionUtils.makeAccessible(field);
            field.set(bd, _value);
        }
    }

    public Object getProperty(String key) {
        return props.get(key);
    }

    @Override
    public void setLocations(Resource[] locations) {

        String env = SystemEnv.getCurrentEnv();

        logger.info("当前的应用环境设置为 {}", env);

        watchFiles(locations);
        super.setLocations(locations);
    }

    public void watchFiles(Resource[] locations) {
        for (Resource resource : locations) {
            try {
                File config = resource.getFile();
                if (config.exists()) {
                    PropertyWatchdog watchdog = new PropertyWatchdog(config.getAbsolutePath(), resource);
                    watchdog.setDelay(refreshInterval);
                    watchdog.start();
                }
            } catch (IOException e) {
                logger.warn("watch config file fail ", e);
            }
        }


    }

    public Properties getProps() {
        return props;
    }

    public void setRefreshInterval(long refreshInterval) {
        this.refreshInterval = refreshInterval;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.beanFactory = ((AbstractApplicationContext) applicationContext).getBeanFactory();
    }

    class PropertyWatchdog extends FileWatchdog {
        private Resource resource;

        PropertyWatchdog(String filename, Resource resource) {
            super(filename);
            this.resource = resource;
        }

        public void doOnChange() {
            if (props != null) {
                logger.info("refresh properties for spring xml config");
                try {
                    PropertiesLoaderUtils.fillProperties(props, resource);
                    refresh();
                } catch (Exception e) {
                    logger.error("reload properties error, form {} ", filename, e);
                }


            }
        }
    }
}
