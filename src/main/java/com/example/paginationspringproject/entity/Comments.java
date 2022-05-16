package com.example.paginationspringproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
        name = "comments", uniqueConstraints = {@UniqueConstraint(columnNames = {"textOfComment"})}
)
public class Comments {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long id;

    @Column(name = "textOfComment", nullable = false)
    private String textOfComment;

    @Column(name = "authorName", nullable = false)
    private String authorName;

    @Column(name = "timeOfComment", nullable = false)
    private String timeOfComment;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTextOfComment() {
        return textOfComment;
    }

    public void setTextOfComment(String textOfComment) {
        this.textOfComment = textOfComment;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getTimeOfComment() {
        return timeOfComment;
    }

    public void setTimeOfComment(String timeOfComment) {
        this.timeOfComment = timeOfComment;
    }
}
