package br.com.mystudies.service.persistence;

import br.com.mystudies.domain.entity.User;

@Deprecated
public interface UserDao {

	User findUserByLogin(User user);

}
