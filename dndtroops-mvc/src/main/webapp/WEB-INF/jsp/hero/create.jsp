<%-- 
    Document   : create
    Created on : 3.1.2018, 23:25:32
    Author     : Martin Šesták
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Create Hero">
<jsp:attribute name="body">

    <form:form method="post" action="${pageContext.request.contextPath}/hero/create" modelAttribute="data" cssClass="form-horizontal">
        <div class="form-group ${name_error?'has-error':''}">
            <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
            <div class="col-sm-10">
                <form:input path="name" cssClass="form-control"/>
                <form:errors path="name" cssClass="help-block"/>
            </div>
        </div>

        <div class="form-group ${troop_error?'has-error':''}">
            <form:label path="troop" cssClass="col-sm-2 control-label">Troop</form:label>
            <div class="col-sm-10">
                <form:select path="troop" cssClass="form-control">
                    <form:options items="${troops}" itemLabel="name" itemValue="id" />
                </form:select>
                <form:errors path="troop" cssClass="help-block"/>
            </div>
        </div>
            

        <form:label path="roles" cssClass="col-sm-2 control-label">Role</form:label>
        <div class="col-sm-10">
            <c:set var="count" value="0" scope="page" />
            <form:select multiple="true" path="roles" cssClass="form-control">
                <c:forEach items="${roles}" var="role">

                    <c:set var="contains" value="false" />
                    <c:forEach var="item" items="${data.roles}">
                      <c:if test="${item eq role}">
                        <c:set var="contains" value="true" />
                      </c:if>
                    </c:forEach>

                    <c:choose>
                        <c:when test = "${contains}">
                            <form:option value="${role.id}" selected="selected"><c:out value="${role.name}" /></form:option>
                        </c:when>
                        <c:otherwise>
                            <form:option value="${role.id}"><c:out value="${role.name}" /></form:option>
                        </c:otherwise>
                    </c:choose>

                    </c:forEach>
            </form:select>
        </div>
          

<div>
        <button class="btn btn-primary" type="submit">Create</button>
        </div>

    </form:form>

</jsp:attribute>
</my:pagetemplate>