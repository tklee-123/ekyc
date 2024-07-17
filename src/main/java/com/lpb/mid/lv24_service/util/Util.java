package com.lpb.mid.lv24_service.util;

import com.lpb.mid.lv24_service.dto.request.GetQRShopRequest;
import com.lpb.mid.lv24_service.dto.respone.GetQRShopResponse;
import com.lpb.mid.lv24_service.dto.respone.ResponseDTO;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.lpb.mid.lv24_service.entity.Header;
import com.lpb.mid.lv24_service.entity.ShopInfo;
import lombok.extern.log4j.Log4j2;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import com.google.gson.Gson;
import okhttp3.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@Log4j2
public class Util {
    @Value("${lv24.noti}")
    private String urlNotiL24 = "http://10.36.126.111:9082/transaction/services/ESBService?wsdl";

    @Value("${file.sms.prefix}")
    private String prefix = "lv24-service/data-xml-base/";

    @Value("${file.sms.xml.lv24}")
    private String lv24xml = "lv24.xml";
    public GetQRShopResponse xmlToResponseLV24(String xml){
        GetQRShopResponse getQRShopResponse = new GetQRShopResponse();
        try {
            XmlMapper xmlMapper = new XmlMapper();
            JsonNode rootNode = xmlMapper.readTree(xml);

            JsonNode returnNode = rootNode
                    .path("Body")
                    .path("receiveNotificationATResponse")
                    .path("return");

            String refId = returnNode.path("refId").asText();
            String resultCode = returnNode.path("resultCode").asText();
            String resultDesc = returnNode.path("resultDesc").asText();
            String accountNo = returnNode.path("accountNo").asText();
            String customerName = returnNode.path("customerName").asText();
            getQRShopResponse.setRefId(refId);
            getQRShopResponse.setResultCode(resultCode);
            getQRShopResponse.setResultDesc(resultDesc);
            getQRShopResponse.setAccountNo(accountNo);
            getQRShopResponse.setCustomerName(customerName);
            return getQRShopResponse;
        }catch (Exception e){
            log.error("xmlToResponseLV24 xml:" + xml + "\nE:"+ e.getMessage());
        }
        return getQRShopResponse;
    }

    public String formatClientTime(String inputDateTime){
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss.SSS");

        LocalDateTime dateTime = LocalDateTime.parse(inputDateTime, inputFormatter);

        String formattedDateTime = dateTime.format(outputFormatter);

        return formattedDateTime;
    }

    public String buildResponseXml(String reqData) {
        final String path = prefix + lv24xml;
        System.out.println(reqData);
        String xmlBase = null;
        try {
            Gson gson = new Gson();
            GetQRShopRequest data = gson.fromJson(reqData, GetQRShopRequest.class);
            System.out.println(data);
            xmlBase = FileUtils.readFileToString(new File(path)).replaceAll("\n", "");

            Header header = data.getHeader();
            xmlBase = String.format(xmlBase,
                    "VN",
                    "System",
                    formatClientTime(header.getTransTime()),
                    header.getRequestId(),
                    new Date().getTime()
            );
            System.out.println(xmlBase);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return xmlBase;
    }
    public String postToLV24(String reqData) {
        String soapXML = buildResponseXml(reqData);
        log.info("OkHttpClient postToLv24 soap Data: {}", soapXML);
        RequestBody body = RequestBody.create(MediaType.parse("text/xml; charset=utf-8"), soapXML);

        Request request = new Request.Builder()
                .url(urlNotiL24)
                .header("Content-Type", "text/xml; charset=utf-8")
                .post(body)
                .build();
        OkHttpClient client = new OkHttpClient();
        try (Response response = client.newCall(request).execute()) {
            if (response.body() != null) {
                String res = response.body().string();
                log.info("OkHttpClient postToLv24 response: {}", res);
                return res;
            }
        } catch (Exception e) {
            log.error("Error on postSendMessage: {}, exception: {}", reqData, ExceptionUtils.getStackTrace(e));
        }
        return null;
    }
//    public String postToLV241(String refNo, String reqData) {
//        String soapXML = buildResponseXml(refNo,reqData);
//
//        log.info("OkHttpClient postToLv24 soap Data: {}", soapXML);
//
//        RequestBody body = RequestBody.create(MediaType.parse("text/xml; charset=utf-8"), soapXML);
//
//        Request request = new Request.Builder()
//                .url(urlNotiL24)
//                .header("Content-Type", "/soap+xml; charset=utf-8")
//                .post(body)
//                .build();
//        OkHttpClient client = new OkHttpClient();
//        try (Response response = client.newCall(request).execute()) {
//            if (response.body() != null) {
//                String res = response.body().string();
//                log.info("OkHttpClient postToLv24 response: {}", res);
//                return res;
//            }
//        } catch (Exception e) {
//            log.error("Error on postSendMessage: {}, exception: {}", reqData, ExceptionUtils.getStackTrace(e));
//        }
//        return null;
//    }
}
