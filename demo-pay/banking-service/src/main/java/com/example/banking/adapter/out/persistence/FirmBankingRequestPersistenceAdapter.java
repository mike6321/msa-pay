package com.example.banking.adapter.out.persistence;

import com.example.banking.application.port.out.RequestFirmBankingPort;
import com.example.banking.domain.FirmBankingRequest;
import com.example.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@PersistenceAdapter
@RequiredArgsConstructor
public class FirmBankingRequestPersistenceAdapter implements RequestFirmBankingPort {

    private final SpringDataFirmBankingRequestRepository firmBankingRequestRepository;

    @Override
    public FirmBankingRequestJpaEntity createFirmBankingRequest(FirmBankingRequest.FromBankName fromBankName,
                                                                   FirmBankingRequest.FromBankAccountNumber fromBankAccountNumber,
                                                                   FirmBankingRequest.ToBankName toBankName,
                                                                   FirmBankingRequest.ToBankAccountNumber toBankAccountNumber,
                                                                   FirmBankingRequest.MoneyAmount moneyAmount,
                                                                   FirmBankingRequest.FirmBankingStatus firmBankingStatus) {
        return firmBankingRequestRepository.save(new FirmBankingRequestJpaEntity(
                fromBankName.getFromBankName(),
                fromBankAccountNumber.getFromBankAccountNumber(),
                toBankName.getToBankName(),
                toBankAccountNumber.getToBankAccountNumber(),
                moneyAmount.getMoneyAmount(),
                firmBankingStatus.getFirmBankingStatus(),
                UUID.randomUUID()
        ));
    }

    @Override
    public FirmBankingRequestJpaEntity modifyFirmBankingRequest(FirmBankingRequestJpaEntity entity) {
        return firmBankingRequestRepository.save(entity);
    }

}
