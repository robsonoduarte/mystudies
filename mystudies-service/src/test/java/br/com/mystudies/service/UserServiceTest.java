package br.com.mystudies.service;


import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import br.com.mystudies.domain.entity.User;
import br.com.mystudies.service.data.request.LoginDataRequest;
import br.com.mystudies.service.persistence.UserDao;

public class UserServiceTest {

	@Mock
	private UserDao UserDao;
	
	
	@InjectMocks
	private UserService userService;



	@Before
	public void setUp() throws Exception {
		userService = new UserServiceBean(); 
		initMocks(this);
	}

	

	// TODO: change of name of test.
	@Test
	public void test1() {
		
		when(UserDao.findUserByLogin(any(User.class))).thenReturn(new User());
		
		User user =  
			userService.login(
				LoginDataRequest.builder()
					.email("robson.o.d@gmail.com")
					.password("abc@123")
					.create()
			);
		
		
		assertThat(user, notNullValue());
		
		verify(UserDao).findUserByLogin(any(User.class));
	}
	
	
}
