package com.koreait.pjt.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.pjt.Const;
import com.koreait.pjt.SecurityUtil;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.UserDAO;
import com.koreait.pjt.vo.UserLoginHistory;
import com.koreait.pjt.vo.UserVO;


@WebServlet("/login")
public class LoginSer extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		if(!SecurityUtil.isLogout(request))
		{
			response.sendRedirect("/board/list");
			return;
		}
		ViewResolver.forward("/login", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String encrypt_pw = SecurityUtil.encryptSHA256(user_pw);
		 
		UserVO param = new UserVO();
		param.setUser_id(user_id);
		param.setUser_pw(encrypt_pw);
		
		int result = UserDAO.selUser(param);
		System.out.println(result);
		
		if(result != 1) 
		{	
			String msg = null;
		
			switch(result)
			{
				case 2: msg = "비밀번호를 확인해주세요."; break;
				case 3: msg = "아이디를 확인해주세요."; break;
				default : msg = "에러가 발생했습니다.";
			}
			request.setAttribute("user_id", user_id);
			request.setAttribute("msg", msg);
			
			doGet(request, response);
			return;
		}

		String agent = request.getHeader("User-Agent");
		System.out.println("agent : " + agent);
		String os = getOs(agent);
		String browser = getBrowser(agent);
		String ip_addr = request.getRemoteAddr();
		
		System.out.println("os : " + os);
		System.out.println("ip : " + ip_addr);
		System.out.println("browser : " + browser);
		
		UserLoginHistory ulhVO = new UserLoginHistory();
		ulhVO.setI_user(param.getI_user());
		ulhVO.setOs(os);
		ulhVO.setIp_addr(ip_addr);
		ulhVO.setBrowser(browser);
		
		HttpSession hs = request.getSession();
		hs.setAttribute(Const.LOGIN_USER, param);

		System.out.println("LOGIN");	
		
		response.sendRedirect("/board/list");
	}
	
	
	private String getBrowser(String agent) 
	{
		// TODO Auto-generated method stub
	
		if(agent.toLowerCase().contains("msie"))
		{
			return "ie";
		}
		else if(agent.toLowerCase().contains("chrome"))
		{
			return "Chrome";
		}
		else if(agent.toLowerCase().contains("safari"))
		{
			return "safari";
		}
		return "";
	}

	private String getOs(String agent)
	{
		String result = null;
		
		if(agent.toLowerCase().contains("mac"))
		{
			return "Mac";
		}
		else if(agent.toLowerCase().contains("windows"))
		{
			return "Windows";
		}
		else if(agent.toLowerCase().contains("x11"))
		{
			return "Unix";
		}
		else if(agent.toLowerCase().contains("android"))
		{
			return "Android";
		}
		else if(agent.toLowerCase().contains("iphone"))
		{
			return "IOS";
		}
		else if(agent.contains("linux"))
		{
			return "Linux";
		}
		return result;
	}
}
