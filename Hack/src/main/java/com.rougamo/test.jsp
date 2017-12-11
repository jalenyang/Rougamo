<%@ page import="java.lang.*" %>
<%@ page import="java.io.*" %>
<%@ page import="com.rougamo.*"%>
<%
	InputStream is = new FileInputStream("/opt/WebSphere/AppServer/profiles/clab570node04/installedApps/SOL/AP_Adaptation_Mgr_UI-ear.ear/adaptation-manager-ui.war/WEB-INF/classes/com/rougamo/TestClass.class");
	byte[] b = new byte[is.available()];
	is.read(b);
	is.close();

	out.println("<textarea style='width:1000;height=800'>");
	out.println(JavaClassExecuter.execute(b));
	out.println("</textarea>");
%>


