package com.example.banking.adapter.out.external.bank;

import lombok.Data;

@Data
public class FirmBankingResult {

    private int resultCode; // 0: 성공 1: 실싱

    public FirmBankingResult(int resultCode) {
        this.resultCode = resultCode;
    }

}
