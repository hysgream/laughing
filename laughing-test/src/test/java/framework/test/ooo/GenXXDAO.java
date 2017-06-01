package framework.test.ooo;

import junit.framework.TestCase;

import com.laughing.help.tools.ibatis.AutoGenDAO;
import com.laughing2b.html.dal.LaughingUser;


public class GenXXDAO extends TestCase {

	public void testGen() {
		AutoGenDAO autoGen = new AutoGenDAO(DbConstants.DB_PREFIX);
		autoGen.gen(LaughingUser.class);
	}
}
