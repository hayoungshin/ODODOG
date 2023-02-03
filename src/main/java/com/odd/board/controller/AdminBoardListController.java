package com.odd.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.odd.board.model.service.AdminBoardService;
import com.odd.board.model.vo.Board;
import com.odd.board.model.vo.Reply;
import com.odd.common.model.vo.PageInfo;

/**
 * Servlet implementation class AdminBoardListController
 */
@WebServlet("/list.adBo")
public class AdminBoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminBoardListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ----------------- 페이징 처리 ------------------
		int listCount; 		// 현재 게시글 개수
		int replyListCount;	// 현재 댓글 개수
		int boardPage; 		// 사용자가 요청한 일반 게시글 페이지 ( == 현재페이지)
		int replyPage;		// 사용자가 요청한 댓글 게시글 페이지 ( == 현재페이지)
		
		int pageLimit; 		// 페이지 하단에 보여질 페이징바의 페이지 최대개수 (몇개 단위씩)
		int boardLimit; 	// 페이지당 보여질 게시글의 최대개수 (몇개 단위씩)

		int maxPage; 		// 가장 마지막 페이지 (총 페이지 수)
		int startPage; 		// 사용자가 요청한 페이지 하단 페이징바의 시작수
		int endPage; 		// 사용자가 요청한 페이지 하단 페이징바의 끝수

		listCount = new AdminBoardService().selectListCount();
		replyListCount = new AdminBoardService().selectReplyListCount();
		boardPage = Integer.parseInt(request.getParameter("bpage"));
		replyPage = Integer.parseInt(request.getParameter("rpage"));
		pageLimit = 5;
		boardLimit = 10;
		maxPage = (int) Math.ceil((double) listCount / boardLimit);
		startPage = (boardPage - 1) / pageLimit * pageLimit + 1;
		endPage = startPage + pageLimit - 1;
		if (endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pi = new PageInfo(listCount, boardPage, pageLimit, boardLimit, maxPage, startPage, endPage);
		PageInfo replyPi = new PageInfo(replyListCount, replyPage, pageLimit, boardLimit, maxPage, startPage, endPage);
		ArrayList<Board> list = new AdminBoardService().selectList(pi);
		// 여기서ㅏ부터 하기! 댓글 목록 어떻게 구현할지 생각해야될듯
		ArrayList<Reply> replyList;

		request.setAttribute("pi", pi);
		request.setAttribute("list", list);
		request.getRequestDispatcher("views/board/adminBoardListView.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
