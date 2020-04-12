<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
   <c:param name="content">
      <c:if test="${flush!=null}">
         <div id="flush_success">
            <c:out value="${flush}"></c:out>
         </div>
      </c:if>
      <h2>ユーザー　一覧</h2>
      <table id="posters_list">
         <tbody>
            <tr>
               <th>ユーザー名</th>
               <th>投稿</th>
               <th>詳細</th>
            </tr>
            <c:forEach var="poster" items="${posters}" varStatus="status">
               <tr class="row${status.count%2}">
                 <td><c:out value="${poster.name}"/></td>
                 <td>
                   <a href="<c:url value='/tweets/userIndex?id=${poster.id}'/>">このユーザーの投稿を見る</a>
                 </td>
                 <td>
                   <a href="<c:url value='/posters/show?id=${poster.id}'/>">詳細を表示(編集はこちら)</a>
                 </td>
               </tr>
            </c:forEach>
         </tbody>
      </table>

      <div id="pagination">
        (全${posters_count}件)<br/>
        <c:forEach var="i" begin="1" end="${((posters_count-1)/20)+1}" step="1">
           <c:choose>
              <c:when test="${i==page}">
                 <c:out value="${i}"/>&nbsp;
              </c:when>
              <c:otherwise>
                <a href="<c:url value='/posters/index?page=${i}'/>"><c:out value="${i}"/></a>
              </c:otherwise>
           </c:choose>
        </c:forEach>
      </div>
      <p><a href="<c:url value='/index.html'/>">トップページに戻る</a></p>
   </c:param>

</c:import>
