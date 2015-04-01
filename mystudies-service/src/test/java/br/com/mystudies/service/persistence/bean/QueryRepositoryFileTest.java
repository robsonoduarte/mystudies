package br.com.mystudies.service.persistence.bean;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import br.com.mystudies.service.persistence.QueryRepository;

public class QueryRepositoryFileTest {


	private QueryRepository queryRepository;


	@Before
	public void setUp() throws Exception {
		queryRepository = new QueryRepositoryFile();
	}


	@Test
	public void shouldReturnTheStringQueryNameWithIsInJPAQueriesFileProperties() {
		String query = queryRepository.getQuery("delete-customer-by-code");
		assertThat(query, equalTo("delete from Customer as c where c.customer_code =:customer_code"));
	}


}
