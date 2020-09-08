package com.koreait.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.board.common.Utils;
import com.koreait.board.db.BoardDAO;
import com.koreait.board.vo.BoardVO;

@WebServlet("/boardWrite")
public class BoardWhiteSer extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		String jsp = "/WEB-INF/view/boardRegmod.jsp";
		request.getRequestDispatcher(jsp).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String title = request.getParameter("title");
		String cont = request.getParameter("cont");
		String strI_student = request.getParameter("i_student"); 
		
		BoardVO param = new BoardVO();
		param.setTitle(title); // title
		param.setCont(cont); // content
		param.setI_student(Utils.parseStringToInt(strI_student)); // student
		
		int result = BoardDAO.insBoard(param); 
		
		if(result == 1)
		{
			response.sendRedirect("/BoardListSer");
		}
		else
		{
			request.setAttribute("msg", "에러가 발생했습니다.");
			doGet(request, response);
		}
		
		
	}
}
