<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="mainHeader.jsp"/>
    <div id="content" class="span10">
        Language : <a href="?language=en">English</a> | <a href="?language=cz">Czech</a><br><br>

        <a href="/createTest">Create test</a><br>
        <a href="/updateTest">Update test</a><br>
        <a href="/findByIdTest">Find by id test</a><br>
        <a href="/findAllTest">Find all test</a><br>
        <a href="/deleteTest">Delete test</a><br>
        <br>
        <br>
        ${created}
        ${updated}
        ${deleted}
        ${role}
        <c:forEach items="${roleList}" var="role">
            ${role}<br>
        </c:forEach>


<jsp:include page="mainFooter.jsp"/>