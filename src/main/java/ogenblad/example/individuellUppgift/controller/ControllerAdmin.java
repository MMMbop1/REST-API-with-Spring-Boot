package ogenblad.example.individuellUppgift.controller;

import ogenblad.example.individuellUppgift.entity.Address;
import ogenblad.example.individuellUppgift.entity.Member;
import ogenblad.example.individuellUppgift.service.ServiceAddress;
import ogenblad.example.individuellUppgift.service.ServiceMember;
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
    public ResponseEntity<Member> getMember(@PathVariable Long id) {
        Optional<Member> member = serviceMember.find(id);
        if (member.isPresent()) {
            return ResponseEntity.ok().body(member.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<Member> putMember(@PathVariable Long id, @RequestBody Member member) {

        Optional<Address> address = serviceAddress.find(member.getAddress().getId());

        if (address.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (serviceMember.find(id).isPresent()) {
            member.setId(id);
            serviceMember.update(member);
            return ResponseEntity.ok().body(member);
        }

        return ResponseEntity.notFound().build();
    }

}
    