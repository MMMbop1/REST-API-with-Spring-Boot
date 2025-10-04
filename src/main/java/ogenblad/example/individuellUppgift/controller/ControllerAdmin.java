package ogenblad.example.individuellUppgift.controller;

import jakarta.validation.Valid;
import ogenblad.example.individuellUppgift.dto.RequestMemberDto;
import ogenblad.example.individuellUppgift.dto.PatchMemberDto;
import ogenblad.example.individuellUppgift.dto.ResponseMemberDto;
import ogenblad.example.individuellUppgift.service.ServiceMember;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ResponseMemberDto> postMember(@RequestBody @Valid RequestMemberDto requestMemberDto) {
        ResponseMemberDto savedMember = serviceMember.save(requestMemberDto);
        return ResponseEntity.ok().body(savedMember);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        serviceMember.delete(id);
        return ResponseEntity.noContent().build();
    }
}
