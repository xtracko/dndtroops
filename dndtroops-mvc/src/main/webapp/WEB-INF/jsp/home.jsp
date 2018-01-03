<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate>
<jsp:attribute name="body">

    <div class="jumbotron">
        <h1 class="text-center">D&D Troops</h1>
        <h4><small>Assignment</small></h4>
        Through Forgotten realms are wandering various troops of several heroes trying to complete assigned mission
        (mission is not an entity, it is just text attribute). Hero has a name, role, experience level. Troop has a
        name, mission and amount of golden money. Role contains name, description and other suitable information.
        Example of a role is "elf magician". Every hero could belong to up to one group and have assigned multiple
        roles. Administrator should be able to manage (CRUD) all entities. Hero could assign himself to some group and
        also can assign himself some role.

        <hr>

        <h4>Team no. 2</h4>
        <h4><small>Active Members:</small></h4>
        <ul>
            <li>Jiří Novotný (team leader)</li>
            <li>Martin Šesták</li>
        </ul>

        <h4><small>Former Members:</small></h4>
        <ul>
            <li>Vojtěch Duchoň - left us before 2nd milestone</li>
            <li>Miroslav Mačor - left us before 3rd milestone; former "formal" team leader</li>
        </ul>
    </div>

</jsp:attribute>
</my:pagetemplate>
