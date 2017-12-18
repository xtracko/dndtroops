<%--
  @author: Jiří Novotný
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Role ${role.name}">
<jsp:attribute name="body">

    <table class="table">
        <caption>Role</caption>
        <tr>
            <th>ID</th>
            <td>${role.id}</td>
        </tr>
        <tr>
            <th>Name</th>
            <td><c:out value="${role.name}"/></td>
        </tr>
        <tr>
            <th>Description</th>
            <td><c:out value="${role.description}"/></td>
        </tr>
        <tr>
            <th>Power</th>
            <td>${role.power}</td>
        </tr>
        <tr>
            <th>Damage Mean</th>
            <td>${role.damageMean}</td>
        </tr>
        <tr>
            <th>Damage Variance</th>
            <td>${role.damageVariance}</td>
        </tr>
        </thead>
    </table>

    <my:admin>
    <div class="row">
        <div class="col-md-1">
            <my:a href="/role/edit/${role.id}" class="btn btn-primary btn-sm">Edit</my:a>
        </div>
        <div class="col-md-1">
            <form method="post" action="${pageContext.request.contextPath}/role/delete/${role.id}">
                <button type="submit" class="btn btn-primary">Delete</button>
            </form>
        </div>
    </div>
    </my:admin>

</jsp:attribute>
</my:pagetemplate>
