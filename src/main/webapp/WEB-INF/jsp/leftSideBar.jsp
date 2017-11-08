<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!-- start: Left SideBar -->
<c:url value="/login" var="loginUrl"/>
<c:url value="/showMap" var="showMapUrl"/>
<c:url value="/createChallenge" var="createChallengeUrl"/>
<c:url value="/registration" var="registrationUrl"/>
<c:url value="/friends" var="friendsUrl"/>
<c:url value="/messages" var="messagesUrl"/>
<div id="sidebar-left" class="span2">
  <div class="nav-collapse sidebar-nav">
    <ul class="nav nav-tabs nav-stacked main-menu">
      <li><a href="/"><i class="icon-bar-chart"></i><span class="hidden-tablet"> Dashboard</span></a></li>
      <li><a href="messages.html"><i class="icon-envelope"></i><span class="hidden-tablet"> Messages</span></a></li>
      <li><a href="tasks.html"><i class="icon-tasks"></i><span class="hidden-tablet"> Tasks</span></a></li>
      <li><a href="${showMapUrl}"><i class="icon-eye-open"></i><span class="hidden-tablet">Mapa</span></a></li>
      <li><a href="${createChallengeUrl}"><i class="icon-dashboard"></i><span class="hidden-tablet">Vytvor challenge</span></a></li>
      <li>
        <a class="dropmenu" href="#"><i class="icon-folder-close-alt"></i><span class="hidden-tablet"> Dropdown</span><span class="label label-important"> 3 </span></a>
        <ul>
          <li><a class="submenu" href="submenu.html"><i class="icon-file-alt"></i><span class="hidden-tablet"> Sub Menu 1</span></a></li>
          <li><a class="submenu" href="submenu2.html"><i class="icon-file-alt"></i><span class="hidden-tablet"> Sub Menu 2</span></a></li>
          <li><a class="submenu" href="submenu3.html"><i class="icon-file-alt"></i><span class="hidden-tablet"> Sub Menu 3</span></a></li>
        </ul>
      </li>
      <li><a href="${registrationUrl}"><i class="icon-edit"></i><span class="hidden-tablet"> <spring:message code="page.menu.registration.label"/></span></a></li>
      <li><a href="${friendsUrl}"><i class="halflings-icon white user"></i><span class="hidden-tablet"> <spring:message code="page.menu.friends.label"/></span></a></li>
      <li><a href="${messagesUrl}"><i class="icon-envelope"></i><span class="hidden-tablet"> <spring:message code="page.menu.messages.label"/></span></a></li>
      <li><a href="gallery.html"><i class="icon-picture"></i><span class="hidden-tablet"> Gallery</span></a></li>
      <li><a href="table.html"><i class="icon-align-justify"></i><span class="hidden-tablet"> Tables</span></a></li>
      <li><a href="calendar.html"><i class="icon-calendar"></i><span class="hidden-tablet"> Calendar</span></a></li>
      <li><a href="file-manager.html"><i class="icon-folder-open"></i><span class="hidden-tablet"> File Manager</span></a></li>
      <li><a href="icon.html"><i class="icon-star"></i><span class="hidden-tablet"> Icons</span></a></li>
      <li><a href="${loginUrl}"><i class="icon-lock"></i><span class="hidden-tablet"> Login Page</span></a></li>
    </ul>
  </div>
</div>
<!-- end: Left SideBar -->