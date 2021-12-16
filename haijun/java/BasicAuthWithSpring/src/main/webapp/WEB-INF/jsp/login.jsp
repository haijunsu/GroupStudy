<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<center>
<h1>Welcome to Spring Boot Security</h1>

<h2>Login Page</h2>
		<c:url value="/login" var="loginUrl"/>
		
		<form:form action="${loginUrl}" method="post">
			<label>Username:</label> <input type="text" name="username" value="navy"/>
			<label>Password:</label> <input type="text" name="password" value="password"/>
			<input type="submit"/>
		</form:form>
</center>
	