package edu.miu.cs.cs544.lotu.springboot.project.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;

@Entity
@NamedQuery(name = "Member.findAllWithMembershipActive", query = "SELECT m FROM Member m WHERE m.subscription.packageStatus = :status")
public class Member extends Person {
    private LocalDate joinedDate;

    @OneToOne(mappedBy = "member", cascade = CascadeType.PERSIST)
    private Subscription subscription;

    public Member() {}

    public LocalDate getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(LocalDate joinedDate) {
        this.joinedDate = joinedDate;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public String toString() {
        return "Member{" +
                "joinedDate=" + joinedDate +
                ", subscription=" + subscription +
                '}';
    }
}
