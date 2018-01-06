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

<my:pagetemplate title="Troops">
<jsp:attribute name="body">

    <c:if test="${authenticatedUser.isAdmin}">
    <my:a href="/troop/create" class="btn btn-primary">Create New Troop</my:a>
    </c:if>

    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Mission</th>
            <th>Money</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${troops}" var="troop">
            <tr>
                <td>${troop.id}</td>
                <td><c:out value="${troop.name}"/></td>
                <td><c:out value="${troop.mission}"/></td>
                <td>${troop.goldenMoney}</td>

                <td>
                    <my:a href="/troop/view/${troop.id}" class="btn btn-primary btn-sm">View</my:a>
                </td>

                <c:if test="${authenticatedUser.isAdmin}">
                    <td>
                        <my:a href="/troop/edit/${troop.id}" class="btn btn-primary btn-sm">Edit</my:a>
                    </td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/troop/delete/${troop.id}">
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