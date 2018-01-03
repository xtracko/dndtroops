<%--
  @author: Jiří Novotný
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Edit Role">
<jsp:attribute name="body">

    <form:form method="post" action="${pageContext.request.contextPath}/role/edit/${role.id}" modelAttribute="role" cssClass="form-horizontal">
        <div class="form-group ${name_error?'has-error':''}">
            <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
            <div class="col-sm-10">
                <form:input path="name" cssClass="form-control"/>
                <form:errors path="name" cssClass="help-block"/>
            </div>
        </div>

        <div class="form-group ${description_error?'has-error':''}">
            <form:label path="name" cssClass="col-sm-2 control-label">Description</form:label>
            <div class="col-sm-10">
                <form:input path="description" cssClass="form-control"/>
                <form:errors path="description" cssClass="help-block"/>
            </div>
        </div>

        <div class="form-group ${power_error?'has-error':''}">
            <form:label path="name" cssClass="col-sm-2 control-label">Power</form:label>
            <div class="col-sm-10">
                <form:select path="power" cssClass="form-control">
                    <form:option value="MAGIC">Magic</form:option>
                    <form:option value="MARTIAL_ARTS">Martial Arts</form:option>
                    <form:option value="WEAPONS">Weapons</form:option>
                </form:select>
                <form:errors path="power" cssClass="help-block"/>
            </div>
        </div>

        <div class="form-group ${damage_error?'has-error':''}">
            <form:label path="name" cssClass="col-sm-2 control-label">Damage</form:label>
            <div class="col-sm-10">
                <form:input path="damage" cssClass="form-control"/>
                <form:errors path="damage" cssClass="help-block"/>
            </div>
        </div>

        <div class="form-group ${cooldown_error?'has-error':''}">
            <form:label path="name" cssClass="col-sm-2 control-label">Cooldown</form:label>
            <div class="col-sm-10">
                <form:input path="cooldown" cssClass="form-control"/>
                <form:errors path="cooldown" cssClass="help-block"/>
            </div>
        </div>

        <button class="btn btn-primary" type="submit">Confirm</button>
    </form:form>

</jsp:attribute>
</my:pagetemplate>