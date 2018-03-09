<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="mainHeader.jsp"/>

<!-- start: Content -->
<div id="content" class="span10 text-center">
    <div class="row-fluid">
        <div class="span6">
            <h1><spring:message code="page.menu.userChallenges.label"/></h1>
            <jsp:include page="infoMessage.jsp"/>
            <div class="profil-box">
                <div class="row-fluid">
                    <strong>
                        <p><spring:message code="challenge.detail.game"/></p>
                    </strong>

                    <p class="profil-50 text-left">${challengeDetail.gameName}</p>
                </div>
                <div class="row-fluid">
                    <strong>
                        <p><spring:message code="challenge.detail.team"/></p>
                    </strong>

                    <p class="profil-50 text-left">${challengeDetail.challengerTeamName}</p>
                </div>
                <div class="row-fluid">
                    <strong>
                        <p><spring:message code="challenge.detail.start"/></p>
                    </strong>

                    <p class="profil-50 text-left">${challengeDetail.challengeStart}</p>
                </div>
                <div class="row-fluid">
                    <strong>
                        <p><spring:message code="challenge.detail.end"/></p>
                    </strong>

                    <p class="profil-50 text-left">${challengeDetail.challengeEnd}</p>
                </div>
                <div class="row-fluid">
                    <strong>
                        <p><spring:message code="challenge.detail.description"/></p>
                    </strong>

                    <p class="profil-50 text-left">${challengeDetail.text}</p>
                </div>
                <div class="row-fluid">
                    <strong>
                        <p><spring:message code="challenge.detail.challengers"/></p>
                    </strong>

                    <div class="profil-50 text-left">
                        <c:forEach items="${challengeDetail.challengers}" var="users">
                            <p>${users}</p>
                        </c:forEach>
                    </div>
                </div>
                <div class="row-fluid">
                    <strong>
                        <p><spring:message code="challenge.detail.opponents"/></p>
                    </strong>

                    <div class="profil-50 text-left">
                        <c:forEach items="${challengeDetail.opponents}" var="users">
                            <p>${users}</p>
                        </c:forEach>
                    </div>
                </div>
                <c:choose>
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
                    <c:when test="${not isUserChallenge && challengeDetail.state == 'CREATED'}">
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
                </c:choose>

            </div>
        </div>
        <div class="span6">

        </div>
    </div>
</div>
<jsp:include page="mainFooter.jsp"/>