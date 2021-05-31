package cn.bbahh.util.valid.validation.hans;

/**
 * 汉字校验类型
 * @author yangchongxiao
 * @date 2021/5/31 15:09
 */
public enum HansEnum {
    ALL("1"),
    HAS("2"),
    NOT_HAS("3");

    private String type;

    HansEnum(String s) {
        this.type=s;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
