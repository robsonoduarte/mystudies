package br.com.r3wa.fiscalpackge.persistence.query;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import br.com.r3wa.fiscalpackage.persistence.exception.FiscalPacakagePersistenceRuntimeException;


public class JPAQueryRepositoryTest {


	private QueryRepository queryRepository;




	@Before
	public void setUp() throws Exception {
		queryRepository = new JPAQueryRepository();
	}


	@Test
	public void shouldReturnTheStringQueryByNameInJPAQueriesFilesPropertiess() {
		assertThat(queryRepository.getQuery("test-1-query1"), equalTo("query 1"));
	}



	@Test(expected=FiscalPacakagePersistenceRuntimeException.class)
	public void whenTheRepoDontHaveQueryThenThrownew() {
		queryRepository.getQuery(null);
	}


}
