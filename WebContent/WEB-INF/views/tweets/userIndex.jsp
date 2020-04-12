<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="../layout/app.jsp">
   <c:param name="content">
     <c:if test="${flush!=null}">
        <div id="flush_success">
           <c:out value="${flush}"></c:out>
        </div>
     </c:if>
     <h2><c:out value="${posterName}"/> さんの投稿一覧</h2>

     <table id="tweet_list">
        <tbody>
           <c:forEach var="tweet" items="${tweets}" varStatus="status">
              <tr class="row${status.count%2}">
                 <td class="tweet_name">
                    <c:out value="${tweet.poster.name}" />&nbsp;&nbsp;&nbsp;
                 </td>
                 <td class="tweet_created_at"><fmt:formatDate value="${tweet.created_at}" pattern="yyyy-MM-dd HH:mm"/></td>
                 <td class="tweet_content">
                    <pre><c:out value="${tweet.content}" /></pre>
                 </td>
                 <td class="tweet_delete">
                    <c:if test="${sessionScope.login_poster.id==tweet.poster.id}">
                       <a href="<c:url value='/tweets/userShow?id=${tweet.id}'/>">投稿を削除する</a>
                    </c:if>
                 </td>
              </tr>
           </c:forEach>
        </tbody>
     </table>
      <div id="pagination">
        （全${tweets_count}件）<br/>
         <c:forEach var="i" begin="1" end="${((tweets_count-1)/30)+1}" step="1">
            <c:choose>
               <c:when test="${i==page}">
                  <c:out value="${i}" />&nbsp;
               </c:when>
               <c:otherwise>
                 <a href="<c:url value='/tweets/userIndex?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
               </c:otherwise>
            </c:choose>
         </c:forEach>
     </div>
     <p><a href="<c:url value='/posters/index' />">ユーザー 一覧に戻る</a></p>
   </c:param>
</c:import>