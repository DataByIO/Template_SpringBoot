package org.project.backend.SecurityService.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/*************************************************************
 /* SYSTEM NAME      : Model
 /* PROGRAM NAME     : MemberEntity.class
 /* DESCRIPTION      : https://sddev.tistory.com/225(어노테이션 역할별 설명)
 /* MODIFIVATION LOG :
 /* DATA         AUTHOR          DESC.
 /*--------     ---------    ----------------------
 /*2025.03.24   KIMDONGMIN   INTIAL RELEASE
 /*************************************************************/

@Getter
@Setter
@Entity
@Table(name = "memberlist")
public class MemberEntity {
    @Id @GeneratedValue
    private String id;
    private String username;
    private String email;
    private String password;
    private String role;
    private String phone_number;
    private String gender;
    private String address;
    private String user_status;
    private String birth_date;
    private String profile_image;
    private int points;
    private String last_login_at;
    private String created_at;
    private String updated_at;

}