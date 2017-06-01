package com.laughing2b.common.dubbo.hessian;

import java.io.IOException;
import java.util.UUID;

import com.alibaba.com.caucho.hessian.io.AbstractDeserializer;
import com.alibaba.com.caucho.hessian.io.AbstractHessianInput;

/**
* @ClassName: UUIDDeserializer 
* @Description:
* @author lifei.pan
* @email plfnet@163.com
* @date 2015年10月12日 上午10:09:00 
*
 */
public class UUIDDeserializer extends AbstractDeserializer {

    @SuppressWarnings("rawtypes")
	@Override
    public Class getType() {
        return UUID.class;
    }

    @Override
    public Object readMap(AbstractHessianInput in)
            throws IOException {
        int ref = in.addRef(null);

        String uuidValue = null;

        while (!in.isEnd()) {
            String key = in.readString();

            if (key.equals("value"))
                uuidValue = in.readString();
            else
                in.readString();
        }

        in.readMapEnd();

        Object value = UUID.fromString(uuidValue);

        in.setRef(ref, value);

        return value;
    }

    @Override
    public Object readObject(AbstractHessianInput in) throws IOException {
        //todo:just ignore
        return UUID.randomUUID();
    }

    @Override
    public Object readObject(AbstractHessianInput in, String[] fieldNames)
            throws IOException {
        int ref = in.addRef(null);

        String uuidValue = null;

        for (String key : fieldNames) {
            if (key.equals("value"))
                uuidValue = in.readString();
            else
                in.readObject();
        }
        UUID value =null;
        if(uuidValue!=null)
             value = UUID.fromString(uuidValue);

        in.setRef(ref, value);

        return value;
    }
}
