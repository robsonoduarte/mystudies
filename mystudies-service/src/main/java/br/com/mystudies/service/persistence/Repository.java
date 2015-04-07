package br.com.mystudies.service.persistence;


import static java.util.Collections.emptyList;

import java.util.List;

import br.com.mystudies.domain.entity.Entity;


public interface Repository {

	default <T extends Entity> T save(T t){
		return t;
	}

	default Integer remove(String queryName, Long code){
		return -1;
	}

	default <T extends Entity> T find(Class<T> entityClass, Long code){
		return null;
	}

	default <T extends Entity >  List<T> select(String queryName, Object... parameters){
		return emptyList();
	}

	default <T extends Entity> List<T> selectIN(String queryName,List<? extends Object> listParameters){
		return emptyList();
	}

}
