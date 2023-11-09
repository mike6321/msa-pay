package com.example.banking.adapter.out.external.bank;

import com.example.banking.application.port.out.RequestBankAccountInfoPort;
import com.example.banking.application.port.out.RequestExternalFirmBankingPort;
import com.example.common.ExternalSystemAdapter;
import lombok.RequiredArgsConstructor;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class BankAccountAdapter implements RequestBankAccountInfoPort, RequestExternalFirmBankingPort {

    @Override
    public BankAccount getBankAccountInfo(GetBankAccountRequest request) {
        return new BankAccount(request.getBankName(), request.getBankAccountNumber(), true);
    }

    @Override
    public FirmBankingResult requestExternalFirmBanking(ExternalFirmBankingRequest request) {
        // 실제 외부 은행에 http 통신을 통해서
        // 펌 뱅킹 요청을 하고

        // 해당 결과를
        // 외부 은행의 실제 결과물 -> FirmBankingResult 파싱
        return new FirmBankingResult(0);
    }

}
