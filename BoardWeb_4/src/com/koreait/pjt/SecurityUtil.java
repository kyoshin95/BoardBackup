package com.koreait.pjt;

import java.io.IOException;
import java.security.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.pjt.vo.UserVO;

public class SecurityUtil 
{
	public static int getIntParameter(HttpServletRequest request, String keyNm) 
	{
		return parseStringToInt(request.getParameter(keyNm));
	}
	
	public static int parseStringToInt(String strI_board, int i) // 정수 판별식과 정수 변경 
	{
		try
		{
			int i_board = Integer.parseInt(strI_board); // 정수로 바꿔서 에러가 안뜨면
			return i_board; // 정수로 리턴
		}
		catch(Exception e)
		{
			e.printStackTrace(); // 오류가 먼지 보고 싶을때.
			return i;
		}
		
	}
	
	public static UserVO getLoginUser(HttpServletRequest request) 
	{
		HttpSession hs = request.getSession();
		return (UserVO)hs.getAttribute(Const.LOGIN_USER);
	}
	
	public static int parseStringToInt(String str) // 정수 변경
	{
		//int i_board = Integer.parseInt(str); // 정수로 변경
		return parseStringToInt(str,0);//i_board; // 정수로 리턴
	}

	
	public static boolean isLogout(HttpServletRequest request) throws ServletException, IOException 
	{
		HttpSession hs = request.getSession();
		
		if(null == hs.getAttribute(Const.LOGIN_USER))
		{
			return true;
		
		}
		return false;
	}

    public static String encryptSHA256(String str)
    {

    	String sha = ""; 

    	try
    	{
    		MessageDigest sh = MessageDigest.getInstance("SHA-256"); 
    		sh.update(str.getBytes()); 
    		byte byteData[] = sh.digest();
    		StringBuffer sb = new StringBuffer(); 
    		for(int i = 0 ; i < byteData.length ; i++){
    			sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
    		}

    		sha = sb.toString();

    	}
    	catch(NoSuchAlgorithmException e)
    	{
    		//e.printStackTrace(); 
    		System.out.println("Encrypt Error - NoSuchAlgorithmException");
    		sha = null; 
    	}

    	return sha;
    }	
	
	/**
     * byte[] ret = HashUtil.digest("MD5", "abcd".getBytes());
     *  처럼 호출
     */
    public String encryptMD5(String str)
    { 
    	//보안 문제점에 발견되어 현재 사용하지 않는 방식이다.
    	String md5 = ""; 
    
    	try
    	{
    		MessageDigest md = MessageDigest.getInstance("MD5"); 
    		md.update(str.getBytes()); 
    		byte byteData[] = md.digest();
    		StringBuffer sb = new StringBuffer(); 
    		for(int i = 0 ; i < byteData.length ; i++)
    		{
    			sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
    		}

    		md5 = sb.toString();
    		
    	}
    	catch(NoSuchAlgorithmException e)
    	{
    		//e.printStackTrace();
    		System.out.println("Encrypt Error - NoSuchAlgorithmException");
    		md5 = null; 
    	}
    	return md5;
    }
}
