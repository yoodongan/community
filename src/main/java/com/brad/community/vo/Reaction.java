package com.brad.community.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reaction {
    private Long id;
    private String regDate;
    private String updateDate;
    private Long memberId;
    private String relTypeCode;
    private Long articleId;
    private Integer point;
}
