package com.brad.community.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    private Long id;
    private Long memberId;
    private String regDate;
    private String updateDate;
    private String title;
    private String body;
    private Integer hitCount;

    private Integer temp_sumLikePoint;
    private String temp_writerName;
    private boolean temp_canDelete;
}
