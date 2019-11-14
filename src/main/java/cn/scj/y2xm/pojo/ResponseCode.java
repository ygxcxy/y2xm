package cn.scj.y2xm.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author by Shaochenjie
 * @Classname ResponseCode
 * @Description TODO
 * @Date 2019/11/13 16:56
 */
@Setter
@Getter
public class ResponseCode implements Serializable {

    public static Integer SUCCESS = 4000;

    public static Integer FAIL = 2000;

    private Integer code;

    private String msg;

    private Object data;


}
