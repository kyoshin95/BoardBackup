<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.koreait.web.SubVO" %> 
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

	SubVO so = new SubVO(); // vo 객체 생성 굉장히 중요. 반복문 밖에있으면 마지막 값만 나옴(마지막 주소값만 들고있기때문에)
%>
<%	
	String strI_board = request.getParameter("i_board"); // request는 패키지, getParameter는 메소드
	
	if(strI_board == null)
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
	List<SubVO> boardList = new ArrayList(); // BoardVO 클래스에서 값들고오기
	
	Connection con = null; // db연결 할때 사용
	PreparedStatement ps = null; // sql문을 담당 / 완성
	ResultSet rs = null; // DB에 접속
	
	int i_board = Integer.parseInt(strI_board);
	String sql = " SELECT title, cont, i_student FROM t_board WHERE i_board = ? ";

	try 
	{
		con = getCon(); // 연결 위의 getCon메소드로 오라클에 연결
		ps = con.prepareStatement(sql); // 위의 sql문을 담아 ps변수에 복사
		ps.setInt(1, i_board); // 위의 sql문의 1번째 물음표에 i_board값을 넣겠다.
		// ps.setString(1, strI_board);
		rs = ps.executeQuery(); // sql문을 오라클데이터베이스에 접속

		if(rs.next())
		{
			String title = rs.getNString("title"); // 각 값들 들고와서 각 변수에 담아줌
			String content = rs.getNString("cont"); // N 이있으나 없으나 상관없음. 하지만 N을 붙히는게 최신방식
			int i_student = rs.getInt("i_student");
			
			so.setTitle(title); // 각 값을 넣기
			so.setCont(content);
			so.setI_student(i_student);
			
			boardList.add(so);
		}
	} 
	catch(Exception e) 
	{
		e.printStackTrace();
	}
	finally
	{
		// 스택처럼 닫아줘야함
		// 위에서는 con->ps->rs로 선언했지만 rs->ps->con순으로 닫아줘야함.
		if(rs != null)
		{
			try {rs.close();}catch(Exception e){}
		}
		if(ps != null)
		{
			try {ps.close();}catch(Exception e){}
		}
		if(con != null)
		{
			try {con.close();}catch(Exception e){}
		}
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>상세 페이지</title>
	</head>
	<body>
		<div>상세 페이지 : <%= strI_board %></div>
		<a href="#" onclick="procDel(<%=i_board%>)">삭제</a>
		<a href="/jsp/boardMod.jsp?i_board=<%=i_board%>">수정</a>
		<div>	
			<h1>제목 : <%= so.getTitle() %></h1>
			<h1>내용 : <%= so.getCont() %></h1>
			<h1>작성자번호 : <%= so.getI_student() %></h1>
		</div>
		<script>
			function procDel(i_board) {
				if(confirm('삭제하시겠습니까?')) {
					location.href = '/jsp/boardDel.jsp?i_board=' + i_board;	
				}
			}
		</script>
	</body>
</html>