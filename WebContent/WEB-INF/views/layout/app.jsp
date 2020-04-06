<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>tweet システム</title>
        <link rel="stylesheet" href="<c:url value='/css/reset.css' />">
        <link rel="stylesheet" href="<c:url value='/css/style.css' />">
    </head>
    <body>
    <div id ="wrapper">
      <div id ="header">
         <div id="header_menu">
            <h1><a href="<c:url value='/'/>">tweet システム</a></h1>&nbsp;&nbsp;&nbsp;
            <c:if test="${sessionScope.login_poster !=null}">
               <a href="<c:url value='/posters/index'/>">登録ユーザー 一覧</a>&nbsp;
            </c:if>
         </div>
         <c:if test="${sessionScope.login_poster !=null}">
            <div id="poster_name">
               <c:out value="${sessionScope.login_poster.name}" />&nbsp;さん&nbsp;&nbsp;
               <a href="<c:url value='/tweet/myIndex'/>">マイページ</a>&nbsp;&nbsp;&nbsp;
               <a href="<c:out value='/logout'/>">ログアウト</a>
            </div>
         </c:if>
      </div>
      <div id="content">
        ${param.content}
      </div>
      <div id ="footer">
      by Reo Murata
      </div>
    </div>

    </body>
</html>