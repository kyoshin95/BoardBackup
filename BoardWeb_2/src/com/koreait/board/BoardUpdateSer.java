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

@WebServlet("/boardMod")
public class BoardUpdateSer extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String strI_board = request.getParameter("i_board");
		//System.out.println(strI_board);
		int i_board = Utils.parseStringToInt(strI_board, 0);
		
		if(i_board == 0)
		{
			response.sendRedirect("/boardList");
			return;
		}
		BoardVO param = new BoardVO();
		param.setI_board(i_board);
		
		BoardVO result = BoardDAO.selBoard(param); 
		request.setAttribute("data", result);
		
		String jsp = "/WEB-INF/view/boardRegmod.jsp";
		request.getRequestDispatcher(jsp).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String strI_board = request.getParameter("i_board");
		String title = request.getParameter("title");
		String cont = request.getParameter("cont");
		
		int i_board = Utils.parseStringToInt(strI_board);
		
		if(i_board == 0)
		{
			response.sendRedirect("/boardList");
			return;
		}
		
		BoardVO param = new BoardVO();
		param.setTitle(title); // title
		param.setCont(cont); // content
		param.setI_board(i_board);
		
		int result = BoardDAO.modBoard(param); 
		response.sendRedirect("/boardDetail?i_board=" + strI_board);
	}
}
