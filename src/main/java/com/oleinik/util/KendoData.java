package com.oleinik.util;

public class KendoData {

    private String value;
    private String text;

    public KendoData() {
    }

    public KendoData(String value, String text) {
        this.value = value;
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
