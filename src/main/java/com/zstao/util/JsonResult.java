package com.zstao.util;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

/**
 * @author
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonResult {
    private int code;
    private String msg;
    private String data;
    public void setData(Object o){
        this.data=JSON.toJSONString(o);
    }
}
