<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.koreait.web.BoardVO" %> 
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

	List<BoardVO> boardList = new ArrayList(); // BoardVO 클래스에서 값들고오기
	Connection con = null; // db연결 할때 사용
	PreparedStatement ps = null; // sql문을 담당 / 완성
	ResultSet rs = null; // DB에 접속
	
	String sql = " SELECT * FROM t_board order by i_board "; // !!! SELECT문때만 " ; " 사용 Why??? SQL Injection Attack.

	try 
	{
		con = getCon(); // 연결 위의 getCon메소드로 오라클에 연결
		ps = con.prepareStatement(sql); // 위의 sql문을 담아 ps변수에 복사
		rs = ps.executeQuery(); // sql문을 오라클데이터베이스에 접속
		
		while(rs.next()) // 1행씩 true, false값 확인
		{
			int stdId = rs.getInt("i_board"); // 각 값들 들고와서 각 변수에 담아줌
			String stdnm = rs.getNString("title"); // N 이있으나 없으나 상관없음. 하지만 N을 붙히는게 최신방식
			
			BoardVO vo = new BoardVO(); // vo 객체 생성 굉장히 중요. 반복문 밖에있으면 마지막 값만 나옴(마지막 주소값만 들고있기때문에)
			vo.setStudent_id(stdId); // 각 값을 넣기
			vo.setStudent_name(stdnm);
			
			boardList.add(vo); // list에 추가
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
<!DOCTYPE html> <!--  위의 JSP문법 대로 List 값들을 Table형태로 출력 -->
<html>
	<head>
		<meta charset="UTF-8">
		<title>게시판</title>
		<style>
			table { width: 400px; border-collapse: collapse; text-align: center;}
			table, th, tr, td { height: 30px; border : 1px solid black;|}}}}
		</style>
	</head>
	<body>
		<div>
			게시판 리스트
			<a href="/jsp/boardWrite.jsp"><button>글쓰기</button></a>
		</div>
		<table>
			<tr>
				<th>No</th>
				<th>이름</th>
			</tr>
			<% for(BoardVO vo : boardList) {%>
			<tr>
				<td><%=vo.getStudent_id()%></td>
				<td>
					<a href="/jsp/boardDetail.jsp?i_board=<%=vo.getStudent_id()%>"> <!-- ? 키값 = value값 & 더보낼 키값, value값 -->
						<%=vo.getStudent_name()%>
					</a>
				</td>
			</tr>
			<% } %>
		</table>
	</body>
</html>