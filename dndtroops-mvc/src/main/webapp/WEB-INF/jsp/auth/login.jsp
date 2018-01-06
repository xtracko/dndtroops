<%-- 
    Document   : login
    Created on : 5.1.2018, 20:06:16
    Author     : Martin Sestak
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<my:pagetemplate title="Login">
    <jsp:attribute name="body">
    
        <form method="POST" action="${pageContext.request.contextPath}/auth/login">
        <label for="username">Username</label>
        <input id="username" type="text" name="username" placeholder="User" required class="form-control"/>

        <label for="password">Password</label>
        <input id="password" type="password" name="password" placeholder="Password" required class="form-control"/>

        <button class="btn btn-lg btn-primary" type="submit"> Login</button>
        <br/>
    </form>
        
    </jsp:attribute>
</my:pagetemplate>
