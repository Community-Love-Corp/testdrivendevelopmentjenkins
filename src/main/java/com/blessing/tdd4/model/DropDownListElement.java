package com.blessing.tdd4.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DropDownListElement {
 
    private String key;
    private String value;

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    public String toString() {
        return "DropDownList [key=" + key + ", value=" + value + "]";
    }
    
}
