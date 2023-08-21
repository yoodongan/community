<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="게시물 수정"></c:set>

<%@ include file="../common/head.jspf"%>

<section class="mt-5">
    <div class="container mx-auto px-3">
        <form method="POST" action="../article/doModify" class="table-1">
            <input type="hidden" name="id" value="${article.id}"/>
            <table class="w-full">
                <colgroup>
                    <col width="200"/>
                </colgroup>
                <tbody>
                <tr>
                    <th>작성자</th>
                    <td>${req.member.nickname}</td>
                </tr>
                <tr>
                    <th>제목</th>
                    <td>
                        <input type="text" name="title" placeholder="게시글 제목을 입력해주세요." value="${article.title}" class="w-full bg-gray-100 p-2 rounded-lg border-1 border-green-200 shadow-sm focus:border-green-500" />
                    </td>

                </tr>
                <tr>
                    <th>내용</th>
                    <td>
                        <textarea name="body" rows ="10" class="w-full bg-gray-100 p-2 rounded-lg border-1 border-green-200 shadow-sm focus:border-green-500">${article.body}</textarea>
                    </td>
                </tr>
            </table>

            <div class="mt-3 flex justify-between">
                <button type=button class="h-10 bg-transparent hover:bg-green-500 text-green-700 font-semibold hover:text-white py-2 px-4 border border-green-500 hover:border-transparent rounded" onclick="history.back();">뒤로 가기</button>
                <div>
                    <c:if test="${req.isLogin()}">
                        <button
                                type="submit"
                                class="cursor-pointer h-10 bg-transparent hover:bg-green-500 text-green-700 font-semibold hover:text-white py-2 px-4 border border-green-500 hover:border-transparent rounded">
                            수정하기
                        </button>
                    </c:if>
                </div>
            </div>
        </form>
    </div>

</section>

<%@ include file="../common/foot.jspf"%>

