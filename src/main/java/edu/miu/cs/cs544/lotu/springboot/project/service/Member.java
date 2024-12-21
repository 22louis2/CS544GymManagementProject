package edu.miu.cs.cs544.lotu.springboot.project.service;

import edu.miu.cs.cs544.lotu.springboot.project.enums.PackageStatus;
import edu.miu.cs.cs544.lotu.springboot.project.jms.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service( "memberService")
public class Member {
    @Autowired
    private edu.miu.cs.cs544.lotu.springboot.project.repository.Member memberRepository;

    @Autowired
    private Sender sender;

    public Member() {}

    public List<edu.miu.cs.cs544.lotu.springboot.project.entity.Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Optional<edu.miu.cs.cs544.lotu.springboot.project.entity.Member> getMemberById(Long id) {
        return memberRepository.findById(id);
    }

    public edu.miu.cs.cs544.lotu.springboot.project.entity.Member createMember(edu.miu.cs.cs544.lotu.springboot.project.entity.Member member) {
        edu.miu.cs.cs544.lotu.springboot.project.entity.Member savedMember = memberRepository.save(member);

        String message = String.format("New member %s (ID: %d) has been successfully created.",
                savedMember.getFirstName() + " " + savedMember.getLastName(),
                savedMember.getId());
        sender.send(message);

        return savedMember;
    }

    public Optional<edu.miu.cs.cs544.lotu.springboot.project.entity.Member> updateMember(Long id, edu.miu.cs.cs544.lotu.springboot.project.entity.Member updatedMember) {
        return memberRepository.findById(id)
                .map(member -> {
                    member.setJoinedDate(updatedMember.getJoinedDate());
                    member.setSubscription(updatedMember.getSubscription());
                    return memberRepository.save(member);
                });
    }

    public boolean deleteMember(Long id) {
        if (memberRepository.existsById(id)) {
            memberRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<edu.miu.cs.cs544.lotu.springboot.project.entity.Member> findAllWithMembershipActive(PackageStatus status) {
        return memberRepository.findAllWithMembershipActive(status);
    }
}
