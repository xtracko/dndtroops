<%--
  @author: Jiří Novotný
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Battle">
<jsp:attribute name="body">

    <form:form method="post" action="${pageContext.request.contextPath}/admin/battle" modelAttribute="battle" cssClass="form-horizontal">

        <div class="form-group center-block">
            <div class="row">
                <form:select path="troop1" cssClass="form-control input-lg text-center" disabled="${notroops}">
                    <form:options items="${troops}" itemLabel="name" itemValue="id" />
                </form:select>
            </div>

            <div class="row">
                <h1 class="text-center">vs.</h1>
            </div>

            <div class="row">
                <form:select path="troop2" cssClass="form-control input-lg text-center" disabled="${notroops}">
                    <form:options items="${troops}" itemLabel="name" itemValue="id" />
                </form:select>
            </div>
        </div>

        <div class="row">
            <c:choose>
                <c:when test="${notroops}">
                    <button class="btn btn-primary btn-lg btn-success center-block" type="submit" disabled>Battle</button>
                </c:when>
                <c:otherwise>
                    <button class="btn btn-primary btn-lg btn-success center-block" type="submit">Battle</button>
                </c:otherwise>
            </c:choose>
        </div>
    </form:form>

</jsp:attribute>
</my:pagetemplate>