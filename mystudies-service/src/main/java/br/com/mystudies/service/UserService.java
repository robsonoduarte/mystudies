package br.com.mystudies.service;

import br.com.mystudies.domain.entity.User;
import br.com.mystudies.service.data.request.LoginDataRequest;




public interface UserService {

	User login(LoginDataRequest loginDataRequest);

}
