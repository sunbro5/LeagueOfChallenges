<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--@elvariable id="team" type="com.astora.web.dao.model.Team"--%>

<html lang="en">
<head>

    <!-- start: Meta -->
    <meta charset="utf-8">
    <title><spring:message code ="page.title"/></title>

    <meta name="description" content="Bootstrap Metro Dashboard">
    <meta name="author" content="Dennis Ji">
    <meta name="keyword" content="Metro, Metro UI, Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
    <!-- end: Meta -->

    <!-- start: Mobile Specific -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- end: Mobile Specific -->

    <!-- start: CSS -->
    <link id="bootstrap-style" href="<c:url value="resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="resources/css/bootstrap-responsive.min.css"/>" rel="stylesheet">
    <link id="base-style" href="<c:url value="resources/css/style.css"/>" rel="stylesheet">
    <link id="base-style-responsive" href="<c:url value="resources/css/style-responsive.css"/>" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800&subset=latin,cyrillic-ext,latin-ext' rel='stylesheet' type='text/css'>
    <!-- end: CSS -->

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet"href="resources/css/bootstrap.minA.css" >

    <!-- Optional theme -->
    <link rel="stylesheet" href="resources/css/bootstrap-theme.min.css" >

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container-fluid-full">
    <div class="row-fluid">
        <jsp:include page="leftSideBar.jsp"/>

        <!--  Note: This example requires that you consent to location sharing when
        // prompted by your browser. If you see the error "The Geolocation service
        // failed.", it means you probably did not give permission for the browser to
        // locate you. -->
        <!-- start: Content -->
        <div id="content" class="span10">

            <div class="container" style="padding-top: 60px;">
                <h1 class="page-header">Edit Profile</h1>
                <div class="row">
                    <!-- left column -->
                    <div class="col-md-4 col-sm-6 col-xs-12">
                        <div class="text-center">
                            <img src="/resources/img/people01.png" class="avatar img-circle img-thumbnail" alt="avatar">
                            <h6>Upload a different photo...</h6>
                            <input type="file" class="text-center center-block well well-sm">
                        </div>
                    </div>
                    <!-- edit form column -->
                    <div class="col-md-8 col-sm-6 col-xs-12 personal-info">
                        <div class="alert alert-info alert-dismissable">
                            <a class="panel-close close" data-dismiss="alert">×</a>
                            <i class="fa fa-coffee"></i>
                            This is an <strong>.alert</strong>. Use this to show important messages to the user.
                        </div>
                        <h3>Personal info</h3>
                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label class="col-lg-3 control-label">First name:</label>
                                <div class="col-lg-8">
                                    <input class="form-control" value="Jane" type="text">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label">Last name:</label>
                                <div class="col-lg-8">
                                    <input class="form-control" value="Bishop" type="text">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label">Company:</label>
                                <div class="col-lg-8">
                                    <input class="form-control" value="" type="text">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label">Email:</label>
                                <div class="col-lg-8">
                                    <input class="form-control" value="janesemail@gmail.com" type="text">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label">Time Zone:</label>
                                <div class="col-lg-8">
                                    <div class="ui-select">
                                        <select id="user_time_zone" class="form-control">
                                            <option value="Hawaii">(GMT-10:00) Hawaii</option>
                                            <option value="Alaska">(GMT-09:00) Alaska</option>
                                            <option value="Pacific Time (US & Canada)">(GMT-08:00) Pacific Time (US & Canada)</option>
                                            <option value="Arizona">(GMT-07:00) Arizona</option>
                                            <option value="Mountain Time (US & Canada)">(GMT-07:00) Mountain Time (US & Canada)</option>
                                            <option value="Central Time (US & Canada)" selected="selected">(GMT-06:00) Central Time (US & Canada)</option>
                                            <option value="Eastern Time (US & Canada)">(GMT-05:00) Eastern Time (US & Canada)</option>
                                            <option value="Indiana (East)">(GMT-05:00) Indiana (East)</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">Username:</label>
                                <div class="col-md-8">
                                    <input class="form-control" value="janeuser" type="text">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">Password:</label>
                                <div class="col-md-8">
                                    <input class="form-control" value="11111122333" type="password">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">Confirm password:</label>
                                <div class="col-md-8">
                                    <input class="form-control" value="11111122333" type="password">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label"></label>
                                <div class="col-md-8">
                                    <input class="btn btn-primary" value="Save Changes" type="button">
                                    <span></span>
                                    <input class="btn btn-default" value="Cancel" type="reset">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div><!--/.fluid-container-->
        <!-- end: Content -->
    </div><!--/#content.span10-->
</div><!--/fluid-row-->

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
        <span style="text-align:left;float:left">&copy; 2013 <a href="http://themifycloud.com/downloads/janux-free-responsive-admin-dashboard-template/" alt="Bootstrap_Metro_Dashboard">JANUX Responsive Dashboard</a></span>

    </p>

</footer>


</body>
</html>
