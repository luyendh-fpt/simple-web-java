<%@ page import="com.t1708m.entity.Student" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: xuanhung
  Date: 2019-05-28
  Time: 10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Student student = (Student) request.getAttribute("student");
%>
<html>
<head>
    <title>Student Detail</title>
</head>
<body>
    <h1>Student Detail</h1>
    <div>
        Username : <%= student.getUsername()%>
    </div>
    <div>
        Email: <%=student.getEmail()%>
    </div>
</body>
</html>
