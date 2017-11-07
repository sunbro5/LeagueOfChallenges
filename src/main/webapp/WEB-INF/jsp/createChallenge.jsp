<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>

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


    <!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <link id="ie-style" href="<c:url value="resources/css/ie.css"/>" rel="stylesheet">
    <![endif]-->

    <!--[if IE 9]>
    <link id="ie9style" href="<c:url value="resources/css/ie9.css"/>" rel="stylesheet">
    <![endif]-->

    <!-- start: Favicon -->
    <link rel="shortcut icon" href="<c:url value="resources/img/favicon.ico"/>">
    <!-- end: Favicon -->

    <style>
        /* Always set the map height explicitly to define the size of the div
         * element that contains the map. */
        #map {
            height: 100%;
        }
        /* Optional: Makes the sample page fill the window. */
        html, body {
            height: 100%;
            margin: 0;
            padding: 0;
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container-fluid-full">
    <div class="row-fluid">
<jsp:include page="leftSideBar.jsp"/>



        <div id="content" class="span10">

            <c:url value="/createChallengeForm" var="createChallengeFormUrl"/>
            <form:form modelAttribute="createChallengeModel" action="${createChallengeFormUrl}">
                <div class="form-group">
                    <p>start</p>
                    <%--<form:input  type="datetime-local" cssClass="form-control" path="challengeStart"/>--%>
                </div>
                <div class="form-group">
                    <p>end</p>
                        <%--<form:input type="datetime-local" cssClass="form-control" path="challengeEnd"/>--%>
                </div>
                <div class="form-group">
                    <p>LAT</p>
                    KLIKNI NA MAPU PRO SOURADNICE
                    <form:input cssClass="form-control" path="coordsLat"  id="Lat" readonly="true"/>
                </div>
                <div class="form-group">
                    <p>LNG</p>
                    KLIKNI NA MAPU PRO SOURADNICE
                    <form:input cssClass="form-control" path="coordsLng"  id="Lng" readonly="true" />
                </div>

                <input type="submit" value="Submit" class="btn btn-default">
            </form:form>

            <div id="map"></div>
            <script>
                function initMap() {
                    map = new google.maps.Map(document.getElementById('map'), {
                        center: {lat: -34.397, lng: 150.644},
                        zoom: 16
                    });

                    // Try HTML5 geolocation.
                    if (navigator.geolocation) {
                        navigator.geolocation.getCurrentPosition(function(position) {
                            var pos = {
                                lat: position.coords.latitude,
                                lng: position.coords.longitude
                            };

                            map.setCenter(pos);

                            google.maps.event.addListener(map, "click", function(event) {
                                var lat = event.latLng.lat();
                                var lng = event.latLng.lng();
                                 document.getElementById('Lat').value = lat;
                                 document.getElementById('Lng').value = lng;
                            });

                        }, function () {
                            // Geolocation is not available
                            handleLocationError(true, infoWindow, map.getCenter());
                        });
                    } else {
                        // Browser doesn't support Geolocation
                        handleLocationError(false, infoWindow, map.getCenter());
                    }
                }

                function handleLocationError(browserHasGeolocation, infoWindow, pos) {
                    infoWindow.setPosition(pos);
                    infoWindow.setContent(browserHasGeolocation ?
                            'Error: The Geolocation service failed.' :
                            'Error: Your browser doesn\'t support geolocation.');
                    infoWindow.open(map);
                }

                function PopupCenter(url, title, w, h) {
                    // Fixes dual-screen position                         Most browsers      Firefox
                    var dualScreenLeft = window.screenLeft != undefined ? window.screenLeft : screen.left;
                    var dualScreenTop = window.screenTop != undefined ? window.screenTop : screen.top;

                    var width = window.innerWidth ? window.innerWidth : document.documentElement.clientWidth ? document.documentElement.clientWidth : screen.width;
                    var height = window.innerHeight ? window.innerHeight : document.documentElement.clientHeight ? document.documentElement.clientHeight : screen.height;

                    var left = ((width / 2) - (w / 2)) + dualScreenLeft;
                    var top = ((height / 2) - (h / 2)) + dualScreenTop;
                    var newWindow = window.open(url, title, 'scrollbars=yes, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left);

                    // Puts focus on the newWindow
                    if (window.focus) {
                        newWindow.focus();
                    }
                }


            </script>
            <script async defer
                    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDM3hLUh10lPdC4qzzQ24HMuVldsSja0yk&callback=initMap">
            </script>

        </div>

    </div>
</div>

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
