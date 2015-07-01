package br.com.mystudies.service.bean;


import static org.apache.commons.codec.digest.DigestUtils.sha1Hex;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import br.com.mystudies.domain.entity.User;
import br.com.mystudies.service.UserService;
import br.com.mystudies.service.data.request.LoginDataRequest;
import br.com.r3wa.fiscalpackage.persistence.Repository;

public class UserServiceBeanTest {



	@InjectMocks
	private UserService userService;


	@Mock
	private Repository repository;


	@Before
	public void setUp() throws Exception {
		userService = new UserServiceBean();
		initMocks(this);
	}



	@Test
	public void test1() {

		when(repository.selectOne("user-by-login", "robson.o.d@gmail.com", sha1Hex("abc@123"))).thenReturn(new User());

		User user =
			userService.login(
				LoginDataRequest.builder()
					.email("robson.o.d@gmail.com")
					.password("abc@123")
					.create()
			);

		assertThat(user, notNullValue());

		System.out.println(sha1Hex("programdorjava"));

		verify(repository).selectOne("user-by-login", "robson.o.d@gmail.com", sha1Hex("abc@123"));
	}


}
