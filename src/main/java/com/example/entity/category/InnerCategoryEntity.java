package com.example.entity.category;

import com.example.entity.AttachEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "innerCategory")
@Getter
@Setter
public class InnerCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "photo_id")
    private Long photoId;
    @OneToOne
    @JoinColumn(name = "photo_id",insertable = false,updatable = false)
    private AttachEntity photo;

    @Column(name = "category_id")
    private Long categoryId;
    @ManyToOne
    @JoinColumn(name = "category_id",insertable = false,updatable = false)
    private CategoryEntity category;

    @Column(name = "name_uz")
    private String nameUz;

    @Column(name = "name_ru")
    private String nameRu;

    @Column(name = "description_uz")
    private String descriptionUz;

    @Column(name = "description_ru")
    private String descriptionRu;

    @CreationTimestamp
    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createdDate;

}
