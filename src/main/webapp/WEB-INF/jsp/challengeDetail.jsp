<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="mainHeader.jsp"/>

<!-- start: Content -->
<div id="content" class="span10 text-center">
    <div class="row-fluid">
        <div class="span6">
            <h1><spring:message code="challenge.detail.title"/></h1>
            <jsp:include page="infoMessage.jsp"/>
            <div class="profil-box">
                <div class="row-fluid">
                    <strong>
                        <p><spring:message code="challenge.detail.game"/></p>
                    </strong>

                    <p>${challengeDetail.gameName}</p>
                </div>
                <div class="row-fluid">
                    <strong>
                        <p><spring:message code="challenge.detail.team"/></p>
                    </strong>

                    <p>${challengeDetail.challengerTeamName}</p>
                </div>
                <div class="row-fluid">
                    <strong>
                        <p><spring:message code="challenge.detail.start"/></p>
                    </strong>

                    <p>${challengeDetail.challengeStart}</p>
                </div>
                <div class="row-fluid">
                    <strong>
                        <p><spring:message code="challenge.detail.end"/></p>
                    </strong>

                    <p>${challengeDetail.challengeEnd}</p>
                </div>
                <div class="row-fluid">
                    <strong>
                        <p><spring:message code="challenge.detail.description"/></p>
                    </strong>

                    <p>${challengeDetail.text}</p>
                </div>
                <div class="row-fluid">
                    <strong>
                        <p><spring:message code="challenge.detail.challengers"/></p>
                    </strong>

                    <div>
                        <c:forEach items="${challengeDetail.challengers}" var="users">
                            <p>${users}</p>
                        </c:forEach>
                    </div>
                </div>
                <c:if test="${not empty challengeDetail.opponents}">
                    <div class="row-fluid">
                        <strong>
                            <p><spring:message code="challenge.detail.opponents"/></p>
                        </strong>

                        <div>
                            <c:forEach items="${challengeDetail.opponents}" var="users">
                                <p>${users}</p>
                            </c:forEach>
                        </div>
                    </div>
                </c:if>
                <c:choose>
                    <c:when test="${isUserChallenge && challengeDetail.state == 'CREATED'}">
                        <div class="row-fluid">
                            <c:url value="/user/cancelChallenge" var="cancelChallengeUrl">
                                <c:param name="challengeId" value="${challengeDetail.challengeId}"/>
                            </c:url>
                            <strong>
                                <a href="${cancelChallengeUrl}"><spring:message
                                        code="userChallenges.form.delete.label"/></a>
                            </strong>
                        </div>
                    </c:when>
                    <c:when test="${isUserChallenge && challengeDetail.state == 'CHALLENGED'}">
                        <div class="row-fluid">
                            <c:url var="acceptUrl" value="/user/acceptChallenge">
                                <%--@elvariable id="challengeDetail" type="com.astora.web.dto.ChallengeInfoDto"--%>
                                <c:param name="challengeId" value="${challengeDetail.challengeId}"/>
                            </c:url>
                            <strong>
                                <a href="${acceptUrl}"><spring:message code="challenge.detail.accept"/></a>
                            </strong>
                        </div>
                        <div class="row-fluid">
                            <c:url var="declineUrl" value="/user/declineChallenge">
                                <c:param name="challengeId" value="${challengeDetail.challengeId}"/>
                            </c:url>
                            <strong>
                                <a href="${declineUrl}"><spring:message code="challenge.detail.decline"/></a>
                            </strong>
                        </div>
                    </c:when>
                    <c:when test="${not isUserChallenge && challengeDetail.state == 'CREATED' && not empty userTeamInformationList}">
                        <form id="joinChallenge-form" action="/user/joinChallenge" method="get">
                            <input type="hidden" name="challengeId" value="${challengeDetail.challengeId}">
                            <select name="teamId">
                                <c:forEach var="userTeamInformation" items="${userTeamInformationList}">
                                    <option value="${userTeamInformation.teamId}">${userTeamInformation.name}</option>
                                </c:forEach>
                            </select>
                            </select>
                            <div class="row-fluid">
                                <strong>
                                    <a href="javascript:{}"
                                       onclick="document.getElementById('joinChallenge-form').submit(); return false;">
                                        <spring:message code="challenge.detail.join"/></a>
                                </strong>
                            </div>
                        </form>
                    </c:when>
                    <c:when test="${(isUserChallenge ||isOpponentChallenge) && canUserSetResult && challengeDetail.state == 'ACCEPTED'}">
                        <div class="row-fluid">
                            <c:url value="/user/challengeResult" var="challengeResultUrl">
                                <c:param name="challengeId" value="${challengeDetail.challengeId}"/>
                            </c:url>
                            <strong>
                                <a href="${challengeResultUrl}"><spring:message
                                        code="userChallenges.form.setChallengeResult.label"/></a>
                            </strong>
                        </div>
                    </c:when>
                </c:choose>
            </div>
        </div>
        <div class="span6">
            <div id="createChallengeMap"></div>
            <script>
                var challengePosition = {lat: ${challengeDetail.coordsLat}, lng: ${challengeDetail.coordsLng}};
                function initMap() {
                    var map = new google.maps.Map(document.getElementById('createChallengeMap'), {
                        zoom: 16,
                        center: challengePosition
                    });
                    var challengeIcon = {
                        url: "/resources/img/map/${challengeDetail.gameName}.png", // url
                        scaledSize: new google.maps.Size(50, 50), // scaled size
                        origin: new google.maps.Point(0, 0), // origin
                        anchor: new google.maps.Point(0, 0) // anchor
                    };
                    var marker = new google.maps.Marker({
                        position: challengePosition,
                        map: map,
                        icon: challengeIcon
                    });
                }
            </script>
            <script async defer
                    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDM3hLUh10lPdC4qzzQ24HMuVldsSja0yk&callback=initMap">
            </script>

        </div>
    </div>
</div>
<jsp:include page="mainFooter.jsp"/>