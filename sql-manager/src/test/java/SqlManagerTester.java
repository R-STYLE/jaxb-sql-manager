import java.util.HashMap;
import java.util.Map;

import net.rstyles.dao.SampleDao;

import org.junit.Test;

import com.rstyles.util.sql.SqlManager;

public class SqlManagerTester {

	@Test
	public void test() throws Exception {
	
		final Map<String, Object> params = new HashMap<>();
		params.put("a", 1);
		params.put("b", 1);
		params.put("c", 1);
		params.put("d", 1);

		final SqlManager manager = SqlManager.getManager();
		
		System.out.println(manager.getSql(SampleDao.class, "test001", params));
		System.out.println(manager.getSql(SampleDao.class, "test002", params));
		System.out.println(manager.getSql(SampleDao.class, "test003", params));
	}

}
