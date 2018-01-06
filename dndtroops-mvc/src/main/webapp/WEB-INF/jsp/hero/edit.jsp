<%-- 
    Document   : edit
    Created on : 3.1.2018, 23:26:08
    Author     : Martin Sestak
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Edit Hero">
<jsp:attribute name="body">

    <form:form method="post" action="${pageContext.request.contextPath}/hero/edit/${hero.id}" modelAttribute="hero" cssClass="form-horizontal">
        <div class="form-group ${name_error?'has-error':''}">
            <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
            <div class="col-sm-10">
                <form:input path="name" cssClass="form-control"/>
                <form:errors path="name" cssClass="help-block"/>
            </div>
        </div>
            
        <div class="form-group ${health_error?'has-error':''}">
            <form:label path="health" cssClass="col-sm-2 control-label">Health</form:label>
            <div class="col-sm-10">
                <form:input path="health" cssClass="form-control"/>
                <form:errors path="health" cssClass="help-block"/>
            </div>
        </div> 
        
        <div class="form-group ${xp_error?'has-error':''}">
            <form:label path="xp" cssClass="col-sm-2 control-label">XP</form:label>
            <div class="col-sm-10">
                <form:input path="xp" cssClass="form-control"/>
                <form:errors path="xp" cssClass="help-block"/>
            </div>
        </div>      

        <form:label path="troopId" cssClass="col-sm-2 control-label">Troop</form:label>
        <div class="col-sm-10">
            <form:select path="troopId" cssClass="form-control">
                <c:forEach items="${troops}" var="troop">
                    <c:choose>
                        <c:when test = "${troop.id eq hero.troop.id}">
                            <form:option selected="selected" value="${troop.id}"><c:out value="${troop.name}"/></form:option>
                        </c:when>
                        <c:otherwise>
                            <form:option value="${troop.id}"><c:out value="${troop.name}"/></form:option>
                        </c:otherwise>
                    </c:choose>

                </c:forEach>
            </form:select>
        </div>
            
        <form:label path="roleId" cssClass="col-sm-2 control-label">Role</form:label>
        <div class="col-sm-10">
            <c:set var="count" value="0" scope="page" />
            <form:select multiple="true" path="roleId" cssClass="form-control">
                <c:forEach items="${roles}" var="role">
                    <c:choose>
                        <c:when test = "${role.id eq hero.role.id}">
                            <form:option selected="selected" value="${role.id}"><c:out value="${role.name}"/></form:option>
                        </c:when>
                        <c:otherwise>
                            <form:option value="${role.id}"><c:out value="${role.name}"/></form:option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </form:select>
        </div>

        <button class="btn btn-primary" type="submit">Edit</button>
        <my:a href="/hero/list" class="btn btn-primary">Cancel</my:a>
    </form:form>

</jsp:attribute>
</my:pagetemplate>
