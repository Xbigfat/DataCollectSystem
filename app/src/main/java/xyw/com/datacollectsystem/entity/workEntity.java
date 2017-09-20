package xyw.com.datacollectsystem.entity;

import java.io.Serializable;

/**
 * Created by 31429 on 2017/9/7.
 */

public class workEntity<T> implements Serializable {
    private static final long serialVersionUID = 8349877244525848802L;

    public final static int REQUEST_COMPLETED = 2;
    public final static int REQUEST_ERROR = -1;
    public final static int REQUEST_TIME_OUT = 1;
    public final static int REQUEST_NOTUI_TASK = 3;
    private Exception exception;

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    private int CurrentState = 0;

    public void setResultState(int state) {
        CurrentState = state;
    }

    public int getResultState() {
        return CurrentState;
    }

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
