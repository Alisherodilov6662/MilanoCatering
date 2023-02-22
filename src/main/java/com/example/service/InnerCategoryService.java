package com.example.service;


import com.example.dto.category.innerCategory.InnerCategoryCreationDto;
import com.example.dto.category.innerCategory.InnerCategoryGetDTO;
import com.example.dto.category.innerCategory.InnerCategoryUpdateDTO;
import com.example.dto.category.superCategory.CategoryGetForPublish;
import com.example.entity.category.InnerCategoryEntity;
import com.example.enums.Status;
import com.example.exception.CategoryNotFoundException;
import com.example.exception.InnerCategoryAlreadyExistsException;
import com.example.exception.InnerCategoryNotFoundException;
import com.example.repositiry.InnerCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class InnerCategoryService {

    @Autowired
    private final InnerCategoryRepository innerCategoryRepository;

    public InnerCategoryCreationDto create(InnerCategoryCreationDto innerCategoryCreationDto) {
        Optional<InnerCategoryEntity> byNameUz = innerCategoryRepository.findByNameUz(innerCategoryCreationDto.getNameUz());
        Optional<InnerCategoryEntity> byNameRu = innerCategoryRepository.findByNameRu(innerCategoryCreationDto.getNameRu());
        if (byNameUz.isEmpty() && byNameRu.isEmpty()) {
            InnerCategoryEntity entity = toEntity(innerCategoryCreationDto);
            innerCategoryRepository.save(entity);
            return innerCategoryCreationDto;
        } else {
            throw new InnerCategoryAlreadyExistsException("Inner category already exists");
        }
    }

    public InnerCategoryCreationDto update(Long innerCategoryId, InnerCategoryCreationDto innerCategoryCreationDto) {
        Optional<InnerCategoryEntity> optional = innerCategoryRepository.findById(innerCategoryId);
        if (optional.isEmpty()) {
            throw new InnerCategoryNotFoundException("Inner category not found");
        } else {
            InnerCategoryEntity entity = optional.get();
            entity.setPhotoId(innerCategoryCreationDto.getPhoto());
            entity.setCategoryId(innerCategoryCreationDto.getCategory());
            entity.setNameUz(innerCategoryCreationDto.getNameUz());
            entity.setNameRu(innerCategoryCreationDto.getNameRu());
            entity.setDescriptionUz(innerCategoryCreationDto.getDescriptionUz());
            entity.setDescriptionRu(innerCategoryCreationDto.getDescriptionRu());
            innerCategoryRepository.save(entity);
            return innerCategoryCreationDto;
        }
    }

    public InnerCategoryEntity toEntity(InnerCategoryCreationDto innerCategoryCreationDto) {
        InnerCategoryEntity entity = new InnerCategoryEntity();
        entity.setPhotoId(innerCategoryCreationDto.getPhoto());
        entity.setCategoryId(innerCategoryCreationDto.getCategory());
        entity.setNameUz(innerCategoryCreationDto.getNameUz());
        entity.setNameRu(innerCategoryCreationDto.getNameRu());
        entity.setDescriptionUz(innerCategoryCreationDto.getDescriptionUz());
        entity.setDescriptionRu(innerCategoryCreationDto.getDescriptionRu());
        entity.setVisible(true);
        entity.setStatus(Status.NOT_PUBLISHED);
        return entity;
    }

    public Boolean delete(Long innerCategoryId) {
        Optional<InnerCategoryEntity> optional = innerCategoryRepository.findById(innerCategoryId);
        if (optional.isEmpty()) {
            throw new InnerCategoryNotFoundException("Inner category not found");
        } else {
            InnerCategoryEntity entity = optional.get();
            entity.setVisible(false);
            innerCategoryRepository.save(entity);
            return true;
        }
    }


    public InnerCategoryGetDTO getDTO(InnerCategoryEntity entity, String language) {
        InnerCategoryGetDTO dto = new InnerCategoryGetDTO();
        dto.setPhoto(entity.getPhotoId());
        if (language.equals("UZ")) {
            dto.setId(entity.getId());
            dto.setNameUz(entity.getNameUz());
            dto.setDescriptionUz(entity.getDescriptionUz());
        } else {
            dto.setId(entity.getId());
            dto.setNameRu(entity.getNameRu());
            dto.setDescriptionRu(entity.getDescriptionRu());
        }

        return dto;
    }

    public String changeStatus(Long innerCategoryId) {
        Optional<InnerCategoryEntity> optional = innerCategoryRepository.findById(innerCategoryId);
        if (optional.isEmpty()) {
            throw new InnerCategoryNotFoundException("Inner category not found");
        } else {
            InnerCategoryEntity entity = optional.get();
            if (entity.getStatus().equals(Status.PUBLISHED)) {
                entity.setStatus(Status.NOT_PUBLISHED);
                this.innerCategoryRepository.save(entity);
                return "Status successfully changed to not published";
            } else {
                if (entity.getStatus().equals(Status.NOT_PUBLISHED)) {
                    entity.setStatus(Status.PUBLISHED);
                }

                this.innerCategoryRepository.save(entity);
                return "Status successfully changed to published";
            }
        }
    }

    public InnerCategoryGetDTO getById(Long innerCategoryId) {
        Optional<InnerCategoryEntity> optional = innerCategoryRepository.findById(innerCategoryId);
        if (optional.isEmpty()) {
            throw new CategoryNotFoundException("innerCategory not found");
        } else {
            InnerCategoryEntity entity = optional.get();
            return toGetDTO(entity);
        }
    }

    public InnerCategoryUpdateDTO getByIdForUpdate(Long innerCategoryId) {
        Optional<InnerCategoryEntity> optional = innerCategoryRepository.findById(innerCategoryId);
        if (optional.isEmpty()) {
            throw new CategoryNotFoundException("innerCategory not found");
        } else {
            InnerCategoryEntity entity = optional.get();
            InnerCategoryUpdateDTO dto = new InnerCategoryUpdateDTO();
            dto.setPhoto(entity.getPhotoId());
            dto.setNameRu(entity.getNameRu());
            dto.setNameUz(entity.getNameUz());
            dto.setDescriptionUz(entity.getDescriptionUz());
            dto.setDescriptionRu(entity.getDescriptionRu());
            return dto;
        }
    }

    public InnerCategoryGetDTO toGetDTO(InnerCategoryEntity entity) {
        InnerCategoryGetDTO dto = new InnerCategoryGetDTO();
        dto.setPhoto(entity.getPhotoId());
        dto.setNameRu(entity.getNameRu());
        dto.setNameUz(entity.getNameUz());
        dto.setDescriptionRu(entity.getDescriptionRu());
        dto.setDescriptionUz(entity.getDescriptionUz());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    public List<CategoryGetForPublish> getInnerCategoryNotPublish() {
        List<InnerCategoryEntity> list = innerCategoryRepository.findByStatusNotPublished(Status.NOT_PUBLISHED.name(), Boolean.TRUE);
        if (list.isEmpty()) {
            throw new CategoryNotFoundException(" InnerCategory not found !");
        } else {
            List<CategoryGetForPublish> listDto = new ArrayList<>();
            list.forEach((entity) -> listDto.add(toDTO(entity)));
            return listDto;
        }
    }

    private CategoryGetForPublish toDTO(InnerCategoryEntity entity) {
        CategoryGetForPublish dto = new CategoryGetForPublish();
        dto.setId(entity.getId());
        dto.setName_Uz(entity.getNameUz());
        dto.setDescription_Uz(entity.getDescriptionUz());
        return dto;
    }

    public Integer getQuantity() {
        List<InnerCategoryEntity> entities = innerCategoryRepository.findByStatusNotPublished(Status.NOT_PUBLISHED.name(), Boolean.TRUE);
        if (entities.isEmpty()) {
            return 0;
        } else {
            return entities.size();
        }
    }

    public List<InnerCategoryGetDTO> getByCategoryId(Long id, String lang) {
        List<InnerCategoryEntity> entities = innerCategoryRepository.getByCategoryId(id);
        List<InnerCategoryGetDTO> dtos = new LinkedList<>();
        if (entities.isEmpty()) {
            return null;
        } else {
            for(InnerCategoryEntity entity : entities){
                if (entity.getVisible().equals(Boolean.TRUE) && entity.getStatus().equals(Status.PUBLISHED)) {
                    dtos.add(getDTO(entity,lang));
                }
            }

            }

            return dtos;
        }
    }



