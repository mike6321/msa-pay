package com.example.money.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberMoney {

    @Getter private final String memberMoneyId;

    // 어떤 고객이 증액/감액 요청을 했는 지에 대한 멤버 정보
    @Getter private final String membershipId;

    // 증액 요청/감액 요청
    @Getter private final int balance;


    public static MemberMoney generateMemberMoney(
            MemberMoneyId memberMoneyId,
            MembershipId membershipId,
            Balance balance) {
        return new MemberMoney(
                memberMoneyId.memberMoneyId,
                membershipId.membershipId,
                balance.balance);
    }

    @Value
    public static class MemberMoneyId {
        public MemberMoneyId(String value) {
            this.memberMoneyId = value;
        }
        String memberMoneyId;
    }

    @Value
    public static class MembershipId {
        public MembershipId(String value) {
            this.membershipId = value;
        }

        String membershipId;
    }
    @Value
    public static class Balance {
        public Balance(int value) {
            this.balance = value;
        }

        int balance;
    }

}
