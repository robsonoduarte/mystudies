package br.com.r3wa.fiscalpackge.persistence.bean;

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

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import br.com.r3wa.fiscalpackage.persistence.Repository;
import br.com.r3wa.fiscalpackage.persistence.bean.RepositoryBean;
import br.com.r3wa.fiscalpackage.persistence.exception.FiscalPacakagePersistenceRuntimeException;
import br.com.r3wa.fiscalpackge.persistence.query.JPAQueryRepository;

public class RepositoryBeanTest {

	@InjectMocks
	private Repository repository;


	@Mock
	private EntityManager entityManager;


	@Mock
	private Query query;


	@Mock
	private JPAQueryRepository repositoryEntityQuery;



	@Mock
	private EntityForTest entityForTest;



	@Before
	public void setUp() throws Exception {
		repository = new RepositoryBean();
		initMocks(this);
	}



















	//  ----------->>> TESTS FOR SAVE <<<--------------

	@Test
	public void shouldSaveAnyThingThatIsOneEntityUsingTheMethodMergeOFEntittyManager() {
		when(entityManager.merge(entityForTest)).thenReturn(entityForTest);
		assertThat(repository.save(entityForTest), notNullValue());
		verify(entityManager).merge(entityForTest);
	}


















	//  ----------->>> TESTS FOR FIND <<<--------------

	@Test
	public void shouldFindAnyThingThatIsOneEntityUsingTheMethodFindOFEntittyManagerWithCode() {
		when(entityManager.find(EntityForTest.class, "0001")).thenReturn(entityForTest);
		assertThat(repository.find(EntityForTest.class, "0001"), notNullValue());
		verify(entityManager).find(EntityForTest.class, "0001");
	}














	//  ----------->>> TESTS FOR REMOVE <<<--------------

	@Test(expected=FiscalPacakagePersistenceRuntimeException.class)
	public void whenTheMethodMergeOFEntityManagerReturnNullThenThrowFiscalPacakageRuntimeException(){
		when(entityManager.merge(any())).thenReturn(null);
		repository.remove(entityForTest);
	}





	@Test
	public void shouldDeleteAnyEntityUsingTheMethodDeleteEntitysManager(){
		when(entityManager.merge(any())).thenReturn(entityForTest);
		repository.remove(entityForTest);
		verify(entityManager).remove(entityForTest);

	}







	








	//  ----------->>> TESTS FOR REMOVE AND SAVE <<<--------------

	@Test
	public void shouldDeleteAndSaveAnyEntityUsingTheSequnceOfMethodEntitysManager(){

		when(entityManager.merge(entityForTest)).thenReturn(entityForTest);


		assertThat(repository.removeAndSave(entityForTest), notNullValue());


		verify(entityManager).merge(entityForTest);
		verify(entityManager).remove(entityForTest);
		verify(entityManager).flush();
		verify(entityManager).persist(entityForTest);

	}







	//  ----------->>> TESTS FOR SELECT <<<--------------

	@Test
	public void shouldExecuteAnyQueryThatIsOneEntityUsingTheMetodGetResultListOFQuery() {

		when(entityManager.createQuery(anyString())).thenReturn(query);
		when(query.setParameter(anyInt(), anyInt())).thenReturn(query);
		when(query.getResultList()).thenReturn(asList(new EntityForTest()));

		assertThat(repository.select("select-all-by-code", 1, 2 , 3 ), not(equalTo(EMPTY_LIST)));


		verify(entityManager).createQuery(anyString());
		verify(query, times(3)).setParameter(anyInt(), anyInt());
		verify(query).getResultList();
	}








	//  ----------->>> TESTS FOR SELECT <<<--------------

	@Test
	public void shouldExecuteAnyQueryThatIsOneObjectUsingTheMetodGetResultListOFQuery() {

		when(entityManager.createQuery(anyString())).thenReturn(query);
		when(query.setParameter(anyInt(), anyInt())).thenReturn(query);
		when(query.getResultList()).thenReturn(asList(new ResultDataForTest()));


		assertThat(repository.selectAtributes("select", 1, 2 , 3 ), not(equalTo(EMPTY_LIST)));


		verify(entityManager).createQuery(anyString());
		verify(query, times(3)).setParameter(anyInt(), anyInt());
		verify(query).getResultList();

	}











	//  ----------->>> TESTS FOR SELECT IN <<<--------------

	@Test
	public void shouldExecuteAnySelectINQueryThatIsOneEntityUsingTheMetodGetResultListOFQuery() {

		when(entityManager.createQuery(anyString())).thenReturn(query);
		when(query.setParameter(1, asList(1,2,3))).thenReturn(query);
		when(query.getResultList()).thenReturn(asList(new EntityForTest()));

		assertThat(repository.selectIN("select-all-by-code", asList(1, 2, 3)), not(equalTo(EMPTY_LIST)));


		verify(entityManager).createQuery(anyString());
		verify(query).setParameter(1, asList(1,2,3));
		verify(query).getResultList();
	}







	@Test
	public void shouldExecuteAnySelectINWithMaxResultQueryThatIsOneEntityUsingTheMetodGetResultListOFQuery() {

		when(entityManager.createQuery(anyString())).thenReturn(query);
		when(query.setParameter(1, asList(1,2,3))).thenReturn(query);
		when(query.setMaxResults(10)).thenReturn(query);
		when(query.getResultList()).thenReturn(asList(new EntityForTest()));


		assertThat(repository.selectIN("select-all-by-code", 10, asList(1, 2, 3)), not(equalTo(EMPTY_LIST)));


		verify(entityManager).createQuery(anyString());
		verify(query).setParameter(1, asList(1,2,3));
		verify(query).setMaxResults(10);
		verify(query).getResultList();
	}













	//  ----------->>> TESTS FOR SELECT ONE <<<--------------
	@Test
	public void shouldExecuteAnySelectOneQueryThatIsOneEntityUsingTheMetodGetSingleResult() {

		when(entityManager.createQuery(anyString())).thenReturn(query);
		when(query.getSingleResult()).thenReturn(new EntityForTest());

		assertThat(repository.selectOne("select-one-by-codes", "cod1", "cod2"), notNullValue());

		verify(entityManager).createQuery(anyString());
		verify(query,times(2)).setParameter(anyInt(), any());
		verify(query).getSingleResult();

	}























	//  ----------->>> TESTS FOR SELECT BETWEEN <<<--------------

	@Test
	public void shouldExecuteAnySelectBetWeenQueryThatIsOneEntityUsingTheMetodGetResultListOFQuery() {

		when(entityManager.createQuery(anyString())).thenReturn(query);
		when(query.setParameter(anyString(), any())).thenReturn(query);
		when(query.getResultList()).thenReturn(asList(new EntityForTest()));

		assertThat(repository.selectBetween("select-one-by-codes", "start", "end" ), not(equalTo(EMPTY_LIST)));

		verify(entityManager).createQuery(anyString());
		verify(query,times(2)).setParameter(anyString(), any());
		verify(query).getResultList();

	}

}
