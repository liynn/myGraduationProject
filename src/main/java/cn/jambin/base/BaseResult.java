package cn.jambin.base;


/**
 * 统一返回结果类
 * Created by jambin on 2017/12/08
 */
public class BaseResult <T>{

    //请求是否成功
    private boolean success;
    // 标志码
    public int code;
    private T data;
    private String info;

    public static BaseResult simpleSuccessResult;
    static {
        if (simpleSuccessResult==null){
            simpleSuccessResult = new BaseResult(true, 1, "", "success");
        }
    }

    public static BaseResult simpleSuccessResult(Object data){
        return new BaseResult(true, 1, "", data);
    }

    public static BaseResult simpleErrorResult(int code, String errorInfo){
        return new BaseResult(false, code, errorInfo);
    }

    public BaseResult() {
    }

    public BaseResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public BaseResult(boolean success, int code, String errorInfo) {
        this.success = success;
        this.code = code;
        this.info = errorInfo;
    }
    public BaseResult(boolean success, int code, String errorInfo, T data) {
        this.success = success;
        this.code = code;
        this.info = errorInfo;
        this.data = data;
    }
    public BaseResult(BaseResultEnum result) {
        this.success = (result.getCode()==1);
        this.code = result.getCode();
        this.info = result.getInfo();
    }
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
