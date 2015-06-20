package br.com.r3wa.fiscalpackge.persistence.query;

import java.net.URL;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;



class JPAFilerQueries implements FilerQueries {


	private Configuration configuration;




	public JPAFilerQueries() {
		loadQueries();
	}






	@Override
	public String query(String name) {
		return configuration.getString(name);
	}






	public void loadQueries(){
		try{
			configuration =
					new PropertiesConfiguration(file());
		} catch (Exception e) {
			logError(e);
		}
	}



























	private URL file() {
		return JPAFilerQueries.class.getResource("/jpa-queries/jpa-queries.properties");
	}




	private void logError(Exception e) {
		// FIXME: remover após egine de log
		System.out.println("Erro ao gerar o arquivo de queries -> ");
	}
}
