package br.com.mystudies.service.bean;

import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import br.com.mystudies.domain.entity.User;
import br.com.mystudies.service.UserService;
import br.com.mystudies.service.data.request.LoginDataRequest;
import br.com.mystudies.service.persistence.Repository;

@Stateless
@Local(UserService.class)
public class UserServiceBean implements UserService {

	
	@EJB
	private Repository repository;
	
	
	@Override
	public User login(LoginDataRequest loginDataRequest) {
		List<User> users = repository.select("select-user-by-login", loginDataRequest.toArray());
		return isNotEmpty(users) ? users.get(0) : null;
	}

	
}
