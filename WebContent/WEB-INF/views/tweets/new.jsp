<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
  <c:param name="content">
     <h2>新規　投稿ページ</h2>
     <c:if test="${errors!=null}">
        <div id="flush_error">
           入力内容にエラーがあります。<br/>
           <c:forEach var="error" items="${errors}">
              .<c:out value="${error}" /><br />
           </c:forEach>
        </div>
     </c:if>
     <form method="POST" action="<c:url value='/tweets/create'/>">
       <label for="name">ユーザー名</label><br />
       <c:out value="${sessionScope.login_poster.name}"/>
       <br /><br />

       <label for="content">投稿内容</label><br />
       <textarea name="content" rows="10" cols="50">${tweets.content}</textarea>
       <br /><br />

       <input type="hidden" name="_token" value="${_token}"/>
       <button type="submit">投稿</button>
     </form>

     <p><a href="<c:url value='/index.html' />">トップに戻る</a></p>
  </c:param>
</c:import>