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

import br.com.mystudies.domain.entity.Sprint;
import br.com.mystudies.service.SprintService;
import br.com.mystudies.service.statistically.StatisticallyService;
import br.com.mystudies.service.statistically.Temp;

import com.google.gson.Gson;


@WebServlet("/statistics")
public class StatisticsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@EJB
	private SprintService sprintService;


	@EJB
	private StatisticallyService statisticallyService;


	private Gson gson;


    public StatisticsServlet() {
        super();
        gson = new Gson();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String getJSONSprints = request.getParameter("getJSONSprints");
		String getJSONStemp = request.getParameter("getJSONStemp");

		if( !StringUtils.isBlank(getJSONSprints)){
			List<Sprint> sprints = sprintService.getAllSprints();

			for (Sprint sprint : sprints) {
				sprint.getStories().clear();
			}
			response.getWriter().print(gson.toJson(sprints));

		}

		else if(!StringUtils.isBlank(getJSONStemp)){

			List<Temp> temps = statisticallyService.get();
			response.getWriter().print(gson.toJson(temps));
		}

		else{
			request.getRequestDispatcher("pages/statistics/statistics.jsp").forward(request, response);
		}



	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("pages/statistics/statistics.jsp").forward(request, response);
	}

}
