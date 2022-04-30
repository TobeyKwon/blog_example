package com.example.blog.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob
    private String content;

    private int count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    @OrderBy("id desc")
    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    List<Reply> replyList = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime dt_create;

    public void increamentCount() {
        this.count = count + 1;
    }

    public void addReply(Reply reply) {
        replyList.add(reply);
        reply.setBoard(this);
    }
}
