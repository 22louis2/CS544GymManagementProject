package edu.miu.cs.cs544.lotu.springboot.project.entity;

import edu.miu.cs.cs544.lotu.springboot.project.enums.PackageType;
import edu.miu.cs.cs544.lotu.springboot.project.enums.PackageStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Subscription {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate startDate;
    private LocalDate endDate;
    @Enumerated(EnumType.STRING)
    private PackageStatus packageStatus;
    @Enumerated(EnumType.STRING)
    private PackageType packageType;

    @OneToOne
    @JoinColumn(name =  "member_id")
    private Member member;

    public Subscription() {}

    public long getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public PackageStatus getPackageStatus() {
        return packageStatus;
    }

    public void setPackageStatus(PackageStatus packageStatus) {
        this.packageStatus = packageStatus;
    }

    public PackageType getPackageType() {
        return packageType;
    }

    public void setPackageType(PackageType packageType) {
        this.packageType = packageType;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", packageStatus=" + packageStatus +
                ", packageType=" + packageType +
                ", member=" + member +
                '}';
    }
}
