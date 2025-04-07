package org.project.backend.Product.Model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/*************************************************************
 /* SYSTEM NAME      : Model
 /* PROGRAM NAME     : Product.class
 /* DESCRIPTION      : https://sddev.tistory.com/225(어노테이션 역할별 설명)
 /* MODIFIVATION LOG :
 /* DATA         AUTHOR          DESC.
 /*--------     ---------    ----------------------
 /*2025.03.24   KIMDONGMIN   INTIAL RELEASE
 /*************************************************************/

@Getter
@Setter
@Entity
@Table(name = "items")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동증가가 될 수 있는 값인걸 명시
    private int id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 100)
    private String img_path;

    @Column
    private int price;

    @Column
    private int discount_per;
}
