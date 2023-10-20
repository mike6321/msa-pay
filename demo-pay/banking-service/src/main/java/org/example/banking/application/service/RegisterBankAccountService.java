package org.example.banking.application.service;

import com.example.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.example.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import org.example.banking.adapter.out.persistence.RegisteredBankAccountMapper;
import org.example.banking.application.port.in.RegisterBankAccountCommand;
import org.example.banking.application.port.in.RegisterBankAccountUseCase;
import org.example.banking.application.port.out.RegisterBankAccountPort;
import org.example.banking.domain.RegisteredBankAccount;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class RegisterBankAccountService implements RegisterBankAccountUseCase {

    private final RegisterBankAccountPort registerBankAccountPort;
    private final RegisteredBankAccountMapper registeredBankAccountMapper;

    @Override
    public RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command) {
        RegisteredBankAccountJpaEntity registeredBankAccountJpaEntity = registerBankAccountPort.createRegisteredBankAccount(
                new RegisteredBankAccount.MembershipId(command.getMembershipId()),
                new RegisteredBankAccount.BankName(command.getBankName()),
                new RegisteredBankAccount.BankAccountNumber(command.getBankAccountNumber()),
                new RegisteredBankAccount.LinkedStatusIsValid(command.isLinkedStatusIsValid())
        );

        return registeredBankAccountMapper.mapToDomainEntity(registeredBankAccountJpaEntity);
    }

}
