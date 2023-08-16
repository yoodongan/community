<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="게시물 상세"></c:set>

<%@ include file="../common/head.jspf"%>

<section class="mt-5">
    <div class="container mx-auto px-3">
        <div class="table-1">
            <table class="w-full">
                <colgroup>
                    <col width="200"/>
                </colgroup>
                <tbody>
                <tr>
                    <th>번호</th>
                    <td>${article.id}</td>
                </tr>
                <tr>
                    <th>제목</th>
                    <td>${article.title}</td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td>${article.body}</td>
                </tr>
                <tr>
                    <th>작성자</th>
                    <td>${article.temp_writerName}</td>
                </tr>
                <tr>
                    <th>작성 날짜</th>
                    <td>${article.regDate.substring(2, 16)}</td>
                </tr>
                <tr>
                    <th>수정 날짜</th>
                    <td>${article.updateDate.substring(2, 16)}</td>
                </tr>
            </table>
        </div>
        <div class="mt-3">
            <button class="bg-transparent hover:bg-green-500 text-green-700 font-semibold hover:text-white py-2 px-4 border border-green-500 hover:border-transparent rounded" onclick="history.back();">뒤로 가기</button>
        </div>
    </div>

</section>

<%@ include file="../common/foot.jspf"%>

