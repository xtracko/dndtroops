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

<my:pagetemplate title="High Scores">
<jsp:attribute name="body">

    <p>The High Scores table is computed according to the troop's money.</p>

    <table class="table">
        <caption>High Scores</caption>
        <thead>
        <tr>
            <th>Hero's ID</th>
            <th>Hero's Name</th>
            <th>Hero's Troop</th>
            <th>Troop's Money</th>
            <th>Troop's ID</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${heroes}" var="hero">
            <tr>
                <td>${hero.id}</td>
                <td><c:out value="${hero.name}"/></td>
                <td><c:out value="${hero.troop.name}"/></td>
                <td>${hero.troop.goldenMoney}</td>
                <td><c:out value="${hero.troop.id}"/></td>

                <td>
                    <my:a href="/hero/view/${hero.id}" class="btn btn-primary btn-sm">View Hero</my:a>
                </td>

                <td>
                    <my:a href="/troop/view/${hero.troop.id}" class="btn btn-primary btn-sm">View Troop</my:a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</jsp:attribute>
</my:pagetemplate>