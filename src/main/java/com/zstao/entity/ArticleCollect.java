package com.zstao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * article_collect
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleCollect implements Serializable {
    private Integer collectId;

    private String articleId;

    private String uId;

    private Timestamp createDate;

    private Integer status;

    private static final long serialVersionUID = 1L;

}