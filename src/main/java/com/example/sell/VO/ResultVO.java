package com.example.sell.VO;

import lombok.Data;

@Data //This annotation is like getter/setter is already done
public class ResultVO<T> {

    /* error code */
    private Integer code;

    /* information */
    private String msg;

    /* contents */
    private T data;
}
