package com.example.banking.adapter.in.web;

import com.example.banking.application.port.in.RegisterBankAccountCommand;
import com.example.banking.application.port.in.RegisterBankAccountUseCase;
import com.example.banking.domain.RegisteredBankAccount;
import com.example.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Objects.*;

@WebAdapter
@RequestMapping("/membership")
@RestController
@RequiredArgsConstructor
public class RegisterBankAccountController {

    private final RegisterBankAccountUseCase registerBankAccountUseCase;

    @PostMapping("/register")
    public ResponseEntity<RegisteredBankAccount> registerBankAccount(@RequestBody RegisterBankAccountRequest request) {
        RegisterBankAccountCommand command = RegisterBankAccountCommand.builder()
                .membershipId(request.getMembershipId())
                .bankName(request.getBankName())
                .bankAccountNumber(request.getBankAccountNumber())
                .linkedStatusIsValid(request.isLinkedStatusIsValid())
                .build();

        RegisteredBankAccount registeredBankAccount = registerBankAccountUseCase.registerBankAccount(command);
        if (isNull(registeredBankAccount)) {
            // TODO: 2023/10/24 Error Handling
            return null;
        }
        return ResponseEntity.ok(registeredBankAccount);
    }

}

