package ogenblad.example.individuellUppgift.controller;

import jakarta.validation.Valid;
import ogenblad.example.individuellUppgift.dto.MemberDto;
import ogenblad.example.individuellUppgift.entity.Member;
import ogenblad.example.individuellUppgift.service.ServiceMember;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Member>> getMembers() {
        return ResponseEntity.ok().body(serviceMember.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMember(@PathVariable Long id) {
        return ResponseEntity.ok().body(serviceMember.find(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putMember(@PathVariable Long id, @RequestBody Member member) {
        return ResponseEntity.ok().body(serviceMember.update(member, id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchMember(@PathVariable Long id,@RequestBody @Valid MemberDto patchMemberDto) {
        return ResponseEntity
                .ok()
                .body(serviceMember.patchUpdate(patchMemberDto, id));
    }

    @PostMapping
    public ResponseEntity<?> postMember(@RequestBody @Valid Member member) throws URISyntaxException {
        Member savedMember = serviceMember.save(member);
        return ResponseEntity
                .created(new URI("/admin/members/" + member.getId()))
                .body(savedMember);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        serviceMember.delete(id);
        return ResponseEntity.noContent().build();
    }
}
