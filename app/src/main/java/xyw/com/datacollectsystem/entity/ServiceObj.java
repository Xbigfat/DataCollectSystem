package xyw.com.datacollectsystem.entity;

/**
 * Created by 31429 on 2017/10/12.
 *
 * 请求服务的实体对象
 */

public class ServiceObj {

    /**
     * 功能编码
     */
    public String functionId;
    /**
     * 发送参数json
     */
    public String sendData = "";
    /**
     * 服务返回状态
     *
     * @since 2, 3, 4...其他提示信息
     */
    public int status = 1;
    /**
     * 服务返回数据 主要针对查询部分 其他操作一般返回空字符
     */
    public String resultData = "";
    /**
     * 当前使用发证机关
     */
    public String curFzjg;


}
