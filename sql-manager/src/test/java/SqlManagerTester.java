import java.util.HashMap;

import net.rstyles.dao.SampleDao;

import org.junit.Test;

import com.rstyles.util.sql.Registry;
import com.rstyles.util.sql.SqlManager;

public class SqlManagerTester {

	@Test
	public void test() throws Exception {
//
//		InputStream is = this.getClass().getResourceAsStream("TestRepository.xml");
//		
//		SqlContainer container = JAXB.unmarshal(is, SqlContainer.class);
//
//		System.out.println(container);
		
		System.out.println(SqlManager.getSql(SampleDao.class, "test001", new HashMap()));
		
		SampleDao dao = (SampleDao) Registry.load(SampleDao.class);
		System.out.println(dao);
		dao = (SampleDao) Registry.load(SampleDao.class);
		System.out.println(dao);
		
	}

}
