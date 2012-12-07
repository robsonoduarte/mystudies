package br.com.mystudies.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.mystudies.domain.entity.Sprint;
import br.com.mystudies.domain.entity.Story;
import br.com.mystudies.domain.entity.Theme;
import br.com.mystudies.domain.enun.StoryStatus;
import br.com.mystudies.service.SprintService;
import br.com.mystudies.service.StoryService;

/**
 * @author Robson
 */
@WebServlet("/kanban")
public class KanbanServlet extends HttpServlet {



    private static final long serialVersionUID = 1L;


    @EJB
    private SprintService sprintService;



    @EJB
    private StoryService storyService;



    public KanbanServlet() {
        super();
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    	String action =
    			request.getParameter("action");


    	if(action != null){

    		switch (action) {


				case "UPDATESTATUSSTORY":
					storyService.updateStatusStory(
							Long.valueOf(request.getParameter("storyID")),
							StoryStatus.valueOf(request.getParameter("kanbanFase"))
						);
				break;

			default:
				break;
			}
    	}

    	setCurrentSprintInRequest(request);


        request.getRequestDispatcher("pages/kanban/kanban.jsp").forward(request, response);
    }



	private void setCurrentSprintInRequest(HttpServletRequest request) {
		// parametro para saber se o evento foi
        // id da story
        // para qual fase do kanbam a story foi passada
        // salvar novo estado da historia.




    	Sprint sprint =
    			sprintService.getCurrentSprint();



    	Set<Story> stories =
    			sprint.getStories();


    	Map<Theme, List<Story>> map = new HashMap<>();


    	for (Story story : stories) {

    		if(map.containsKey(story.getTheme())){
    			map.get(story.getTheme()).add(story);
    		}else{

    			List<Story> s = new ArrayList<>();

    			s.add(story);

    			map.put(story.getTheme(), s);
    		}
		}


    	request.setAttribute("map", map);
	}


















































    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("pages/kanban/kanban.jsp").forward(request, response);

    }


}
