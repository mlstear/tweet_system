<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="../layout/app.jsp">
  <c:param name="content">
     <c:if test="${flush!=null}">
        <div id="flush_success">
           <c:out value="${flush}"></c:out>
        </div>
     </c:if>
     <h2>最新のtweet</h2>
     <p><a href="<c:url value='/tweets/new'/>">新規　投稿する</a></p>
     <table id="tweet_list">
        <tbody>
          <c:forEach var="tweet" items="${tweets}" varStatus="status">
            <tr class="row${status.count%2}">
               <td class="tweet_name">
                  <c:out value="${tweet.poster.name}"/>&nbsp;&nbsp;
                  <!-- ここにフォローシステムいれる -->
               </td>
               <td class="tweet_created_at"><fmt:formatDate value="${tweet.created_at}" pattern="yyyy-MM-dd HH:mm"/></td>
               <td class="tweet_content">
                  <pre><c:out value="${tweet.content}" /></pre>
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
                 <a href="<c:url value='/index.html?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
               </c:otherwise>
            </c:choose>
         </c:forEach>
     </div>
  </c:param>
</c:import>