package com.koreait.pjt.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Param;

import com.koreait.pjt.SecurityUtil;
import com.koreait.pjt.db.BoardCmtDAO;
import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.vo.BoardCmtVO;
import com.koreait.pjt.vo.BoardDomain;
import com.koreait.pjt.vo.UserVO;

@WebServlet("/board/cmt")
public class BoardCmtSer extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strI_board = request.getParameter("i_board");
		int i_cmt = SecurityUtil.getIntParameter(request, "i_cmt");
		
		UserVO loginUser = SecurityUtil.getLoginUser(request);
		
		BoardCmtVO param = new BoardCmtVO();
		param.setI_cmt(i_cmt);
		param.setI_user(loginUser.getI_user());
		
		BoardCmtDAO.delCmt(param);
		
		response.sendRedirect("/board/detail?i_board="+strI_board);
	}
	
	//댓글 (등록/수정)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strI_cmt = request.getParameter("i_cmt");
		String strI_board = request.getParameter("i_board");
		String cmt = request.getParameter("cmt");
		
		int i_board = SecurityUtil.parseStringToInt(strI_board);
		
		UserVO loginUser = SecurityUtil.getLoginUser(request);
		
		BoardCmtVO param = new BoardCmtVO();
		param.setCmt(cmt);
		param.setI_user(loginUser.getI_user());
		
		switch(strI_cmt) {
		case "0": //등록
			param.setI_board(i_board);
			BoardCmtDAO.insCmt(param);
			
			break;
		default: //수정 (수정 일자 변경)
			int i_cmt = SecurityUtil.parseStringToInt(strI_cmt);
			param.setI_cmt(i_cmt);
			
			BoardCmtDAO.updCmt(param);
			
			break;
		}
		
		response.sendRedirect("/board/detail?i_board="+strI_board);
	}

}
