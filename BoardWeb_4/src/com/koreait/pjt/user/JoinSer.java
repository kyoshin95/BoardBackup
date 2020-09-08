package com.koreait.pjt.user;
import com.koreait.pjt.*;
import com.koreait.pjt.db.UserDAO;
import com.koreait.pjt.vo.UserVO;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/join")
public class JoinSer extends HttpServlet 
{
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//화면에 보여줄 때 사용.
		ViewResolver.forward("join", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// 업무 처리할 때 사용.
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String encrypt_pw = SecurityUtil.encryptSHA256(user_pw);
		String nm = request.getParameter("nm");
		String email = request.getParameter("email");
		
		UserVO param = new UserVO();
		
		param.setUser_id(user_id);
		param.setUser_pw(encrypt_pw);
		param.setNm(nm);
		param.setEmail(email);
		
		int result = UserDAO.insUser(param);
		
		System.out.println("result : " + result);
		
		if(result != 1)
		{
			request.setAttribute("msg",	"에러가 발생했습니다. 관리자에게 문의하십시오.");
			request.setAttribute("data", param);
			
			doGet(request, response);
			return;
		}
		response.sendRedirect("/login");
	}

}
