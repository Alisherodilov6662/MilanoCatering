package com.example.controller;

import com.example.dto.category.InnerCategoryCreationDto;
import com.example.dto.category.InnerCategoryGetDTO;
import com.example.dto.jwt.JwtDTO;
import com.example.service.InnerCategoryService;
import com.example.util.JwtTokenUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
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

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    @Operation(summary = "Method for create", description = "This method used to create inner category")
//    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<InnerCategoryCreationDto> create(@RequestBody InnerCategoryCreationDto innerCategoryCreationDto){
        InnerCategoryCreationDto result = innerCategoryService.create(innerCategoryCreationDto);
        return ResponseEntity.ok(result);
    }
//    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    @Operation(summary = "Method for update", description = "This method used to update inner category")
//    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<InnerCategoryCreationDto> update(@PathVariable("id") Long id,
                                                           @RequestBody InnerCategoryCreationDto innerCategoryCreationDto){
        InnerCategoryCreationDto result = innerCategoryService.update(id,innerCategoryCreationDto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getList/uz")
    @Operation(summary = "Method for get", description = "This method used to get inner category with pagination")
    public ResponseEntity<Page<InnerCategoryGetDTO>> getListUz(@PathParam("page") Integer page,
                                                             @PathParam("size") Integer size){

        Page<InnerCategoryGetDTO> result = innerCategoryService.getList(page, size,"uz");
        return ResponseEntity.ok(result);
    }
    @GetMapping("/getList/ru")
    @Operation(summary = "Method for get", description = "This method used to get inner category with pagination")
    public ResponseEntity<Page<InnerCategoryGetDTO>> getListRu(@PathParam("page") Integer page,
                                                             @PathParam("size") Integer size){

        Page<InnerCategoryGetDTO> result = innerCategoryService.getList(page, size,"ru");
        return ResponseEntity.ok(result);
    }
//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Method for delete", description = "This method used to delete inner category")
//    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id){
        Boolean result = innerCategoryService.delete(id);
        return ResponseEntity.ok(result);
    }

}
