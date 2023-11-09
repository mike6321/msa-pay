package com.example.banking.application.port.in;

import com.example.banking.domain.FirmBankingRequest;

public interface RequestFirmBankingUseCase {

    FirmBankingRequest requestFirmBanking(FirmBankingRequestCommand command);

}
