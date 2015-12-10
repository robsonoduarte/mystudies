package br.com.mystudies.service.mongo.migration;

import java.util.List;

import javax.ejb.Schedule;
import javax.inject.Inject;

import br.com.mystudies.domain.entity.Entity;
import br.com.r3wa.fiscalpackage.persistence.Repository;

// migrant
/*@Singleton*/
public class MongoMigrant {

	

	@Inject
	private Repository repository;

	
	
	
	@Inject
	private MongoDB mongoDB;

	
	
	
	@Schedule(hour="*", minute="*")
	public void emigrate(){
		
		
		System.out.println("emigrate.......");
		
		List<Entity> backlog = repository.select("list-backlog");
		
		
	
		backlog.forEach(b -> {
	//		mongoDB.save(b);			
		});
			
	}

	
	
	
}
