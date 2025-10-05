package org.ogenblad.spring.rest.controller;

import jakarta.validation.Valid;
import org.ogenblad.spring.rest.dto.DietMemberDto;
import org.ogenblad.spring.rest.dto.RequestMemberDto;
import org.ogenblad.spring.rest.dto.ResponseMemberDto;
import org.ogenblad.spring.rest.service.ServiceMember;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mypages/members")
public class ControllerMember {

    private final ServiceMember serviceMember;

    public ControllerMember(ServiceMember serviceMember) {
        this.serviceMember = serviceMember;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    ResponseEntity<List<DietMemberDto>> getMembers() {
        return ResponseEntity.ok().body(serviceMember.findAllDietMembers());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ResponseMemberDto> putMember(@PathVariable Long id,
                                                       @RequestBody @Valid RequestMemberDto requestMemberDto,
                                                       Authentication authentication) {
        return ResponseEntity.ok().body(serviceMember.update(requestMemberDto, id, authentication));
    }
}
