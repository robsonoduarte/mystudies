package br.com.mystudies.service.persistence;

import br.com.mystudies.domain.entity.EntityBase;

public interface Repository {

	 default <T extends EntityBase> T save(T t){
		 return t;
	 }

}
