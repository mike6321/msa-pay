package com.example.money.application.service;

import com.example.common.UseCase;
import com.example.money.adapter.out.persistence.MemberMoneyJpaEntity;
import com.example.money.adapter.out.persistence.MoneyChangingRequestJpaEntity;
import com.example.money.adapter.out.persistence.MoneyChangingRequestMapper;
import lombok.RequiredArgsConstructor;
import com.example.money.application.port.in.IncreaseMoneyRequestCommand;
import com.example.money.application.port.in.IncreaseMoneyRequestUseCase;
import com.example.money.application.port.out.IncreaseMoneyPort;
import com.example.money.domain.MemberMoney;
import com.example.money.domain.MoneyChangingRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
@Transactional
public class IncreaseMoneyRequestService implements IncreaseMoneyRequestUseCase {

    private final IncreaseMoneyPort increaseMoneyPort;
    private final MoneyChangingRequestMapper mapper;

    @Override
    public MoneyChangingRequest increaseMoneyRequest(IncreaseMoneyRequestCommand command) {
        /**
         * 머니 충전
         * 1. 고객 정보가 정상 확인 (멤버)
         * 2. 고객 연동 계좌 여부 확인, 잔액 확인 (뱅킹)
         * 3. 법인 계좌 상태 확인 (뱅킹)
         * 4. 증액을 위한 기록 (머니)
         * 5. 펌뱅킹 수행 (고객 연동 계좌 -> 법인 계좌) (뱅킹)
         * 6. 결과 정상/실패 시 MoneyChangingRequest 상태 변동 후 리턴
         * -> 성공 시 멤버의 MemberMoney 값 증액
         * */

        MemberMoneyJpaEntity memberMoneyJpaEntity = increaseMoneyPort.increaseMoney(
                new MemberMoney.MembershipId(command.getTargetMembershipId()),
                command.getAmount()
        );

        if (memberMoneyJpaEntity != null) {
            MoneyChangingRequestJpaEntity moneyChangingRequestJpaEntity = increaseMoneyPort.createMoneyChangingRequest(
                    new MoneyChangingRequest.TargetMembershipId(command.getTargetMembershipId()),
                    new MoneyChangingRequest.MoneyChangingType(0),
                    new MoneyChangingRequest.ChangingMoneyAmount(command.getAmount()),
                    new MoneyChangingRequest.ChangingMoneyStatus(1),
                    new MoneyChangingRequest.Uuid(UUID.randomUUID())
            );

            return mapper.mapToDomainEntity(moneyChangingRequestJpaEntity);
        }

        return null;
    }

}
