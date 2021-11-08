package com.robot.study.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 自定义异常类
 */
@Data
@AllArgsConstructor
public class MyException extends RuntimeException{

    private Integer retCode;
    private String message;

    // 按照模块定义CodeMsg

    // 通用异常
    public static MyException SUCCESS = new MyException(0,"success");
    public static MyException SERVER_EXCEPTION = new MyException(500100,"服务端异常");
    public static MyException PARAMETER_ISNULL = new MyException(500101,"输入参数为空");
    // 业务异常
    public static MyException USER_NOT_EXIST = new MyException(500102,"用户不存在");
    public static MyException USER_IS_DELETE = new MyException(500103,"账号密码不正确");
    public static MyException USER_IS_CANCEL = new MyException(500103,"您的账号已注销，请联系管理员启用");
    public static MyException USER__EXIST = new MyException(500104,"用户已存在");
    public static MyException ONLINE_USER_OVER = new MyException(500104,"在线用户数超出允许登录的最大用户限制。");
    public static MyException SESSION_NOT_EXSIST =  new MyException(500105,"不存在离线session数据");
    public static MyException NOT_FIND_DATA = new MyException(500111,"查找不到对应数据");

    public static MyException EXCEL_IMPORT_FAIL = new MyException(500301,"表格导入失败");
    public static MyException PDF_OUTPUT_FAIL = new MyException(500302,"PDF导出失败");
    public static MyException INFO_ADD_FAIL = new MyException(500107,"添加信息失败");
    public static MyException INFO_UPDATE_FAIL = new MyException(500108,"修改信息失败");
    public static MyException INFO_DELETE_FAIL = new MyException(500109,"删除信息失败");
    public static MyException PASSWORD_ERROR = new MyException(500110,"密码错误");
    public static MyException USER_INFO_NULL = new MyException(500111,"用户不存在，请确认账号");
    public static MyException USER_INFO_EXISTENCE = new MyException(500112,"该账号已存在");

    public static MyException JPG_UPLOAD_FAILED = new MyException(501101,"图片上传失败");
    public static MyException FILE_UPLOAD_FAILED = new MyException(501101,"图片上传失败");

    public String getMessage(){
        return this.message;
    }

}
