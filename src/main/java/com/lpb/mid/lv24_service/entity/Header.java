package com.lpb.mid.lv24_service.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Header {
    @JsonProperty(value = "language")
    private String language;
    @JsonProperty(value = "channelCode")
    private String channelCode;
    @JsonProperty(value = "clientTime")
    private Date clientTime;
    @JsonProperty(value = "requestId")
    private String requestId;
    @JsonProperty(value = "transTime")
    private String transTime;
}
