<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
    <link id="bootstrap-style" href="resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/css/bootstrap-responsive.min.css" rel="stylesheet">
    <link id="base-style" href="resources/css/style.css" rel="stylesheet">
    <link id="base-style-responsive" href="resources/css/style-responsive.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800&subset=latin,cyrillic-ext,latin-ext'
          rel='stylesheet' type='text/css'>
    <link href="resources/css/date-picker/flatpickr.min.css" rel="stylesheet">

    <!-- end: CSS -->


    <!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <link id="ie-style" href="resources/css/ie.css" rel="stylesheet">
    <![endif]-->

    <!--[if IE 9]>
    <link id="ie9style" href="resources/css/ie9.css" rel="stylesheet">
    <![endif]-->

    <!-- start: Favicon -->
    <%--<link rel="shortcut icon" href="resources/img/favicon.ico">--%>
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
                <div class="span6">
                    <h1><spring:message code="page.menu.createChallenge.label"/></h1>
                    <jsp:include page="infoMessage.jsp"/>
                    <c:url value="/createChallengeForm" var="createChallengeFormUrl"/>
                    <form:form modelAttribute="createChallengeModel" action="${createChallengeFormUrl}">
                        <form:input type="hidden" id="coordsLat" path="coordsLat"/>
                        <form:input type="hidden" id="coordsLng" path="coordsLng"/>
                        <div class="form-group">
                            <p><spring:message code="createChallenge.form.gameType.label"/></p>
                            <form:select id="gameName" path="gameName">
                                <c:forEach items="${gameTypes}" var="gameType">
                                    <form:option value="${gameType.gameName}">${gameType.gameName}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div id="teamName-group" class="form-group">
                            <p><spring:message code="createChallenge.form.team.label"/></p>
                            <form:select id="teamName" path="challengerTeamId"/>
                            </span>
                        </div>
                        <div class="form-group">
                            <p><spring:message code="createChallenge.form.dateStart.label"/></p>
                            <div class="input-group date">
                                <form:errors path="challengeStart" cssClass="error" element="p"/>
                                <form:input id="datePickerStart" cssClass="flatpickr" path="challengeStart"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <p><spring:message code="createChallenge.form.dateEnd.label"/></p>
                            <div class="input-group date">
                                <form:errors path="challengeEnd" cssClass="error" element="p"/>
                                <form:input id="datePickerEnd" cssClass="flatpickr" path="challengeEnd"/>
                            </div>
                            <div class="form-group">
                                <p><spring:message code="createChallenge.form.text.label"/></p>
                                <form:textarea cssClass="form-control" path="text"/>
                            </div>
                        </div>
                        <input type="submit" value="<spring:message code="createChallenge.form.button.label" />"
                               class="btn btn-default"/>

                    </form:form>
                </div>
                <div class="span6">
                    <div id="createChallengeMap"></div>
                    <script>
                        function initMap() {
                            map = new google.maps.Map(document.getElementById('createChallengeMap'), {
                                center: {lat: -34.397, lng: 150.644},
                                zoom: 16
                            });

                            // Try HTML5 geolocation.
                            if (navigator.geolocation) {
                                navigator.geolocation.getCurrentPosition(function (position) {
                                    var pos = {
                                        lat: position.coords.latitude,
                                        lng: position.coords.longitude
                                    };
                                    yourAvatarIcon = {
                                        url: "/resources/img/map/anonym.png", // url
                                        scaledSize: new google.maps.Size(50, 50), // scaled size
                                        origin: new google.maps.Point(0, 0), // origin
                                        anchor: new google.maps.Point(0, 0) // anchor
                                    };

                                    selectedLocation = new google.maps.Marker({
                                        position: new google.maps.LatLng(pos.lat, pos.lng),
                                        map: map,
                                        icon: yourAvatarIcon
                                    });

                                    document.getElementById('coordsLat').value = pos.lat;
                                    document.getElementById('coordsLng').value = pos.lng;

                                    map.setCenter(pos);

                                    google.maps.event.addListener(map, "click", function (event) {
                                        var lat = event.latLng.lat();
                                        var lng = event.latLng.lng();
                                        document.getElementById('coordsLat').value = lat;
                                        document.getElementById('coordsLng').value = lng;
                                        selectedLocation.setMap(null);
                                        selectedLocation = new google.maps.Marker({
                                            position: new google.maps.LatLng(lat, lng),
                                            map: map,
                                            icon: yourAvatarIcon
                                        });

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
                    </script>
                    <script async defer
                            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDM3hLUh10lPdC4qzzQ24HMuVldsSja0yk&callback=initMap">
                    </script>
                </div>
            </div>
            <!--/.fluid-container-->

            <!-- end: Content -->
        </div>
        <!--/#content.span10-->
    </div>
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

<script src="resources/js/date-picker/flatpickr.js"></script>
<script src="resources/js/date-picker/locales/cs.js"></script>
<script src="resources/js/jquery-1.9.1.min.js"></script>
<script src="resources/js/jquery-migrate-1.0.0.min.js"></script>

<script src="resources/js/jquery-ui-1.10.0.custom.min.js"></script>

<script src="resources/js/jquery.ui.touch-punch.js"></script>

<script src="resources/js/modernizr.js"></script>

<script src="resources/js/bootstrap.min.js"></script>

<script src="resources/js/jquery.cookie.js"></script>

<script src="resources/js/fullcalendar.min.js"></script>

<script src="resources/js/jquery.dataTables.min.js"></script>

<script src="resources/js/excanvas.js"></script>
<script src="resources/js/jquery.flot.js"></script>
<script src="resources/js/jquery.flot.pie.js"></script>
<script src="resources/js/jquery.flot.stack.js"></script>
<script src="resources/js/jquery.flot.resize.min.js"></script>

<script src="resources/js/jquery.chosen.min.js"></script>

<script src="resources/js/jquery.uniform.min.js"></script>

<script src="resources/js/jquery.cleditor.min.js"></script>

<script src="resources/js/jquery.noty.js"></script>

<script src="resources/js/jquery.elfinder.min.js"></script>

<script src="resources/js/jquery.raty.min.js"></script>

<script src="resources/js/jquery.iphone.toggle.js"></script>

<script src="resources/js/jquery.uploadify-3.1.min.js"></script>

<script src="resources/js/jquery.gritter.min.js"></script>

<script src="resources/js/jquery.imagesloaded.js"></script>

<script src="resources/js/jquery.masonry.min.js"></script>

<script src="resources/js/jquery.knob.modified.js"></script>

<script src="resources/js/jquery.sparkline.min.js"></script>

<script src="resources/js/counter.js"></script>

<script src="resources/js/retina.js"></script>

<script src="resources/js/custom.js"></script>

<script>
    flatpickr("#datePickerStart", { enableTime: true, dateFormat: "d.m.Y H:i"});
    flatpickr("#datePickerEnd", { enableTime: true, dateFormat: "d.m.Y H:i"});

    teams = [
        <c:forEach items="${userTeamsList}" var="team" varStatus="status">
        [
            '${team.gameName}',
            '${team.teamId}',
            '${team.teamName}',
            '${team.noTeamGame}'
        ]
        <c:if test="${!status.last}">
        ,
        </c:if>
        </c:forEach>
    ];
    $(document).ready(function () {
        var i;
        for (i = 0; i < teams.length; i++) {
            if ($('#gameName option:selected').text() === teams[i][0]) {
                if (teams[i][3] === true) {

                    $('#teamName')
                        .append($('<option></option>')
                            .attr('value', teams[i][1])
                            .text('Default'));//TODO HIDE input
                } else {

                    $('#teamName')
                        .append($('<option></option>')
                            .attr('value', teams[i][1])
                            .text(teams[i][2]));
                }
            }
        }

    });
    $('#gameName').change(function () {
        $('#teamName').find('option').remove();
        var i;
        for (i = 0; i < teams.length; i++) {
            if ($('#gameName option:selected').text() === teams[i][0]) {
                if (teams[i][3] === true) {

                    $('#teamName')
                        .append($('<option></option>')
                            .attr('value', teams[i][1])
                            .text('Default'));//TODO HIDE input
                } else {

                    $('#teamName')
                        .append($('<option></option>')
                            .attr('value', teams[i][1])
                            .text(teams[i][2]));
                }

            }
        }
    });
</script>

</body>
</html>
