package com.odd.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.odd.board.model.service.BoardService;
import com.odd.board.model.vo.Board;
import com.odd.common.model.vo.PageInfo;

/**
 * Servlet implementation class BoardListController
 */
@WebServlet("/list.bo")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// ----------------- 페이징 처리 ------------------
		int listCount; 		// 현재 게시글 개수
		int currentPage; 	// 사용자가 요청한 페이지 ( == 현재페이지)
		int pageLimit; 		// 페이지 하단에 보여질 페이징바의 페이지 최대개수 (몇개 단위씩)
		int boardLimit; 	// 페이지당 보여질 게시글의 최대개수 (몇개 단위씩)

		int maxPage; 		// 가장 마지막 페이지 (총 페이지 수)
		int startPage; 		// 사용자가 요청한 페이지 하단 페이징바의 시작수
		int endPage; 		// 사용자가 요청한 페이지 하단 페이징바의 끝수

		listCount = new BoardService().selectListCount();
		
		currentPage = Integer.parseInt(request.getParameter("cpage"));
		
		pageLimit = 10;

		// * boardLimit : 페이지당 보여질 게시글의 최대개수 (단위)
		boardLimit = 10;

		/*
		 * * maxPage : 제일 마지막 페이지 수 (총 페이지 수)
		 * 
		 * listCount, boardLimit에 영향을 받음
		 * 
		 * ex) 게시글이 10개 단위씩 보여지는 경우 listCount boardLimit maxPage 100.0 / 10 => 10.0 10
		 * 101.0 / 10 => 10.1 11 105.0 / 10 => 10.5 11 110.0 / 10 => 11.0 11
		 * 
		 * ex) 게시글이 5개 단위씩 보여지는 경우 10.0 / 5 => 2.0 2 11.0 / 5 => 2.1 3 14.0 / 5 => 2.4 3
		 * 15.0 / 5 => 3.0 3
		 * 
		 * 총게시글개수(실수형) / boardLimit -> 올림처리
		 *
		 */
		maxPage = (int) Math.ceil((double) listCount / boardLimit);

		/*
		 * * startPage : 요청된 페이지 하단 페이징바의 시작수
		 * 
		 * pageLimit, currentPage에 영향을 받음
		 * 
		 * ex) 페이징바의 목록이 10개 단위(pageLimit)씩이라는 가정 하에 startPage : 1, 11, 21, 31, ... => n
		 * * pageLimit + 1
		 * 
		 * currentPage startPage 1 1 => 0 * pageLimit + 1 => n=0 5 1 => 0 * pageLimit +
		 * 1 => n=0 10 1 => 0 * pageLimit + 1 => n=0
		 * 
		 * 11 11 => 1 * pageLimit + 1 => n=1 15 11 => 1 * pageLimit + 1 => n=1 20 11 =>
		 * 1 * pageLimit + 1 => n=1
		 * 
		 * 1~10 => n=0 11~20 => n=1 21~30 => n=2 ...
		 *
		 * (currentPage - 1) / pageLimit => n
		 *
		 */
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;

		/*
		 * * endPage : 요청된 페이지 하단 페이징바의 끝수
		 * 
		 * startPage, pageLimit에 영향을 받음 (단, maxPage에도 영향 받음)
		 * 
		 * ex) pageLimit : 10 가정하에
		 * 
		 * startPage : 1 => endPage : 10 startPage : 11 => endPage : 20 startPage : 21
		 * => endPage : 30
		 *
		 */
		endPage = startPage + pageLimit - 1;

		// startPage가 11일 경우 endPage는 20으로 됨 (근데 maxPage가 13까지밖에 안된다면?)
		if (endPage > maxPage) {
			endPage = maxPage;
		}

		// 7개의 값들 담을 vo클래스 임의로 만들기 (vo클래스를 반드시 테이블이랑만 매칭시킬 필요는 없습니당)
		// com.br.common.model.vo.PageInfo // 다양한 게시판에 페이징처리가 필요할 것이기 때문에 common 패키지 안에
		// 생성할겨
		// * 페이징바를 만들 때 필요한 객체
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);

		// * 현재 요청한 페이지(currentPage)에 보여질 게시글 리스트 boardLimit수만큼 조회
		ArrayList<Board> list = new BoardService().selectList(pi);

		// 처음 : forwarding으로 띄워줄 거얌
		request.setAttribute("pi", pi);
		request.setAttribute("list", list);
		request.getRequestDispatcher("views/board/boardListView.jsp").forward(request, response);

	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
