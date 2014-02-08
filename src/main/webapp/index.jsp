<html>
<body>
<%
    String result = request.getParameter("result");
    if (result == null) result = "";
%>
<h2>Please Login</h2>
<form action="Login" method="post">
    <input type="text" name="username"/>
    <input type="password" name="password"/>
    <input type="submit" value="Login"/>
</form>
<div><%= result %></div>
</body>
</html>
