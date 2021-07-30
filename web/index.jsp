
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
</head>
<body>
<div style="text-align: center ">
    <form action="${pageContext.request.contextPath}/login" method="post">
        <div style="text-align: center "><h3> email:<label>
            <input type="text" name="email" placeholder ="input your email">
        </label></h3><br></div>
        <div style="text-align: center "><h3> password: <label>
            <input type="password" name="password" placeholder="input your password">
        </label></h3><br></div>
        <div style="text-align: center "><h1><input type="submit" value="Login"></h1><br></div>
    </form>
</div>
</body>
</html>
