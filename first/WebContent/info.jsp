<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="fcm.koreait.first.*" %>
<%@ page import="java.sql.*" %>
<%@ page import = "java.util.*" %>
<%
Class.forName("oracle.jdbc.driver.OracleDriver");
String url = "jdbc:oracle:thin:@localhost:1521:orcl";
String userName = "hr";
String password = "koreait2020";
Connection con = null;
PreparedStatement ps =null;
ResultSet rs = null;

String sql = "SELECT * FROM countries";
List<CountriesVO> list = new ArrayList<>();
try{
	con = DriverManager.getConnection(url,userName,password);
	ps = con.prepareStatement(sql);
	rs = ps.executeQuery();
	String country_id = rs.getString("country_id");
	String country_name = rs.getString("country_name");
	int region_id = rs.getInt("region_id");
	while(rs.next()){
		CountriesVO vo = new CountriesVO();
		vo.getCountry_id();
		vo.getCountry_name();
		vo.getRegion_id();
		list.add(vo);
	}
}catch(Exception e){
	
}finally{
	try{
		rs.close();
		ps.close();
		con.close();
	}catch(Exception e){
		
	}
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>information</title>
</head>

<body>
	<div>Information</div>
	<div>나라정보</div>
	<div>
	<table>
		<tr>
		<th>country_id</th>
		<th>나라명</th>
		<th>지역ID</th>
		</tr>
		<% for(CountriesVO vo : list) { %>
		<tr>
			<td><%=vo.getCountry_id()%></td>
			<td><%=vo.getCountry_name() %></td>
			<td><%=vo.getRegion_id() %></td>
		</tr>
		<%} %>
	</table>
	</div>
</body>
</html>