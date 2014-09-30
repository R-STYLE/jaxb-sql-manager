import java.util.HashMap;
import java.util.Map;

import net.rstyles.dao.SampleDao;

import org.junit.Test;

import com.rstyles.util.sql.SqlManager;

public class SqlManagerTester {

	@Test
	public void test() throws Exception {
	
		final Map<String, Object> params = new HashMap<>();
		
		params.put("d", 1);

		final SqlManager manager = SqlManager.getManager();
		
		System.out.println(manager.getSql(SampleDao.class, "test001", params));
	}

}
