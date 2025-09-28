package ogenblad.example.individuellUppgift.controller;

import ogenblad.example.individuellUppgift.entity.Address;
import ogenblad.example.individuellUppgift.entity.Member;
import ogenblad.example.individuellUppgift.exceptions.AddressNotFoundException;
import ogenblad.example.individuellUppgift.exceptions.MemberNotFoundException;
import ogenblad.example.individuellUppgift.service.ServiceAddress;
import ogenblad.example.individuellUppgift.service.ServiceMember;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/admin/members")
public class ControllerAdmin {

    private ServiceMember serviceMember;

    public ControllerAdmin(ServiceMember serviceMember) {
        this.serviceMember = serviceMember;
    }

    @GetMapping
    public ResponseEntity<List<Member>> getMembers() {
        List<Member> members = serviceMember.findAll();
        return ResponseEntity.ok().body(members);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMember(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(serviceMember.find(id));
        } catch (MemberNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putMember(@PathVariable Long id, @RequestBody Member member) {
        try {
            Member updatedMember = serviceMember.update(member, id);
            return ResponseEntity.ok().body(updatedMember);
        } catch (AddressNotFoundException | MemberNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchMember(@PathVariable Long id, @RequestBody Member patchMember) {
        try {
            Member updatedMember = serviceMember.patchUpdate(patchMember, id);
            return ResponseEntity.ok().body(updatedMember);
        } catch (AddressNotFoundException | MemberNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Member> postMember(@RequestBody Member member) {
        Member savedMember = serviceMember.save(member);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMember);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        try {
            serviceMember.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (MemberNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
}
