package xyw.com.datacollectsystem.entity;

/**
 * Created by 31429 on 2017/9/19.
 * 登陆成功后解析的 用户 实体类
 */

public class UserBean {

    /**
     * Qybh : H34010002
     * Fzjg : 皖A
     * Yhdh : qyyh1
     * Zt : 1
     * Xm : 藤原纪香
     * Ksip : 0.0.0.0
     * Jsip : 255.255.255.255
     * Qx :
     * Zhyxqz : 2018-10-16T00:00:00
     * Mmyxqz : 2018-01-16T00:00:00
     * Dlsbbh :
     * Cjsj : 2017-10-16T09:55:12
     * Czy : 默认管理员
     * Mm : 258FED406B50C98615E240C0BB6A825B
     * Gxsj : 2017-10-17T17:02:39
     * Corp : {"Qybh":"H34010002","Zt":"A","Mc":"合肥市机动车回收交易公司","Lxdz":"合肥市瑶海区胜利路汽车站","Lxr":"王羲之","Lxdh":"0551-62801534","Fzjg":"皖A","Glbm":"340100000400","Tbbj":"0","Cjsj":"2017-10-14T20:10:33","Gxsj":"2017-10-16T09:08:01","Czy":"默认管理员"}
     */

    private String Qybh;
    private String Fzjg;
    private String Yhdh;
    private String Zt;
    private String Xm;
    private String Ksip;
    private String Jsip;
    private String Qx;
    private String Zhyxqz;
    private String Mmyxqz;
    private String Dlsbbh;
    private String Cjsj;
    private String Czy;
    private String Mm;
    private String Gxsj;
    private CorpBean Corp;

    public String getQybh() {
        return Qybh;
    }

    public void setQybh(String Qybh) {
        this.Qybh = Qybh;
    }

    public String getFzjg() {
        return Fzjg;
    }

    public void setFzjg(String Fzjg) {
        this.Fzjg = Fzjg;
    }

    public String getYhdh() {
        return Yhdh;
    }

    public void setYhdh(String Yhdh) {
        this.Yhdh = Yhdh;
    }

    public String getZt() {
        return Zt;
    }

    public void setZt(String Zt) {
        this.Zt = Zt;
    }

    public String getXm() {
        return Xm;
    }

    public void setXm(String Xm) {
        this.Xm = Xm;
    }

    public String getKsip() {
        return Ksip;
    }

    public void setKsip(String Ksip) {
        this.Ksip = Ksip;
    }

    public String getJsip() {
        return Jsip;
    }

    public void setJsip(String Jsip) {
        this.Jsip = Jsip;
    }

    public String getQx() {
        return Qx;
    }

    public void setQx(String Qx) {
        this.Qx = Qx;
    }

    public String getZhyxqz() {
        return Zhyxqz;
    }

    public void setZhyxqz(String Zhyxqz) {
        this.Zhyxqz = Zhyxqz;
    }

    public String getMmyxqz() {
        return Mmyxqz;
    }

    public void setMmyxqz(String Mmyxqz) {
        this.Mmyxqz = Mmyxqz;
    }

    public String getDlsbbh() {
        return Dlsbbh;
    }

    public void setDlsbbh(String Dlsbbh) {
        this.Dlsbbh = Dlsbbh;
    }

    public String getCjsj() {
        return Cjsj;
    }

    public void setCjsj(String Cjsj) {
        this.Cjsj = Cjsj;
    }

    public String getCzy() {
        return Czy;
    }

    public void setCzy(String Czy) {
        this.Czy = Czy;
    }

    public String getMm() {
        return Mm;
    }

    public void setMm(String Mm) {
        this.Mm = Mm;
    }

    public String getGxsj() {
        return Gxsj;
    }

    public void setGxsj(String Gxsj) {
        this.Gxsj = Gxsj;
    }

    public CorpBean getCorp() {
        return Corp;
    }

    public void setCorp(CorpBean Corp) {
        this.Corp = Corp;
    }

    public static class CorpBean {
        /**
         * Qybh : H34010002
         * Zt : A
         * Mc : 合肥市机动车回收交易公司
         * Lxdz : 合肥市瑶海区胜利路汽车站
         * Lxr : 王羲之
         * Lxdh : 0551-62801534
         * Fzjg : 皖A
         * Glbm : 340100000400
         * Tbbj : 0
         * Cjsj : 2017-10-14T20:10:33
         * Gxsj : 2017-10-16T09:08:01
         * Czy : 默认管理员
         * <p>
         * corp: 单位信息
         */

        private String Qybh;
        private String Zt;
        private String Mc;
        private String Lxdz;
        private String Lxr;
        private String Lxdh;
        private String Fzjg;
        private String Glbm;
        private String Tbbj;
        private String Cjsj;
        private String Gxsj;
        private String Czy;

        public String getQybh() {
            return Qybh;
        }

        public void setQybh(String Qybh) {
            this.Qybh = Qybh;
        }

        public String getZt() {
            return Zt;
        }

        public void setZt(String Zt) {
            this.Zt = Zt;
        }

        public String getMc() {
            return Mc;
        }

        public void setMc(String Mc) {
            this.Mc = Mc;
        }

        public String getLxdz() {
            return Lxdz;
        }

        public void setLxdz(String Lxdz) {
            this.Lxdz = Lxdz;
        }

        public String getLxr() {
            return Lxr;
        }

        public void setLxr(String Lxr) {
            this.Lxr = Lxr;
        }

        public String getLxdh() {
            return Lxdh;
        }

        public void setLxdh(String Lxdh) {
            this.Lxdh = Lxdh;
        }

        public String getFzjg() {
            return Fzjg;
        }

        public void setFzjg(String Fzjg) {
            this.Fzjg = Fzjg;
        }

        public String getGlbm() {
            return Glbm;
        }

        public void setGlbm(String Glbm) {
            this.Glbm = Glbm;
        }

        public String getTbbj() {
            return Tbbj;
        }

        public void setTbbj(String Tbbj) {
            this.Tbbj = Tbbj;
        }

        public String getCjsj() {
            return Cjsj;
        }

        public void setCjsj(String Cjsj) {
            this.Cjsj = Cjsj;
        }

        public String getGxsj() {
            return Gxsj;
        }

        public void setGxsj(String Gxsj) {
            this.Gxsj = Gxsj;
        }

        public String getCzy() {
            return Czy;
        }

        public void setCzy(String Czy) {
            this.Czy = Czy;
        }
    }
}
