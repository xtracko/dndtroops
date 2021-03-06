<%-- 
    Document   : edit
    Created on : 3.1.2018, 23:26:08
    Author     : Martin Sestak and Jiří Novotný (hero edit functionality)
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

        <div class="form-group ${troop_error?'has-error':''}">
            <form:label path="troop" cssClass="col-sm-2 control-label">Troop</form:label>
            <div class="col-sm-10">
                <form:select path="troop" cssClass="form-control">
                    <form:options items="${troops}" itemLabel="name" itemValue="id" />
                </form:select>
                <form:errors path="troop" cssClass="help-block"/>
            </div>
        </div>

        <div class="form-group ${roles_error?'has-error':''}">
            <form:label path="roles" cssClass="col-sm-2 control-label">Roles</form:label>
            <div class="col-sm-10">
                <form:select multiple="true" path="roles" cssClass="form-control">
                    <c:forEach items="${roles}" var="role">

                        <c:set var="contains" value="false" />
                        <c:forEach var="item" items="${hero.roles}">
                          <c:if test="${item eq role}">
                            <c:set var="contains" value="true" />
                          </c:if>
                        </c:forEach>

                        <c:choose>
                            <c:when test = "${contains}">
                                <form:option value="${role.id}" label="${role.name}" selected="selected" />
                            </c:when>
                            <c:otherwise>
                                <form:option value="${role.id}" label="${role.name}" />
                            </c:otherwise>
                        </c:choose>

                    </c:forEach>
                </form:select>
                <form:errors path="roles" cssClass="help-block"/>
            </div>
        </div>

        <button class="btn btn-primary" type="submit">Edit</button>
        <my:a href="/hero/list" class="btn btn-primary">Cancel</my:a>
    </form:form>

</jsp:attribute>
</my:pagetemplate>
