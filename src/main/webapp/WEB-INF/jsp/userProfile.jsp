<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="mainHeader.jsp"/>
        <div id="content" class="span10 text-center">
            <h1><spring:message code="menu.user.profile.title"/></h1>

            <div class="profil-box">
                <div class="row-fluid">
                    <strong>
                    <p class="span6 text-right"><spring:message code="user.profile.userInformation.nickname"/></p>
                    </strong>
                    <p class="span6 text-left">${userByNicknameInfo.nickname}</p>
                </div>
                <div class="row-fluid">
                    <strong>
                    <p class="span6 text-right"><spring:message code="user.profile.userInformation.firstName"/></p>
                    </strong>
                    <p class="span6 text-left">${userByNicknameInfo.firstName}</p>
                </div>
                <div class="row-fluid">
                    <strong>
                    <p class="span6 text-right"><spring:message code="user.profile.userInformation.email"/></p>
                    </strong>
                    <p class="span6 text-left">${userByNicknameInfo.email}</p>
                </div>
                <div class="row-fluid">
                    <strong>
                    <p class="span6 text-right"><spring:message code="user.profile.userInformation.lastLogin"/></p>
                    </strong>
                    <p class="span6 text-left">${userByNicknameInfo.lastLogin}</p>
                </div>
                <div class="row-fluid">
                    <strong>
                    <p class="span6 text-right"><spring:message code="user.profile.userInformation.rating"/></p>
                    </strong>
                    <p class="span6 text-left">${userByNicknameInfo.rating}</p>
                </div>
                <div class="row-fluid">
                    <strong>
                    <p class="span6 text-right"><spring:message code="user.profile.userInformation.reports"/></p>
                    </strong>
                    <div class="span6 text-left">
                        <c:forEach items="${userByNicknameInfo.reportList}" var="report">
                            <p><spring:message code="${report.key.code}"/> = ${report.value}</p>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
<jsp:include page="mainFooter.jsp"/>