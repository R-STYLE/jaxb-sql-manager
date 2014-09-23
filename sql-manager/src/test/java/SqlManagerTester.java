import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXB;

import org.junit.Test;

import com.rstyles.util.sql.Sql;
import com.rstyles.util.sql.ISql;
import com.rstyles.util.sql.SqlContainer;

public class SqlManagerTester {

	@Test
	public void test() throws IOException {

		InputStream is = this.getClass().getResourceAsStream("TestRepository.xml");
		
		SqlContainer container = JAXB.unmarshal(is, SqlContainer.class);
		
//		for (Sql sql : container.getSqls()) {
//			System.out.println(sql);
//			System.out.println(sql.getClass());
//		}

		System.out.println(container);
	}

}
