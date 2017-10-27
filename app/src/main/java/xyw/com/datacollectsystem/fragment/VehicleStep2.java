package xyw.com.datacollectsystem.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import xyw.com.datacollectsystem.R;

/**
 * Created by 31429 on 2017/10/25.
 */

public class VehicleStep2 extends VehicleProcess implements View.OnClickListener {
    Button backToStep1Btn, step2To3Btn;
    private View view;
    /**
     * 奔驰牌
     */
    private TextView vehicleStep2Clpp;
    /**
     * 张三丰
     */
    private TextView vehicleStep2Czxm;
    /**
     * C220 4 Matic
     */
    private TextView vehicleStep2Clxh;
    /**
     * 车辆类型
     */
    private TextView vehicleStep2Cllx;
    /**
     * 车辆识别代号
     */
    private TextView vehicleStep2Clsbdh;
    /**
     * 发动机号
     */
    private TextView vehicleStep2Fdjh;
    /**
     * 出厂日期
     */
    private TextView vehicleStep2Ccrq;
    /**
     * 强制报废期止
     */
    private TextView vehicleStep2Qzbfqz;
    /**
     * 检验有效期至
     */
    private TextView vehicleStep2Jyyx;
    /**
     * 使用性质
     */
    private TextView vehicleStep2Syxz;
    /**
     * 机动车状态
     */
    private TextView vehicleStep2Jdczt;
    /**
     * 确认无误，开始拍摄照片
     */
    private Button vehicleStep2Next;
    /**
     * 重新输入
     */
    private Button vehicleStep2Previous;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vehicle_add_step2, container, false);
        initView(view);
        displayData();
        return view;
    }

    private void displayData() {
        vehicleStep2Clpp.setText(carData.getClpp());
        vehicleStep2Czxm.setText(carData.getCzxm());
        vehicleStep2Clxh.setText(carData.getClxh());
        vehicleStep2Cllx.setText(carData.getCllx());
        vehicleStep2Clsbdh.setText(carData.getClsbdh());
        vehicleStep2Fdjh.setText(carData.getFdjh());
        vehicleStep2Ccrq.setText(carData.getCcrq());
        vehicleStep2Qzbfqz.setText(carData.getQzbfqz());
        vehicleStep2Jyyx.setText(carData.getJyyx());
        vehicleStep2Syxz.setText(carData.getSyxz());
        vehicleStep2Jdczt.setText(carData.getJdczt());
    }

    private void initView(View view) {
        vehicleStep2Clpp = (TextView) view.findViewById(R.id.vehicle_step2_clpp);
        vehicleStep2Czxm = (TextView) view.findViewById(R.id.vehicle_step2_czxm);
        vehicleStep2Clxh = (TextView) view.findViewById(R.id.vehicle_step2_clxh);
        vehicleStep2Cllx = (TextView) view.findViewById(R.id.vehicle_step2_cllx);
        vehicleStep2Clsbdh = (TextView) view.findViewById(R.id.vehicle_step2_clsbdh);
        vehicleStep2Fdjh = (TextView) view.findViewById(R.id.vehicle_step2_fdjh);
        vehicleStep2Ccrq = (TextView) view.findViewById(R.id.vehicle_step2_ccrq);
        vehicleStep2Qzbfqz = (TextView) view.findViewById(R.id.vehicle_step2_qzbfqz);
        vehicleStep2Jyyx = (TextView) view.findViewById(R.id.vehicle_step2_jyyx);
        vehicleStep2Syxz = (TextView) view.findViewById(R.id.vehicle_step2_syxz);
        vehicleStep2Jdczt = (TextView) view.findViewById(R.id.vehicle_step2_jdczt);
        vehicleStep2Next = (Button) view.findViewById(R.id.vehicle_step2_next);
        vehicleStep2Next.setOnClickListener(this);
        vehicleStep2Previous = (Button) view.findViewById(R.id.vehicle_step2_previous);
        vehicleStep2Previous.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.vehicle_step2_next:
                scroll.onStep2to3();
                break;
            case R.id.vehicle_step2_previous:
                scroll.onPrevious();
                break;
        }
    }
}
