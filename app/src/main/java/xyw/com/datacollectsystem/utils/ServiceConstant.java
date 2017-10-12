package xyw.com.datacollectsystem.utils;

/**
 * Created by 31429 on 2017/10/12.
 */

public class ServiceConstant {
    public static String QUERYDATA = "QueryData";
    public static String WRITEDATA = "WriteData";
    public static String QUERYDOC = "queryJson";
    public static String WRITEDOC = "writeJson";
    public static String NAMESPACE = "http://tempuri.org";

    public static String SNAME = "IvirDataService/";
    public static final String SN = "sn";
    public static String IP = "60.166.5.118";
    public static String PORT = "8087";
    public static String SPATH = "/virDataService.svc";
    public static String SSERVICE = "virWeb";
    public static String SADDRESS = "http://" + IP + ":" + PORT + SPATH;
    public static final String JKID = "jkid";
    public static final String KEY = "KEY";
    public static final String TITLE = "TITLE";
    public static final String VALUE = "VALUE";
    public static final int WRITE = 1;
    public static final int READ = 2;
    /**
     * Web 客户端调用。
     */
    public static final String SN_VIRWEB = "sn_VirWeb";
    /**
     * 打印客户端的调用序列号。
     */
    public static final String SN_PRINTCLIENT = "sn_PrintClient";
    /**
     * 移动手机端调用序列号。
     */
    public static final String SN_MOBILEAPP = "sn_MobileApp";

    /**
     * 查验 service 接口 id 常量。
     */
    public static class VirServiceJkid {
        /**
         * 待打印队列列表获取。
         */
        public static final String PRINT_QUEUE_LIST = "01Q001";

        /**
         * 登录接口
         */
        public static final String Login_User = "011001";

        /**
         * 查验登记业务数据列表获取。
         */
        public static final String VEHCHECK_FLOW_LIST = "01Q002";

        /**
         * 六合一平台违规车型获取。
         */
        public static final String VEHIRREGULARITIES_GAB_LIST = "01Q003";

        /**
         * 六合一平台违规车型图片获取。
         */
        public static final String VEHIRREGULARITIES_PIC_LIST = "01Q004";

        /**
         * 行驶证照片待上传数据获取接口。
         */
        public static final String RUNCARD_PHOTO_UPLOAD_LIST = "01Q005";

        /**
         * 机动车基本信息获取。
         */
        public static final String MON_VEHICLE_GET = "01Q006";

        /**
         * 获取机动车查验流程照片。
         */
        public static final String GET_VEHCHECK_IMAGE = "01Q007";

        /**
         * 获取机动车技术参数信息。
         */
        public static final String PCB_FINAL_PARA_LIST = "01Q008";

        /**
         * 机动车照片下载。
         */
        public static final String GET_VEHICLE_PIC = "51Q004";
        /**
         * 机动车公告照片索引信息下载。
         */
        public static final String GET_PCB_STPHOTO_DES = "51Q005";
        /**
         * 查验表打印日志写入。
         */
        public static final String WRITE_CHECKPAPER_PRINTLOG = "01W001";

        /**
         * 写入查验结果明细。
         */
        public static final String WRITE_CHECK_DETAIL = "01W002";

        /**
         * 写入查验图片。
         */
        public static final String WRITE_CHECK_IMAGE = "01W003";

        /**
         * 向六合一平台及照相系统传输行驶证照片。
         */
        public static final String UPLOAD_RUNCARD_PHOTO = "01W004";

        /**
         * 获取某车辆最近的查验流水资料信息。
         */
        public static final String GET_CHECKFLOW_LIST = "01Q016";

        /**
         * 获取某查验流水的详细数据。
         */
        public static final String GET_FLOW_VEHICLE_DETAILS = "01Q017";

        /**
         * 获取某查验流水的所有图片列表（不含图片）。
         */
        public static final String GET_FLOW_NOPIC_IMAGELIST = "01Q018";

        /**
         * 修改登录密码。
         */
        public static final String MODIFY_PASSWORD = "01W006";

        /**
         * 删除查验图片记录。
         */
        public static final String DELETE_CHECK_IMAGE = "01W007";

        /**
         * 直接完结查验流水。
         */
        public static final String FINISH_CHECK_FLOW = "01W008";

        /**
         * 获取最近一次查验是否合格。
         */
        public static final String GET_RECENT_CHECKFLOW_RESULT = "01Q019";


        /**
         * 获得底盘公告信息的列表。
         */
        public static final String GET_PCB_CHASSIS_LIST = "01Q021";

        /**
         * 某查验员某段时间内的查验流水记录列表。
         */
        public static final String QUERY_INSPECTOR_CHECKPAPER = "01Q024";

        /**
         * 请求重新查验已查验完成的业务流水，或复制查验流水。
         */
        public static final String REQUEST_CHECK_FLOWEDIT = "01W012";

        /**
         * 获取查验流水的扩展验证结果文本。合肥支队针对 28 家重点单位车辆入户的提醒。
         */
        public static final String GET_CHECKFLOW_EXTEND_VALID = "01Q027";

        /**
         * 根据车辆识别代号、车型公告批次等数据，获取该车或该车型的预警信息。
         */
        public static final String GET_IRREGULARITY_FAILCHECK_TIPS = "01Q029";
    }

    /**
     * 综合平台相关接口 id 常量。
     */
    public static class TrffServiceJkid {
        /**
         * 机动车基本信息下载。
         */
        public static final String GET_VEHICLE_INFO = "51Q001";
        /**
         * 机动车技术参数文本下载。
         */
        public static final String GET_PCB_FINAL_STR = "51Q002";
        /**
         * 机动车技术参数图片下载。
         */
        public static final String GET_PCB_FINAL_PIC = "51Q003";
        /**
         * 机动车照片下载。
         */
        public static final String GET_VEHICLE_PIC = "51Q004";
        /**
         * 机动车公告照片索引信息下载。
         */
        public static final String GET_PCB_STPHOTO_DES = "51Q005";

        /**
         * 六合一平台违规车型获取。
         */
        public static final String GET_VEHIRREGULARITIES_GAB = "51Q006";
        /**
         * 六合一平台违规车型图片获取。
         */
        public static final String GET_VEHIRREGULARITIES_GAB_PIC = "51Q007";

        /**
         * 机动车照片上传至照相管理系统平台和综合平台。
         */
        public static final String WRITE_VEHICLE_PIC = "51W004";

    }

}
