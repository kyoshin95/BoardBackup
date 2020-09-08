<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.koreait.web.BoardVO" %> 
<%!
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
	BoardVO vo = new BoardVO();
%>
<%	
	String strI_board = request.getParameter("i_board"); // request는 패키지, getParameter는 메소드
	
	if(strI_board == null) // 사용자가 주소값으로 접근할때 막는 구문 or strI_board가 null값일때 처리하는 구문
	{
%>
		<script>
			alert('잘못된 접근입니다.');
			location.href='/jsp/boardList.jsp';
		</script>
<%
		return;
	}
%>
<%
	List<BoardVO> boardList = new ArrayList(); // BoardVO 클래스에서 값들고오기
	
	int i_board = Integer.parseInt(strI_board);
	
	// 순서 1
	
	Connection con = null; // db연결 할때 사용
	
	PreparedStatement ps = null; // sql문을 담당 / 완성
	int result = -1; // 밑의 Update문을 참고.
	
	String sql = " DELETE FROM t_board WHERE i_board = ? ";

	try 
	{
		// 순서 2
		con = getCon(); // 연결 위의 getCon메소드로 오라클에 연결
		ps = con.prepareStatement(sql); // 위의 sql문을 담아 ps변수에 복사
		ps.setInt(1, i_board); // 위의 sql문의 1번째 물음표에 3값을 넣겠다.
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
	System.out.println("result : " + result);
	switch(result)
	{
		case -1 : response.sendRedirect("/jsp/boardDetail.jsp?err=-1&i_board=" + i_board); break; // & 기준으로 getParameter가 err, i_board를 들고와준다.
		case 0 : response.sendRedirect("/jsp/boardDetail.jsp?err=0&i_board=" + i_board); break;
		case 1 : response.sendRedirect("/jsp/boardDetail.jsp"); break;
	}
%>

<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
		<title></title>
	</head>
	<body>
		<div>삭제되었습니다.</div>
	</body>
</html>