package xyw.com.datacollectsystem.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import xyw.com.datacollectsystem.R;

/**
 * Created by 31429 on 2017/10/25.
 */

public class VehicleStep3 extends VehicleProcess implements View.OnClickListener {
    private View view;
    private ImageButton vehicleStep3Frontleft;
    private ImageButton vehicleStep3Behindright;
    private ImageButton vehicleStep3Cjh;
    private ImageButton vehicleStep3Fdjh;
    /**
     * 提交登记信息
     */
    private Button vehicleStep3Commit;
    /**
     * 返回上一步
     */
    private Button vehicleStep3Previous;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vehicle_add_step3, container, false);
        initView(view);
        return view;

    }

    private void initView(View view) {
        vehicleStep3Frontleft = (ImageButton) view.findViewById(R.id.vehicle_step3_frontleft);
        vehicleStep3Behindright = (ImageButton) view.findViewById(R.id.vehicle_step3_behindright);
        vehicleStep3Cjh = (ImageButton) view.findViewById(R.id.vehicle_step3_cjh);
        vehicleStep3Fdjh = (ImageButton) view.findViewById(R.id.vehicle_step3_fdjh);
        vehicleStep3Commit = (Button) view.findViewById(R.id.vehicle_step3_commit);
        vehicleStep3Commit.setOnClickListener(this);
        vehicleStep3Frontleft.setOnClickListener(this);
        vehicleStep3Behindright.setOnClickListener(this);
        vehicleStep3Cjh.setOnClickListener(this);
        vehicleStep3Fdjh.setOnClickListener(this);
        vehicleStep3Previous = (Button) view.findViewById(R.id.vehicle_step3_previous);
        vehicleStep3Previous.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.vehicle_step3_commit:
                executeUpload();
                break;
            case R.id.vehicle_step3_frontleft:
                break;
            case R.id.vehicle_step3_behindright:
                break;
            case R.id.vehicle_step3_cjh:
                break;
            case R.id.vehicle_step3_fdjh:
                break;
            case R.id.vehicle_step3_previous:
                scroll.onPrevious();
                break;
        }
    }

    private void executeUpload() {
        scroll.onCompleted();
    }
}
