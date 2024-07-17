package com.lpb.mid.lv24_service.dto.respone;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetQRShopResponse {
    @JsonProperty(value = "refId")
    private String refId;
    @JsonProperty(value = "resultCode")
    private String resultCode;
    @JsonProperty(value = "resultDesc")
    private String resultDesc;
    @JsonProperty(value = "accountNo")
    private String accountNo;
    @JsonProperty(value = "customerName")
    private String customerName;
}
