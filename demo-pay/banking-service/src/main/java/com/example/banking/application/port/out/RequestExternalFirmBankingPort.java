package com.example.banking.application.port.out;

import com.example.banking.adapter.out.external.bank.ExternalFirmBankingRequest;
import com.example.banking.adapter.out.external.bank.FirmBankingResult;

public interface RequestExternalFirmBankingPort {

    FirmBankingResult requestExternalFirmBanking(ExternalFirmBankingRequest request);

}
