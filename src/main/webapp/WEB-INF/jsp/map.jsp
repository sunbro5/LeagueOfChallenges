<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--@elvariable id="challenge" type="com.astora.web.dto.challenge.ChallengeDto"--%>
<%--@elvariable id="challenges" type="java.util.List<com.astora.web.dto.challenge.ChallengeDto>"--%>

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
    <link id="bootstrap-style" href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/bootstrap-responsive.min.css"/>" rel="stylesheet">
    <link id="base-style" href="<c:url value="/resources/css/style.css"/>" rel="stylesheet">
    <link id="base-style-responsive" href="<c:url value="/resources/css/style-responsive.css"/>" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800&subset=latin,cyrillic-ext,latin-ext' rel='stylesheet' type='text/css'>
    <!-- end: CSS -->


    <!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <link id="ie-style" href="<c:url value="/resources/css/ie.css"/>" rel="stylesheet">
    <![endif]-->

    <!--[if IE 9]>
    <link id="ie9style" href="<c:url value="/resources/css/ie9.css"/>" rel="stylesheet">
    <![endif]-->

    <!-- start: Favicon -->
    <%--<link rel="shortcut icon" href="<c:url value="resources/img/favicon.ico"/>">--%>
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
    <div class="row-fluid" >
        <jsp:include page="leftSideBar.jsp"/>

        <!--  Note: This example requires that you consent to location sharing when
        // prompted by your browser. If you see the error "The Geolocation service
        // failed.", it means you probably did not give permission for the browser to
        // locate you. -->
        <!-- start: Content -->
        <div id="content" class="span10 map-no-padding">
            <div id="map"></div>
            <script>
                var challenges = [
                    <c:forEach items="${challenges}" var="challenge" varStatus="status">
                    [
                        '${challenge.challengerTeamName}', // Name - [i][0]
                        '${challenge.text}', // Text - [i][1]
                        '${challenge.challengeStart}',
                        '${challenge.challengeEnd}',
                        '${challenge.coordsLat}',
                        '${challenge.coordsLng}',
                        '${challenge.opponentTeamId}',
                        '${challenge.challengerTeamId}',
                        '${challenge.challengeId}',
                        '${challenge.gameName}'
                    ]
                    <c:if test="${!status.last}">
                    ,
                    </c:if>
                    </c:forEach>
                ];

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
                            sessionStorage.setItem('position',pos);

                            var a = document.createElement('a');
                            var linkText = document.createTextNode("my title text");
                            a.appendChild(linkText);
                            a.title = "my title texta";
                            a.href = "/hibernate?location=" + pos;
                            document.body.appendChild(a);



                            var yourAvatarIcon = {
                                url: "/resources/img/map/anonym.png", // url
                                scaledSize: new google.maps.Size(50, 50), // scaled size
                                origin: new google.maps.Point(0,0), // origin
                                anchor: new google.maps.Point(0, 0) // anchor
                            };

                            var markerAvatar = new google.maps.Marker({
                                position: new google.maps.LatLng(pos.lat, pos.lng),
                                map: map,
                                icon: yourAvatarIcon
                            });
                            var infowindow = new google.maps.InfoWindow;


                            map.setCenter(pos);

                            var marker, i;

                            var image21 = new Image();
                            image21.src = "/resources/img/map/anonym.png";



                            // jednotlivy vyzvy
                            for (i = 0; i < challenges.length; i++) {

                                // game icon
                                var icon = {
                                    url: "/resources/img/map/" + challenges[i][9] + ".png", // url
                                    scaledSize: new google.maps.Size(50, 50), // scaled size
                                    origin: new google.maps.Point(0, 0), // origin
                                    anchor: new google.maps.Point(0, 0) // anchor
                                };

                                marker = new google.maps.Marker({
                                    position: new google.maps.LatLng(challenges[i][4], challenges[i][5]),
                                    map: map,
                                    icon: icon
                                });

                                // Vyskakovaci okno vyzvy(markeru), kdyz na ni klikneme.
                                google.maps.event.addListener(marker, 'click', (function (marker, i) {
                                    return function () {
                                        var linkToChallenge = "<b><a href='/user/challengeDetail?challengeId=" + challenges[i][8] + "'><spring:message code="map.linkToChallenge"/></a></b>";
                                        infowindow.setContent(
                                                '<b><spring:message code="map.team"/></b> ' + challenges[i][0] + "<br/>" +
                                                '<b><spring:message code="map.startTime"/></b> ' + challenges[i][2] + "<br/>" +
                                                '<b><spring:message code="map.endTime"/></b> ' + challenges[i][3] + "<br/>" +
                                                challenges[i][1] + "<br/>" + linkToChallenge
                                        );
                                        infowindow.open(map, marker);
                                    }
                                })(marker, i));
                            }
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
            <!--AIzaSyAgmM1VPSmo3QtkT4cOyZ_UR_uaDfUhH8Q -->
            <!--geolocation key AIzaSyDM3hLUh10lPdC4qzzQ24HMuVldsSja0yk -->
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

<script src="/resources/js/jquery-1.9.1.min.js"></script>
<script src="/resources/js/jquery-migrate-1.0.0.min.js"></script>

<script src="/resources/js/jquery-ui-1.10.0.custom.min.js"></script>

<script src="/resources/js/jquery.ui.touch-punch.js"></script>

<script src="/resources/js/modernizr.js"></script>

<script src="/resources/js/bootstrap.min.js"></script>

<script src="/resources/js/jquery.cookie.js"></script>

<script src="/resources/js/fullcalendar.min.js"></script>

<script src="/resources/js/jquery.dataTables.min.js"></script>

<script src="/resources/js/excanvas.js"></script>
<script src="/resources/js/jquery.flot.js"></script>
<script src="/resources/js/jquery.flot.pie.js"></script>
<script src="/resources/js/jquery.flot.stack.js"></script>
<script src="/resources/js/jquery.flot.resize.min.js"></script>

<script src="/resources/js/jquery.chosen.min.js"></script>

<script src="/resources/js/jquery.uniform.min.js"></script>

<script src="/resources/js/jquery.cleditor.min.js"></script>

<script src="/resources/js/jquery.noty.js"></script>

<script src="/resources/js/jquery.elfinder.min.js"></script>

<script src="/resources/js/jquery.raty.min.js"></script>

<script src="/resources/js/jquery.iphone.toggle.js"></script>

<script src="/resources/js/jquery.uploadify-3.1.min.js"></script>

<script src="/resources/js/jquery.gritter.min.js"></script>

<script src="/resources/js/jquery.imagesloaded.js"></script>

<script src="/resources/js/jquery.masonry.min.js"></script>

<script src="/resources/js/jquery.knob.modified.js"></script>

<script src="/resources/js/jquery.sparkline.min.js"></script>

<script src="/resources/js/counter.js"></script>

<script src="/resources/js/retina.js"></script>

<script src="/resources/js/custom.js"></script>

</body>
</html>
