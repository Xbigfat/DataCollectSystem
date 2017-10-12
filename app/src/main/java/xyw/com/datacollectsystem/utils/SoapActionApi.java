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
    // private static boolean isExit = false;

    // 服务地址

    //	protected String url = "http://192.168.1.104/tgsService.svc?wsdl";
//	protected String url = "http://192.168.1.248/DrvEduService/DrvEduService.svc?wsdl";
//	protected String writeMethodName = "WriteData";
//	private String readMethodName = "QueryData";
    private String methodName = "QueryData";//默认查询
    private String doc = "QueryDoc";
    //	protected String serviceName = "ItgsService/";
    private int readOrWrite;
    protected SoapObject request = null;



    //	private final String NameSpace = "http://tempuri.org/";
    private final int TIMEOUT = 30 * 1000;
    protected Context mContext;
    private ServiceObj sObj;

    public final int REQUEST_COMPLETED = 2;
    public final int REQUEST_ERROR = -1;
    public final int REQUEST_TIME_OUT = 1;
    public final int REQUEST_NOTUI_TASK = 3;

    private String url;

    public SoapActionApi(Context context, ServiceObj obj,int choose) {
        this.mContext = context;
        this.sObj = obj;
        this.readOrWrite = choose;
        SharedPreferences serviceip = context.getSharedPreferences("serviceip", Activity.MODE_PRIVATE);
        if(serviceip==null){
            url = ServiceConstant.SADDRESS;
        }else{
            if(!serviceip.contains("ip")||!serviceip.contains("port")||!serviceip.contains("service")){
                url = ServiceConstant.SADDRESS;
            }else{
                url = "http://" + serviceip.getString("ip",ServiceConstant.IP)+":"
                        +serviceip.getString("port", ServiceConstant.PORT)+"/"
                        +serviceip.getString("service", ServiceConstant.SSERVICE)
                        +ServiceConstant.SPATH;
            }
        }

    }


    /**
     * 发送请求
     * @param <T>
     */
    public <T> workEntity<T> request(Type type) {
        workEntity<T> work = new workEntity<T>();
        try {

            if (mContext == null) {
                work.setResultState(REQUEST_ERROR);
                work.setException(new Exception("上下文传输为空，连接失败！"));
                return work;
            }

    /*        if(StringUtils.isEmpty(sObj.curFzjg)){
                sObj.curFzjg = "";//IntegratedApp.getInstance().getUser().getFZJG();
            }
            if(Parameters.DEBUG){
                Log.i("soapApi", sObj.curFzjg);
            }
*/

            if(readOrWrite==ServiceConstant.WRITE){

                methodName = ServiceConstant.WRITEDATA;

                doc = ServiceConstant.WRITEDOC;
            }else if (readOrWrite == ServiceConstant.READ){

                methodName = ServiceConstant.QUERYDATA;

                doc = ServiceConstant.QUERYDOC;
            }
            request = new SoapObject(ServiceConstant.NAMESPACE+"/", methodName);
            request.addProperty(ServiceConstant.SN,ServiceConstant.SN_MOBILEAPP);
            request.addProperty(ServiceConstant.JKID, sObj.functionId);
            String s = sObj.sendData;
            try {
                s = AES.Encrypt(sObj.sendData);
            } catch (Exception e) {
                // TODO: handle exception
                if (Parameters.DEBUG)
                    e.printStackTrace();
            }
            request.addProperty(doc,s);

            // soapheader在这里
            Element[] header = new Element[1];
            header[0] = new Element().createElement(ServiceConstant.NAMESPACE +"/",
                    "header");
            header[0].addChild(Node.TEXT, "");

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.headerOut = header;
            envelope.dotNet = true;
            envelope.bodyOut = request;
            envelope.setOutputSoapObject(request);
            HttpTransportSE ht = new HttpTransportSE(url, TIMEOUT);
            try {
                if(Parameters.DEBUG)ht.debug = true;
                ht.call(ServiceConstant.NAMESPACE + "/" + ServiceConstant.SNAME + methodName, envelope);
                if(Parameters.DEBUG)Log.i("", ht.responseDump);// i = TIMEOUT_COUNT;
            } catch (Exception e) {
                if(Parameters.DEBUG){
                    e.printStackTrace();
                }
                work.setResultState(REQUEST_ERROR);
                work.setException(e);
                return work;
            }
            sObj.resultData = envelope.getResponse().toString();
            if(sObj.resultData.trim().equals("anyType{}")){
                work.setResultState(REQUEST_ERROR);
                work.setException(new Exception("网络连接错误！"));
                return work;
            }
            String result = sObj.resultData;
            try {
                result = AES.Decrypt(sObj.resultData);
            } catch (Exception e) {
                // TODO: handle exception
                if (Parameters.DEBUG)
                    e.printStackTrace();
            }
            if(Parameters.DEBUG)Log.i("", result);
            Gson g = new Gson();
            work.setResultState(REQUEST_COMPLETED);
            T t = g.fromJson(result, type);
            work.setData(t);
            return work;


        } catch (Exception e) {// 请求出错开始-----------------------------------------------------------------------------
            if(Parameters.DEBUG)
                e.printStackTrace();
            work.setResultState(REQUEST_ERROR);
            work.setException(e);
            return work;
        }// 请求出错结束------------------------------------------------------------------------------------------------

    }


}
