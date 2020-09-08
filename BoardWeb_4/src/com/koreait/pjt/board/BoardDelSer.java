package com.koreait.pjt.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.SecurityUtil;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.BoardDAO;

@WebServlet("/board/del")
public class BoardDelSer extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		String strI_board = request.getParameter("i_board");
		int i_board = Integer.parseInt(strI_board);
		int result = BoardDAO.delBoard(i_board);
		
		response.sendRedirect("/board/list");
	}
}
