package org.project.backend.Member.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class BoardEntity {
    @Id
    @GeneratedValue
    private int boardNumber;//게시글번호 PK
    private String id;//아이디
    private String title;//제목
    private String content;//내용
    private String author;//작성자
    private LocalDateTime createdAt;//작성시
    private LocalDateTime updatedAt;//업데이트
    private String comment;//댓글 FK
    private String commentYn;//댓글 허용여부
    private String notesYn;//공지여부
    private String secret;// 비밀글
    private String image;//이미지
    private String ipAddress;//작성자아이피
    private String status;//게시글 상태
    private String reportedCount;//게시글 신고 건 수
    private String attachment;//첨부파일
    private int views;//조회수
    private String password;// 게스트 글 비밀번호용 컬럼 (필요한 경우)
}
