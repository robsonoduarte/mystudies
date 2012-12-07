package br.com.mystudies.service.persistence;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import br.com.mystudies.domain.enun.SprintStatus;

public class SprintDaoBeanTest {

	
	private SprintDaoBean sprintDaoBean;
	
	
	@Before
	public void setUp() throws Exception {
		sprintDaoBean = new SprintDaoBean();
	}

	@After
	public void tearDown() throws Exception {
		sprintDaoBean = null;
	}

	@Test
	@Ignore
	public void test() {
		fail("Not yet implemented");
		sprintDaoBean.findSprintByStatus(SprintStatus.RUNNING);
	}

}
