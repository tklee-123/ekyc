package com.lpb.mid.lv24_service.service;


import com.lpb.mid.lv24_service.dto.*;
import com.lpb.mid.lv24_service.dto.respone.GetQRShopResponse;
import com.lpb.mid.lv24_service.dto.respone.ResponseDTO;

public interface Lv24Service {

    ResponseDTO<GetQRShopResponse> sendMessage();

}
