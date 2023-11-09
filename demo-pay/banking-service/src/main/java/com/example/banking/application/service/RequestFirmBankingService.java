package com.example.banking.application.service;

import com.example.banking.adapter.out.external.bank.ExternalFirmBankingRequest;
import com.example.banking.adapter.out.external.bank.FirmBankingResult;
import com.example.banking.adapter.out.persistence.FirmBankingRequestJpaEntity;
import com.example.banking.adapter.out.persistence.FirmBankingRequestMapper;
import com.example.banking.application.port.in.FirmBankingRequestCommand;
import com.example.banking.application.port.in.RequestFirmBankingUseCase;
import com.example.banking.application.port.out.RequestExternalFirmBankingPort;
import com.example.banking.application.port.out.RequestFirmBankingPort;
import com.example.banking.domain.FirmBankingRequest;
import com.example.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
@Transactional
public class RequestFirmBankingService implements RequestFirmBankingUseCase {

    private final FirmBankingRequestMapper firmBankingRequestMapper;
    private final RequestFirmBankingPort requestFirmBankingPort;
    private final RequestExternalFirmBankingPort requestExternalFirmBankingPort;

    @Override
    public FirmBankingRequest requestFirmBanking(FirmBankingRequestCommand command) {
        // 1. 요청에 대한 정보를 write
        FirmBankingRequestJpaEntity requestedEntity = requestFirmBankingPort.createFirmBankingRequest(
                new FirmBankingRequest.FromBankName(command.getFromBankName()),
                new FirmBankingRequest.FromBankAccountNumber(command.getFromBankAccountNumber()),
                new FirmBankingRequest.ToBankName(command.getToBankName()),
                new FirmBankingRequest.ToBankAccountNumber(command.getToBankAccountNumber()),
                new FirmBankingRequest.MoneyAmount(command.getMoneyAmount()),
                new FirmBankingRequest.FirmBankingStatus(0)
        );

        // 2. 외부 은행에 펌뱅킹 요청
        FirmBankingResult result = requestExternalFirmBankingPort.requestExternalFirmBanking(new ExternalFirmBankingRequest(
                command.getFromBankName(),
                command.getFromBankAccountNumber(),
                command.getToBankName(),
                command.getToBankAccountNumber()
        ));

        UUID uuid = UUID.randomUUID();
        requestedEntity.setUuid(uuid);
        // 3. 결과에 따라서 1번에 작성하였던 FirmBankingRequest 정보를 업데이트
        if (result.getResultCode() == 0) {
            // 성공
            requestedEntity.setFirmBankingStatus(1);
        } else {
            // 실패
            requestedEntity.setUuid(uuid);
            requestedEntity.setFirmBankingStatus(2);
        }
        requestFirmBankingPort.modifyFirmBankingRequest(requestedEntity);

        // 4. 결과를 리턴
        return firmBankingRequestMapper.mapToDomainEntity(requestedEntity, uuid);
    }

}
