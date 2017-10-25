package xyw.com.datacollectsystem.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import xyw.com.datacollectsystem.R;

/**
 * Created by 31429 on 2017/10/25.
 */

public class VehicleStep1 extends VehicleProcess {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vehicle_add_step1, container, false);
        Button next = (Button) view.findViewById(R.id.vehicle_step1_next);
        next.setOnClickListener(new onNextClick());
        return view;
    }

    private class onNextClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            scroll.onNextStep();
        }
    }
}
