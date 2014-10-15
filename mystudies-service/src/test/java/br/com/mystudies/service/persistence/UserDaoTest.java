package br.com.mystudies.service.persistence;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

public class UserDaoTest {

	
	
	/*	@Mock
	private EntityManager entityManager;
	
	
	@Mock
	private Query query;*/
	
	
	@InjectMocks
	private UserDao userDao;

	

	@Before
	public void setUp() throws Exception {
		userDao = new UserDaoBean();
		initMocks(this);
	}

	

	// TODO: create integration test with arquilian
	// http://arquillian.org/guides/testing_java_persistence/
	@Test
	public void test() {
		
	// prepareMocks();
		
	//User user = userDao.findUserByLogin(new User());
		
	//	assertThat(user, notNullValue());
		
	// verifyMocks();
		
	}



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// >>>>>>>>>>>>>>>>> AUXILIARY METHODS <<<<<<<<<<<<<<<<<<<<<<<<<<<<
	

	
}
