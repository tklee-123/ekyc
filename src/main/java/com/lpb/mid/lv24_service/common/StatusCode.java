package com.lpb.mid.lv24_service.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCode {
    SUCCESS("00", "Successful", 0),
    SYSTEM_UPGRADE("10", "Hệ thống đang trong giai đoạn nâng cấp", 1),
    LOGIN_INFO_INCORRECT("11", "Thông tin đăng nhập không chính xác", 1),
    VERSION_API_NOT_SUPPORTED("12", "Version API không được hỗ trợ", 1),
    TOKEN_INVALID("13", "Token không hợp lệ", 1),
    WHITELIST_IP_NOT_REGISTERED("14", "White list IP chưa được đăng ký", 1),
    REQUEST_WRONG_FORMAT("20", "Request sai định dạng", 1),
    HMAC_INVALID("21", "HMAC không hợp lệ", 1),
    AMOUNT_INVALID("22", "Số tiền không hợp lệ", 1),
    ACCOUNT_INVALID("23", "Tài khoản không hợp lệ", 1),
    TRANSACTION_PROCESSED_PLEASE_NOT_DO_IT_AGAIN("24", "Giao dịch tương tự đang xử lý, vui lòng không thực hiện lại", 1),
    TRANSACTION_LIMIT_EXPIRED("25", "Hết hạn mức giao dịch ", 1),
    REQUESTS_NOT_MATCH_PLEASE_TRY_AGAIN_ANOTHER_TIME("26", "Số lượng request không hợp lệ, thực hiện lại thời điểm khác", 1),
    TRANSACTIONS_PROCESSED("90", "Giao dịch đang xử lý", 2),
    PARTNER_CONNECT_ERROR("93", "Lỗi trong quá trình kết nối đối tác", 2),
    SYSTEM_ERROR("99", "Lỗi hệ thống.", 1);

    private final String statusCode;
    private final String description;
    private final Integer type;
}
