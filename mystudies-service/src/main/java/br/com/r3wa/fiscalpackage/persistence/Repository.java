package br.com.r3wa.fiscalpackage.persistence;


import static java.util.Collections.emptyList;

import java.util.List;

import br.com.mystudies.domain.entity.Entity;



public interface Repository {

	default <T extends Entity> T save(T t){
		return t;
	}

	default <T> void remove(T t){}


	default <T extends Entity> T removeAndSave(T t){
		return null;
	}

	default <T extends Entity> T find(Class<T> entityClass, Object code){
		return null;
	}

	default <T extends Entity >  List<T> select(String queryName, Object... parameters){
		return emptyList();
	}


	default <T extends Entity> List<T> selectIN(String queryName, List<? extends Object> listParameters){
		return emptyList();
	}

	default <T extends Entity> List<T> selectIN(String queryName, int maxResult, List<? extends Object> listParameters){
		return emptyList();
	}

	default <T extends Entity> T selectOne(String queryName, Object... parameters){
		return null;
	}


	default <T extends Entity> List<T>  selectBetween(String queryName, Object start, Object end){
		return null;
	}


	default <T extends ResultData> List<T> selectAtributes(String queryName, Object... parameters) {
		return emptyList();
	}


}
