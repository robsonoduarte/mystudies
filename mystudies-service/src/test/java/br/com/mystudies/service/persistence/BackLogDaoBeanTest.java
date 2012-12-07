package br.com.mystudies.service.persistence;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import br.com.mystudies.service.persistence.BackLogDAOBean;

/**
 * @author Robson
 *
 */
// TODO: TECHNICAL DEBT TO UNIT TEST TO PERSISTENCE CLASS.
public class BackLogDaoBeanTest {


	private BackLogDAOBean backLogDaoBen;


	@Before
	public void setUp() throws Exception {
		backLogDaoBen = new BackLogDAOBean();
	}

	@After
	public void tearDown() throws Exception {
		backLogDaoBen = null;
	}

	@Test
	@Ignore
	public void test() {
		//fail("Not yet implemented");
	}

}
