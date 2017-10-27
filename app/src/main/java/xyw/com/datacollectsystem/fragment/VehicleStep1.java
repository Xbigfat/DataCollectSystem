package xyw.com.datacollectsystem.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

import xyw.com.datacollectsystem.R;
import xyw.com.datacollectsystem.customviews.CustomProgressBarDialog;
import xyw.com.datacollectsystem.entity.ServiceObj;
import xyw.com.datacollectsystem.entity.SvcResult;
import xyw.com.datacollectsystem.entity.workEntity;
import xyw.com.datacollectsystem.utils.BaseDoWorkApi;
import xyw.com.datacollectsystem.utils.OnLocalWorkListener;
import xyw.com.datacollectsystem.utils.ServiceConstant;
import xyw.com.datacollectsystem.utils.SoapActionApi;

/**
 * Created by 31429 on 2017/10/25.
 */

public class VehicleStep1 extends VehicleProcess {
    private Spinner hplxSpinner, provinceSpinner, citySpinner;
    private String[] suoxie = {"京", "津", "沪", "渝", "冀", "豫", "云", "辽", "黑", "湘", "皖", "鲁", "新", "苏", "浙", "赣", "鄂", "桂", "甘", "晋", "蒙", "陕", "吉", "闽", "贵", "粤", "青", "藏", "川", "宁", "琼"};
    private Button step1To2Btn;
    private EditText hphmEt;
    private View view;
    private CustomProgressBarDialog dialog;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.vehicle_add_step1, container, false);
        findViews();
        initSpinner();
        step1To2Btn.setOnClickListener(new onNextClick());
        return view;
    }

    /**
     * step1 中点击下一步 触发监听事件 cphm -> 车牌号码 组合好的车牌号码
     */
    private class onNextClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //获取号牌类型存入 carData
            String hplx = ServiceConstant.hplx[hplxSpinner.getSelectedItemPosition()].substring(0, 2);
            //组合号牌号码存入 carData
            String hphm = suoxie[provinceSpinner.getSelectedItemPosition()] + citySpinner.getSelectedItem().toString().substring(0, 1) + hphmEt.getText().toString();
            Log.i("xyw", hphm);
            carData.setHphm(hphm);
            carData.setHplx(hplx);
            //executeQuery();
            scroll.onStep1to2();
        }
    }

    private void findViews() {
        hplxSpinner = (Spinner) view.findViewById(R.id.vehicle_step1_hpzl);
        provinceSpinner = (Spinner) view.findViewById(R.id.vehicle_step1_province);
        citySpinner = (Spinner) view.findViewById(R.id.vehicle_step1_city);
        hphmEt = (EditText) view.findViewById(R.id.vehicle_step1_hphm);
        step1To2Btn = (Button) view.findViewById(R.id.vehicle_step1_next);
    }

    private void initSpinner() {
        ArrayAdapter<String> provinceAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, ServiceConstant.CityData.provinces);
        provinceSpinner.setAdapter(provinceAdapter);
        provinceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] selectedCityList = ServiceConstant.CityData.cities[position];
                ArrayAdapter citiesAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, selectedCityList);
                citySpinner.setAdapter(citiesAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<String> hplxAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, ServiceConstant.hplx);
        hplxSpinner.setAdapter(hplxAdapter);
    }

    private void executeQuery() {
        BaseDoWorkApi<SvcResult> work = new BaseDoWorkApi<>();
        work.setWorkListener(new BaseDoWorkApi.OnWorkListener<SvcResult>() {
            @Override
            public workEntity<SvcResult> doWork() {
                ServiceObj obj = new ServiceObj();
                Gson g = new Gson();
                Map<String, Object> map = new HashMap<>();
                map.put("hplx", carData.getHplx());
                map.put("hphm", carData.getHphm());
                String sendData = g.toJson(map);
                //插入function ID
                obj.functionId = "1";
                obj.curFzjg = "";
                obj.sendData = sendData;
                SoapActionApi api = new SoapActionApi(getContext(), obj, 1);
                TypeToken<SvcResult> type = new TypeToken<SvcResult>() {
                };
                workEntity<SvcResult> we = api.request(type.getType());
                return we;
            }
        });
        work.setLocalWorkListener(new OnLocalWorkListener<SvcResult>() {
            @Override
            public void onRequestCompleted(SvcResult obj) {
                scroll.onStep1to2();
            }

            @Override
            public void onRequestNotUiTask(SvcResult obj) {

            }

            @Override
            public void onRequestError(SvcResult obj, Exception e) {

            }

            @Override
            public void onRequestTimeout(SvcResult obj) {

            }

            @Override
            public void onRequestLoading() {
                dialog = new CustomProgressBarDialog(getContext());
                dialog.setCancelable(false);
                dialog.setMessage("正在查询");
                dialog.show();
            }
        });
        work.doWork();
    }
}
