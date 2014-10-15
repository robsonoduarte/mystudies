package br.com.mystudies.service;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.mystudies.domain.entity.User;
import br.com.mystudies.service.data.request.LoginDataRequest;
import br.com.mystudies.service.persistence.UserDao;

@Stateless
@Remote(UserService.class)
public class UserServiceBean implements UserService {

	
	@EJB
	private UserDao userDao;
	
	
	
	@Override
	public User login(LoginDataRequest loginDataRequest) {		
		return userDao.findUserByLogin(loginDataRequest.covertToUser());
	}

	
	
}
