package com.example.sell.VO;

import lombok.Data;
import java.io.Serializable;

@Data
public class ResultVO {

    /* error code */
    private Integer code;

    /* information */
    private String msg;

    /* contents */
    private T data;

}
