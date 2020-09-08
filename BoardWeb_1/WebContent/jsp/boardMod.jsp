<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.koreait.web.SubVO" %> 
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

	SubVO so = new SubVO(); // vo 객체 생성 굉장히 중요. 반복문 밖에있으면 마지막 값만 나옴(마지막 주소값만 들고있기때문에)
%>
<%
	String strI_board = request.getParameter("i_board");
	int i_board = Integer.parseInt(strI_board);
	List<SubVO> boardList = new ArrayList(); // BoardVO 클래스에서 값들고오기
	
	Connection con = null; // db연결 할때 사용
	PreparedStatement ps = null; // sql문을 담당 / 완성
	ResultSet rs = null; // DB에 접속
	
	String sql = " SELECT title, cont, i_student FROM t_board WHERE i_board = ? ";

	try 
	{
		con = getCon(); // 연결 위의 getCon메소드로 오라클에 연결
		ps = con.prepareStatement(sql); // 위의 sql문을 담아 ps변수에 복사
		ps.setInt(1, i_board); // 위의 sql문의 1번째 물음표에 i_board값을 넣겠다.
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
		<title>수정</title>
		<style> /* css는 밑에 적지 않는다. 동작은 하지만 번쩍임 현상이 일어남. */
			#msg { color : red; }
		</style>
	</head>
	<body>
		
		<div>
			<form id="frm" action="/jsp/boardModProc.jsp?i_board=<%=i_board %>" method="post" onsubmit="return chk()"> <!-- post : url에 아무것도 나타나지 않지만, get : 각 요소들과 값이 나온다. -->
				<div><label>제목: <input type="text" name="title"></label></div> <!-- name은 key값 -->
				<div><label>내용: <textarea name="cont"></textarea></label></div>
				<div><input type="submit" value="수정"></div>
			</form>
		</div>
		<script> // script를 body 밑에 적는 이유 : 위의 div, form등 elements 들을 사용하기 때문이다. 하지만 선언은 head안에.
			function eleValid(ele, nm)
			{
				if(ele.value.length == 0)
				{
					alert(nm + "을(를) 입력해 주세요.");
					ele.focus();
					return true;
				}
			}
			
			function chk()
			{
				console.log(`title : \${frm.title.value}`); // console 내용 표시
			
				if(eleValid(frm.title, '제목')) // frm의 자식 title이라는 name을 가진 값이 없다면.
				{
					return false;
				}
				else if(eleValid(frm.cont, '내용'))
				{
					return false;
				}
			}
		</script>
	</body>
</html>