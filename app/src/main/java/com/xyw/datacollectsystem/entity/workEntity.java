package com.xyw.datacollectsystem.entity;

import java.io.Serializable;

/**
 * Created by 31429 on 2017/9/7.
 *
 * @author 31429
 *         此类为实体类
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

    private int currentState = 0;

    public void setResultState(int state) {
        currentState = state;
    }

    public int getResultState() {
        return currentState;
    }

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
