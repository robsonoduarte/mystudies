package br.com.mystudies.service.persistence.bean;

import org.junit.Before;
import org.junit.Test;

import br.com.mystudies.domain.entity.Sprint;
import br.com.mystudies.service.persistence.Repository;

public class RepositoryBeanTest {

	private Repository repository;

	@Before
	public void setUp() throws Exception {
		repository = new RepositoryBean();
	}

	@Test
	public void test() {
		// TODO: continuar o teste...
	  Sprint sprint = repository.save(new Sprint());
	}

}
