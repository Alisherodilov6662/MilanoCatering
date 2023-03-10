package com.example.entity.category;

import com.example.entity.AttachEntity;
import com.example.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "category")
@Getter
@Setter
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "photo_id")
    private Long photoId;
    @OneToOne
    @JoinColumn(name = "photo_id",insertable = false,updatable = false)
    private AttachEntity photo;

    @Column(name = "name_uz")
    private String nameUz;

    @Column(name = "name_ru")
    private String nameRu;

    @Column(name = "description_uz",columnDefinition = "varchar")
    private String descriptionUz;

    @Column(name = "description_ru",columnDefinition = "varchar")
    private String descriptionRu;

    @CreationTimestamp
    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @UpdateTimestamp
    @Column(name = "update_date")
    private LocalDateTime updatedDate;

    @Column
    private Boolean visible;
    @Column
    @Enumerated(EnumType.STRING)
    private Status status;
}
