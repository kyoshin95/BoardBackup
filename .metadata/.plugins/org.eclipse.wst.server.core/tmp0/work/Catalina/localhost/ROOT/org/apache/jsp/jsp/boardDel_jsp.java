/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.37
 * Generated at: 2020-08-03 01:19:15 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;
import java.util.*;
import com.koreait.web.BoardVO;

public final class boardDel_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {


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

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("java.sql");
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("java.util");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("com.koreait.web.BoardVO");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("    \r\n");
      out.write("\r\n");
      out.write(" \r\n");
      out.write('\r');
      out.write('\n');
	
	String strI_board = request.getParameter("i_board"); // request는 패키지, getParameter는 메소드
	
	if(strI_board == null) // 사용자가 주소값으로 접근할때 막는 구문 or strI_board가 null값일때 처리하는 구문
	{

      out.write("\r\n");
      out.write("\t\t<script>\r\n");
      out.write("\t\t\talert('잘못된 접근입니다.');\r\n");
      out.write("\t\t\tlocation.href='/jsp/boardList.jsp';\r\n");
      out.write("\t\t</script>\r\n");

		return;
	}

      out.write('\r');
      out.write('\n');

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

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("\t<head>\r\n");
      out.write("\t<meta charset=\"UTF-8\">\r\n");
      out.write("\t\t<title></title>\r\n");
      out.write("\t</head>\r\n");
      out.write("\t<body>\r\n");
      out.write("\t\t<div>삭제되었습니다.</div>\r\n");
      out.write("\t</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
