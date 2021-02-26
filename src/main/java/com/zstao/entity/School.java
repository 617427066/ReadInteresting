package com.zstao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class School implements Serializable {
    private Integer id;
    private String college;
    private String number;
    private String introduce;
    private String status;
    private Timestamp addTime;
}
