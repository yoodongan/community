<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="게시물 작성"></c:set>

<%@ include file="../common/head.jspf"%>

<section class="mt-5">
    <div class="container mx-auto px-3">
        <form method="POST" action="../article/doWrite" class="table-1">
            <table class="w-full">
                <colgroup>
                    <col width="200"/>
                </colgroup>
                <tbody>
                    <tr>
                        <th>작성자</th>
                        <td class="writer">
                            ${req.member.nickname}
                        </td>
                    </tr>
                    <tr>
                        <th>게시판</th>
                        <td>
                            <select name="boardId" class="w-full text-gray-500 bg-gray-50 border border-gray-300 bg-gray-100 text-sm rounded-lg focus:ring-green-500 focus:border-green-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-green-500 dark:focus:border-green-500">
                                <option selected disabled>게시판을 선택하세요.</option>
                                <option value="1">공지사항</option>
                                <option value="2">자유게시판</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>제목</th>
                        <td>
                            <input type="text" name="title" placeholder="게시글 제목을 입력해주세요." class="w-full bg-gray-50 border border-gray-300 bg-gray-100 text-sm rounded-lg focus:ring-green-500 focus:border-green-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-green-500 dark:focus:border-green-500" />
                        </td>

                    </tr>
                    <tr>
                        <th>내용</th>
                        <td>
                            <textarea name="body" rows ="10" class="w-full bg-gray-50 border border-gray-300 bg-gray-100 text-sm rounded-lg focus:ring-green-500 focus:border-green-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-green-500 dark:focus:border-green-500"></textarea>
                        </td>
                    </tr>
                </tbody>
            </table>

            <div class="mt-3 flex justify-between">
                <button type=button class="h-10 bg-transparent hover:bg-green-500 text-green-700 font-semibold hover:text-white px-4 border border-green-500 hover:border-transparent rounded" onclick="history.back();">뒤로 가기</button>
                <div>
                    <c:if test="${req.isLogin()}">
                        <button
                                type="submit"
                                class="cursor-pointer h-10 bg-transparent hover:bg-green-500 text-green-700 font-semibold hover:text-white px-4 border border-green-500 hover:border-transparent rounded">
                            작성하기
                        </button>
                    </c:if>
                </div>
            </div>
        </form>
    </div>

</section>

<%@ include file="../common/foot.jspf"%>

