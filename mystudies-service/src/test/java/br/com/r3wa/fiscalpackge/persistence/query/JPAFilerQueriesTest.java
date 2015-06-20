package br.com.r3wa.fiscalpackge.persistence.query;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class JPAFilerQueriesTest {


	private FilerQueries filerQueries;



	@Before
	public void setUp() throws Exception {
		filerQueries = new JPAFilerQueries();
	}


	@Test
	public void shouldReturnOneQueryByName() throws IOException, Exception {
		assertThat(filerQueries.query("test-3-query3"), equalTo("query 1 varias linhas, varias linhas, varias linhas, varias linhas varias linhas"));
		assertThat(filerQueries.query("test-2-query3"), equalTo("query 3"));
	}


}
