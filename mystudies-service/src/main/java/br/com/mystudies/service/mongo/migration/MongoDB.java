package br.com.mystudies.service.mongo.migration;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

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
	
	
	

	
	
	
	
	
	
	
	
	
	
	
}
