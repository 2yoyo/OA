/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2019-05-07 02:45:21 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.application;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

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
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<base href=\"");
      out.print(request.getContextPath() + "/");
      out.write("\" />\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<link href=\"css/H-ui.min.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("<link href=\"css/H-ui.login.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("<link href=\"css/style.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("<link href=\"lib/Hui-iconfont/1.0.1/iconfont.css\" rel=\"stylesheet\"\r\n");
      out.write("\ttype=\"text/css\" />\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<input type=\"hidden\" id=\"TenantId\" name=\"TenantId\" value=\"\" />\r\n");
      out.write("\t<div class=\"header\"></div>\r\n");
      out.write("\t<div class=\"loginWraper\">\r\n");
      out.write("\t\t<div id=\"loginform\" class=\"loginBox\">\r\n");
      out.write("\t\t\t<form class=\"form form-horizontal\" action=\"login\" method=\"post\">\r\n");
      out.write("\t\t\t\t<div class=\"row cl\">\r\n");
      out.write("\t\t\t\t\t<label class=\"form-label col-3\"><i class=\"Hui-iconfont\">&#xe60d;</i></label>\r\n");
      out.write("\t\t\t\t\t<div class=\"formControls col-8\">\r\n");
      out.write("\t\t\t\t\t\t<input id=\"\" name=\"username\" type=\"text\" placeholder=\"账户\"\r\n");
      out.write("\t\t\t\t\t\t\tclass=\"input-text size-L\">\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"row cl\">\r\n");
      out.write("\t\t\t\t\t<label class=\"form-label col-3\"><i class=\"Hui-iconfont\">&#xe60e;</i></label>\r\n");
      out.write("\t\t\t\t\t<div class=\"formControls col-8\">\r\n");
      out.write("\t\t\t\t\t\t<input id=\"\" name=\"password\" type=\"password\" placeholder=\"密码\"\r\n");
      out.write("\t\t\t\t\t\t\tclass=\"input-text size-L\">\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"row cl\">\r\n");
      out.write("\t\t\t\t\t<div class=\"formControls col-8 col-offset-3\">\r\n");
      out.write("\t\t\t\t\t\t<input class=\"input-text size-L\" type=\"text\" placeholder=\"验证码\"\r\n");
      out.write("\t\t\t\t\t\t\tonblur=\"if(this.value==''){this.value='验证码:'}\"\r\n");
      out.write("\t\t\t\t\t\t\tonclick=\"if(this.value=='验证码:'){this.value='';}\" value=\"验证码:\"\r\n");
      out.write("\t\t\t\t\t\t\tstyle=\"width: 150px;\"> <img\r\n");
      out.write("\t\t\t\t\t\t\tsrc=\"images/VerifyCode.aspx.png\"> <a id=\"kanbuq\"\r\n");
      out.write("\t\t\t\t\t\t\thref=\"javascript:;\">看不清，换一张</a>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"row\">\r\n");
      out.write("\t\t\t\t\t<div class=\"formControls col-8 col-offset-3\">\r\n");
      out.write("\t\t\t\t\t\t<label for=\"online\"> <input type=\"checkbox\" name=\"online\"\r\n");
      out.write("\t\t\t\t\t\t\tid=\"online\" value=\"\"> 使我保持登录状态\r\n");
      out.write("\t\t\t\t\t\t</label>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"row\">\r\n");
      out.write("\t\t\t\t\t<div class=\"formControls col-8 col-offset-3\">\r\n");
      out.write("\t\t\t\t\t\t<input name=\"\" type=\"submit\" class=\"btn btn-success radius size-L\"\r\n");
      out.write("\t\t\t\t\t\t\tvalue=\"&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;\"> <input\r\n");
      out.write("\t\t\t\t\t\t\tname=\"\" type=\"reset\" class=\"btn btn-default radius size-L\"\r\n");
      out.write("\t\t\t\t\t\t\tvalue=\"&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;\">\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div class=\"footer\">Copyright 你的公司名称 by H-ui.admin.v2.3</div>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"lib/jquery/1.9.1/jquery.min.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/H-ui.js\"></script>\r\n");
      out.write("\t<script>\r\n");
      out.write("\t\tvar _hmt = _hmt || [];\r\n");
      out.write("\t\t(function() {\r\n");
      out.write("\t\t\tvar hm = document.createElement(\"script\");\r\n");
      out.write("\t\t\thm.src = \"//hm.baidu.com/hm.js?080836300300be57b7f34f4b3e97d911\";\r\n");
      out.write("\t\t\tvar s = document.getElementsByTagName(\"script\")[0];\r\n");
      out.write("\t\t\ts.parentNode.insertBefore(hm, s);\r\n");
      out.write("\t\t})();\r\n");
      out.write("\t\tvar _bdhmProtocol = ((\"https:\" == document.location.protocol) ? \" https://\"\r\n");
      out.write("\t\t\t\t: \" http://\");\r\n");
      out.write("\t\tdocument\r\n");
      out.write("\t\t\t\t.write(unescape(\"%3Cscript src='\"\r\n");
      out.write("\t\t\t\t\t\t+ _bdhmProtocol\r\n");
      out.write("\t\t\t\t\t\t+ \"hm.baidu.com/h.js%3F080836300300be57b7f34f4b3e97d911' type='text/javascript'%3E%3C/script%3E\"));\r\n");
      out.write("\t</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
