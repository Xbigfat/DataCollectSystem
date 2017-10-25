package xyw.com.datacollectsystem.fragment;

/**
 * Created by 31429 on 2017/10/25.
 */

public interface onRegisterCallback<T> {

    void onNextStep();

    void onPrevious();

    void onCompleted();
}
