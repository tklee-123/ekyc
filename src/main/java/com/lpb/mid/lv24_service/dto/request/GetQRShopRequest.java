package com.lpb.mid.lv24_service.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpb.mid.lv24_service.entity.Header;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetQRShopRequest {
    @JsonProperty(value = "qrShopRefAccount")
    private String qrShopRefAccount;
    @JsonProperty(value = "header")
    private Header header;
}
