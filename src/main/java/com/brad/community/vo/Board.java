package com.brad.community.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board {
    private Long id;
    private String regDate;
    private String updateDate;
    private String code;
    private String name;
    private boolean delStatus;
    private boolean delDate;
}
