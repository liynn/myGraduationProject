package cn.jambin.base;

/**
 * @Author: Jambin
 * @Description:
 * @Date: Created in 17:37 2017/12/8
 */
public enum BaseResultEnum {
    FAILED(0, "failed"),
    SUCCESS(1, "success"),

    REQUEST_PARAM_ERROR(100,"请求参数错误"),

    ADD_ARTICLE_FAILURE(201,"添加文章失败"),
    EMPTY_PIC(202, "图片不能为空"),
    ERROR_PIC_TYPE(203, "图片格式不支持"),
    EMPTY_TITLE(204, "标题不能为空"),

    INVALID_LENGTH(10001, "Invalid length"),
    EMPTY_USERNAME(10101, "Username cannot be empty"),
    EMPTY_PASSWORD(10102, "Password cannot be empty"),
    INVALID_USERNAME(10103, "Account does not exist"),
    INVALID_PASSWORD(10104, "Password error"),
    INVALID_ACCOUNT(10105, "Invalid account"),
    EMPTY_PID(10106,"请选择正确的上级");

    protected int code;
    protected String info;

    BaseResultEnum(int code, String info) {
        this.code = code;
        this.info = info;
    }

    public int getCode() {
        return code;
    }
    public String getInfo() {
        return info;
    }

//    public static BaseResultEnum stateOf(int index)
//    {
//        for (BaseResultEnum state : values())
//        {
//            if (state.getCode()==index)
//            {
//                return state;
//            }
//        }
//        return null;
//    }
}
