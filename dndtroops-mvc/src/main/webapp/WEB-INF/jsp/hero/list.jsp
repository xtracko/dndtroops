<%-- 
    Author     : Martin Sestak
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Heroes">
<jsp:attribute name="body">

    <c:if test="${authenticatedUser.isAdmin}">
        <my:a href="/hero/create" class="btn btn-primary">Create New Hero</my:a>
    </c:if>

    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Troop</th>
            <th>XP</th>
            <th>Health</th>
            <th>Role</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${heroes}" var="hero">
            <tr>
                <td>${hero.id}</td>
                <td><c:out value="${hero.name}"/></td>
                <td><c:out value="${hero.troop.name}"/></td>
                <td>${hero.xp}</td>
                <td>${hero.health}</td>
                <td>
                    <c:forEach items="${hero.roles}" var="role">
                        <c:out value="${role.name}" />
                        <c:out value=", " />
                    </c:forEach>
                </td>  

                <c:if test="${authenticatedUser.isAdmin}">
                    <td>
                        <my:a href="/hero/edit/${hero.id}" class="btn btn-primary btn-sm">Edit</my:a>
                    </td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/hero/delete/${hero.id}">
                            <button type="submit" class="btn btn-primary btn-sm">Delete</button>
                        </form>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</jsp:attribute>
</my:pagetemplate>
