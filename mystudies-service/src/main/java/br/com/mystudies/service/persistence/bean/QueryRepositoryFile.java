package br.com.mystudies.service.persistence.bean;

import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import br.com.mystudies.service.persistence.QueryRepository;



public class QueryRepositoryFile implements QueryRepository {


	private static Properties QUERYS_PROPERTIES = new Properties();




	public QueryRepositoryFile() {
		 try {
			loadQueries();
		} catch (IOException e) {
			// TODO add mecanismo de log
		}
	}



	@Override
	public String getQuery(String queryName) {
		return QUERYS_PROPERTIES.getProperty(queryName);
	}


	/*@PostConstruct*/
	private static void loadQueries() throws IOException{
		if(!isNotEmpty(QUERYS_PROPERTIES.entrySet())){
			QUERYS_PROPERTIES.load(getQueryFile());
		}
	}


	private static InputStream getQueryFile() {
		return QueryRepositoryFile.class.getResourceAsStream("/jpa-queries.properties");
	}

}
