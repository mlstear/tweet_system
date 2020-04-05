<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="/WEB-INF/views/layout/app.jsp">
  <c:param name="content">
     <c:choose>
       <c:when test="${poster!=null}">
          <h2>${poster.name}さんの登録情報　詳細ページ</h2>

          <table>
             <tbody>
                <tr>
                   <th>ユーザー名</th>
                   <td><c:out value="${poster.name}"/></td>
                </tr>
                 <tr>
                   <th>登録日時</th>
                   <td><fmt:formatDate value="${poster.created_at}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                </tr>
                <tr>
                   <th>更新日時</th>
                   <td><fmt:formatDate value="${poster.updated_at}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                </tr>

             </tbody>
          </table>
          <c:if test="${sessionScope.login_poster.id==poster.id}">
             <p><a href="<c:url value='/posters/edit?=${poster.id}'/>">登録情報を編集する</a><p>
          </c:if>
       </c:when>
       <c:otherwise>
         <h2>お探しのページは見つかりませんでした。</h2>
       </c:otherwise>
     </c:choose>
     <p><a href="<c:url value='/posters/index'/>">一覧に戻る</a></p>
  </c:param>
</c:import>