package com.example.membership.adapter.in.web;

import com.example.common.WebAdapter;
import com.example.membership.application.port.in.RegisterMembershipCommand;
import com.example.membership.application.port.in.RegisterMembershipUseCase;
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
public class RegisterMembershipController {

    private final RegisterMembershipUseCase registerMembershipUseCase;

    @PostMapping("/register")
    public ResponseEntity<Membership> registerMembership(@RequestBody RegisterMembershipRequest request) {
        RegisterMembershipCommand command = RegisterMembershipCommand.builder()
                .name(request.getName())
                .address(request.getAddress())
                .email(request.getEmail())
                .isValid(true)
                .isCorp(request.isCorp())
                .build();

        return ResponseEntity.ok(registerMembershipUseCase.registerMembership(command));
    }

}

