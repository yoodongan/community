<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="게시물 상세"></c:set>

<%@ include file="../common/head.jspf"%>

<script>
    const params = {};
    params.id = parseInt('${param.id}');

    function increaseHitCount() {
        const localStorageKey = 'articleId : ' + params.id + ', alreadyWatched';

        if ( localStorage.getItem(localStorageKey) ) {
            return;
        }

        localStorage.setItem(localStorageKey, true);

        $.get('../article/doIncreaseHitCount', {
            id : params.id,
            ajaxMode: 'Y'
        }, function(data) {
            $('.hitCount').empty().html(data.data);
        }, 'json');
    }

    $(function() {
        increaseHitCount();
    })
</script>

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
                    <th>조회수</th>
                    <td class="hitCount">${article.hitCount}</td>
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
        <div class="mt-3 flex justify-between">
            <button class="bg-transparent hover:bg-green-500 text-green-700 font-semibold hover:text-white py-2 px-4 border border-green-500 hover:border-transparent rounded" onclick="history.back();">뒤로 가기</button>
            <div class="mt-2">
                <c:if test="${article.temp_canDelete}">
                <a
                   href="../article/modify?id=${article.id}"
                   class="mr-3 h-10 bg-transparent hover:bg-green-500 text-green-700 font-semibold hover:text-white py-2 px-4 border border-green-500 hover:border-transparent rounded">
                    수정하기
                </a>
                <a onclick="if (confirm('정말 삭제하시겠습니까?') == false) return false;"
                   href="../article/doDelete?id=${article.id}"
                   class="h-10 bg-transparent hover:bg-green-500 text-green-700 font-semibold hover:text-white py-2 px-4 border border-green-500 hover:border-transparent rounded">
                    삭제하기</a>
                </c:if>
            </div>
        </div>
    </div>

</section>

<%@ include file="../common/foot.jspf"%>

