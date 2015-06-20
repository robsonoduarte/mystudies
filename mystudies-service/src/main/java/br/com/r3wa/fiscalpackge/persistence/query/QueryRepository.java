package br.com.r3wa.fiscalpackge.persistence.query;




public interface QueryRepository {

	default String getQuery(String queryName){
		return "implemention default";
	}
}
