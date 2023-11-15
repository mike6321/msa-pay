package com.example.money.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MoneyChangingRequest {

    @Getter private final String moneyChangingRequestId;

    // 어떤 고객이 증액/감액 요청을 했는 지에 대한 멤버 정보
    @Getter private final String targetMembershipId;

    // 증액 요청/감액 요청
    @Getter private final int changingType;

    // 증액/감액 요청 금액
    @Getter private final int changingMoneyAmount;

    // 증액/감액 요청 상태
    @Getter private final int changingMoneyStatus;

    @Getter private final String uuid;

    @Getter private final Date createdAt;


//    enum ChangeMoneyStatus {
//        REQUESTED, // 요청됨
//        SUCCEEDED, // 성공
//        FAILED, // 실패
//        CANCELLED // 취소
//    }

//    enum ChangingType {
//        INCREASING, // 증액
//        DECREASING // 감액
//    }

    public static MoneyChangingRequest generateMoneyChangingRequest(
            MoneyChangingRequestId moneyChangingRequestId,
            TargetMembershipId targetMembershipId,
            MoneyChangingType moneyChangingType,
            ChangingMoneyAmount changingMoneyAmount,
            ChangingMoneyStatus changingMoneyStatus,
            Uuid uuid) {
        return new MoneyChangingRequest(
                moneyChangingRequestId.moneyChangingRequestId,
                targetMembershipId.targetMembershipId,
                moneyChangingType.changingType,
                changingMoneyAmount.changingMoneyAmount,
                changingMoneyStatus.changingMoneyStatus,
                uuid.uuid,
                new Date());
    }

    @Value
    public static class MoneyChangingRequestId {
        public MoneyChangingRequestId(String value) {
            this.moneyChangingRequestId = value;
        }
        String moneyChangingRequestId;
    }

    @Value
    public static class TargetMembershipId {
        public TargetMembershipId(String value) {
            this.targetMembershipId = value;
        }

        String targetMembershipId;
    }
    @Value
    public static class MoneyChangingType {
        public MoneyChangingType(int value) {
            this.changingType = value;
        }
        int changingType;
    }

    @Value
    public static class ChangingMoneyAmount {
        public ChangingMoneyAmount(int value) {
            this.changingMoneyAmount = value;
        }
        int changingMoneyAmount;
    }

    @Value
    public static class ChangingMoneyStatus {
        public ChangingMoneyStatus(int value) {
            this.changingMoneyStatus = value;
        }
        int changingMoneyStatus;
    }

    @Value
    public static class Uuid {
        public Uuid(UUID value) {
            this.uuid = value.toString();
        }
        String uuid;
    }

    @Value
    public static class CreatedAt {
        public CreatedAt(Date value) {
            this.createdAt = value;
        }
        Date createdAt;
    }

}
