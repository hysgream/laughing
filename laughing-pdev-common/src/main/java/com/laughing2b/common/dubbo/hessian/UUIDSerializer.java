package com.laughing2b.common.dubbo.hessian;

import java.io.IOException;
import java.util.UUID;

import com.alibaba.com.caucho.hessian.io.AbstractHessianOutput;
import com.alibaba.com.caucho.hessian.io.AbstractSerializer;

/**
* @ClassName: UUIDSerializer 
* @Description: 
* @author lifei.pan
* @email plfnet@163.com
* @date 2015年10月12日 上午10:08:39 
*
 */
public class UUIDSerializer  extends AbstractSerializer {

    @SuppressWarnings("rawtypes")
	@Override
    public void writeObject(Object obj, AbstractHessianOutput out) throws IOException {

        if (obj == null) {
            out.writeNull();
        } else {
            Class cl = obj.getClass();

            if (out.addRef(obj))
                return;

            int ref = out.writeObjectBegin(cl.getName());

            UUID bi = (UUID) obj;

            if (ref < -1) {
                out.writeString("value");
                out.writeString(bi.toString());
                out.writeMapEnd();
            } else {
                if (ref == -1) {
                    out.writeInt(1);
                    out.writeString("value");
                    out.writeObjectBegin(cl.getName());
                }

                out.writeString(bi.toString());
            }
        }

    }

}
