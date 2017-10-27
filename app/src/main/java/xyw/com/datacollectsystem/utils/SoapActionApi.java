package xyw.com.datacollectsystem.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;

import java.lang.reflect.Type;

import xyw.com.datacollectsystem.encryption.AES;
import xyw.com.datacollectsystem.entity.ServiceObj;
import xyw.com.datacollectsystem.entity.workEntity;

/**
 * Created by 31429 on 2017/9/27.
 */

public class SoapActionApi {
    private String methodName = "QueryData";//默认查询
    private String doc = "QueryDoc";
    private int readOrWrite;
    private SoapObject request = null;
    private Context mContext;
    private ServiceObj sObj;

    private final int REQUEST_COMPLETED = 2;
    private final int REQUEST_ERROR = -1;
    public final int REQUEST_TIME_OUT = 1;
    public final int REQUEST_NOTUI_TASK = 3;

    private String url;

    /**
     * WebService 通用框架，构造函数
     *
     * @param context 请求的上下文环境
     * @param obj     请求数据对象。 包含 ： 功能编码 function id 发送的词典 SendData 返回的状态 intState 返回数据 resultData
     * @param choose  设置当前操作为读或者写  READDATA\WRITEDATA
     */
    public SoapActionApi(Context context, ServiceObj obj, int choose) {
        this.mContext = context;
        this.sObj = obj;
        this.readOrWrite = choose;
        /**
         * 重组webservice地址
         */
        SharedPreferences serviceip = context.getSharedPreferences("ip", Activity.MODE_PRIVATE);
        if (serviceip == null) {
            url = ServiceConstant.SADDRESS;
        } else {
            if (!serviceip.contains("ip") || !serviceip.contains("port") || !serviceip.contains("service")) {
                url = ServiceConstant.SADDRESS;
            } else {
                url = "http://" + serviceip.getString("ip", ServiceConstant.IP) + ":"
                        + serviceip.getString("port", ServiceConstant.PORT) + "/"
                        + serviceip.getString("service", ServiceConstant.SSERVICE)
                        + ServiceConstant.SPATH;
            }
        }
    }


    /**
     * 调用该方法开始请求webService
     *
     * @param type 将收到的数据解析成的bean类型
     * @param <T>
     * @return 返回一个实体对象
     */
    public <T> workEntity<T> request(Type type) {
        workEntity<T> work = new workEntity<T>();
        try {
            if (mContext == null) {
                work.setResultState(REQUEST_ERROR);
                work.setException(new Exception("上下文传输为空，连接失败！"));
                return work;
            }
            /**
             * 设置操作的方法类型
             */
            if (readOrWrite == ServiceConstant.WRITE) {
                methodName = ServiceConstant.WRITEDATA;
                doc = ServiceConstant.WRITEDOC;
            } else if (readOrWrite == ServiceConstant.READ) {
                methodName = ServiceConstant.QUERYDATA;
                doc = ServiceConstant.QUERYDOC;
            }
            /**
             * 构造一个名称为 request 的 SoapObject
             * 设置 request 添加到 envelope 的 bodyOut 中去
             * 包含命名空间、方法名称、提交的3个参数
             *  sn:sn_MobileApp jkid: 接口ID Json : encrypted data
             */
            request = new SoapObject(ServiceConstant.NAMESPACE + "/", methodName);
            request.addProperty(ServiceConstant.SN, ServiceConstant.SN_MOBILEAPP);
            request.addProperty(ServiceConstant.JKID, sObj.functionId);
            String s = sObj.sendData;
            try {
                s = AES.Encrypt(sObj.sendData);
            } catch (Exception e) {
                // TODO: handle exception
                if (Parameters.DEBUG) {
                    e.printStackTrace();
                }
            }
            request.addProperty(doc, s);
            /**
             * 制作识别的 headerOut
             * 添加到 envelope 的 headerOut中去
             * 包括 命名空间
             */
            Element[] header = new Element[1];
            header[0] = new Element().createElement(ServiceConstant.NAMESPACE + "/", "header");
            header[0].addChild(Node.TEXT, "");
            /**
             * 创建 SoapSerializationEnvelope 对象 ，envelope 设定为 VER.11
             * 添加进去
             */
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.headerOut = header;
            envelope.dotNet = true;
            envelope.bodyOut = request;
            envelope.setOutputSoapObject(request);
            /**
             * 创建 HT 对象，将 envelope 添加到 Ht中
             * 调用 call 方法发起请求
             */
            HttpTransportSE ht = new HttpTransportSE(url, 4000);
            int i = 0;
            while (i < 2) {
                try {
                    if (Parameters.DEBUG) {
                        ht.debug = true;
                    }
                    ht.call(ServiceConstant.NAMESPACE + "/" + ServiceConstant.SNAME + methodName, envelope);
                    if (Parameters.DEBUG) {
                        Log.i("", ht.responseDump);
                    }
                    break;
                } catch (Exception e) {
                    /**
                     * 请求出错处理
                     */
                    Log.i("request timeout", "the " + i + " fatal");
                    //e.printStackTrace();

                }
                i++;
            }
            if (i == 2) {
                work.setResultState(workEntity.REQUEST_TIME_OUT);
                return work;
            }
            /**
             * 收到的数据处理
             */
            sObj.resultData = envelope.getResponse().toString();
            if ("anyType{}".equals(sObj.resultData.trim())) {
                work.setResultState(REQUEST_ERROR);
                work.setException(new Exception("anyType异常"));
                return work;
            }
            String result = sObj.resultData;
            try {
                result = AES.Decrypt(sObj.resultData);
            } catch (Exception e) {
                if (Parameters.DEBUG) {
                    e.printStackTrace();
                }
            }
            if (Parameters.DEBUG) {
                Log.i("", result);
            }
            Gson g = new Gson();
            work.setResultState(REQUEST_COMPLETED);
            T t = g.fromJson(result, type);
            work.setData(t);
            return work;
        } catch (Exception e) {
            if (Parameters.DEBUG) {
                e.printStackTrace();
            }
            work.setResultState(REQUEST_ERROR);
            work.setException(e);
            return work;
        }
    }
}
