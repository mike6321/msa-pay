package com.example.membership.adapter.in.web;

import com.example.common.WebAdapter;
import com.example.membership.application.port.in.FindMembershipCommand;
import com.example.membership.application.port.in.FindMembershipUseCase;
import com.example.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RequestMapping("/membership")
@RestController
@RequiredArgsConstructor
public class FindMembershipController {

    private final FindMembershipUseCase findMembershipUseCase;

    @GetMapping("/{membershipId}")
    public ResponseEntity<Membership> findMembership(@PathVariable String membershipId) {
        FindMembershipCommand command = FindMembershipCommand.builder()
                .membershipId(membershipId)
                .build();

        Membership membership = findMembershipUseCase.findMembership(command);

        return ResponseEntity.ok(membership);
    }

}
