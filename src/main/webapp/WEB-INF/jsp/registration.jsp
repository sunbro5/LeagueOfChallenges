<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="mainHeader.jsp"/>
        <div id="content" class="span10 text-center">
            <h1><spring:message code="registration.form.title.label"/></h1>

            <p>${registrationMessage}</p>
            <c:url value="/createUser" var="createUserUrl"/>
            <form:form modelAttribute="registrationModel" action="${createUserUrl}" method="post">
                <div class="form-group">
                    <p><spring:message code="registration.form.firstName.label"/></p>
                    <form:input cssClass="form-control" path="firstName"/>
                    <form:errors path="firstName" element="p" cssClass="error"/>
                </div>
                <div class="form-group">
                    <p><spring:message code="registration.form.lastName.label"/></p>
                    <form:input cssClass="form-control" path="lastName"/>
                    <p><form:errors path="lastName" cssClass="error"/></p>
                </div>
                <div class="form-group">
                    <p><spring:message code="registration.form.username.label"/></p>
                    <form:input cssClass="form-control" path="nickname"/>
                    <p><form:errors path="nickname" cssClass="error"/></p>
                </div>
                <div class="form-group">
                    <p><spring:message code="registration.form.email.label"/></p>
                    <form:input cssClass="form-control" path="email"/>
                    <p><form:errors path="email" cssClass="error"/></p>
                </div>
                <div class="form-group">
                    <p><spring:message code="registration.form.password.label"/></p>
                    <form:password cssClass="form-control" path="password"/>
                    <p><form:errors path="password" cssClass="error"/></p>
                </div>
                <div class="form-group">
                    <p><spring:message code="registration.form.passwordConfirm.label"/></p>
                    <form:password cssClass="form-control" path="passwordConfirm"/>
                    <p><form:errors path="passwordConfirm" cssClass="error"/></p>
                </div>
                <input type="submit" value="<spring:message code="registration.form.confirmButton.label"/>" class="btn btn-default">
            </form:form>
        </div>
<jsp:include page="mainFooter.jsp"/>