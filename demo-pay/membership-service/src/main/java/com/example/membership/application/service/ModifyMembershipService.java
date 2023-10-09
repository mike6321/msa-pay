package com.example.membership.application.service;

import com.example.common.UseCase;
import com.example.membership.adapter.out.persistence.MembershipJpaEntity;
import com.example.membership.adapter.out.persistence.MembershipMapper;
import com.example.membership.application.port.in.ModifyMembershipCommand;
import com.example.membership.application.port.in.ModifyMembershipUseCase;
import com.example.membership.application.port.out.ModifyMembershipPort;
import com.example.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class ModifyMembershipService implements ModifyMembershipUseCase {

    private final ModifyMembershipPort modifyMembershipPort;
    private final MembershipMapper membershipMapper;

    @Override
    public Membership modifyMembership(ModifyMembershipCommand command) {
        MembershipJpaEntity membershipJpaEntity = modifyMembershipPort.modifyMembership(
                new Membership.MembershipId(command.getMembershipId()),
                new Membership.MembershipName(command.getName()),
                new Membership.MembershipEmail(command.getEmail()),
                new Membership.MembershipAddress(command.getAddress()),
                new Membership.MembershipIsValid(command.isValid()),
                new Membership.MembershipIsCorp(command.isCorp())
        );

        return membershipMapper.mapToDomainEntity(membershipJpaEntity);
    }

}
