<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="mainHeader.jsp"/>
        <div id="content" class="span10 text-center">
            <h1><spring:message code="page.menu.report.label"/></h1>
            <jsp:include page="infoMessage.jsp"/>
            <div class="row-fluid">
                <div class="span6">
                    <h2><spring:message code="page.menu.reportSend.label"/></h2>
                    <c:url value="/user/sendReport" var="sendReportUrl"/>
                    <form:form modelAttribute="userReportModel" action="${sendReportUrl}" method="post">
                        <div class="form-group">
                            <p><spring:message code="report.form.nickname.label"/></p>
                            <form:input cssClass="form-control" path="nickname"/>
                            <form:errors path="nickname" element="p" cssClass="error"/>
                        </div>
                        <div class="form-group">
                            <p><spring:message code="report.form.reason.label"/></p>
                            <form:select path="reason">
                                <c:forEach items="${reportReasonTypes}" var="reportReasonType">
                                    <form:option value="${reportReasonType}"><spring:message code="${reportReasonType.code}"/></form:option>
                                </c:forEach>
                            </form:select>
                            <p><form:errors path="reason" cssClass="error"/></p>
                        </div>
                        <div class="form-group">
                            <p><spring:message code="report.form.reasonText.label"/></p>
                            <form:input cssClass="form-control" path="reasonText"/>
                            <p><form:errors path="reasonText" cssClass="error"/></p>
                        </div>
                        <input type="submit" value="<spring:message code="report.form.confirmButton.label"/>" class="btn btn-default">
                    </form:form>
                </div>
                <div class="span6">
                    <h2><spring:message code="page.menu.myReports.label"/></h2>
                    <table class="table">
                        <thead>
                        <tr>
                            <th><spring:message code="reports.table.nickname.head"/></th>
                        </tr>
                        </thead>
                        <c:forEach items="${reportedUsersList}" var="reportedUser">
                        <tr>
                            <th>${reportedUser.nickname}</th>
                            <th><spring:message code="${reportedUser.reason.code}"/></th>
                            <c:url var="reportChangeUrl" value="/user/report">
                                <c:param name="userNickname" value="${reportedUser.nickname}"/>
                            </c:url>
                            <th><a href="${reportChangeUrl}"><spring:message code="reports.table.report.change"/></a></th>
                        </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
<jsp:include page="mainFooter.jsp"/>