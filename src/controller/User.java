package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.UserService;


//@WebServlet("/User")
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UserService userservice;
	public User() {
		userservice=new UserService();
	}
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request,response);
	}

	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String contextPath=request.getContextPath();
		String requestUri=request.getRequestURI();
		
		if(requestUri.equals(contextPath+"/signup.do")) {
			String name=request.getParameter("userName");
			String id=request.getParameter("id");
			String password=request.getParameter("password");
			String passwordchk=request.getParameter("passwordchk");
			boolean result=userservice.signup(id, password, passwordchk, name);
			if(result==true) {
				RequestDispatcher dis=request.getRequestDispatcher("interests.jsp");
				dis.forward(request, response);
			}else {
				//오류
				response.sendRedirect("index.jsp");
			}
		}else if(requestUri.equals(contextPath+"/login.do")){
			String id=request.getParameter("id");
			String password=request.getParameter("password");
			if(userservice.login(id,password)) {                //로그인 성공시 True 값을 반환
				request.setAttribute("msg", "로그인 성공");
				request.getSession().setAttribute("id", id);
				request.getRequestDispatcher("feed.jsp").forward(request, response);
			}else {
				request.setAttribute("msg", "로그인 실패");
			}
		}else if(requestUri.equals(contextPath+"")){
			//로그아웃
			//session이 없다면 로그인을 하라는 메시지 
			//session이 있다면 invalidate()으로 로그아웃 처리 
			HttpSession session=request.getSession();
			session.invalidate();
			RequestDispatcher dis=request.getRequestDispatcher("index.jsp");
			dis.forward(request, response);
		}else if(requestUri.equals(contextPath+"")) {
			//카테고리 수정하기
		}else if(requestUri.equals(contextPath+"")) {
			//사진 수정하기
		}
	}
}
