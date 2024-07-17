package com.lpb.mid.lv24_service.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ShopInfo {
    @JsonProperty(value = "accountNo")
    private String accountNo;
    @JsonProperty(value = "customerName")
    private String customerName;

    public ShopInfo(String accountNo, String customerName) {
        this.accountNo = accountNo;
        this.customerName = customerName;
    }
}
