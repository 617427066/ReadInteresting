package com.zstao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * user_like
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLike implements Serializable {
    private Integer likeId;

    private String userId;

    private String authorId;

    private Timestamp createDate;

    private Integer status;

    private static final long serialVersionUID = 1L;

}