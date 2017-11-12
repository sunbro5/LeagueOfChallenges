<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<html>
<head>
    <title><spring:message code="page.title"/></title>

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

    <%-- Start: date picker styles --%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/date-picker/datepicker-style.css">
    <script src="${pageContext.request.contextPath}/resources/js/date-picker/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/date-picker/bootstrap.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/js/date-picker/moment.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/date-picker/moment-with-locales.js"></script>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/css/date-picker/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/css/date-picker/bootstrap-datetimepicker-standalone.css">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/js/date-picker/bootstrap-datetimepicker.min.js"></script>
    <%-- End: date picker styles --%>

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

            <script type="text/javascript">
                $(function () {
                    $('#datetimepickerStart').datetimepicker({
                        format: "DD.MM.YYYY HH:mm",
                        useCurrent: true,
                        showTodayButton: false,
                        minDate: new Date().toISOString(),
                        locale: 'cs'
                    });
                });
            </script>

            <script type="text/javascript">
                $(function () {
                    $('#datetimepickerEnd').datetimepicker({
                        format: "DD.MM.YYYY HH:mm",
                        useCurrent: true,
                        showTodayButton: false,
                        minDate: new Date().toISOString(),
                        locale: 'cs'
                    });
                });
            </script>


            <c:url value="/createChallengeForm" var="createChallengeFormUrl"/>
            <form:form modelAttribute="createChallengeModel" action="${createChallengeFormUrl}">

                <div class="form-group">
                    <p><form:label path="challengerTeamId">Team</form:label></p>
                    <form:select path="challengerTeamId">

                        <c:forEach items="${userTeams}" var="team">
                            <form:option value="${team.teamId}">
                                ${team.name}
                            </form:option>
                        </c:forEach>
                    </form:select>
                    <form:errors path="challengerTeamId" element="div" cssClass="form__error-msg"/>
                </div>

                <div class="form-group">
                    <p><form:label path="challengeStart">Challenge start:</form:label></p>
                    <div class='input-group date' id='datetimepickerStart'>
                        <form:input cssClass="form-control" path="challengeStart" value="24.11.2017 21:48"/>
                        <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </span>
                    </div>
                </div>
                <div class="form-group">
                    <p><form:label path="challengeEnd">Challenge end: </form:label></p>
                    <div class='input-group date' id='datetimepickerEnd'>
                        <form:input cssClass="form-control" path="challengeEnd" value="25.11.2017 21:48"/>
                        <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                        </span>
                    </div>
                </div>
                <div class="form-group">
                    <p><form:label path="coordsLat">Souradnice</form:label></p>
                    <form:input cssClass="form-control" path="coordsLat" id="Lat" readonly="true" value="49.802238"/>
                </div>
                <div class="form-group">
                    <form:input cssClass="form-control" path="coordsLng" id="Lng" readonly="true" value="16.539289"/>
                    <p><a onclick="PopupCenter('chooseCoords', 'Coords chooser',600,600);" class="btn" role="button">Zadej
                        souradnice</a></p>
                </div>
                <div class="form-group">
                    <p><form:label path="coordsLat">Popis vyzvy</form:label></p>
                    <form:textarea cssClass="form-control" path="text"   />
                </div>


                <input type="submit" value="Submit" class="btn btn-default">
            </form:form>

            <script>
                function PopupCenter(url, title, w, h) {
                    var dualScreenLeft = window.screenLeft != undefined ? window.screenLeft : screen.left;
                    var dualScreenTop = window.screenTop != undefined ? window.screenTop : screen.top;

                    var width = window.innerWidth ? window.innerWidth : document.documentElement.clientWidth ? document.documentElement.clientWidth : screen.width;
                    var height = window.innerHeight ? window.innerHeight : document.documentElement.clientHeight ? document.documentElement.clientHeight : screen.height;

                    var left = ((width / 2) - (w / 2)) + dualScreenLeft;
                    var top = ((height / 2) - (h / 2)) + dualScreenTop;
                    var newWindow = window.open(url, title, 'scrollbars=yes, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left);

                    if (window.focus) {
                        newWindow.focus();
                    }
                }
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
        <span style="text-align:left;float:left">&copy; 2013 <a
                href="http://themifycloud.com/downloads/janux-free-responsive-admin-dashboard-template/"
                alt="Bootstrap_Metro_Dashboard">JANUX Responsive Dashboard</a></span>

    </p>

</footer>


</body>
</html>
