package com.laughing2b.common.dubbo;

import java.util.UUID;

import com.alibaba.com.caucho.hessian.io.Deserializer;
import com.alibaba.com.caucho.hessian.io.ExtSerializerFactory;
import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.common.serialize.support.hessian.Hessian2SerializerFactory;
import com.laughing2b.common.dubbo.hessian.UUIDDeserializer;
import com.laughing2b.common.dubbo.hessian.UUIDSerializer;

/**
* @ClassName: DubboExtInit 
* @Description: UUID没有提供默认的构造函数，hessian序列化的时候会报异常
* @author lifei.pan
* @email plfnet@163.com
* @date 2015年10月12日 上午10:09:22 
*
 */
public class DubboExtInit {
    private static final Logger logger = LoggerFactory.getLogger(DubboExtInit.class);

    static {

        ExtSerializerFactory factory = new ExtSerializerFactory();
        factory.addDeserializer(UUID.class, (Deserializer) new UUIDDeserializer());
        factory.addSerializer(UUID.class, new UUIDSerializer());
        Hessian2SerializerFactory.SERIALIZER_FACTORY.setAllowNonSerializable(true);
        logger.info("add uuid serialize ext");
        Hessian2SerializerFactory.SERIALIZER_FACTORY.addFactory(factory);
    }
}
