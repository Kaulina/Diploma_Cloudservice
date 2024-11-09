package ru.netology.diploma_cloudservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Table(name = "TFILE", schema = "NETOLOGY")
@Data
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
public class StoreFile {
    @Id
    @Column(nullable = false, unique = true)
    private String filename;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private long size;

    @Lob
    @Column(nullable = false)
    private byte[] fileContent;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    public StoreFile(User user, String test) {
        this.user = user;
    }
}