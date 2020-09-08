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


@WebServlet("/boardDel")
public class BoardDelSer extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String strI_board = request.getParameter("i_board"); 
		
		BoardVO param = new BoardVO();
		param.setI_board(Utils.parseStringToInt(strI_board)); // student
		
		int result = BoardDAO.delBoard(param);
		
		if(result == 1)
		{
			response.sendRedirect("/BoardListSer");
		}
		else
		{
			response.sendRedirect("/err?err=1&target=BoardListSer");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	}
}
