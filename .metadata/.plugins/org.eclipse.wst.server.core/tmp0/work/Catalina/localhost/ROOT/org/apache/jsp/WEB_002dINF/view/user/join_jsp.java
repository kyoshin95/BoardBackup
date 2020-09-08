/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.37
 * Generated at: 2020-09-08 07:32:11 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.view.user;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class join_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
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
      out.write("<div id=\"sectionContainerCenter\">\r\n");
      out.write("\t<form id=\"frm\" class=\"frm\" action=\"/user/joinProc\" method=\"post\" onsubmit=\"return joinCheck()\">\r\n");
      out.write("\t\t<div id=\"idCheckResult\" class=\"msg\"></div>\r\n");
      out.write("\t\t<div><input type=\"text\" name=\"user_id\" placeholder=\"아이디\"><button  type=\"button\" onclick=\"idCheck()\">중복확인</button></div>\r\n");
      out.write("\t\t<div><input type=\"password\" name=\"user_pw\" placeholder=\"비밀번호\"></div>\r\n");
      out.write("\t\t<div><input type=\"password\" name=\"user_pwre\" placeholder=\"비밀번호 확인\"></div>\r\n");
      out.write("\t\t<div><input type=\"text\" name=\"nm\" placeholder=\"이름\"></div>\r\n");
      out.write("\t\t<div><input type=\"submit\" value=\"회원가입\"></div>\r\n");
      out.write("\t</form>\r\n");
      out.write("\t<div>\r\n");
      out.write("\t\t<a href=\"/user/login\">로그인</a>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<script src=\"https://unpkg.com/axios/dist/axios.min.js\"></script> ");
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("function idCheck() \r\n");
      out.write("{\r\n");
      out.write("\tconst user_id = frm.user_id.value\r\n");
      out.write("\taxios.get('/user/ajaxIdCheck', {\r\n");
      out.write("\t\tparams: {\r\n");
      out.write("\t\t\tuser_id\r\n");
      out.write("\t\t} \r\n");
      out.write("\t}).then(function(res) {\r\n");
      out.write("\t\tconsole.log(res)\r\n");
      out.write("\t\tif(res.data.result == 2) { //아이디 없음\r\n");
      out.write("\t\t\tidCheckResult.innerText = '사용할 수 있는 아이디입니다.'\r\n");
      out.write("\t\t} else if(res.data.result == 3) { //아이디 중복됨\r\n");
      out.write("\t\t\tidCheckResult.innerText = '이미 사용중입니다.'\r\n");
      out.write("\t\t}\r\n");
      out.write("\t})\r\n");
      out.write("}\r\n");
      out.write("\t\r\n");
      out.write("\tfunction joinCheck()\r\n");
      out.write("\t{\t\r\n");
      out.write("\t\tif(frm.user_id.value.length < 8) // id 검증(글자수)\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t\talert('아이디는 8글자 이상이 되어야 합니다.')\r\n");
      out.write("\t\t\tfrm.user_id.focus()\r\n");
      out.write("\t\t\treturn false\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\telse if(frm.user_pw.value.length < 8) // pw 검증(글자수)\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\r\n");
      out.write("\t\t\talert('비밀번호는 8글자 이상이 되어야 합니다.')\r\n");
      out.write("\t\t\tfrm.user_pw.focus()\r\n");
      out.write("\t\t\treturn false\t\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\telse if(frm.user_pw.value != frm.user_pwre.value) // pw 검증(확인)\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t\talert('비밀번호를 확인해 주세요.')\r\n");
      out.write("\t\t\tfrm.user_pw.focus()\r\n");
      out.write("\t\t\treturn false\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tif(frm.nm.value.length > 0) // 이름 검증\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t\tconst korean = /[^가-힣]/\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tif(korean.test(frm.nm.value))\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\talert('이름은 한글만 입력해 주세요.')\r\n");
      out.write("\t\t\t\tfrm.nm.focus()\r\n");
      out.write("\t\t\t\treturn false\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("</script>");
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
