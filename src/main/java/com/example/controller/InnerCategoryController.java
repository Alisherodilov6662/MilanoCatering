package com.example.controller;

import com.example.dto.category.innerCategory.InnerCategoryCreationDto;
import com.example.dto.category.innerCategory.InnerCategoryGetDTO;
import com.example.dto.category.innerCategory.InnerCategoryShortInfo;
import com.example.dto.category.innerCategory.InnerCategoryUpdateDTO;
import com.example.enums.Language;
import com.example.service.InnerCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/innerCategory")
public class InnerCategoryController {
    private final InnerCategoryService innerCategoryService;

    public InnerCategoryController(InnerCategoryService innerCategoryService) {
        this.innerCategoryService = innerCategoryService;
    }
    /**
     * @param innerCategoryCreationDto
     * @return innerCategoryCreationDto
     * This method for creat InnerCategory
    **/

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    @Operation(summary = "Method for create", description = "This method used to create inner category")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<InnerCategoryCreationDto> create(@Valid @RequestBody InnerCategoryCreationDto innerCategoryCreationDto){
        InnerCategoryCreationDto result = innerCategoryService.create(innerCategoryCreationDto);
        return ResponseEntity.ok(result);
    }
    /**
     * This Method for Update InnerCategory by id
     * @param innerCategoryCreationDto
     * @param innerCategoryId
     * @return innerCategoryCreationDto
     **/
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    @Operation(summary = "Method for update", description = "This method used to update inner category")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<InnerCategoryCreationDto> update(@PathVariable("id") Long innerCategoryId,
                                                           @Valid @RequestBody InnerCategoryCreationDto innerCategoryCreationDto){
        InnerCategoryCreationDto result = innerCategoryService.update(innerCategoryId,innerCategoryCreationDto);
        return ResponseEntity.ok(result);
    }
    /**
     * This method for get list innerCategory by lang
     * @param lang
     * @param page
     * @param size
     * @return Page<InnerCategoryGetDTO>
     **/
    @GetMapping("/public/getList/{lang}")
    @Operation(summary = "Method for get", description = "This method used to get inner category with pagination")
    public ResponseEntity<Page<InnerCategoryGetDTO>> getList(@PathVariable("lang") Language lang,
                                                               @PathParam("page") Integer page,
                                                             @PathParam("size") Integer size){

        Page<InnerCategoryGetDTO> result = innerCategoryService.getList(page, size,lang.name());
        return ResponseEntity.ok(result);
    }
    /**
     * This method for get list innerCategoryId by categoryId with pagination
     * @param categoryId
     * @param size
     * @param page
     * @return Page<InnerCategoryShortInfo>
     **/
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getListAll/{id}")
    @Operation(summary = "Method for get", description = "This method used to get all inner category with pagination")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Page<InnerCategoryShortInfo>> getListAll(@PathVariable("id") Long categoryId,
                                                                    @PathParam("page") Integer page,
                                                                   @PathParam("size") Integer size){

        Page<InnerCategoryShortInfo> result = innerCategoryService.getListAll(page, size,categoryId);
        return ResponseEntity.ok(result);
    }
    /**
     * This method for get innerCategory by id
     * @param innerCategoryId 
     * @return innerCategoryGetDTO
     **/
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getInnerCategoryById/{id}")
    @Operation(summary = "Method for get",description = "This method used to get innerCategory by id")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<InnerCategoryGetDTO> getInnerCategoryById(@PathVariable("id") Long innerCategoryId){
        InnerCategoryGetDTO result = innerCategoryService.getById(innerCategoryId);
        return ResponseEntity.ok(result);
    }


    /**
     * This method for delete innerCategory by id
     * @param innerCategoryId
     * @return Boolean
     **/
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Method for delete", description = "This method used to delete inner category")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long innerCategoryId){
        Boolean result = innerCategoryService.delete(innerCategoryId);
        return ResponseEntity.ok(result);
    }
    /**
     * This method for change status by id
     * @param innerCategoryId
     * @return String
     **/
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Method for change status", description = "This method used to change status inner category")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/changeStatus/{id}")
    public ResponseEntity<String> changeStatus(@PathVariable("id") Long innerCategoryId){
        String result = innerCategoryService.changeStatus(innerCategoryId);
        return ResponseEntity.ok(result);
    }
    /**
     * This method for update innerCategory from admin
     * @param innerCategoryId
     * @return InnerCategoryUpdateDTO
     **/
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getByIdForUpdate/{id}")
    @Operation(summary = "Method for get",description = "This method used to get innerCategory by id")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<InnerCategoryUpdateDTO> getByIdForUpdate(@PathVariable("id") Long innerCategoryId){
        InnerCategoryUpdateDTO result = innerCategoryService.getByIdForUpdate(innerCategoryId);
        return ResponseEntity.ok(result);
    }
}
