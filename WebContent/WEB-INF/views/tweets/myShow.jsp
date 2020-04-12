<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
  <c:param name="content">
     <c:choose>
        <c:when test="${tweet !=null}">
          <h2>このTweetを削除しますか？</h2>

          <table>
             <tbody>
               <tr>
                 <th>投稿者</th>
                 <td><c:out value="${tweet.poster.name}"/></td>
               </tr>
               <tr>
                  <th>登録日時</th>
                  <td>
                     <fmt:formatDate value="${tweet.created_at}" pattern="yyyy-MM-dd HH:mm" />
                  </td>
               </tr>
               <tr class="box1">
                 <th>投稿内容</th>
                 <td>
                   <pre><c:out value="${tweet.content}"/></pre>
                 </td>
               </tr>
             </tbody>
          </table>

          <p><a href="#" onclick="confirmDestroy();">削除 マイページから</a></p>
               <form method="POST" action="<c:url value='/tweets/myDestroy'/>">
                 <input type="hidden" name="_token" value="${_token}" />
               </form>
               <script>
                      function confirmDestroy() {
                          if(confirm("本当に削除してよろしいですか？")) {
                              document.forms[0].submit();
                        }
                    }
                </script>
        </c:when>
        <c:otherwise>
           <h2>お探しのデータは見つかりませんでした。</h2>
        </c:otherwise>
     </c:choose>
     <p><a href="<c:url value='/tweets/myIndex?id=${sessionScope.login_poster.id}'/>">マイページ トップに戻る</a></p>
  </c:param>
</c:import>