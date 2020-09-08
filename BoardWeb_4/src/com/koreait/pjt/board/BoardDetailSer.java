  
package com.koreait.pjt.board;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.SecurityUtil;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.BoardCmtDAO;
import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.vo.BoardVO;
import com.koreait.pjt.vo.UserVO;

@WebServlet("/board/detail")
public class BoardDetailSer extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		UserVO loginUser = SecurityUtil.getLoginUser(request);
		
		if(loginUser == null)
		{
			response.sendRedirect("/login");
			return;
		}
		
		if(SecurityUtil.isLogout(request)) 
		{
			response.sendRedirect("/login");
			return;
		}

		// pk값 식별
		String strI_board = request.getParameter("i_board");
		int i_board = Integer.parseInt(strI_board);
		
		ServletContext application = getServletContext();
		Integer readI_user = (Integer)application.getAttribute("read_" + strI_board);
		
		if(readI_user == null || readI_user != loginUser.getI_user())
		{
			BoardDAO.addHits(i_board);
			application.setAttribute("read_" + strI_board, loginUser.getI_user());
		}
		
		BoardVO param = new BoardVO();
		param.setI_user(loginUser.getI_user());
		param.setI_board(i_board);
		request.setAttribute("data", BoardDAO.selBoard(param));
		request.setAttribute("likeList", BoardDAO.selBoardLikeList(i_board));
		
		request.setAttribute("cmtList", BoardCmtDAO.selCmtList(i_board));
		ViewResolver.forward("board/detail", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

	}

}