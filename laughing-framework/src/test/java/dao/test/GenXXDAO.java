package dao.test;

import junit.framework.TestCase;
import test.ShopCustomerDO;

import com.laughing.help.tools.ibatis.AutoGenDAO;

/**
 * @author qiu.shui
 */
public class GenXXDAO extends TestCase {

	public void testGen() {
		AutoGenDAO autoGen = new AutoGenDAO("test");
		autoGen.gen(ShopCustomerDO.class);
	}
}
