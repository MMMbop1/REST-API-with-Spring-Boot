package ogenblad.example.individuellUppgift.controller;

import jakarta.validation.Valid;
import ogenblad.example.individuellUppgift.dto.RequestMemberDto;
import ogenblad.example.individuellUppgift.dto.PatchMemberDto;
import ogenblad.example.individuellUppgift.dto.ResponseMemberDto;
import ogenblad.example.individuellUppgift.entity.Member;
import ogenblad.example.individuellUppgift.service.ServiceMember;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<ResponseMemberDto>> getMembers() {
        return ResponseEntity.ok().body(serviceMember.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMemberDto> getMember(@PathVariable Long id) {
        return ResponseEntity.ok().body(serviceMember.find(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> putMember(@PathVariable Long id, @RequestBody @Valid RequestMemberDto member) {
        return ResponseEntity.ok().body(serviceMember.update(member, id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Member> patchMember(@PathVariable Long id, @RequestBody @Valid PatchMemberDto patchMemberDto) {
        return ResponseEntity
                .ok()
                .body(serviceMember.patchUpdate(patchMemberDto, id));
    }

    @PostMapping
    public ResponseEntity<Member> postMember(@RequestBody @Valid Member member) {
        Member savedMember = serviceMember.save(member);
        return ResponseEntity.ok().body(savedMember);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        serviceMember.delete(id);
        return ResponseEntity.noContent().build();
    }
}
