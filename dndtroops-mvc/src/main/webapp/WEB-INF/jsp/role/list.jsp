<%--
  @author: Jiří Novotný
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Roles">
<jsp:attribute name="body">

    <my:admin>
        <my:a href="/role/create" class="btn btn-primary">Create New Role</my:a>
    </my:admin>

    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Power</th>
            <th>Damage Mean</th>
            <th>Damage Variance</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${roles}" var="role">
            <tr>
                <td>${role.id}</td>
                <td><c:out value="${role.name}"/></td>
                <td><c:out value="${role.description}"/></td>
                <td>${role.power}</td>
                <td>${role.damageMean}</td>
                <td>${role.damageVariance}</td>

                <my:admin>
                    <td>
                        <my:a href="/role/edit/${role.id}" class="btn btn-primary btn-sm">Edit</my:a>
                    </td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/role/delete/${role.id}">
                            <button type="submit" class="btn btn-primary btn-sm">Delete</button>
                        </form>
                    </td>
                </my:admin>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</jsp:attribute>
</my:pagetemplate>
