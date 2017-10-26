package xyw.com.datacollectsystem.entity;

import java.io.Serializable;

/**
 * Created by 31429 on 2017/10/13.
 * <p>
 * 返回的数据，将其解析为实体对象
 */


public class SvcResult implements Serializable {

    static final long serialVersionUID = 1346914262783309499L;
    /**
     * 操作代码。
     */
    String Code;

    public final String getCode() {
        return Code;
    }

    public final void setCode(String value) {
        Code = value;
    }

    /**
     * 操作结果消息。
     */
    String Message;

    public final String getMessage() {
        return Message;
    }

    public final void setMessage(String value) {
        Message = value;
    }

    /**
     * 成功操作代码定义。
     */
    public static final String SUCCEED = "0";

    /**
     * cylsh
     * 操作是否成功。
     */
    public final boolean getOK() {
        return getCode().equals(SUCCEED);
    }
}
