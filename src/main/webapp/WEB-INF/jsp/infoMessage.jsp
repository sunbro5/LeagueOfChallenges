<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div id="userMessages">
    <c:forEach items="${userInfoMessages}" var="userInfo">
        <p><spring:message code="${userInfo}"/></p>
    </c:forEach>
</div>