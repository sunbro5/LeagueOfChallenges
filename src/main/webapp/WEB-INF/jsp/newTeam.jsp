<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="mainHeader.jsp"/>
        <!-- start: Content -->
        <div id="content" class="span10 text-center">
            <h1><spring:message code="newTeam.form.title.label"/></h1>
            <jsp:include page="infoMessage.jsp"/>
            <p>${registrationMessage}</p>
            <c:url value="/user/createTeam" var="createTeamUrl"/>
            <form:form modelAttribute="newTeamModel" action="${createTeamUrl}" method="post">
                <form:hidden path="gameName"/>
                <div class="form-group">
                    <p><spring:message code="newTeam.form.name.label"/></p>
                    <form:input cssClass="form-control" path="name"/>
                    <form:errors path="name" element="p" cssClass="error"/>
                </div>
                <div class="form-group">
                    <p><spring:message code="newTeam.form.description.label"/></p>
                    <form:input cssClass="form-control" path="description"/>
                </div>
                <c:forEach begin="0" end="${teamUsersCount - 1}" var="i">
                    <div class="form-group">
                        <p><spring:message code="newTeam.form.users.label"/> ${i + 1}</p>
                        <form:input cssClass="form-control" path="users[${i}]"/>
                        <p><form:errors path="users[${i}]" cssClass="error"/></p>
                    </div>
                </c:forEach>
                <input type="submit" value="<spring:message code="registration.form.confirmButton.label"/>" class="btn btn-default">
            </form:form>
        </div>
<jsp:include page="mainFooter.jsp"/>