package br.com.mystudies.service.persistence.bean;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import br.com.mystudies.service.persistence.bean.ThemeDaoBean;


//TODO: TECHNICAL DEBT TO UNIT TEST TO PERSISTENCE CLASS.
public class ThemeDaoBeanTest {


	private ThemeDaoBean themeDaoBean;


	@Before
	public void setUp() throws Exception {
		themeDaoBean = new ThemeDaoBean();
	}

	@After
	public void tearDown() throws Exception {
		themeDaoBean = null;
	}

	@Test
	@Ignore
	public void test() {
		//fail("Not yet implemented");
	}

}
