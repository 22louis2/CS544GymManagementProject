package edu.miu.cs.cs544.lotu.springboot.project.repository;

import edu.miu.cs.cs544.lotu.springboot.project.enums.PackageStatus;
import jakarta.persistence.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("memberRepository")
public interface Member extends JpaRepository<edu.miu.cs.cs544.lotu.springboot.project.entity.Member, Long> {
    @Query(name = "Member.findAllWithMembershipActive")
    List<edu.miu.cs.cs544.lotu.springboot.project.entity.Member> findAllWithMembershipActive(@Param("status") PackageStatus status);
}
