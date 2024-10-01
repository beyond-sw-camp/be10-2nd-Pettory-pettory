package com.pettory.pettory.category.controller;

import com.pettory.pettory.category.entity.BoardCategoryEntity;
import com.pettory.pettory.category.service.BoardCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class BoardCategoryController {

    private final BoardCategoryService boardCategoryService;

    @Autowired
    public BoardCategoryController(BoardCategoryService boardCategoryService) {
        this.boardCategoryService = boardCategoryService;
    }

    // 관리자만 접근 가능하도록 설정
    @PostMapping("/create")
    public ResponseEntity<BoardCategoryEntity> createCategory(@RequestParam String title, @AuthenticationPrincipal UserDetails userDetails) {
        if (!userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(boardCategoryService.createCategory(title), HttpStatus.CREATED);
    }

    @PutMapping("/update/{categoryNum}")
    public ResponseEntity<BoardCategoryEntity> updateCategory(@PathVariable int categoryNum, @RequestParam String newTitle, @AuthenticationPrincipal UserDetails userDetails) {
        if (!userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(boardCategoryService.updateCategory(categoryNum, newTitle), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{categoryNum}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int categoryNum, @AuthenticationPrincipal UserDetails userDetails) {
        if (!userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        boardCategoryService.deleteCategory(categoryNum);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

