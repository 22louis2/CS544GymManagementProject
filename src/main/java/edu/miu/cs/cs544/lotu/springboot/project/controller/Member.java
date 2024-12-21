package edu.miu.cs.cs544.lotu.springboot.project.controller;

import edu.miu.cs.cs544.lotu.springboot.project.enums.PackageStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("memberController")
@RequestMapping("/api/members")
public class Member {
    @Autowired
    private edu.miu.cs.cs544.lotu.springboot.project.service.Member memberService;

    @GetMapping
    public ResponseEntity<List<edu.miu.cs.cs544.lotu.springboot.project.entity.Member>> getAllMembers() {
        List<edu.miu.cs.cs544.lotu.springboot.project.entity.Member> members = memberService.getAllMembers();
        return ResponseEntity.ok(members);
    }

    @GetMapping("/{id}")
    public ResponseEntity<edu.miu.cs.cs544.lotu.springboot.project.entity.Member> getMemberById(@PathVariable Long id) {
        Optional<edu.miu.cs.cs544.lotu.springboot.project.entity.Member> member = memberService.getMemberById(id);
        return member
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<edu.miu.cs.cs544.lotu.springboot.project.entity.Member> createMember(@RequestBody edu.miu.cs.cs544.lotu.springboot.project.entity.Member member) {
        edu.miu.cs.cs544.lotu.springboot.project.entity.Member createdMember = memberService.createMember(member);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMember);
    }

    @PutMapping("/{id}")
    public ResponseEntity<edu.miu.cs.cs544.lotu.springboot.project.entity.Member> updateMember(@PathVariable Long id, @RequestBody edu.miu.cs.cs544.lotu.springboot.project.entity.Member updatedMember) {
        Optional<edu.miu.cs.cs544.lotu.springboot.project.entity.Member> member = memberService.updateMember(id, updatedMember);
        return member
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        if (memberService.deleteMember(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/active")
    public ResponseEntity<List<edu.miu.cs.cs544.lotu.springboot.project.entity.Member>> findAllWithMembershipActive(@RequestParam PackageStatus status) {
        List<edu.miu.cs.cs544.lotu.springboot.project.entity.Member> activeMembers = memberService.findAllWithMembershipActive(status);
        if (activeMembers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(activeMembers);
    }
}
