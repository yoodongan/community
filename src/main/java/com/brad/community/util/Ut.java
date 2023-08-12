package com.brad.community.util;

public class Ut {
    // 유효성 체크를 위한 메서드
    public static boolean isEmpty(Object obj) {
        if (obj == null) return true;  // 비어 있다.
        if (obj instanceof String == false) return true; // String 이 아니면, 비어 있는 것이다.
        String str = (String) obj;
        if (str.trim().length() == 0) return true;  // 공백 제거 후 길이 0이어도 empty. ("" 이 들어왔을 때를 처리해야 함)
        return false;
    }
    public static String f(String format, Object... args) {
        return String.format(format, args);
    }
}
