package br.com.mystudies.service.mongo.migration;

import java.util.List;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

import br.com.mystudies.domain.entity.BackLog;
import br.com.r3wa.fiscalpackage.persistence.Repository;

// migrant
@Singleton
public class MongoMigrant {

	

	@Inject
	private Repository repository;
	

	
	
	
	@Schedule(hour="*", minute="*")
	public void emigrate(){
		
		List<BackLog> backlog = repository.select("list-backlog");
		
		
	
		backlog.forEach(b -> {
			b.getThemes().forEach(t -> { 
				System.out.println(t); 
			});
		});
		
			
	}

	
	
	
}
