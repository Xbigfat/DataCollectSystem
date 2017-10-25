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

public class VehicleStep2 extends VehicleProcess {
    Button previous, next;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vehicle_add_step2, container, false);
        previous = (Button) view.findViewById(R.id.vehicle_step2_previous);
        next = (Button) view.findViewById(R.id.vehicle_step2_next);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scroll.onPrevious();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scroll.onNextStep();
            }
        });
        return view;
    }
}
