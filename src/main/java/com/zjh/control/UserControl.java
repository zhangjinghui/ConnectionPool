package com.zjh.control;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.zjh.service.IUserService;
import com.zjh.service.UserServiceImpl;
import com.zjh.vo.User;

@WebServlet(name="UserControl",urlPatterns={"/user"})
public class UserControl extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private IUserService service=new UserServiceImpl();
	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action=request.getParameter("action");
		RequestDispatcher dispatcher=null;
		if(action.equals("login")){
			User user=new User();
			user.setUsername(request.getParameter("username"));
			user.setPassword(request.getParameter("password"));
			User user2=null;
			if((user2=service.login(user))!=null){
				request.getSession().setAttribute("user", user2);
				dispatcher=request.getRequestDispatcher("success.jsp");
			}else{
				dispatcher=request.getRequestDispatcher("fail.html");
			}
		}
		if(dispatcher!=null){
			dispatcher.forward(request, response);
		}
	}

	public void init() throws ServletException {
		
	}

}
