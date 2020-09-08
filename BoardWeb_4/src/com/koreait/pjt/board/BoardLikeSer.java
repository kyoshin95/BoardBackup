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
import com.koreait.pjt.vo.BoardDomain;
import com.koreait.pjt.vo.UserVO;

@WebServlet("/board/toggleLike")
public class BoardLikeSer extends HttpServlet 
{
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String strI_board = request.getParameter("i_board");
		String strYn_like = request.getParameter("yn_like");
		int yn_like = SecurityUtil.parseStringToInt(strYn_like);
		int i_board = SecurityUtil.parseStringToInt(strI_board);
		
		UserVO vo = SecurityUtil.getLoginUser(request);
		
		BoardDomain param = new BoardDomain();
		param.setI_board(i_board);
		param.setI_user(vo.getI_user());
		
		
		param.setYn_like(yn_like);
		
		if(yn_like == 0)
		{
			BoardDAO.insLike(param);
			response.sendRedirect("/board/detail?i_board=" + i_board);
		}
		else if(yn_like == 1)
		{
			BoardDAO.delLike(param);
			response.sendRedirect("/board/detail?i_board=" + i_board);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
}
