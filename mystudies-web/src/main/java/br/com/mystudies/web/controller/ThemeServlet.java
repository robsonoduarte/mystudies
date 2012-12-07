package br.com.mystudies.web.controller;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.Semaphore;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.mystudies.domain.entity.Story;
import br.com.mystudies.domain.entity.Theme;
import br.com.mystudies.domain.enun.Priority;
import br.com.mystudies.domain.enun.StoryStatus;
import br.com.mystudies.service.SprintService;
import br.com.mystudies.service.StoryService;
import br.com.mystudies.service.ThemeService;

/**
 * @author Robson
 */
@WebServlet("/theme")
public class ThemeServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;

	@EJB
	private ThemeService themeService;

	@EJB
	private SprintService sprintService;

	@EJB
	private StoryService storyService;


    public ThemeServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sendToThemePage(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action =
				request.getParameter("action");


		if(action != null){

			switch (action) {

				case "ADDSTORY":{
					sendToThemeStoriesFragment(
							request,
							response,
							themeService.addStory(getTheme(request), getStory(request))
							);
					break;
				}

				case "ADDSTORYSPRINT":{
					sprintService.addStoryInSprint(
							storyService.getStory(
									Long.parseLong(request.getParameter("storyId"))
									)
							);


					sendToThemeStoriesFragment(
							request,
							response,
							getTheme(request)
							);
					break;
				}


				default:{
					sendToThemePage(request, response);
					break;
				}
			}
		}
	}


	private Theme getTheme(HttpServletRequest request) {
		return themeService.getTheme(Long.valueOf(request.getParameter("themeId")));
	}



	private Story getStory(HttpServletRequest request){
		Story story =
			new Story(
				request.getParameter("title"),
				Priority.valueOf(request.getParameter("priority")),
				StoryStatus.BACKLOG,
				new Date(),
				Integer.valueOf(request.getParameter("points"))
			);

		return story;
	}

	private void sendToThemePage(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("theme", getTheme(request));
		request.setAttribute("includeThemesStoriesFragment", true);
		request.getRequestDispatcher("pages/theme/theme.jsp").forward(request, response);
	}


	private void sendToThemeStoriesFragment(HttpServletRequest request,HttpServletResponse response, Theme theme) throws ServletException, IOException {
		request.setAttribute("theme", theme);
		request.getRequestDispatcher("pages/theme/theme-stories-fragment.jsp").forward(request, response);
	}
}
