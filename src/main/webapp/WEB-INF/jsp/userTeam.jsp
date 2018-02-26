<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="mainHeader.jsp"/>

        <!-- start: Content -->
        <div id="content" class="span10 text-center">
            <h1><spring:message code="page.menu.userTeam.label"/></h1>
            <jsp:include page="infoMessage.jsp"/>
            <div class="row-fluid">
                <div class="span6">
                    <h2><spring:message code="userTeam.games.header"/></h2>
                    <table class="table">
                        <thead>
                        <tr>
                            <th><spring:message code="userTeam.table.game.name"/></th>
                            <th><spring:message code="userTeam.table.game.description"/></th>
                            <th><spring:message code="userTeam.table.game.teamCount"/></th>
                        </tr>
                        </thead>
                        <c:forEach items="${userGamesInformationList}" var="userGameInformation">
                            <tr>
                                <%--TODO texty pridat pres klice --%>
                                <c:url value="/user/userTeam" var="userTeamUrl">
                                    <c:param name="gameName" value="${userGameInformation.gameName}"/>
                                </c:url>
                                <th><a href="${userTeamUrl}">${userGameInformation.gameName}</a></th>
                                <th>${userGameInformation.gameDescription}</th>
                                <th>${userGameInformation.teamsCount}</th>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <div class="span6">
                    <c:if test="${not empty gameName}">
                    <c:choose>
                        <c:when test="${isNoTeamGame}">
                            <h2><spring:message code="userTeam.game.header"/> ${gameName}</h2>
                            <c:choose>
                                <c:when test="${not empty userNoTeamGameInformation}">
                                    <strong><p><spring:message code="userTeam.table.team.league"/></p></strong>
                                    <p><spring:message code="${userNoTeamGameInformation.code}"/></p>
                                </c:when>
                                <c:otherwise>
                                    <c:url value="/user/createDefaultTeam" var="createDefaultTeamUrl">
                                        <c:param name="gameName" value="${gameName}"/>
                                    </c:url>
                                    <a href="${createDefaultTeamUrl}"><spring:message code="userTeam.table.team.newGame"/></a>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${not empty userTeamInformationList}">
                            <h2><spring:message code="userTeam.team.header"/> ${gameName}</h2>
                            <table class="table">
                                <thead>
                                <tr>
                                    <th><spring:message code="userTeam.table.team.name"/></th>
                                    <th><spring:message code="userTeam.table.team.description"/></th>
                                    <th><spring:message code="userTeam.table.team.league"/></th>
                                    <th><spring:message code="userTeam.table.team.users"/></th>
                                </tr>
                                </thead>
                                <c:forEach items="${userTeamInformationList}" var="userTeam">
                                    <tr>
                                        <th>${userTeam.name}</th>
                                        <th>${userTeam.description}</th>
                                        <th><spring:message code="${userTeam.league.code}"/></th>
                                        <th>
                                            <c:forEach items="${userTeam.users}" var="user">
                                                <p>${user}</p>
                                            </c:forEach>
                                        </th>
                                    </tr>
                                </c:forEach>
                            </table>
                            </c:if>
                            <c:url value="/user/newTeam" var="newTeamUrl">
                                <c:param name="gameName" value="${gameName}"/>
                            </c:url>
                            <a href="${newTeamUrl}"><spring:message code="userTeam.table.team.newTeam"/></a>
                        </c:otherwise>
                    </c:choose>
                    </c:if>
                </div>
            </div>
<jsp:include page="mainFooter.jsp"/>