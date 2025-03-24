package org.africalib.gallery.backend.repository;

import org.africalib.gallery.backend.entity.Item;
import org.africalib.gallery.backend.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/*************************************************************
 /* SYSTEM NAME      : repository
 /* PROGRAM NAME     : MemberRepository.interface
 /* DESCRIPTION      :
 /* MODIFIVATION LOG :
 /* DATA         AUTHOR          DESC.
 /*--------     ---------    ----------------------
 /*2025.03.21   KIMDONGMIN   INTIAL RELEASE
 /*************************************************************/

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findByEmailAndPassword(String email, String password);
}
