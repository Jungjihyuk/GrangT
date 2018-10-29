package controller;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardDAO;
import model.Viw_boardBean;
import service.BoardService;
import service.HashService;
import service.Viw_boardService;

//import java.io.File;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

//@WebServlet("/uploadBoard.do")
public class Board extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	private BoardService boardservice;
	private HashService hashservice;
	private Viw_boardService viwboardservice;
	public Board() {
		boardservice=new BoardService();
		hashservice=new HashService();
		viwboardservice=new Viw_boardService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}
	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String contextPath=request.getContextPath();
		String requestUri=request.getRequestURI();
		
		//HttpSession session=request.getSession();
		//UserBean userbean=(UserBean) session.getAttribute("userbean");
		
		//requestUri.equals(contextPath+"uploadBoard.do")
		if(requestUri.equals(contextPath+"/uploadBoard.act")) {
			//게시물 업로드
			String directory=request.getSession().getServletContext().getRealPath("/upload/");
			
			int maxSize=1024*1024*100;
			String encoding="UTF-8";
			
			MultipartRequest multipartRequest=new MultipartRequest(request, directory, maxSize, encoding, new DefaultFileRenamePolicy());
					
			String fileName=multipartRequest.getOriginalFileName("photo"); 
			String fileRealName=multipartRequest.getFilesystemName("photo");
			System.out.println(fileName);
			System.out.println(fileRealName);
			
			HttpSession session=request.getSession();
			//UserBean userbean=(UserBean) session.getAttribute("userbean");
			//String userId=(String) userbean.getId();
			
			String content=multipartRequest.getParameter("content");
			//String photo=request.getParameter("photo");//photo도 여러개 받아와야되는데...
			String hash=multipartRequest.getParameter("hash");//hash 어떻게 받아올까
			String category=multipartRequest.getParameter("category");
			String userId=(String) session.getAttribute("id");
		
			System.out.println(category);
			System.out.println(hash);
			System.out.println(content);
			System.out.println(userId);
			
			//게시물 DB insert
			int result=boardservice.uploadBoard(content, fileName,fileRealName, category, userId);
			
			if(result==1) {
				//board가 제대로 저장되었다면 boardNum을 가지고와 hash 테이블에도 넣기.
				//저장 작업이 완료되었으면 feed.jsp로 이동
				int boardNum=0;
				BoardDAO boarddao=new BoardDAO();
				boardNum=boarddao.selectOneBoardNum();
				hashservice.uploadBoard(hash, boardNum);
				
				RequestDispatcher dis=request.getRequestDispatcher("feed.jsp");
				dis.forward(request, response);
			}else if(boardservice.uploadBoard(content, fileName, fileRealName, category, userId)==2) {
				//로그인이 필요합니다.
			}else if(boardservice.uploadBoard(content, fileName, fileRealName, category, userId)==3) {
				//카테고리를 선택해주세요.
			}else {
				//오류
				RequestDispatcher dis=request.getRequestDispatcher("uploadError.jsp");
				dis.forward(request, response);
			}
		}else if(requestUri.equals(contextPath+"/selectAllBoard.act")) {
			//게시물 보여주기
			Vector <Viw_boardBean> list=viwboardservice.selectAllBoard();
			request.setAttribute("list", list);
			RequestDispatcher dis=request.getRequestDispatcher("feed.jsp");
			dis.forward(request, response);
		}else if(requestUri.equals(contextPath+"/")) {
			//게시물 수정
		}else if(requestUri.equals(contextPath+"")) {
			//게시물 삭제 
		}else if(requestUri.equals(contextPath+"search.act")) {
			//해시 검색 (여기서 해도 될라나?)
			String hash=request.getParameter("hash");
			//response.getWriter().write(getJSON(hash));
		}
		
	}

}
