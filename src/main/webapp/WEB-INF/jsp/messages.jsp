<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <!-- start: Meta -->
    <meta charset="utf-8">
    <title><spring:message code="page.title"/></title>

    <meta name="description" content="Bootstrap Metro Dashboard">
    <meta name="author" content="Dennis Ji">
    <meta name="keyword"
          content="Metro, Metro UI, Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
    <!-- end: Meta -->

    <!-- start: Mobile Specific -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- end: Mobile Specific -->

    <!-- start: CSS -->
    <link id="bootstrap-style" href="<c:url value="resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="resources/css/bootstrap-responsive.min.css"/>" rel="stylesheet">
    <link id="base-style" href="<c:url value="resources/css/style.css"/>" rel="stylesheet">
    <link id="base-style-responsive" href="<c:url value="resources/css/style-responsive.css"/>" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800&subset=latin,cyrillic-ext,latin-ext'
          rel='stylesheet' type='text/css'>
    <!-- end: CSS -->


    <!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <link id="ie-style" href="<c:url value=" resources/css/ie.css"/>" rel="stylesheet">
    <![endif]-->

    <!--[if IE 9]>
    <link id="ie9style" href="<c:url value=" resources/css/ie9.css"/>" rel="stylesheet">
    <![endif]-->

    <!-- start: Favicon -->
    <link rel="shortcut icon" href="<c:url value="resources/img/favicon.ico"/>">
    <!-- end: Favicon -->
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container-fluid-full">
    <div class="row-fluid">

        <jsp:include page="leftSideBar.jsp"/>

        <noscript>
            <div class="alert alert-block span10">
                <h4 class="alert-heading">Warning!</h4>

                <p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a>
                    enabled to use this site.</p>
            </div>
        </noscript>

        <!-- start: Content -->
        <div id="content" class="span10 text-center">
            <div class="row-fluid">

                <div class="span7">
                    <h1><spring:message code="messages.inbox.title"/></h1>

                    <ul class="messagesList">
                        <c:forEach items="${userInboxMessageList}" var="userInboxMessage">
                            <li>
                                <span class="from"><span class="glyphicons star"><i></i></span> ${userInboxMessage.fromNickname} <span class="glyphicons paperclip"><i></i></span></span><span class="title">${userInboxMessage.text}</span><span class="date">${userInboxMessage.sendDate}</span>
                            </li>
                        </c:forEach>
                    </ul>
                    <div class="common-modal modal fade" id="common-Modal1" tabindex="-1" role="dialog" aria-hidden="true">
                        <div class="modal-content">
                            <ul class="list-inline item-details">
                                <li><a href="http://themifycloud.com">Admin templates</a></li>
                                <li><a href="http://themescloud.org">Bootstrap themes</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="span5 noMarginLeft">

                    <div class="message dark">

                        <div class="header">
                            <div class="from"><i class="halflings-icon user"></i> <b>Dennis Ji</b> / jiguofei@msn.com</div>
                            <div class="date"><i class="halflings-icon time"></i> Today, <b>3:47 PM</b></div>

                            <div class="menu"></div>

                        </div>

                        <div class="content">
                            <p>
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                            </p>
                            <blockquote>
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                            </blockquote>
                            <p>
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                            </p>
                        </div>

                        <div class="attachments">
                            <ul>
                                <li>
                                    <span class="label label-important">zip</span> <b>bootstrap.zip</b> <i>(2,5MB)</i>
									<span class="quickMenu">
										<a href="#" class="glyphicons search"><i></i></a>
										<a href="#" class="glyphicons share"><i></i></a>
										<a href="#" class="glyphicons cloud-download"><i></i></a>
									</span>
                                </li>
                                <li>
                                    <span class="label label-info">txt</span> <b>readme.txt</b> <i>(7KB)</i>
									<span class="quickMenu">
										<a href="#" class="glyphicons search"><i></i></a>
										<a href="#" class="glyphicons share"><i></i></a>
										<a href="#" class="glyphicons cloud-download"><i></i></a>
									</span>
                                </li>
                                <li>
                                    <span class="label label-success">xls</span> <b>spreadsheet.xls</b> <i>(984KB)</i>
									<span class="quickMenu">
										<a href="#" class="glyphicons search"><i></i></a>
										<a href="#" class="glyphicons share"><i></i></a>
										<a href="#" class="glyphicons cloud-download"><i></i></a>
									</span>
                                </li>
                            </ul>
                        </div>

                        <form class="replyForm" method="post" action="">

                            <fieldset>
                                <textarea tabindex="3" class="input-xlarge span12" id="message" name="body" rows="12" placeholder="Click here to reply"></textarea>

                                <div class="actions">

                                    <button tabindex="3" type="submit" class="btn btn-success">Send message</button>

                                </div>

                            </fieldset>

                        </form>

                    </div>

                </div>

            </div>
        </div>
        <!--/.fluid-container-->

        <!-- end: Content -->
    </div>
    <!--/#content.span10-->
</div>
<!--/fluid-row-->

<div class="modal hide fade" id="myModal">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">×</button>
        <h3>Settings</h3>
    </div>
    <div class="modal-body">
        <p>Here settings can be configured...</p>
    </div>
    <div class="modal-footer">
        <a href="#" class="btn" data-dismiss="modal">Close</a>
        <a href="#" class="btn btn-primary">Save changes</a>
    </div>
</div>

<div class="common-modal modal fade" id="common-Modal1" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-content">
        <ul class="list-inline item-details">
            <li><a href="http://themifycloud.com">Admin templates</a></li>
            <li><a href="http://themescloud.org">Bootstrap themes</a></li>
        </ul>
    </div>
</div>

<div class="clearfix"></div>

<footer>

    <p>
        <span style="text-align:left;float:left">&copy; 2013 <a
                href="http://themifycloud.com/downloads/janux-free-responsive-admin-dashboard-template/"
                alt="Bootstrap_Metro_Dashboard">JANUX Responsive Dashboard</a></span>

    </p>

</footer>

<script src="<c:url value="resources/js/jquery-1.9.1.min.js"/>"></script>
<script src="<c:url value="resources/js/jquery-migrate-1.0.0.min.js"/>"></script>

<script src="<c:url value="resources/js/jquery-ui-1.10.0.custom.min.js"/>"></script>

<script src="<c:url value="resources/js/jquery.ui.touch-punch.js"/>"></script>

<script src="<c:url value="resources/js/modernizr.js"/>"></script>

<script src="<c:url value="resources/js/bootstrap.min.js"/>"></script>

<script src="<c:url value="resources/js/jquery.cookie.js"/>"></script>

<script src="<c:url value="resources/js/fullcalendar.min.js"/>"></script>

<script src="<c:url value="resources/js/jquery.dataTables.min.js"/>"></script>

<script src="<c:url value="resources/js/excanvas.js"/>"></script>
<script src="<c:url value="resources/js/jquery.flot.js"/>"></script>
<script src="<c:url value="resources/js/jquery.flot.pie.js"/>"></script>
<script src="<c:url value="resources/js/jquery.flot.stack.js"/>"></script>
<script src="<c:url value="resources/js/jquery.flot.resize.min.js"/>"></script>

<script src="<c:url value="resources/js/jquery.chosen.min.js"/>"></script>

<script src="<c:url value="resources/js/jquery.uniform.min.js"/>"></script>

<script src="<c:url value="resources/js/jquery.cleditor.min.js"/>"></script>

<script src="<c:url value="resources/js/jquery.noty.js"/>"></script>

<script src="<c:url value="resources/js/jquery.elfinder.min.js"/>"></script>

<script src="<c:url value="resources/js/jquery.raty.min.js"/>"></script>

<script src="<c:url value="resources/js/jquery.iphone.toggle.js"/>"></script>

<script src="<c:url value="resources/js/jquery.uploadify-3.1.min.js"/>"></script>

<script src="<c:url value="resources/js/jquery.gritter.min.js"/>"></script>

<script src="<c:url value="resources/js/jquery.imagesloaded.js"/>"></script>

<script src="<c:url value="resources/js/jquery.masonry.min.js"/>"></script>

<script src="<c:url value="resources/js/jquery.knob.modified.js"/>"></script>

<script src="<c:url value="resources/js/jquery.sparkline.min.js"/>"></script>

<script src="<c:url value="resources/js/counter.js"/>"></script>

<script src="<c:url value="resources/js/retina.js"/>"></script>

<script src="<c:url value="resources/js/custom.js"/>"></script>

</body>
</html>
