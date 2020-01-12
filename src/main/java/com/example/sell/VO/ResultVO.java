package com.example.sell.VO;

import lombok.Data;

@Data //This annotation is like getter/setter is already done
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVO<T> {

    /* error code */
    private Integer code;

    /* information */
    private String msg;

    /* contents */
    private T data;
}
