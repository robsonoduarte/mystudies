package br.com.mystudies.web.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.mystudies.domain.entity.User;
import br.com.mystudies.service.UserService;
import br.com.mystudies.service.data.request.LoginDataRequest;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;


	@EJB
	private UserService userService;


    public LoginServlet() {
        super();
    }




	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		addUserInSession(
				request,
				userService.login(createLoginDataRequest(request))
			);

		response.sendRedirect("backlog");
	}



	private LoginDataRequest createLoginDataRequest(HttpServletRequest request) {
		return LoginDataRequest.builder()
				.email(request.getParameter("email"))
				.password(request.getParameter("password"))
				.create();
	}



	private void addUserInSession(HttpServletRequest request, User user) {
		request.getSession().setAttribute("user", user);
	}


}
