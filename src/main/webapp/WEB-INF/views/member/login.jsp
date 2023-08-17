<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="로그인"></c:set>

<%@ include file="../common/head.jspf"%>

<form method="post" action="../member/doLogin" class="p-10 xs:p-0 mx-auto md:w-full md:max-w-md">
    <div class="bg-gray-200 w-full rounded-lg divide-y divide-gray-200 p-5">
        <h1 class="font-bold text-center text-2xl">Community Service</h1>
        <div class="px-5 py-7">
            <c:if test="${!req.isLogin()}">
            <label class="font-semibold text-sm text-gray-600 pb-1 block">아이디</label>
            <input name="loginId" placeholder="아이디를 입력해주세요." type="text" class="border rounded-lg px-3 py-2 mt-1 mb-5 text-sm w-full" />
            <label class="font-semibold text-sm text-gray-600 pb-1 block">비밀번호</label>
            <input name="loginPw" placeholder="비밀번호를 입력해주세요." type="password" class="border rounded-lg px-3 py-2 mt-1 mb-5 text-sm w-full" />
            </c:if>

            <c:if test="${!req.isLogin()}">
            <button type="submit" class="transition duration-200 bg-green-500 hover:bg-green-600 focus:bg-green-700 focus:shadow-sm focus:ring-4 focus:ring-green-500 focus:ring-opacity-50 text-white w-full py-2.5 rounded-lg text-sm shadow-sm hover:shadow-md font-semibold text-center inline-block">
                <span class="inline-block mr-2">Login</span>
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor" class="w-4 h-4 inline-block">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 8l4 4m0 0l-4 4m4-4H3" />
                </svg>
            </button>
            </c:if>
            <c:if test="${req.isLogin()}">
            <button type="button" onclick="location.href='../member/doLogout'" class="mt-3 transition duration-200 bg-green-500 hover:bg-green-600 focus:bg-green-700 focus:shadow-sm focus:ring-4 focus:ring-green-500 focus:ring-opacity-50 text-white w-full py-2.5 rounded-lg text-sm shadow-sm hover:shadow-md font-semibold text-center inline-block">
                <span class="inline-block mr-2">Logout</span>
            </button>
            </c:if>
        </div>

        <div class="grid grid-cols-2 gap-1">
            <div class="text-center sm:text-left whitespace-nowrap">
                <button class="transition duration-200 mx-5 px-5 py-4 cursor-pointer font-normal text-sm rounded-lg text-gray-500 hover:bg-gray-100 focus:outline-none focus:bg-gray-200 focus:ring-2 focus:ring-gray-400 focus:ring-opacity-50 ring-inset">
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor" class="w-4 h-4 inline-block align-text-top">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 11V7a4 4 0 118 0m-4 8v2m-6 4h12a2 2 0 002-2v-6a2 2 0 00-2-2H6a2 2 0 00-2 2v6a2 2 0 002 2z" />
                    </svg>
                    <span class="inline-block ml-1">Forgot Password</span>
                </button>
            </div>
            <div class="text-center sm:text-right  whitespace-nowrap">
                <button class="transition duration-200 px-5 py-4 cursor-pointer font-normal text-sm rounded-lg text-gray-500 hover:bg-gray-100 focus:outline-none focus:bg-gray-200 focus:ring-2 focus:ring-gray-400 focus:ring-opacity-50 ring-inset">
                </button>
            </div>
        </div>
    </div>
</form>

<%@ include file="../common/foot.jspf"%>