package com.example.banking.application.service;

import com.example.banking.adapter.out.external.bank.BankAccount;
import com.example.banking.adapter.out.external.bank.GetBankAccountRequest;
import com.example.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.example.banking.adapter.out.persistence.RegisteredBankAccountMapper;
import com.example.banking.application.port.in.RegisterBankAccountCommand;
import com.example.banking.application.port.in.RegisterBankAccountUseCase;
import com.example.banking.application.port.out.RegisterBankAccountPort;
import com.example.banking.application.port.out.RequestBankAccountInfoPort;
import com.example.banking.domain.RegisteredBankAccount;
import com.example.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class RegisterBankAccountService implements RegisterBankAccountUseCase {

    private final RegisterBankAccountPort registerBankAccountPort;
    private final RequestBankAccountInfoPort requestBankAccountInfoPort;
    private final RegisteredBankAccountMapper registeredBankAccountMapper;

    @Override
    public RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command) {
        /**
         * 은행 계좌를 등록해야하는 서비스
         *
         * 1. 등록된 계좌 인지 확인
         * 외부의 은행에 계좌 정상 여부 확인
         *
         * 2. 등록 가능한 계좌 라면 등록
         * 2-1. 등록가능하지 않은 계좌라면 에러를 리턴
         * */
        String bankName = command.getBankName();
        String bankAccountNumber = command.getBankAccountNumber();
        BankAccount bankAccountInfo = requestBankAccountInfoPort.getBankAccountInfo(new GetBankAccountRequest(bankName, bankAccountNumber));
        boolean accountIsValid = bankAccountInfo.isValid();
        if (accountIsValid) {
            RegisteredBankAccountJpaEntity registeredBankAccountJpaEntity = registerBankAccountPort.createRegisteredBankAccount(
                    new RegisteredBankAccount.MembershipId(command.getMembershipId()),
                    new RegisteredBankAccount.BankName(command.getBankName()),
                    new RegisteredBankAccount.BankAccountNumber(command.getBankAccountNumber()),
                    new RegisteredBankAccount.LinkedStatusIsValid(command.isLinkedStatusIsValid())
            );

            return registeredBankAccountMapper.mapToDomainEntity(registeredBankAccountJpaEntity);
        } else {
            return null;
        }

    }

}
