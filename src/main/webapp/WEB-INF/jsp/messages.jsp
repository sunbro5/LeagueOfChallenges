<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="mainHeader.jsp"/>
        <div id="content" class="span10 text-center">

            <div class="row-fluid">

                <div class="span6">
                    <h1><spring:message code="messages.inbox.title"/></h1>
                    <jsp:include page="infoMessage.jsp"/>
                    <ul class="messagesList">
                        <c:forEach items="${userMessagesPreview}" var="mesagePreview">
                            <c:url value="/user/messages" var="showMessageUrl">
                                <c:param name="friendMessages" value="${mesagePreview.userNickname}"/>
                            </c:url>
                            <a href="${showMessageUrl}">
                            <c:choose>
                                <c:when test="${mesagePreview.alreadyRead == 0}">
                                    <li class="already-read">
                                        <span class="from"><span class="glyphicons star "><i></i></span> ${mesagePreview.userNickname} </span><span class="title">${mesagePreview.textPreview}</span><span class="date">${mesagePreview.textPreviewDate}</span>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li>
                                        <span class="from"><span class="glyphicons envelope"><i></i></span> ${mesagePreview.userNickname} </span><span class="title">${mesagePreview.textPreview}</span><span class="date">${mesagePreview.textPreviewDate}</span>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                            </a>
                        </c:forEach>
                    </ul>
                    <div class="common-modal modal fade" id="common-Modal1" tabindex="-1" role="dialog" aria-hidden="true">
                        <div class="modal-content">
                            <ul class="list-inline item-details">
                                <li><a href="http://themifycloud.com">Admin templates</a></li>
                                <li><a href="http://themescloud.org">Bootstrap themes</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="span6 chat-form noMarginLeft">
                    <c:url value="/user/sendMessage" var="sendMessageUrl"/>
                    <form:form cssClass="dark" modelAttribute="sendMessageModel" action="${sendMessageUrl}" method="post">
                    <div>
                        <p><spring:message code="message.form.toNickname.label" /></p>
                        <form:input path="toNickname"/>
                        <p><form:errors path="toNickname" cssClass="error"/></p>
                    </div>
                    <div class="box-content">
                        <div id="scroll-chat" class="scrollable-chat">

                            <ul class="chat">
                                <c:forEach items="${userMessages}" var="userMessage">
                                    <c:set var="messagePosition" value="right"/>
                                    <c:set var="messageFrom" value="${userName}"/>
                                    <c:if test="${userMessage.received}">
                                        <c:set var="messagePosition" value="left"/>
                                        <c:set var="messageFrom" value="${sendMessageModel.toNickname}"/>
                                    </c:if>

                                <li class="${messagePosition}">
								<span class="message"><span class="arrow"></span>
									<span class="from">${messageFrom}</span>
									<span class="time">${userMessage.sentDate}</span>
									<span class="text">
										${userMessage.text}
									</span>
								</span>
                                </li>

                                </c:forEach>
                            </ul>
                        </div>
                        <div class="chat-form">
                            <form:textarea path="text"></form:textarea>
                            <form:errors path="text" cssClass="error"/>
                            <input type="submit" class="btn btn-info" value="<spring:message code="message.form.sendConfirm.label" />">
                        </div>
                    </div>
                    </form:form>
                </div>
            </div>
<jsp:include page="mainFooter.jsp"/>