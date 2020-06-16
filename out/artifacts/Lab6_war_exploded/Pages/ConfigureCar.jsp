<%--
  Created by IntelliJ IDEA.
  User: Kuma
  Date: 6/15/2020
  Time: 8:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Configure Car</title>
</head>
<body>
<h1>Configure Car</h1>
<%
    String fooParameter = request.getParameter( "isUploaded" );
    if ( fooParameter.equals("false") )
    {
%>
<%
}
else
{
%>
<p>The value of parameter foo is <%= fooParameter.toString() %>.</p>
<%
    }
%>

<a href="/Lab6_war_exploded/">Back Home</a>
</body>
</html>
