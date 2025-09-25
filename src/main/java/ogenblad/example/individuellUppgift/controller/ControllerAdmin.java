package ogenblad.example.individuellUppgift.controller;

import ogenblad.example.individuellUppgift.entity.Member;
import ogenblad.example.individuellUppgift.service.ServiceMember;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
    