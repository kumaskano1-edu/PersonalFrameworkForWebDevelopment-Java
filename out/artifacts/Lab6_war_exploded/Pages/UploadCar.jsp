<%--
  Created by IntelliJ IDEA.
  User: Kuma
  Date: 6/15/2020
  Time: 8:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload Car</title>
</head>
<body>
<h1>Upload Car</h1>
    <div class="form">
        <form action="UploadCar" method="post" enctype="multipart/form-data">
            <input type="file" id="myFile" name="filename">
            <input type="text" name="txtPath" />
            <input type="submit" value="Upload">
        </form>
    </div>

<a href="/Lab6_war_exploded/">Back Home</a>

</body>
</html>
