package br.com.mystudies.service.data.request;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import br.com.mystudies.domain.entity.User;

public class LoginDataRequestTest {


	
	// TODO: change method's name.
	@Test
	public void test1() {
		
		LoginDataRequest loginDataRequest = 
				LoginDataRequest.builder()
					.email("robson.o.d@gmail.com")
					.password("abc@123")
					.create();
		
		assertThat(loginDataRequest.getEmail(), equalTo("robson.o.d@gmail.com"));
		assertThat(loginDataRequest.getPassword(), equalTo("ddac418a1be76098d01107464026f65d2a3192bf"));
	}
	
	
	
	

	
	
	// TODO: change method's name.
	@Test(expected=IllegalStateException.class)
	public void test2() {
		LoginDataRequest loginDataRequest = 
				LoginDataRequest.builder()
				.password("abc@123")
				.create();		
	}
	
	
	
	// TODO: change method's name.
	
	@Test(expected=IllegalStateException.class)
	public void test3() {
		LoginDataRequest loginDataRequest = 
				LoginDataRequest.builder()
				.email("robson.o.d@gmail.com")
				.create();		
	}	

	
	
	// TODO: change method's name.
	@Test
	public void test4() {
		
		LoginDataRequest loginDataRequest = 
				LoginDataRequest.builder()
				.email("robson.o.d@gmail.com")
				.password("abc@123")
				.create();
		
		User user = loginDataRequest.covertToUser();
		
		assertThat(user.getEmail(), equalTo("robson.o.d@gmail.com"));
		assertThat(user.getPassword(), equalTo("ddac418a1be76098d01107464026f65d2a3192bf"));
		
	}
	
}
