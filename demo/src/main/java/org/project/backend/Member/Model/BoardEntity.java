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
    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String comment;
    private String commentYn;//댓글 허용여부
    private String notesYn;
    private String secret;
    private String image;
    private String ipAddress;
    private String status;
    private String reportedCount;
    private String attachment;


    private int views;
    // 게스트 글 비밀번호용 컬럼 (필요한 경우)
    private String password;
}
