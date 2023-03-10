package com.example.controller;


import com.example.dto.category.superCategory.CategoryCreationDto;
import com.example.dto.category.superCategory.CategoryGetDTO;
import com.example.dto.category.superCategory.CategoryGetForPublish;
import com.example.enums.Language;
import com.example.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: Alisher Odilov
 * Date: 19.01.2023
 */

@RestController
@AllArgsConstructor
@RequestMapping({"/category"})
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping({"/create"})
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Method for create", description = "This method used to create Category")
    @SecurityRequirement(name = "Bearer Authentication")
    public HttpEntity<?> create(@RequestBody @Valid CategoryCreationDto dto) {
        return ResponseEntity.ok(categoryService.create(dto));
    }

    @PutMapping({"/update/{id}"})
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Method for create", description = "This method used to update Category")
    @SecurityRequirement(name = "Bearer Authentication")
    public HttpEntity<?> update(@PathVariable("id") Long id, @RequestBody @Valid CategoryCreationDto dto) {
        return ResponseEntity.ok(categoryService.update(dto, id));
    }

    @DeleteMapping({"/delete/{id}"})
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Method for create", description = "This method used to delete Category By Id")
    public HttpEntity<?> deleteById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(categoryService.deleteById(id));
    }

    @GetMapping({"/public/getList/{lang}"})
    @Operation(summary = "Method for get", description = "This method used to get Category with pagination for users")
    public ResponseEntity<Page<CategoryGetDTO>> getList(@PathVariable("lang") Language lang, @PathParam("page") Integer page, @PathParam("size") Integer size) {
        Page<CategoryGetDTO> result = categoryService.getList(page, size, lang.name());
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Method for change status", description = "This method used to change status Category")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping({"/changeStatus/{id}"})
    public ResponseEntity<String> changeStatus(@PathVariable("id") Long id) {
        String result = categoryService.changeStatus(id);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping({"/getByIdForUpdate/{id}"})
    @Operation(summary = "Method for get", description = "This method used to get Category By Id for Update for Admin")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<CategoryCreationDto> getByIdForUpdate(@PathVariable("id") Long id) {
        CategoryCreationDto result = categoryService.getByIdForUpdate(id);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping({"/getNotPublishedCategoryList"})
    @Operation(summary = "Method for get", description = "This method used to get Category which is not published")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<List<CategoryGetForPublish>> getCategoryNotPublish() {
        List<CategoryGetForPublish> result = categoryService.getCategoryNotPublish();
        return ResponseEntity.ok(result);
    }

    @GetMapping({"/public/QuantityOfNotPublished"})
    @Operation(summary = "Method for get", description = "This method used to get quantity of Categories which are not published")
    public ResponseEntity<?> getQuantityNotPublishedCategory() {
        Integer result = categoryService.getQuantity();
        return ResponseEntity.ok(result);
    }
}


