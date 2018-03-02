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
                        <p class="profil-50 text-right"><spring:message code="challenge.detail.game"/></p>
                    </strong>
                    <p class="profil-50 text-left">${challengeDetail.gameName}</p>
                </div>
                <div class="row-fluid">
                    <strong>
                        <p class="profil-50 text-right"><spring:message code="challenge.detail.team"/></p>
                    </strong>
                    <p class="profil-50 text-left">${challengeDetail.challengerTeamName}</p>
                </div>
                <div class="row-fluid">
                    <strong>
                        <p class="profil-50 text-right"><spring:message code="challenge.detail.start"/></p>
                    </strong>
                    <p class="profil-50 text-left">${challengeDetail.challengeStart}</p>
                </div>
                <div class="row-fluid">
                    <strong>
                        <p class="profil-50 text-right"><spring:message code="challenge.detail.end"/></p>
                    </strong>
                    <p class="profil-50 text-left">${challengeDetail.challengeEnd}</p>
                </div>
                <div class="row-fluid">
                    <strong>
                        <p class="profil-50 text-right"><spring:message code="challenge.detail.description"/></p>
                    </strong>
                    <p class="profil-50 text-left">${challengeDetail.text}</p>
                </div>
                <div class="row-fluid">
                    <strong>
                        <p class="profil-50 text-right"><spring:message code="challenge.detail.challengers"/></p>
                    </strong>
                    <div class="profil-50 text-left">
                        <c:forEach items="${challengeDetail.challengers}" var="users">
                            <p>${users}</p>
                        </c:forEach>
                    </div>
                </div>
                <div class="row-fluid">
                    <strong>
                        <p class="profil-50 text-right"><spring:message code="challenge.detail.opponents"/></p>
                    </strong>
                    <div class="profil-50 text-left">
                        <c:forEach items="${challengeDetail.opponents}" var="users">
                            <p>${users}</p>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <div class="span6">

        </div>
    </div>
</div>
<jsp:include page="mainFooter.jsp"/>