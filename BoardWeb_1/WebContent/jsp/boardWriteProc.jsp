<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>

<%!
	// 메소드 안이 아니라 맴버필드
	private Connection getCon() throws Exception // 연결 메소드 getCon
	{
		String url = "jdbc:oracle:thin:@localhost:1521:orcl"; // DB정보
		String username = "hr";
		String password = "koreait2020";
		Class.forName("oracle.jdbc.driver.OracleDriver"); 
		
		Connection con = DriverManager.getConnection(url, username, password); // .getConnection메소드는 static메소드다. 
		// 클래스명의 첫글자는 대문자가 쓰임, !!! Dri... : 클래스, .get... 메소드 클래스
		
		System.out.println("DB에 접속되었습니다.");
		
		return con;
	}
%>
<%
	String title = request.getParameter("title");
	String cont = request.getParameter("cont");
	String strI_student = request.getParameter("i_student");
	
	if("".equals(title) || "".equals(cont) ||"".equals(strI_student))
	{
		response.sendRedirect("/jsp/boardWrite.jsp?err=10");
		return;
	}
	
	int i_student = Integer.parseInt(strI_student);
	
	Connection con = null; // db연결 할때 사용	
	PreparedStatement ps = null; // sql문을 담당 / 완성
	int result = -1;
	String sql = " insert into t_board(i_board, title, cont, i_student) " + " select nvl(max(i_board), 0) + 1, ?, ?, ? " + " from t_board ";
	
	try 
	{
		// 순서 2
		con = getCon(); // 연결 위의 getCon메소드로 오라클에 연결
		ps = con.prepareStatement(sql); // 위의 sql문을 담아 ps변수에 복사
		
		ps.setString(1, title); // 위의 sql문의 1번째 물음표에 title값을 넣겠다.
		ps.setString(2, cont); // 위의 sql문의 2번째 물음표에 cont값을 넣겠다.
		ps.setInt(3, i_student); // 위의 sql문의 3번째 물음표에 strI_student값을 넣겠다.
		
		result = ps.executeUpdate(); // !! Update문의 리턴반환형이 int문이라서 int문으로 rs를 선언
	} 
	catch(Exception e) 
	{
		e.printStackTrace();
	}
	finally
	{
		if(ps != null)
		{
			try {ps.close();}catch(Exception e){}
		}
		if(con != null)
		{
			try {con.close();}catch(Exception e){}
		}
	}
	
	int err = 0;
	switch(result)
	{
		case 1 : response.sendRedirect("/jsp/boardList.jsp"); return;
		case 0 : err = 10; break;
		case -1 : err = 20; break;
	}
	response.sendRedirect("/jsp/boardWrite.jsp?err=" + err);
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>추가</title>
		<style>
			table { margin: 0 auto; border-collapse : collapse; text-align : center; }
			label {  margin: 0 auto; text-align : center; }
			tr, td { height: 40px; width: 100px; border: 1px solid gray; }
		</style>
	</head>
	<body>
		<label>성공적으로 추가되었습니다.</label>
		<table>
			<tr>
				<td>제목</td>
				<td><%=title %></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><%=cont %></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><%=strI_student %></td>
			</tr>
		</table>
	</body>
</html>