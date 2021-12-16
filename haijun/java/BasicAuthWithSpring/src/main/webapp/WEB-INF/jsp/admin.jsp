<center>
<h1>Welcome to Spring Boot Simple Security Example</h1>
<form action="/logout" method=post>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="submit" value="logout">
</form>
</center>
