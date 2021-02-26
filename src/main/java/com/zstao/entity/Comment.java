package com.zstao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * comment
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment implements Serializable {
    private Integer cid;

    private String articleId;

    private String title;

    private String cMain;

    private String userId;

    private Timestamp createDate;

    private Integer status;

    private static final long serialVersionUID = 1L;

}