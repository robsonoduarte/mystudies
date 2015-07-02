package br.com.mystudies.service.bean;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import br.com.mystudies.domain.entity.User;
import br.com.mystudies.service.UserService;
import br.com.mystudies.service.data.request.LoginDataRequest;
import br.com.r3wa.fiscalpackage.persistence.Repository;

@Stateless
@Local(UserService.class)
public class UserServiceBean implements UserService {


	@EJB
	private Repository repository;



	@Override
	public User login(LoginDataRequest loginDataRequest) {
		return repository.selectOne("user-by-login", loginDataRequest.toArray());
	}


}
