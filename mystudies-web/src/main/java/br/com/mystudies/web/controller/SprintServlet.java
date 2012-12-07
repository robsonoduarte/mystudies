package br.com.mystudies.web.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.mystudies.domain.entity.Sprint;
import br.com.mystudies.domain.entity.Story;
import br.com.mystudies.domain.enun.Priority;
import br.com.mystudies.domain.enun.SprintStatus;
import br.com.mystudies.domain.enun.StoryStatus;
import br.com.mystudies.service.SprintService;


@WebServlet("/sprints")
public class SprintServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;


	@EJB
	private SprintService sprintService;



    public SprintServlet() {
        super();
    }



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sendToSprintsPage(
				request,
				response,
				sprintService.getAllSprints()
			);
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action =
				request.getParameter("action");


		if(action != null){

			switch (action) {
				case "NEWSPRINT":
					sendToSprintFormFragment(
							request,
							response,
							sprintService.containsSprintInRun()
						);
					break;
				case "CREATESPRINT":
					sprintService.create(getSprint(request, response));
					sendToSprintsFragment(request, response);
					break;
			}
		}
	}





	private Sprint getSprint(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			return new Sprint(
					sdf.parse(request.getParameter("startDate")),
					sdf.parse(request.getParameter("finalDate")),
					SprintStatus.RUNNING
				);
		} catch (ParseException e) {
			throw new ServletException(e);
		}
	}


	private void sendToSprintsPage(HttpServletRequest request,HttpServletResponse response, List<Sprint> sprints) throws ServletException, IOException {
		request.setAttribute("includeSprintsFragment", true);
		request.setAttribute("sprints", sprints);
		request.getRequestDispatcher("pages/sprint/sprints.jsp").forward(request, response);
	}


	private void sendToSprintFormFragment(HttpServletRequest request,HttpServletResponse response, boolean containsSprintInRun) throws ServletException, IOException {
		request.setAttribute("containsSprintInRun", containsSprintInRun);
		request.getRequestDispatcher("pages/sprint/sprint-form-fragment.jsp").forward(request, response);
	}


	private void sendToSprintsFragment(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("pages/sprint/sprints-fragment.jsp").forward(request, response);
	}

}
