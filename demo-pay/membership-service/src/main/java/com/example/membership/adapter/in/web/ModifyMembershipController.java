package com.example.membership.adapter.in.web;

import com.example.common.WebAdapter;
import com.example.membership.application.port.in.ModifyMembershipCommand;
import com.example.membership.application.port.in.ModifyMembershipUseCase;
import com.example.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RequestMapping("/membership")
@RestController
@RequiredArgsConstructor
public class ModifyMembershipController {

    private final ModifyMembershipUseCase modifyMembershipUseCase;

    @PostMapping("/modify")
    public ResponseEntity<Membership> modifyMembership(@RequestBody ModifyMembershipRequest request) {

        ModifyMembershipCommand command = ModifyMembershipCommand.builder()
                .membershipId(request.getMembershipId())
                .name(request.getName())
                .email(request.getEmail())
                .address(request.getAddress())
                .isCorp(request.isCorp())
                .isValid(request.isValid())
                .build();

        return ResponseEntity.ok(modifyMembershipUseCase.modifyMembership(command));
    }

}
