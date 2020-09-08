package com.koreait.board;

import java.util.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.koreait.board.db.BoardDAO;
import com.koreait.board.db.DbCon;
import com.koreait.board.vo.BoardVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BoardListSer")
public class BoardListSer extends HttpServlet // 클래스
{
	private static final long serialVersionUID = 1L; // 맴버필드
         
    public BoardListSer() // 기본생성자
    {
        super(); // 직속 부모
    }
	
    // JSP Container가 전체를 관리
    
    // get : queryString으로 url에 보낼 정보들을 표시하여 보냄, post : queryString으로 url에 정보를 표시하지않음.
    // post방식을 사용하는 경우
    // 1. 보낼 정보가 많아서 queryString으로 표시하기에는 많을 때.
    // 2. 보안적으로 중요할 때.
    
    // JSP : View 담당, Servlet : Logic 담당.
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{ 
    	// request는 사용자가 준 모든 정보를 담고 있음, response는 request의 대한 응답을 보냄.

    	// sendRedirect는 전송할때 주소값이 변하고 response와 request가 전달이 안됨. 새로 생성.
    	// doget, dopost방식에서 모두 get방식으로 넘어감
    	// RequestDispatcher는 전송할때 주소값이 변하지 않고 response과 request가 전달이됨.
    	// doget, dopost둘 다 각각의 방식으로 날라감.
    	
    	// application 객체 : 서버가 켜지는 순간 생성, 끄는 순간 없어짐 - 전체용
    	// pagecontext 객체 : JSP 페이지에 대한 정보를 저장하고 있는 객체 (범위 : 페이지 생성 ~ 응답)
    	// request 객체 : 요청 정보를 저장하고 있는 객체 (범위 : 페이지 요청 ~ 응답)
    	// session 객체 : 하나의 웹 브라우저의 정보를 유지하기 위한 세션 정보를 저장하고 있는 객체(범위 : 브라우저를 켜질 때 브라우저가 꺼질 때)
    	
    	List<BoardVO> list = BoardDAO.selBoardList();
    	request.setAttribute("data", list);
    	
    	RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/boardList.jsp");
    	rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
}