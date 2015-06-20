package br.com.r3wa.fiscalpackge.persistence.query;

import static org.apache.commons.lang3.StringUtils.isBlank;
import br.com.r3wa.fiscalpackage.persistence.exception.FiscalPacakagePersistenceRuntimeException;



public class JPAQueryRepository implements QueryRepository {


	private static final FilerQueries FILER_QUERIES = new JPAFilerQueries();




	public JPAQueryRepository() {
	}



	@Override
	public String getQuery(String queryName) {
		String query = FILER_QUERIES.query(queryName);
		if(isBlank(query)){
			throw new FiscalPacakagePersistenceRuntimeException("Query com o nome -> " + queryName + " nao encontrda");
		}
		return query;
	}


}
