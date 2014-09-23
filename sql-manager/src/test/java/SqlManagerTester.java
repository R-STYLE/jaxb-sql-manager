import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXB;

import org.junit.Test;

import com.rstyles.util.sql.SqlContainer;

public class SqlManagerTester {

	@Test
	public void test() throws IOException {

		InputStream is = this.getClass().getResourceAsStream("TestRepository.xml");
		
		SqlContainer container = JAXB.unmarshal(is, SqlContainer.class);

		System.out.println(container);
	}

}
