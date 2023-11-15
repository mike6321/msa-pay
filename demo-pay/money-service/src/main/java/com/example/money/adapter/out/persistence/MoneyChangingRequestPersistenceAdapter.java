package com.example.money.adapter.out.persistence;

import com.example.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import com.example.money.application.port.out.IncreaseMoneyPort;
import com.example.money.domain.MemberMoney;
import com.example.money.domain.MoneyChangingRequest;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@PersistenceAdapter
@RequiredArgsConstructor
public class MoneyChangingRequestPersistenceAdapter implements IncreaseMoneyPort {

    private final SpringDataMoneyChangingRequestRepository moneyChangingRequestRepository;
    private final SpringDataMemberMoneyRepository memberMoneyRepository;

    @Override
    public MoneyChangingRequestJpaEntity createMoneyChangingRequest(MoneyChangingRequest.TargetMembershipId targetMembershipId,
                                                                    MoneyChangingRequest.MoneyChangingType moneyChangingType,
                                                                    MoneyChangingRequest.ChangingMoneyAmount changingMoneyAmount,
                                                                    MoneyChangingRequest.ChangingMoneyStatus changingMoneyStatus,
                                                                    MoneyChangingRequest.Uuid uuid) {
        return moneyChangingRequestRepository.save(
                new MoneyChangingRequestJpaEntity(
                        targetMembershipId.getTargetMembershipId(),
                        moneyChangingType.getChangingType(),
                        changingMoneyAmount.getChangingMoneyAmount(),
                        changingMoneyStatus.getChangingMoneyStatus(),
                        new Timestamp(System.currentTimeMillis()),
                        UUID.randomUUID()
                )
        );
    }

    @Override
    public MemberMoneyJpaEntity increaseMoney(MemberMoney.MembershipId membershipId, int increaseMoneyAmount) {
        MemberMoneyJpaEntity memberMoneyJpaEntity;
        try {
            List<MemberMoneyJpaEntity> memberMoneyJpaEntities = memberMoneyRepository.findByMembershipId(Long.parseLong(membershipId.getMembershipId()));
            memberMoneyJpaEntity = memberMoneyJpaEntities.get(0);
            memberMoneyJpaEntity.setBalance(memberMoneyJpaEntity.getBalance() + increaseMoneyAmount);

            return memberMoneyRepository.save(memberMoneyJpaEntity);
        } catch (Exception e) {
            memberMoneyJpaEntity = new MemberMoneyJpaEntity(
                    membershipId.getMembershipId(),
                    increaseMoneyAmount
            );
            memberMoneyJpaEntity =  memberMoneyRepository.save(memberMoneyJpaEntity);
            return memberMoneyJpaEntity;
        }
    }

}
