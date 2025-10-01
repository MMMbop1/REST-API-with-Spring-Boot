package ogenblad.example.individuellUppgift.controller;

import jakarta.validation.Valid;
import ogenblad.example.individuellUppgift.dto.DietMemberDto;
import ogenblad.example.individuellUppgift.dto.MemberDto;
import ogenblad.example.individuellUppgift.entity.Member;
import ogenblad.example.individuellUppgift.service.ServiceMember;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity<List<DietMemberDto>> getMembers() {
        return ResponseEntity.ok().body(serviceMember.findAllDietMembers());
    }

    @PutMapping("/mypages/members/{id}")
    public ResponseEntity<Member> putMember(@PathVariable Long id, @RequestBody @Valid MemberDto member) {
        return ResponseEntity.ok().body(serviceMember.update(member, id));
    }
}
