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
    //车辆品牌
    private String Clpp;
    //车主姓名
    private String Czxm;
    //车辆型号
    private String Clxh;
    //车辆类型
    private String Cllx;
    //车辆识别代号
    private String Clsbdh;
    //发动机号
    private String Fdjh;
    //出厂日期
    private String Ccrq;
    //强制报废期止
    private String Qzbfqz;
    //检验有效期至
    private String Jyyx;
    //使用性质
    private String Syxz;
    //机动车状态
    private String Jdczt;

    public String getClpp() {
        return Clpp;
    }

    public String getCzxm() {
        return Czxm;
    }

    public String getClxh() {
        return Clxh;
    }

    public String getCllx() {
        return Cllx;
    }

    public String getClsbdh() {
        return Clsbdh;
    }

    public String getFdjh() {
        return Fdjh;
    }

    public String getCcrq() {
        return Ccrq;
    }

    public String getQzbfqz() {
        return Qzbfqz;
    }

    public String getJyyx() {
        return Jyyx;
    }

    public String getSyxz() {
        return Syxz;
    }

    public String getJdczt() {
        return Jdczt;
    }

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
