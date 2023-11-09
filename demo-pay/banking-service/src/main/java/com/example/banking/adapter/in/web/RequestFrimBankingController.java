package com.example.banking.adapter.in.web;

import com.example.banking.application.port.in.FirmBankingRequestCommand;
import com.example.banking.application.port.in.RequestFirmBankingUseCase;
import com.example.banking.domain.FirmBankingRequest;
import com.example.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Objects.isNull;

@WebAdapter
@RequestMapping("/banking")
@RestController
@RequiredArgsConstructor
public class RequestFrimBankingController {

    private final RequestFirmBankingUseCase requestFirmBankingUseCase;

    @PostMapping("/firmbanking/request")
    public ResponseEntity<FirmBankingRequest> registerBankAccount(@RequestBody RequestFirmBankingRequest request) {
        FirmBankingRequestCommand command = FirmBankingRequestCommand.builder()
                .fromBankName(request.getFromBankName())
                .fromBankAccountNumber(request.getFromBankAccountNumber())
                .toBankName(request.getToBankName())
                .toBankAccountNumber(request.getToBankAccountNumber())
                .moneyAmount(request.getAmount())
                .build();

        FirmBankingRequest firmBankingRequest = requestFirmBankingUseCase.requestFirmBanking(command);
        if (isNull(firmBankingRequest)) {
            // TODO: 2023/10/24 Error Handling
            return null;
        }
        return ResponseEntity.ok(firmBankingRequest);
    }

}

