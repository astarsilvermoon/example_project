package ru.bellintegrator.practice.universal.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by Alena on 20.03.2018.
 */
//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ResponseView {


    public Boolean result;
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

    public ResponseView(Boolean result){this.result = result;}

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

    public Boolean isResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}
