package com.pettory.pettory.jointshopping.command.application.controller;

import com.pettory.pettory.jointshopping.command.application.dto.JointShoppingParticipationRequest;
import com.pettory.pettory.jointshopping.command.application.service.JointShoppingParticipationApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("jointshopping")
public class JointShoppingParticipationCommandController {

    private final JointShoppingParticipationApplicationService jointShoppingParticipationApplicationService;

    /* 공동구매 참가 등록 */
    @PostMapping("/participation")
    public ResponseEntity<Void> createBookmark(
            @RequestBody @Valid JointShoppingParticipationRequest participationRequest
    ) {
        Long participationNum = jointShoppingParticipationApplicationService.createParticipation(participationRequest);

        return ResponseEntity
                .created(URI.create("/jointshopping/participation/" + participationNum))
                .build();
    }

    /* 공동구매 참가 취소 */
    @DeleteMapping("/participation/{participationNum}")
    public ResponseEntity<Void> deleteBookmark(@PathVariable final Long participationNum) {

        jointShoppingParticipationApplicationService.deleteParticipation(participationNum);

        return ResponseEntity.noContent().build();
    }
}
