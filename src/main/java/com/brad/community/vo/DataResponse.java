package com.brad.community.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class DataResponse<T> {
    private String responseCode;
    private String msg;
    @JsonProperty(value = "data")
    private T data;

    // 성공 응답
    public static DataResponse of(String responseCode, String msg, Object data) {
        return new DataResponse<>(responseCode, msg, data);
    }
    // 실패 응답 : data가 null이 들어간다.
    public static DataResponse of(String responseCode, String msg) {
        return of(responseCode, msg, null);
    }

    public static DataResponse ofNew(DataResponse dataResponse, Object newData) {
        return of(dataResponse.getResponseCode(), dataResponse.getMsg(), newData);
    }

    // 응답 객체가 성공인지 확인하는 메서드, "S-" 로 시작하면 성공이다.
    public boolean isSuccess() {
        return responseCode.startsWith("S-");
    }
    public boolean isFail() {
        return isSuccess() == false;  // 성공 메서드의 반대이면, 실패이다.
    }
}
