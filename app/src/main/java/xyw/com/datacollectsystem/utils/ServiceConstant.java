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
    public static String IP = "192.168.0.248";
    public static String PORT = "80";
    public static String SPATH = "/virDataService.svc";
    public static String SSERVICE = "virWeb";
    public static String SADDRESS = "http://" + IP + ":" + PORT + SPATH;
    public static final String JKID = "jkid";
    public static final String KEY = "KEY";
    public static final String TITLE = "TITLE";
    public static final String VALUE = "VALUE";
    public static final int WRITE = 1;
    public static final int READ = 2;
    public static final String[] hplx = {"01	大型汽车", "02	小型汽车", "03	使馆汽车", "04	领馆汽车", "05	境外汽车",
            "06	外籍汽车", "07	普通摩托车", "08	轻便摩托车", "09	使馆摩托车", "10	领馆摩托车", "11	境外摩托车", "12	外籍摩托车",
            "13	低速车", "14	拖拉机", "15	挂车", "16	教练汽车", "17	教练摩托车", "18	试验汽车", "19	试验摩托车", "20	临时入境汽车",
            "21	临时入境摩托车", "22	临时行驶车", "23	警用汽车", "24	警用摩托", "25	原农机号牌", "26	香港入出境车", "27	澳门入出境车",
            "31	武警号牌", "32	军队号牌", "41	无号牌", "42	假号牌", "43	挪用号牌", "51	大型新能源汽车", "52	小型新能源汽车", "99	其他号牌"};

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
        public static final String Login_USER = "011001";

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

    public static class CityData {
        public static final String[] provinces = new String[]{"北京（京）", "天津（津）", "上海（沪）", "重庆（渝）",
                "河北（冀）", "河南（豫）", "云南（云）", "辽宁（辽）", "黑龙江（黑）", "湖南（湘）",
                "安徽（皖）", "山东（鲁）", "新疆（新）", "江苏（苏）", "浙江（浙）", "江西（赣）",
                "河北（鄂）", "广西（桂）", "甘肃（甘）", "山西（晋）", "内蒙古（蒙）", "陕西（陕）",
                "吉林（吉）", "福建（闽）", "贵州（贵）", "广东（粤）", "青海（青）", "西藏（藏）",
                "四川（川）", "宁夏（宁）", "海南（琼）"};

        public static final String[][] cities = new String[][]{
                //北京
                new String[]{"A 中央国家机关,公交车", "B 出租车,营运车辆", "C 市区号牌", "D 警务车辆", "E 市区号牌", "F  市区号牌",
                        "G 北京市", "H 市区号牌", "I 市区号牌", "J 市区号牌", "K 市区号牌", "L 市区号牌", "M 市区号牌", "N 市区号牌",
                        "O 中央国家机关", "P 市区号牌", "Q 市区号牌"},
                //天津
                new String[]{"A 公交车，公安局", "B 天津市", "C 天津市", "D 天津市", "E 出租车", "F 天津市", "G 天津市", "H 天津市",
                        "J 天津市", "K 天津市", "L 天津市", "M 天津市", "N 天津市", "Q 天津市", "R 天津市"},
                //上海
                new String[]{"A 上海市", "B 上海市", "C 上海市郊区号牌", "D 上海市", "E 上海市", "F 上海市", "G 上海市",
                        "H 上海市", "J 上海市", "K 上海市", "L 上海市", "M 上海市", "N 上海市"},
                //重庆
                new String[]{"A", "B", "C", "D", "F", "G", "H"},
                //河北
                new String[]{"A 石家庄市", "B 唐山市", "C 秦皇岛市", "D 邯郸市", "E 邢台市", "F 保定市，定州市", "G 张家口市", "H 承德市", "J 沧州市", "R 廊坊市", "T 衡水市"},
                //河南
                new String[]{"A 郑州市", "B 开封市", "C 洛阳市", "D 平顶山市", "E 安阳市", "F 鹤壁市", "G 新乡市",
                        "H 焦作市", "J 濮阳市", "K 许昌市", "L 漯河市", "M 三门峡市", "N 商丘市", "P 周口市", "Q 驻马店市",
                        "R 南阳市", "S 信阳市", "U 济源市"},
                //云南
                new String[]{"A 昆明市", "B 东川市", "C 昭通市", "D 曲靖市", "E 楚雄彝族自治州", "F 玉溪市", "G 红河哈尼族彝族自治州",
                        "H 文山壮族苗族自治州", "J 普洱市（原思茅市）", "K 西双版纳傣族自治州", "L 大理白族自治州", "M 保山市",
                        "N 德宏傣族景颇族自治州", "P 丽江市", "Q 怒江傈僳族自治州", "R 迪庆藏族自治州", "S 临沧地区"},
                //辽宁
                new String[]{"A 沈阳市", "B 大连市", "C 鞍山市", "D 抚顺市", "E 本溪市", "F 丹东市", "G 锦州市",
                        "H 营口市", "J 阜新市", "K 辽阳市", "L 盘锦市", "M 铁岭市", "N 朝阳市", "P 葫芦岛市", "V 省直机关"},
                //黑龙江
                new String[]{"A 哈尔滨市", "B 齐齐哈尔市", "C 牡丹江市", "D 佳木斯市", "E 大庆市", "F 伊春市", "G 鸡西市",
                        "H 鹤岗市", "J 双鸭山市", "K 七台河市", "L 松花江地区", "M 绥化市", "N 黑河市", "P 大兴安岭地区", "R 农垦系统"},
                //湖南省
                new String[]{"A 长沙市", "B 株洲市", "C 湘潭市", "D 衡阳市", "E 邵阳市", "F 岳阳市", "G 张家界市",
                        "H 益阳市", "J 常德市", "K 娄底市", "L 郴州市", "M 永州市", "N 怀化市", "U 湘西土家族苗族自治州"},
                //安徽省
                new String[]{"A 合肥", "B 芜湖", "C 蚌埠", "D 淮南", "E 马鞍山", "F 淮北", "G 铜陵",
                        "H 安庆", "J 黄山", "K 阜阳", "L 宿州", "M 滁州", "N 六安", "P 宣城", "R 池州", "S 亳州"},
                //山东省
                new String[]{"A 济南", "B 青岛", "C 淄博", "D 枣庄", "E 东营", "F 烟台", "G 潍坊 ",
                        "H 济宁", "J 泰安", "K 威海", "L 日照", "M 滨州", "N 德州", "P 聊城", "Q 临沂", "R 菏泽", "S 莱芜"},
                //新疆省
                new String[]{"A 乌鲁木齐市", "B 昌吉回族自治州", "C 石河子市", "D 奎屯市", "E 博尔塔拉蒙古自治州", "F 伊犁哈萨克自治州直辖",
                        "G 塔城地区", "H 阿勒泰地区", "J 克拉玛依市", "K 吐鲁番地区", "L 哈密地区", "M 巴音郭楞蒙古自治州", "N 阿克苏地区",
                        "P 克孜勒苏柯尔克孜自治州", "Q 喀什地区", "R 和田地区", "S 昆玉市"},
                //江苏省
                new String[]{"A 南京", "B 无锡", "C 徐州", "D 常州", "E 苏州", "F 南通", "G 连云港",
                        "H 淮安", "J 盐城", "K 扬州", "L 镇江", "M 泰州", "N 宿迁"},
                //浙江省
                new String[]{"A 杭州", "B 宁波", "C 温州", "D 绍兴", "E 湖州", "F 嘉兴", "G 金华",
                        "H 衢州", "J 台州", "K 丽水", "L 舟山"},
                //江西省
                new String[]{"A 南昌市", "B 赣州市", "C 宜春市", "D 吉安市", "E 上饶市", "F 抚州市", "G 九江市",
                        "H 景德镇市", "J 萍乡市", "K 新余市", "L 鹰潭市", "M "},
                //河北省
                new String[]{"A 武汉市", "B 黄石市", "C 十堰市", "D 荆州市", "E 宜昌市", "F 襄樊市", "G 鄂州市",
                        "H 荆门市", "J 黄冈市", "K 孝感市", "L 咸宁市", "M 仙桃市", "N 潜江市",
                        "P 神农架林区", "Q 恩施土家族苗族自治州", "R 天门市", "S 随州市"},
                //广西省
                new String[]{"A 南宁市", "B 柳州市", "C 桂林市", "D 梧州市", "E 北海市", "F 崇左市", "G 来宾市",
                        "H 桂林地区 ", "J 贺州市", "K 玉林市", "L 百色市", "M 河池市", "N 钦州市", "P 防城港市", "R 贵港市"},
                //甘肃省
                new String[]{"A 兰州市", "B 嘉峪关市", "C 金昌市", "D 白银市", "E 天水市", "F 酒泉市", "G 张掖市",
                        "H 武威市", "J 定西地区", "K 陇南地区", "L 平凉市", "M 庆阳市", "N 临夏回族自治州", "P 甘南藏族自治州"},
                //山西省
                new String[]{"A 太原", "B 大同", "C 阳泉", "D 长治", "E 晋城", "F 朔州", "H 忻州",
                        "J 吕梁", "K 晋中", "L 临汾", "M 运城"},
                //内蒙古
                new String[]{"A 呼和浩特", "B 包头", "C 乌海", "D 赤峰", "E 呼伦贝尔", "F 兴安盟", "G 通辽",
                        "H 锡林郭勒", "J 乌兰察布", "K 鄂尔多斯", "L 巴彦淖尔", "M 阿拉善盟"},
                //陕西省
                new String[]{"A 西安", "B 铜川", "C 宝鸡", "D 咸阳", "E 渭南", "F 汉中", "G 安康",
                        "H 商洛", "J 延安", "K 榆林"},
                //吉林省
                new String[]{"A 长春", "B 吉林", "C 四平", "D 辽源", "E 通化", "F 白山", "G 白城", "H 延边", "J 松原",},
                //福建省
                new String[]{"A 福州", "B 莆田", "C 泉州", "D 厦门", "E 漳州", "F 龙岩", "G 三明",
                        "H 南平", "J 宁德", "K 平潭县"},
                //贵州省
                new String[]{"A 贵阳", "B 六盘水", "C 遵义", "D 铜仁", "E 兴义", "F 毕节", "G 安顺",
                        "H 凯里", "J 都匀"},
                //广东省
                new String[]{"A 广州市", "B 深圳市", "C 珠海市", "D 汕头市", "E 佛山市", "F 韶关市", "G 湛江市",
                        "H 肇庆市", "J 江门市", "K 茂名市", "L 惠州市", "M 梅州市", "N 汕尾市", "P 河源市", "Q 阳江市",
                        "R 清远市", "S 东莞市", "T 中山市", "U 潮州市", "V 揭阳市", "W 云浮市", "X 顺德区", "Y 南海区",
                        "Z 香港澳门进入内地车辆"},
                //青海省
                new String[]{"A 西宁市", "B 海东地区", "C 海北藏族自治州", "D 黄南藏族自治州", "E 海南藏族自治州",
                        "F 果洛藏族自治州", "G 玉树藏族自治州", "H 海西蒙古族藏族自治州"},
                //西藏
                new String[]{"A 拉萨", "B 昌都", "C 山南", "D 日喀则", "E 那曲", "F 阿里", "G 林芝"},
                //四川
                new String[]{"A 成都市", "B 绵阳市", "C 自贡市", "D 攀枝花市", "E 泸州市", "F 德阳市", "H 广元市",
                        "J 遂宁市", "K 内江市", "L 乐山市", "M 资阳市", "Q 宜宾市", "R 南充市", "S 达州市", "T 雅安市",
                        "U 阿坝藏族羌族自治州", "V 甘孜藏族自治州", "W 凉山彝族自治州", "X 广安市", "Y 巴中市", "Z 眉山市"},
                //宁夏
                new String[]{"A 银川市", "B 石嘴山市", "C 吴忠市", "D 固原市", "E 中卫市"},
                //海南
                new String[]{"A 海口", "B 三亚", "C 琼海", "D 五指山", "E 洋浦经济开发区", "F 儋州市"},
        };

    }
}
