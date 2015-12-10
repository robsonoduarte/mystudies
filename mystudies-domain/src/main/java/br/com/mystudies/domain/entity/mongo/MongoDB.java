package br.com.mystudies.domain.entity.mongo;

import java.util.Date;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

import br.com.mystudies.domain.enun.Priority;

public class MongoDB {

	
	private Datastore datastore = create();
	
	
	
	public void save(Object object){
		datastore.save(object);
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	private Datastore create() {				
		Morphia morphia = new Morphia();
		//morphia.mapPackage("br.com.mystudies.domain.entity");
		return morphia.createDatastore(new MongoClient(), "mystudies");
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		MongoDB mongoDB = new MongoDB();
		mongoDB.save(backlog());
	}




















































	private static Object backlog() {
		BackLog backLog = new BackLog();
		for (int i = 0; i < 10; i++) {
			backLog.addTheme(theme(i));
			
		}
		return backLog;
	}




















































	private static Theme theme(int i) {
		Theme theme = new Theme();
		theme.creationDate = new Date();
		theme.priority = Priority.HEIGHT;
		theme.title = "teste -> " + i;		
		return theme;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
