package org.shop.backend.entity;
import jakarta.persistence.*;
import lombok.Getter;

/*************************************************************
/* SYSTEM NAME      : entity
/* PROGRAM NAME     : item.class
/* DESCRIPTION      : https://sddev.tistory.com/225(어노테이션 역할별 설명)
/* MODIFIVATION LOG :
/* DATA         AUTHOR          DESC.
/*--------     ---------    ----------------------
/*2025.03.21   KIMDONGMIN   INTIAL RELEASE
/*************************************************************/

@Getter
@Entity
@Table(name = "items")
public class Item {

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
