package org.ogenblad.spring.rest.controller;

import jakarta.validation.Valid;
import org.ogenblad.spring.rest.dto.RequestMemberDto;
import org.ogenblad.spring.rest.dto.PatchMemberDto;
import org.ogenblad.spring.rest.dto.ResponseMemberDto;
import org.ogenblad.spring.rest.service.ServiceMember;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@RestController
@RequestMapping("/admin/members")
public class ControllerAdmin {

    private final ServiceMember serviceMember;

    public ControllerAdmin(ServiceMember serviceMember) {
        this.serviceMember = serviceMember;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ResponseMemberDto>> getMembers() {
        return ResponseEntity.ok().body(serviceMember.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseMemberDto> getMember(@PathVariable Long id) {
        return ResponseEntity.ok().body(serviceMember.find(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseMemberDto> putMember(@PathVariable Long id,
                                                       @RequestBody @Valid RequestMemberDto requestMemberDto) {
        return ResponseEntity.ok().body(serviceMember.update(requestMemberDto, id));
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseMemberDto> patchMember(@PathVariable Long id,
                                              @RequestBody @Valid PatchMemberDto patchMemberDto) {
        return ResponseEntity.ok().body(serviceMember.patchUpdate(patchMemberDto, id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseMemberDto> postMember(@RequestBody @Valid RequestMemberDto requestMemberDto) throws URISyntaxException {
        ResponseMemberDto savedMember = serviceMember.save(requestMemberDto);
        return ResponseEntity
                .created(new URI("admin/members/" + savedMember.id()))
                .body(savedMember);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        serviceMember.delete(id);
        return ResponseEntity.noContent().build();
    }
}
