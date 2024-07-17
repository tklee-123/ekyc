package com.lpb.mid.lv24_service.controller;

import com.lpb.mid.lv24_service.dto.request.GetQRShopRequest;
import com.lpb.mid.lv24_service.dto.respone.GetQRShopResponse;
import com.lpb.mid.lv24_service.dto.respone.ResponseDTO;
import com.lpb.mid.lv24_service.entity.ShopInfo;
import com.lpb.mid.lv24_service.util.Util;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.lpb.mid.lv24_service.common.StatusCode;

import javax.jws.soap.SOAPBinding;
import com.google.gson.Gson;
@RestController
@SOAPBinding
public class GetQRShopController {

    @RequestMapping(value = "/getQRShop", method = RequestMethod.GET)
    public ResponseDTO<GetQRShopResponse> getQRShop(@RequestBody GetQRShopRequest getQRShopRequest) {
        ResponseDTO<GetQRShopResponse> responseDTO = new ResponseDTO<GetQRShopResponse>();
        try {
            Gson gson = new Gson();
            String json = gson.toJson(getQRShopRequest);
            Util util = new Util();
            String resString = util.postToLV24(json);
            if (resString != null) {
                responseDTO.setData(util.xmlToResponseLV24(resString));
                responseDTO.setType(StatusCode.SUCCESS.getType());
                responseDTO.setStatusCode(StatusCode.SUCCESS.getStatusCode());
                responseDTO.setDescription(StatusCode.SUCCESS.getDescription());
            } else {
                responseDTO.setType(StatusCode.PARTNER_CONNECT_ERROR.getType());
                responseDTO.setStatusCode(StatusCode.PARTNER_CONNECT_ERROR.getStatusCode());
                responseDTO.setDescription(StatusCode.PARTNER_CONNECT_ERROR.getDescription());
            }
        } catch (Exception e) {
            responseDTO.setType(StatusCode.SYSTEM_ERROR.getType());
            responseDTO.setStatusCode(StatusCode.SYSTEM_ERROR.getStatusCode());
            responseDTO.setDescription(StatusCode.SYSTEM_ERROR.getDescription());
        }
        return responseDTO;
    }

}
