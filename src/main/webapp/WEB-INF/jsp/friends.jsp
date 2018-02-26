<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="mainHeader.jsp"/>
        <div id="content" class="span10 text-center">
            <h1><spring:message code="friends.page.title.label"/></h1>
            <jsp:include page="infoMessage.jsp"/>
            <div class="row-fluid">
                <div class="span6">
                    <h2><spring:message code="friends.list.title.label"/></h2>
                    <table class="table">
                        <thead>
                        <tr>
                            <th><spring:message code="friends.list.tableHead.nickname"/></th>
                        </tr>
                        </thead>
                        <c:forEach items="${userFriendList}" var="userFriend">
                            <tr>
                                <th>${userFriend.nickname}</th>
                                <c:url value="/user/messages" var="friendMessageUrl">
                                    <c:param name="friendMessages" value="${userFriend.nickname}"/>
                                </c:url>
                                <th><a href="${friendMessageUrl}"><spring:message code="friends.list.sendMessage.label"/></a></th>
                                <c:url value="/user/userProfile" var="userProfileUrl">
                                    <c:param name="userNickname" value="${userFriend.nickname}"/>
                                </c:url>
                                <th><a href="${userProfileUrl}"><spring:message code="friends.list.userProfile.label"/></a></th>
                                <c:url value="/user/deleteFriend" var="deleteFriendUrl">
                                    <c:param name="nickname" value="${userFriend.nickname}"/>
                                </c:url>
                                <th><a href="${deleteFriendUrl}"><spring:message code="friends.list.removeFriend.label"/></a></th>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <div class="span6">
                    <h2><spring:message code="friends.search.title.label"/></h2>
                    <c:url value="/user/findFriendUser" var="findFriendUserUrl"/>
                    <form action="${findFriendUserUrl}" method="get">
                        <div class="form-group">
                            <input type="text" name="nickname"/>
                        </div>
                        <input type="submit" value="<spring:message code="friends.search.confirmButton.label"/>" class="btn btn-default"/>
                    </form>
                    <c:if test="${not empty foundUsersList}">
                        <table class="table">
                            <thead>
                            <tr>
                                <th><spring:message code="friends.list.tableHead.nickname"/></th>
                            </tr>
                            </thead>
                            <c:forEach items="${foundUsersList}" var="foundUser">
                                <tr>
                                    <th>${foundUser}</th>
                                    <c:url value="/user/userProfile" var="userProfileUrl">
                                        <c:param name="userNickname" value="${foundUser}"/>
                                    </c:url>
                                    <th><a href="${userProfileUrl}"><spring:message code="friends.list.userProfile.label"/></a></th>
                                    <c:url value="/user/createFriend" var="createFriendUrl">
                                        <c:param name="nickname" value="${foundUser}"/>
                                    </c:url>
                                    <th><a href="${createFriendUrl}"><spring:message code="friends.list.createFriend.link"/></a></th>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:if>
                </div>
            </div>

        </div>
<jsp:include page="mainFooter.jsp"/>