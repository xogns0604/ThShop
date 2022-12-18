package com.JPA.THShop.member.domain;
import com.JPA.THShop.common.entity.BaseTime;
import com.JPA.THShop.Shop.domain.Order;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTime {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USER_ID", unique = true)
    private String userId;
    private String pwd;
    private String name;
    private String email;
    private int holdings; // 보유자산
    private int point; //포인트

    @Enumerated(EnumType.STRING) // 사용자의 상태 관리자, 회원
    private MemberStatus status;

    @OneToMany(mappedBy = "member") //@JsonIgnore
    private List<Order> orders = new ArrayList<>();

    public Member(String userId, String pwd, String name, String email) {
        this.userId = userId;
        this.pwd = pwd;
        this.name = name;
        this.email = email;

        this.status = MemberStatus.generalMem;
        this.holdings = 0;
        this.point = 0;
    }

    public Member(String userId, String pwd, String name) {
        this.userId = userId;
        this.pwd = pwd;
        this.name = name;
    }

    public void editInfo(String pwd){
        this.pwd = pwd;
    }

    //==연관관계 매서드==//

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", holdings=" + holdings +
                ", point=" + point +
                ", status=" + status +
                '}';
    }
}
//    public void update(String userPwd, String userName, int age, String email, MemberStatus status) {
//        this.pwd = userPwd;
//        this.name = userName;
//        this.email = email;
//        this.status = status;
//    }

//    @OneToMany(mappedBy = "member")
//    private List<Board> boards = new ArrayList<>();
//    @Builder
//    public Member(String userId, String pwd, String name, String email, int holdings, int point, MemberStatus status) {
//        this.userId = userId;
//        this.pwd = pwd;
//        this.name = name;
//        this.email = email;
//        this.enrollDate = LocalDateTime.now();
//        this.holdings = holdings;
//        this.point = point;
//        this.status = status;
//    }
