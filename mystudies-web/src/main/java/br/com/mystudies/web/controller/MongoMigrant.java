package br.com.mystudies.web.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.mystudies.domain.entity.BackLog;
import br.com.mystudies.service.mongo.migration.MongoDB;
import br.com.r3wa.fiscalpackage.persistence.Repository;

/**
 * @author Robson
 */
@WebServlet("/mongo")
public class MongoMigrant extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	
	@Inject
	private Repository repository;

	
	
	
	@Inject
	private MongoDB mongoDB;



    public MongoMigrant() {
        super();
    }



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("emigrate.......");
		
		List<BackLog> backlog = repository.select("list-backlog");
		
		
	
		try {
			
			
			backlog.forEach(b ->{
				b.getThemes().forEach(t -> {mongoDB.save(t.tomongo());});							
			});
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
	}


}
