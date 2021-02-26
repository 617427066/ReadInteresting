package com.zstao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NumberWelcome implements Serializable {
    private Integer userNumber;
    private Integer articleNumber;
    private Integer commentNumber;
    private Integer typeNumber;

    private static final long serialVersionUID = 1L;
}
