package org.shop.backend.Member.Model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/*************************************************************
 /* SYSTEM NAME      : Model
 /* PROGRAM NAME     : Member.class
 /* DESCRIPTION      : https://sddev.tistory.com/225(어노테이션 역할별 설명)
 /* MODIFIVATION LOG :
 /* DATA         AUTHOR          DESC.
 /*--------     ---------    ----------------------
 /*2025.03.24   KIMDONGMIN   INTIAL RELEASE
 /*************************************************************/

@Getter
@Setter
@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동증가가 될 수 있는 값인걸 명시
    private int id;

    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Column(length = 100 , nullable = false)// null을 허용하지 않음
    private String password;
}
