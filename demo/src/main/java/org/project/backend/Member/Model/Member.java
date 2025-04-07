package org.project.backend.Member.Model;
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
public class Member {

    @Id
    private int id;
    private String email;
    private String password;

}
