package com.example.service;

import com.example.dto.category.InnerCategoryCreationDto;
import com.example.dto.category.InnerCategoryGetDTO;
import com.example.dto.jwt.JwtDTO;
import com.example.entity.category.InnerCategoryEntity;
import com.example.exception.InnerCategoryAlreadyExistsException;
import com.example.exception.InnerCategoryNotFoundException;
import com.example.repositiry.InnerCategoryRepository;
import com.example.util.RoleUtil;
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
        innerCategoryCreationDto.setVisible(entity.getVisible());
        return innerCategoryCreationDto;
    }

    public InnerCategoryCreationDto update(Long id,InnerCategoryCreationDto innerCategoryCreationDto) {
        Optional<InnerCategoryEntity> optional = innerCategoryRepository.findById(id);
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
        entity.setVisible(false);
        return entity;
    }

    public Boolean delete(Long id) {
        Optional<InnerCategoryEntity> optional = innerCategoryRepository.findById(id);
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
    public Page<InnerCategoryGetDTO> getListAll(Integer page, Integer size,String language) {
        Pageable pageable = PageRequest.of(page,size);
//        Page<InnerCategoryEntity> pageObj = innerCategoryRepository.findAll(pageable);
        Page<InnerCategoryEntity> pageObj = innerCategoryRepository.findAll(pageable);
        List<InnerCategoryEntity> content = pageObj.getContent();
        long totalElements = pageObj.getTotalElements();
        List<InnerCategoryGetDTO> dtoList = new LinkedList<>();
        content.forEach(entity->dtoList.add(getDTOForAdmin(entity,language)));
        return new PageImpl<>(dtoList,pageable,totalElements);
    }
    public InnerCategoryGetDTO getDTO(InnerCategoryEntity entity,String language){
        InnerCategoryGetDTO dto = new InnerCategoryGetDTO();
        dto.setPhoto(entity.getPhotoId());
        dto.setCategory(entity.getCategoryId());
        if (language.equals("UZ")){
        dto.setNameUz(entity.getNameUz());
        dto.setDescriptionUz(entity.getDescriptionUz());
        }else {
            dto.setNameRu(entity.getNameRu());
            dto.setDescriptionRu(entity.getDescriptionRu());
        }
        return dto;
    }
    public InnerCategoryGetDTO getDTOForAdmin(InnerCategoryEntity entity,String language){
        InnerCategoryGetDTO dto = new InnerCategoryGetDTO();
        dto.setPhoto(entity.getPhotoId());
        dto.setCategory(entity.getCategoryId());
        if (language.equals("UZ")){
            dto.setNameUz(entity.getNameUz());
            dto.setDescriptionUz(entity.getDescriptionUz());
        }else if (language.equals("RU")){
            dto.setNameRu(entity.getNameRu());
            dto.setDescriptionRu(entity.getDescriptionRu());
        }else {
            dto.setNameUz(entity.getNameUz());
            dto.setDescriptionUz(entity.getDescriptionUz());
            dto.setNameRu(entity.getNameRu());
            dto.setDescriptionRu(entity.getDescriptionRu());
        }
        return dto;
    }

    public String changeStatus(Long id) {
        Optional<InnerCategoryEntity> optional = innerCategoryRepository.findById(id);
        if (optional.isEmpty()) {
            throw new InnerCategoryNotFoundException("Inner category not found");
        }
        InnerCategoryEntity entity = optional.get();
        entity.setVisible(true);
        innerCategoryRepository.save(entity);
        return "Status successfully changed";
    }
}
