package br.com.mystudies.service.persistence.bean;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import br.com.mystudies.service.persistence.bean.StoryDaoBean;

public class StoryDaoTest {

	// FIXME: make test using anything tecnologic about persistence with unit.


	private StoryDaoBean storyDaoBean;


	@Before
	public void setUp() throws Exception {
		storyDaoBean  = new StoryDaoBean();
	}

	@After
	public void tearDown() throws Exception {
		storyDaoBean = null;
	}

	@Ignore
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
