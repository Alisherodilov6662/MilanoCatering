package com.example.controller;

import com.example.dto.category.innerCategory.InnerCategoryCreationDto;
import com.example.dto.category.innerCategory.InnerCategoryGetDTO;
import com.example.dto.category.innerCategory.InnerCategoryUpdateDTO;
import com.example.dto.category.superCategory.CategoryGetForPublish;
import com.example.enums.Language;
import com.example.service.InnerCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping({"/innerCategory"})
public class InnerCategoryController {
    private final InnerCategoryService innerCategoryService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping({"/create"})
    @Operation(summary = "Method for create", description = "This method used to create inner category")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<InnerCategoryCreationDto> create(@RequestBody @Valid InnerCategoryCreationDto innerCategoryCreationDto) {
        InnerCategoryCreationDto result = innerCategoryService.create(innerCategoryCreationDto);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping({"/update/{id}"})
    @Operation(summary = "Method for update", description = "This method used to update inner category")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<InnerCategoryCreationDto> update(@PathVariable("id") Long id, @RequestBody @Valid InnerCategoryCreationDto innerCategoryCreationDto) {
        InnerCategoryCreationDto result = innerCategoryService.update(id, innerCategoryCreationDto);
        return ResponseEntity.ok(result);
    }

    @GetMapping({"/public/getInnerCategoryById/{id}"})
    @Operation(summary = "Method for get", description = "This method is used for getting InnerCategory By Id")
    public ResponseEntity<?> getInnerCategoryById(@PathVariable("id") Long id) {
        InnerCategoryGetDTO result = innerCategoryService.getById(id);
        return ResponseEntity.ok(result);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping({"/delete/{id}"})
    @Operation(summary = "Method for delete", description = "This method used to delete inner category")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        Boolean result = innerCategoryService.delete(id);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Method for change status", description = "This method used to change status inner category")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping({"/changeStatus/{id}"})
    public ResponseEntity<String> changeStatus(@PathVariable("id") Long id) {
        String result = innerCategoryService.changeStatus(id);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping({"/getByIdForUpdate/{id}"})
    @Operation(summary = "Method for get", description = "This method used to get innerCategory by id")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<InnerCategoryUpdateDTO> getByIdForUpdate(@PathVariable("id") Long innerCategoryId) {
        InnerCategoryUpdateDTO result=innerCategoryService.getByIdForUpdate(innerCategoryId);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping({"/getNotPublishedInnerCategoryList"})
    @Operation(summary = "Method for get", description = "This method used to get InnerCategory which is not published")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<CategoryGetForPublish>> getInnerCategoryNotPublish() {
        List<CategoryGetForPublish> result = innerCategoryService.getInnerCategoryNotPublish();
        return ResponseEntity.ok(result);
    }

    @GetMapping({"/public/QuantityOfNotPublished"})
    @Operation(summary = "Method for get", description = "This method used to get quantity of InnerCategories which are not published")
    public ResponseEntity<?> getQuantityNotPublishedCategory() {
        Integer result = innerCategoryService.getQuantity();
        return ResponseEntity.ok(result);
    }

    @GetMapping({"/public/getByCategoryId/{id}/{lang}"})
    @Operation(summary = "Method for get", description = "This method used to get InnerCategory by CategoryId")
    public ResponseEntity<?> getByCategoryId(@PathVariable("id") Long id, @PathVariable("lang") Language lang) {
        List<InnerCategoryGetDTO> result = innerCategoryService.getByCategoryId(id, lang.name());
        return ResponseEntity.ok(result);
    }
}
