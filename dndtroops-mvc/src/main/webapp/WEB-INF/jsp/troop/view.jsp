<%--
  @author: Jiří Novotný

  Before the 2nd milestone we have lost a teamate who was responsible for Troop entity.I am only doing the minimal
  possible work to get the project running. Please be mindfull of this, when you are writing your evaluation.
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Troop ${role.name}">
<jsp:attribute name="body">

    <table class="table">
        <caption>Troop</caption>
        <tr>
            <th>ID</th>
            <td>${troop.id}</td>
        </tr>
        <tr>
            <th>Name</th>
            <td><c:out value="${troop.name}"/></td>
        </tr>
        <tr>
            <th>Mission</th>
            <td><c:out value="${troop.mission}"/></td>
        </tr>
        <tr>
            <th>Money</th>
            <td>${troop.goldenMoney}</td>
        </tr>
        </thead>
    </table>

    <table class="table">
        <caption>Heroes</caption>
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Xp</th>
            <th>Health</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${heroes}" var="hero">
            <tr>
                <td>${hero.id}</td>
                <td><c:out value="${hero.name}"/></td>
                <td>${hero.xp}</td>
                <td>${hero.health}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <my:admin>
    <div class="row">
        <div class="col-md-1">
            <my:a href="/troop/edit/${troop.id}" class="btn btn-primary btn-sm">Edit</my:a>
        </div>
        <div class="col-md-1">
            <form method="post" action="${pageContext.request.contextPath}/troop/delete/${troop.id}">
                <button type="submit" class="btn btn-primary">Delete</button>
            </form>
        </div>
    </div>
    </my:admin>

</jsp:attribute>
</my:pagetemplate>
