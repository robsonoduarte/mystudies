package br.com.mystudies.service.persistence.bean;

import static java.util.Arrays.asList;
import static java.util.Collections.EMPTY_LIST;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import br.com.mystudies.service.persistence.Repository;

public class RepositoryBeanTest {

	@InjectMocks
	private Repository repository;


	@Mock
	private EntityManager entityManager;


	@Mock
	private Query query;


	@Mock
	private QueryRepositoryFile repositoryEntityQuery;


	@Mock
	private EntityForTest entityForTest;


	@Before
	public void setUp() throws Exception {
		repository = new RepositoryBean();
		initMocks(this);
	}



	@Test
	public void shouldSaveAnyThingThatIsOneEntityUsingTheMethodMergeOFEntittyManager() {
		when(entityManager.merge(entityForTest)).thenReturn(entityForTest);
		entityForTest = repository.save(entityForTest);
		assertThat(entityForTest, notNullValue());
		verify(entityManager).merge(entityForTest);
	}



	@Test
	public void shouldFindAnyThingThatIsOneEntityUsingTheMethodFindOFEntittyManager() {
		when(entityManager.find(EntityForTest.class, "0001")).thenReturn(entityForTest);
		EntityForTest entityForTest = repository.find(EntityForTest.class, "0001");
		assertThat(entityForTest, notNullValue());
		verify(entityManager).find(EntityForTest.class, "0001");
	}



	@Test
	public void shouldRemoveAnyThingThatIsOneEntityUsingTheMetodExecuteUpdateOFQuery() {

		when(entityManager.createQuery(anyString())).thenReturn(query);
		when(query.executeUpdate()).thenReturn(1);

		Integer result = repository.remove("delete-customer-by-code", "001");
		assertThat(result, equalTo(1));

		verify(entityManager).createQuery(anyString());
		verify(query).setParameter(1, "001");
		verify(query).executeUpdate();
	}


	@Test
	public void shouldExecuteAnyQueryThatIsOneEntityUsingTheMetodGetResultListOFQuery() {

		when(entityManager.createQuery(anyString())).thenReturn(query);
		when(query.getResultList()).thenReturn(asList(new EntityForTest()));

		List<EntityForTest> entitys =
				repository.select("select-all-by-code", 1, 2 , 3 );

		assertThat(entitys, not(equalTo(EMPTY_LIST)));

		verify(entityManager).createQuery(anyString());
		verify(query,times(3)).setParameter(anyInt(), any());
		verify(query).getResultList();

	}

	@Test
	public void shouldExecuteAnySelectINQueryThatIsOneEntityUsingTheMetodGetResultListOFQuery() {

		when(entityManager.createQuery(anyString())).thenReturn(query);
		when(query.getResultList()).thenReturn(asList(new EntityForTest()));

		List<EntityForTest> entitys =
				repository.selectIN("select-all-by-code", asList(1, 2, 3));

		assertThat(entitys, not(equalTo(EMPTY_LIST)));

		verify(entityManager).createQuery(anyString());
		verify(query,times(1)).setParameter(anyInt(), any());
		verify(query).getResultList();

	}


}
