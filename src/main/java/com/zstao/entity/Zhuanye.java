package com.zstao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Zhuanye implements Serializable {
    private Integer id;
    private String sch_id;
    private String name;
    private Timestamp addTime;
    private String sch_name;
}
