package ru.bellintegrator.practice.universal.view;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by Alena on 20.03.2018.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseView {

    //общая вьюха с data
    public boolean result;
    public String error;
    public Object data;

    public ResponseView() {
    }

    public ResponseView(Object data) {
        this.data = data;
    }

    public ResponseView(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "ResponseView{" +
                "result=" + result +
                ", error='" + error + '\'' +
                ", data=" + data +
                '}';
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
