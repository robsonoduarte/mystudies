package br.com.mystudies.service.data.request;

import static org.apache.commons.codec.digest.DigestUtils.sha1Hex;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.io.Serializable;

public final class LoginDataRequest implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;



	private String email;
	private String password;


	private LoginDataRequest() {}





	public static Builder builder() {
		return new Builder();
	}



	public String getEmail() {
		return email;
	}



	public String getPassword() {
		return sha1Hex(password);
	}

















	// >>>>>>>>>>>>> BUILDER OF CLASS <<<<<<<<<<<<<<<<<<<<

	public static final class Builder{


		private LoginDataRequest loginDataRequest = new LoginDataRequest();


		private Builder() {}


		public Builder email(String email) {
			loginDataRequest.email = email;
			return this;
		}


		public Builder password(String password) {
			loginDataRequest.password = password;
			return this;
		}


		public LoginDataRequest create() {
			System.out.println("Email..: " + loginDataRequest.email + " password..: " + loginDataRequest.password);

			validateState();
			return loginDataRequest;
		}


		private void validateState() {
			if(isBlank(loginDataRequest.email ) || isBlank(loginDataRequest.password)){
				throw new IllegalStateException("the email and password is mandatory");
			}
		}

	}




	public static void 	main(String[] args) {
		System.out.println(sha1Hex("programadorjava"));
	}





	public Object[] toArray() {
		System.out.println("Email -> " + email + " password -> " + sha1Hex(password));
		return new String[]{email, sha1Hex(password)};
	}


}
