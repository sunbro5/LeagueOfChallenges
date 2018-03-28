<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="mainHeader.jsp"/>
<div id="content" class="span10 text-center">
    <h1><spring:message code="challengeResult.form.title.label"/></h1>
    <c:url value="/user/creteChallengeResult" var="creteChallengeResultUrl"/>
    <form:form modelAttribute="challengeResultModel" action="${creteChallengeResultUrl}" method="post">
        <form:hidden path="challengeId"/>
        <c:choose>
            <c:when test="${isUserChallenge}">
                <div class="form-group">
                    <p><spring:message code="challengeResult.form.yourScore.label"/></p>
                    <form:input cssClass="form-control" path="scoreChallenger"/>
                    <form:errors path="scoreChallenger" element="p" cssClass="error"/>
                </div>
                <div class="form-group">
                    <p><spring:message code="challengeResult.form.opponentScore.label"/></p>
                    <form:input cssClass="form-control" path="scoreOpponent"/>
                    <form:errors path="scoreOpponent" element="p" cssClass="error"/>
                </div>
                <div class="form-group">
                    <p><spring:message code="challengeResult.form.winner.label"/></p>
                    <form:radiobutton path="winnerTeamId" value="${challengeDetail.challengerTeamId}"/><spring:message
                        code="challengeResult.form.challengerWinner.label"/>
                    <form:radiobutton path="winnerTeamId" value="0"/><spring:message code="registration.form.draw.label"/>
                    <form:radiobutton path="winnerTeamId" value="${challengeDetail.opponentTeamId}"/><spring:message
                        code="registration.form.opponentWinner.label"/>
                </div>
                <input type="submit" value="<spring:message code="challengeResult.form.confirmButton.label"/>"
                       class="btn btn-default">
            </c:when>
            <c:when test="${isOpponentChallenge}">
                <div class="form-group">
                    <p><spring:message code="challengeResult.form.opponentScore.label"/></p>
                    <form:input cssClass="form-control" path="scoreChallenger"/>
                    <form:errors path="scoreChallenger" element="p" cssClass="error"/>
                </div>
                <div class="form-group">
                    <p><spring:message code="challengeResult.form.yourScore.label"/></p>
                    <form:input cssClass="form-control" path="scoreOpponent"/>
                    <p><form:errors path="scoreOpponent" cssClass="error"/></p>
                </div>
                <div class="form-group">
                    <p><spring:message code="challengeResult.form.winner.label"/></p>
                    <form:radiobutton path="winnerTeamId" value="${challengeDetail.challengerTeamId}"/><spring:message
                        code="registration.form.opponentWinner.label"/>
                    <form:radiobutton path="winnerTeamId" value="0"/><spring:message code="registration.form.draw.label"/>
                    <form:radiobutton path="winnerTeamId" value="${challengeDetail.opponentTeamId}"/><spring:message
                        code="challengeResult.form.challengerWinner.label"/>
                </div>
                <input type="submit" value="<spring:message code="challengeResult.form.confirmButton.label"/>"
                       class="btn btn-default">
            </c:when>
        </c:choose>
    </form:form>
</div>
<jsp:include page="mainFooter.jsp"/>