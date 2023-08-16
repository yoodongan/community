<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="게시물 전체 조회"></c:set>
<%@ include file="../common/head.jspf"%>

<div class="container mx-auto px-3 mt-5">
    <div class="table-1">
        <table class="w-full">
            <colgroup>
                <col width="50"/>
                <col width="200"/>
                <col width="100"/>
                <col width="100"/>
                <col width="100"/>
            </colgroup>
            <thead>
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성날짜</th>
                <th>수정날짜</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="article" items="${articles}">
                <tr>
                    <td>${article.id}</td>
                    <td>
                        <a href="../article/detail?id=${article.id}">${article.title}</a>
                    </td>
                    <td>${article.temp_writerName}</td>
                    <td>${article.regDate.substring(2,16)}</td>
                    <td>${article.updateDate.substring(2,16)}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>



<%@ include file="../common/foot.jspf"%>