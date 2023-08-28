<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="${board.name}"></c:set>
<%@ include file="../common/head.jspf"%>

<div class="container mx-auto px-3 mt-5">
    <div class="mb-1">
        게시물 개수 : <span class="bg-green-100 text-green-800 text-xs font-medium mr-2 px-2.5 py-0.5 rounded dark:bg-green-900 dark:text-green-300">
                    ${articlesCount}
                    </span>개
    </div>
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

    <!-- tailwind pagination -->
    <nav aria-label="Page text-center" class="my-3">
        <ul class="flex justify-center text-sm"> <!-- flex justify-center 추가 -->
            <c:set var="pageMenuArmLen" value="4"/>
            <c:set var="startPage" value="${page - pageMenuArmLen >= 1 ? page - pageMenuArmLen : 1}"/>
            <c:set var="endPage" value="${page + pageMenuArmLen <= totalPage ? page + pageMenuArmLen : totalPage}"/>

            <li>
                <a href="?page=${page-1 < 1 ? 1 : page-1}&boardId=${boardId}" class="flex items-center justify-center px-3 h-8 leading-tight text-gray-500 bg-white border border-gray-300 rounded-l-lg hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">
                    &lt;&lt;
                </a>
            </li>
            <c:forEach begin="${startPage}" end="${endPage}" var="i">
                <li>
                    <a href="?page=${i}&boardId=${boardId}" class="flex items-center justify-center px-3 h-8 leading-tight text-gray-500 bg-white border border-gray-300 hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white
                    ${page == i ? 'bg-green-200 text-gray-700' : ''}">
                        ${i}
                    </a>
                </li>
            </c:forEach>
            <li>
                <a href="?page=${page + 1 > totalPage ? totalPage : page + 1}&boardId=${boardId}" class="flex items-center justify-center px-3 h-8 leading-tight text-gray-500 bg-white border border-gray-300 rounded-r-lg hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">
                    &gt;&gt;
                </a>
            </li>
        </ul>
        </ul>
    </nav>

</div>



<%@ include file="../common/foot.jspf"%>