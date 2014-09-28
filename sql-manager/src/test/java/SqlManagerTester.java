import java.util.HashMap;

import net.rstyles.dao.SampleDao;

import org.junit.Test;

import com.rstyles.util.sql.SqlManager;

public class SqlManagerTester {

	@Test
	public void test() throws Exception {
	
		System.out.println(SqlManager.getSql(SampleDao.class, "test001", new HashMap()));
		
	}

}
