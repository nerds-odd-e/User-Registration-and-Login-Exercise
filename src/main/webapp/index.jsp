<html>
<body>
<%
    String errorCode = request.getParameter("errorCode");
    if (errorCode == null) errorCode = "";
%>
<h2>Please Login</h2>
<form action="Login" method="post">
    <input type="text" name="username"/>
    <input type="password" name="password"/>
    <input type="submit" value="Login"/>
</form>
<div><%= errorCode %></div>
</body>
</html>
