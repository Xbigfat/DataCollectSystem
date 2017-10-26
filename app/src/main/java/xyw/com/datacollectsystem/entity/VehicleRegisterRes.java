package xyw.com.datacollectsystem.entity;

/**
 * Created by 31429 on 2017/10/26.
 * 机动车登记界面，登记提交的数据和返回的数据解析类
 */

public class VehicleRegisterRes {
    //号牌号码
    private String hphm;
    //号牌类型
    private String hplx;

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public String getHplx() {
        return hplx;
    }

    public void setHplx(String hplx) {
        this.hplx = hplx;
    }
}
