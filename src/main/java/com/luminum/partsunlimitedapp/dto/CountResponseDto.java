package com.luminum.partsunlimitedapp.dto;

public class CountResponseDto {

    private String brandAndModel;
    private long count;

    public CountResponseDto(String brandName, String carName, long count) {
        this.brandAndModel = brandName + " " + carName;
        this.count = count;
    }

    public CountResponseDto() {

    }

    public String getBrandAndModel() {
        return brandAndModel;
    }

    public void setBrandAndModel(String brandAndModel) {
        this.brandAndModel = brandAndModel;
    }

    public long getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
