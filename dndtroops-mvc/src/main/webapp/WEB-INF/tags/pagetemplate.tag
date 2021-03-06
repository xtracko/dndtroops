<%@ tag pageEncoding="utf-8" dynamic-attributes="dynattrs" trimDirectiveWhitespaces="true" %>
<%@ attribute name="title" required="false" %>
<%@ attribute name="head" fragment="true" %>
<%@ attribute name="body" fragment="true" required="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><c:out value="${title}"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css" crossorigin="anonymous">
    <jsp:invoke fragment="head"/>
</head>
<body>
<!-- navigation bar -->
<nav class="navbar navbar-inverse navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <div style="margin-top:10px;padding-top: 2px;margin-right: 20px">
                <a style="font-size: 18px; color: rgb(157,157,157)" href="${pageContext.request.contextPath}">
                    <f:message key="navigation.home"/>
                </a>
            </div>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><my:a href="/scores"><f:message key="navigation.scores"/></my:a></li>
            <c:if test="${not empty authenticatedUser}">            
                <li><my:a href="/troop/list"><f:message key="navigation.troops"/></my:a></li>
                <li><my:a href="/hero/list"><f:message key="navigation.heroes"/></my:a></li>
                <li><my:a href="/role/list"><f:message key="navigation.roles"/></my:a></li>
                <c:if test="${authenticatedUser.isAdmin}">
                    <li><my:a href="/admin/battle"><f:message key="navigation.battle"/></my:a></li>
                </c:if>
            </ul>
            </c:if>
             <c:if test="${empty authenticatedUser}">
                 <div class="pull-right"  style="margin-top: 10px;">
                <div class="panel panel-default" style="margin-top: 10px @important">
                    <div style = "padding:5px 5px 5px 5px;  ">
                      <my:a href="/auth/login">Login  <span class="glyphicon glyphicon-log-in"></span></my:a>
                    </div>
                </div>
            </div>
            </c:if>
         <!-- authenticated user info -->    
           <c:if test="${not empty authenticatedUser}">
            <div class="pull-right"  style="margin-top: 10px;">
                <div class="panel panel-default" style="margin-top: 10px @important">
                    <div style = "padding:5px 5px 5px 5px;  ">
                      <b>User:</b>  <c:out value="${authenticatedUser.name}" />  <my:a href="/auth/logout">  <span class="glyphicon glyphicon-log-out" style = "padding-left: 10px"></span></my:a>
                    </div>
                </div>
            </div>
        </c:if>      
        </div><!--/.nav-collapse -->
    
    </div>
</nav>

<div class="container">

    <!-- page title -->
    <c:if test="${not empty title}">
        <div class="page-header">
            <h1><c:out value="${title}"/></h1>
        </div>
    </c:if>

   

    <!-- alerts -->
    <c:if test="${not empty alert_danger}">
        <div class="alert alert-danger" role="alert">
            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
            <c:out value="${alert_danger}"/></div>
    </c:if>
    <c:if test="${not empty alert_info}">
        <div class="alert alert-info" role="alert"><c:out value="${alert_info}"/></div>
    </c:if>
    <c:if test="${not empty alert_success}">
        <div class="alert alert-success" role="alert"><c:out value="${alert_success}"/></div>
    </c:if>
    <c:if test="${not empty alert_warning}">
        <div class="alert alert-warning" role="alert"><c:out value="${alert_warning}"/></div>
    </c:if>

    <!-- page body -->
    <jsp:invoke fragment="body"/>

    <!-- footer -->
    <footer class="footer">
        <p>&copy;&nbsp;2017&nbsp;Novotný, Šesták</p>
    </footer>
</div>
<!-- javascripts placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>