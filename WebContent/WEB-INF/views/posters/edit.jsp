<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
  <c:param name="content">
     <c:choose>
        <c:when test="${poster!=null}">
           <h2>${poster.name}の登録情報　編集ページ</h2>
           <p>（パスワードを変更する場合のみ入力してください）</p>
           <form method="POST" action="<c:url value='/posters/update'/>">
              <c:import url="_form.jsp"/>
           </form>

           <p><a href="#" onclick="confirmDestroy();">このユーザー情報を削除する</a></p>
           <form method="POST" action="<c:url value='/posters/destroy'/>">
              <input type="hidden" name="_token" value="${_token}"/>
           </form>
           <script>
              function confirmDestroy(){
                  if(confirm("本当に削除してよろしいでしょうか。")){
                      document.forms[1].submit();
                  }
              }

           </script>
        </c:when>
         <c:otherwise>
           <h2>お探しのデータは見つかりませんでした。</h2>
         </c:otherwise>
     </c:choose>

     <p><a href="<c:url value='/posters/index'/>">ユーザー一覧に戻る</a></p>

  </c:param>
</c:import>