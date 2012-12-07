package br.com.mystudies.web.controller;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;

import br.com.mystudies.domain.entity.Sprint;
import br.com.mystudies.service.SprintService;


@WebServlet("/statistics")
public class StatisticsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	@EJB
	private SprintService sprintService;
	
	
	private Gson gson; 
	
    public StatisticsServlet() {
        super();
        gson = new Gson();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String getJSONSprints = request.getParameter("getJSONSprints");
		
		if( !StringUtils.isBlank(getJSONSprints)){			
			List<Sprint> sprints = sprintService.getAllSprints();
			response.getWriter().print(gson.toJson(sprints));
		}else{			
			request.getRequestDispatcher("pages/statistics/statistics.jsp").forward(request, response);			
		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("pages/statistics/statistics.jsp").forward(request, response);
	}

}
