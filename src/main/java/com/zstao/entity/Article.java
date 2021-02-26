package com.zstao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * article
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Article implements Serializable {
    private Integer aid;

    private String title;

    private String userId;

    private String atypeId;

    private Integer viewNumber;

    private Timestamp createDate;

    private String aMain;

    private Integer status;

    private String likeNumber;

    private String nickname;

    private Integer commentNum;

}