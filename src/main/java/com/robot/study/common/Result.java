package com.robot.study.common;

import com.robot.study.exception.MyException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 定义 RESTFul 返回 JSON 数据的消息类
 */
@Data
@AllArgsConstructor
@ApiModel(value = "消息返回结构体")
public class Result<T> {

    @ApiModelProperty("信息")
    private String message;

    @ApiModelProperty("状态码")
    private int retCode;

    @ApiModelProperty("返回对象信息")
    private T data;

    private Result(T data) {
        this.retCode = 0;
        this.message = "成功";
        this.data = data;
    }

    private Result(Integer code, String msg) {
        this.retCode = code;
        this.message = msg;
    }

    private Result(MyException cm) {
        if(cm == null){
            return;
        }
        this.retCode = cm.getRetCode();
        this.message = cm.getMessage();
    }
    /**
     * 自定义信息调用
     * @return
     */
    public static <T> Result<T> info(Integer code,String msg){
        return new Result<T>(code,msg);
    }

    /**
     * 成功时候的调用
     * @return
     */
    public static <T> Result<T> success(T data){
        return new Result<T>(data);
    }
    /**
     * 成功，不需要传入参数
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Result<T> success(){
        return (Result<T>) success("");
    }

    /**
     * 成功时候的调用，传信息，不传数据
     * @return
     */
    public static <T> Result<T> successMsg(MyException cm){
        return new Result<T>(cm);
    }

    /**
     * 失败时候的调用
     * @return
     */
    public static <T> Result<T> error(MyException cm){
        return new Result<T>(cm);
    }
    /**
     * 失败时候的调用,扩展消息参数
     * @param cm
     * @param msg
     * @return
     */
    public static <T> Result<T> error(MyException cm, String msg){
        cm.setMessage(cm.getMessage()+"--"+msg);
        return new Result<T>(cm);
    }
}