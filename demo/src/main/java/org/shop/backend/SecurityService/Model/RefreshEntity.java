package org.shop.backend.SecurityService.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/*************************************************************
 /* SYSTEM NAME      : Model
 /* PROGRAM NAME     : RefreshEntity.class
 /* DESCRIPTION      : https://sddev.tistory.com/225(어노테이션 역할별 설명)
 /* MODIFIVATION LOG :
 /* DATA         AUTHOR          DESC.
 /*--------     ---------    ----------------------
 /*2025.04.01   KIMDONGMIN   INTIAL RELEASE
 /*************************************************************/

@Getter
@Setter
@Entity
@Table(name = "tokenhistory")
public class RefreshEntity {
    @Id @GeneratedValue
    private String id;
    private String username;
    private String refresh;
    private String expiration;

}
