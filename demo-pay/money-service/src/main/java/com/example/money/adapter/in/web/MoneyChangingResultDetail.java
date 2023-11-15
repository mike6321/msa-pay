package com.example.money.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class MoneyChangingResultDetail {

    private String moneyChangingRequestId;
    private int moneyChangingType;
    private int moneyChangingResultStatus;
    private int amount;

}
enum MoneyChangingResultStatus {
    SUCCEEDED, // 성공
    FAILED, // 실패
    FAILED_NOT_ENOUGH_MONEY,
    FAILED_NOT_EXIST_MEMBERSHIP,
    FAILED_NOT_EXIST_MONEY,CHANGING_REQUEST
}


enum MoneyChangingType {
    INCREASING, // 증액
    DECREASING // 감액
}
