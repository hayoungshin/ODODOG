package com.odd.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.odd.member.model.dao.MemberDao;
import com.odd.member.model.service.MemberService;
import com.odd.member.model.vo.Member;

/**
 * Servlet implementation class SearchIdController
 */
@WebServlet("/searchId.me")
public class SearchIdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchIdController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String user_Name = request.getParameter("user_Name");
		String email = request.getParameter("email");
		
		Member searchId =  new MemberService().searchId(user_Name, email);
		
		System.out.println(searchId);
		
		/*
		if(searchId == null) {
			request.setAttribute("errorMsg", "조회된 회원정보가 없습니다.");
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPafe.jsp");
			view.forward(request, response);			
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("alertMsg", "회원님의 아이디는" + searchId + "입니다.");
			response.sendRedirect(request.getContextPath());
		}*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
