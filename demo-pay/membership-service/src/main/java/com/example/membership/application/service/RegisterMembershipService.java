package com.example.membership.application.service;

import com.example.common.UseCase;
import com.example.membership.adapter.out.persistence.MembershipJpaEntity;
import com.example.membership.adapter.out.persistence.MembershipMapper;
import com.example.membership.application.port.in.RegisterMembershipCommand;
import com.example.membership.application.port.in.RegisterMembershipUseCase;
import com.example.membership.application.port.out.RegisterMembershipPort;
import com.example.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class RegisterMembershipService implements RegisterMembershipUseCase {

    private final RegisterMembershipPort registerMembershipPort;
    private final MembershipMapper membershipMapper;

    @Override
    public Membership registerMembership(RegisterMembershipCommand command) {
        MembershipJpaEntity membershipJpaEntity = registerMembershipPort.createMembership(
                new Membership.MembershipName(command.getName()),
                new Membership.MembershipEmail(command.getEmail()),
                new Membership.MembershipAddress(command.getAddress()),
                new Membership.MembershipIsValid(command.isValid()),
                new Membership.MembershipIsCorp(command.isCorp())
        );

        return membershipMapper.mapToDomainEntity(membershipJpaEntity);
    }

}
