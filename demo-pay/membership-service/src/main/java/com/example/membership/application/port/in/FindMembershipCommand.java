package com.example.membership.application.port.in;

import com.example.common.SelfValidating;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class FindMembershipCommand extends SelfValidating<FindMembershipCommand> {

    private String membershipId;

    public FindMembershipCommand(String membershipId) {
        this.membershipId = membershipId;
        this.setMembershipId(membershipId);
    }

}
