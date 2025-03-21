package org.africalib.gallery.backend.repository;

import org.africalib.gallery.backend.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

/*************************************************************
 /* SYSTEM NAME      : repository
 /* PROGRAM NAME     : ItemRepository.interface
 /* DESCRIPTION      :
 /* MODIFIVATION LOG :
 /* DATA         AUTHOR          DESC.
 /*--------     ---------    ----------------------
 /*2025.03.21   KIMDONGMIN   INTIAL RELEASE
 /*************************************************************/

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
