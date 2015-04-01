package br.com.mystudies.service.persistence;




public interface QueryRepository {

	default String getQuery(String queryName){
		return "implemention default";
	}
}
