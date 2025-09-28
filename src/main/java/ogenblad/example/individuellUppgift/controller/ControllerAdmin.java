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
    private ServiceAddress serviceAddress;

    public ControllerAdmin(ServiceMember serviceMember, ServiceAddress serviceAddress) {
        this.serviceMember = serviceMember;
        this.serviceAddress = serviceAddress;
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

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<?> putMember(@PathVariable Long id, @RequestBody Member member) {
        try {
            serviceMember.update(member, id);
            return ResponseEntity.ok().body(member);
        } catch (AddressNotFoundException | MemberNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PatchMapping(value = "/id")
    public ResponseEntity<Member> patchMember(@PathVariable Long id, @RequestBody Member member) {
        return null;
    }
}
    