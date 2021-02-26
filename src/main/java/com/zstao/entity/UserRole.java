package com.zstao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * user_role
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRole implements Serializable {
    private Integer id;

    private Integer roleId;

    private Integer userId;

    private String rname;

    private String nickname;

    private String name;

    private String phone;
}