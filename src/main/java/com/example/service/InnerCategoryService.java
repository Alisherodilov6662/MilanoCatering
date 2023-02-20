package com.example.service;

import com.example.dto.category.innerCategory.InnerCategoryCreationDto;
import com.example.dto.category.innerCategory.InnerCategoryGetDTO;
import com.example.dto.category.innerCategory.InnerCategoryShortInfo;
import com.example.dto.category.innerCategory.InnerCategoryUpdateDTO;
import com.example.entity.category.InnerCategoryEntity;
import com.example.enums.Status;
import com.example.exception.CategoryNotFoundException;
import com.example.exception.InnerCategoryAlreadyExistsException;
import com.example.exception.InnerCategoryNotFoundException;
import com.example.repositiry.InnerCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class InnerCategoryService {
    @Autowired
    private InnerCategoryRepository innerCategoryRepository;


    public InnerCategoryCreationDto create(InnerCategoryCreationDto innerCategoryCreationDto) {
        Optional<InnerCategoryEntity> byNameUz = innerCategoryRepository.findByNameUz(innerCategoryCreationDto.getNameUz());
        Optional<InnerCategoryEntity> byNameRu = innerCategoryRepository.findByNameRu(innerCategoryCreationDto.getNameRu());
        if (byNameUz.isPresent() || byNameRu.isPresent()){
            throw new InnerCategoryAlreadyExistsException("Inner category already exists");
        }
        InnerCategoryEntity entity = toEntity(innerCategoryCreationDto);
        innerCategoryRepository.save(entity);
        return innerCategoryCreationDto;
    }

    public InnerCategoryCreationDto update(Long innerCategoryId,InnerCategoryCreationDto innerCategoryCreationDto) {
        Optional<InnerCategoryEntity> optional = innerCategoryRepository.findById(innerCategoryId);
        if (optional.isEmpty()){
            throw new InnerCategoryNotFoundException("Inner category not found");
        }
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


    public InnerCategoryEntity toEntity(InnerCategoryCreationDto innerCategoryCreationDto){
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
        if (optional.isEmpty()){
            throw new InnerCategoryNotFoundException("Inner category not found");
        }
        InnerCategoryEntity entity = optional.get();
        entity.setVisible(false);
        innerCategoryRepository.save(entity);
        return true;
    }

    public Page<InnerCategoryGetDTO> getList(Integer page, Integer size,String language) {
        Pageable pageable = PageRequest.of(page,size);
//        Page<InnerCategoryEntity> pageObj = innerCategoryRepository.findAll(pageable);
        Page<InnerCategoryEntity> pageObj = innerCategoryRepository.findByVisibleTrue(pageable);
        List<InnerCategoryEntity> content = pageObj.getContent();
        long totalElements = pageObj.getTotalElements();
        List<InnerCategoryGetDTO> dtoList = new LinkedList<>();
        content.forEach(entity->dtoList.add(getDTO(entity,language)));
        return new PageImpl<>(dtoList,pageable,totalElements);
    }
    public Page<InnerCategoryShortInfo> getListAll(Integer page, Integer size,Long categoryId) {
        Pageable pageable = PageRequest.of(page,size);
        Page<InnerCategoryEntity> pageObj = innerCategoryRepository.findByCategoryId(pageable,categoryId);
        List<InnerCategoryEntity> content = pageObj.getContent();
        long totalElements = pageObj.getTotalElements();
        List<InnerCategoryShortInfo> dtoList = new LinkedList<>();
        content.forEach(entity->dtoList.add(getDTOForAdmin(entity)));
        return new PageImpl<>(dtoList,pageable,totalElements);
    }
    public InnerCategoryGetDTO getDTO(InnerCategoryEntity entity,String language){
        InnerCategoryGetDTO dto = new InnerCategoryGetDTO();
        dto.setPhoto(entity.getPhotoId());
        if (language.equals("UZ")){
        dto.setNameUz(entity.getNameUz());
        dto.setDescriptionUz(entity.getDescriptionUz());
        }else {
            dto.setNameRu(entity.getNameRu());
            dto.setDescriptionRu(entity.getDescriptionRu());
        }
        return dto;
    }
    public InnerCategoryShortInfo getDTOForAdmin(InnerCategoryEntity entity){
        InnerCategoryShortInfo dto = new InnerCategoryShortInfo();
        dto.setId(entity.getId());
        dto.setNameUz(entity.getNameUz());
        return dto;
    }

    public String changeStatus(Long innerCategoryId) {
        Optional<InnerCategoryEntity> optional = innerCategoryRepository.findById(innerCategoryId);
        if (optional.isEmpty()) {
            throw new InnerCategoryNotFoundException("Inner category not found");
        }
        InnerCategoryEntity entity = optional.get();
        if (entity.getStatus().equals(Status.PUBLISHED)){
            entity.setStatus(Status.NOT_PUBLISHED);
            innerCategoryRepository.save(entity);
            return "Status successfully changed to not published";
        }
        if (entity.getStatus().equals(Status.NOT_PUBLISHED)){
            entity.setStatus(Status.PUBLISHED);
        }
        innerCategoryRepository.save(entity);
        return "Status successfully changed to published";
    }

    public InnerCategoryGetDTO getById(Long innerCategoryId) {
        Optional<InnerCategoryEntity> optional = innerCategoryRepository.findById(innerCategoryId);
        if (optional.isEmpty()) {
            throw new CategoryNotFoundException("innerCategory not found");
        }
        InnerCategoryEntity entity = optional.get();
        return toGetDTO(entity);
    }
    public InnerCategoryUpdateDTO getByIdForUpdate(Long innerCategoryId) {
        Optional<InnerCategoryEntity> optional = innerCategoryRepository.findById(innerCategoryId);
        if (optional.isEmpty()) {
            throw new CategoryNotFoundException("innerCategory not found");
        }
        InnerCategoryEntity entity = optional.get();
        InnerCategoryUpdateDTO dto = new InnerCategoryUpdateDTO();
        dto.setPhoto(entity.getPhotoId());
        dto.setNameRu(entity.getNameRu());
        dto.setNameUz(entity.getNameUz());
        dto.setDescriptionUz(entity.getDescriptionUz());
        dto.setDescriptionRu(entity.getDescriptionRu());
        return dto;
    }
    public InnerCategoryGetDTO toGetDTO(InnerCategoryEntity entity){
        InnerCategoryGetDTO dto = new InnerCategoryGetDTO();
        dto.setPhoto(entity.getPhotoId());
        dto.setNameRu(entity.getNameRu());
        dto.setNameUz(entity.getNameUz());
        dto.setDescriptionRu(entity.getDescriptionRu());
        dto.setDescriptionUz(entity.getDescriptionUz());
        dto.setStatus(entity.getStatus());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
}
