package com.example.demo.service;

import com.example.demo.domain.CategoryDTO;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public ResponseEntity<List<CategoryDTO>> findAllCategories(){
        return new ResponseEntity<>(categoryMapper.fromEntityListToDTOList(categoryRepository.findAll()), HttpStatus.OK);
    }

    public ResponseEntity<?> createCategory (CategoryDTO categoryDTO){
        Category category = categoryMapper.fromDTOtoEntity(categoryDTO);

        categoryRepository.save(category);

        return new ResponseEntity<>(categoryMapper.fromEntityToDTO(category), HttpStatus.OK);
    }

    public ResponseEntity<?> deleteCategory (Long id){
        categoryRepository.deleteById(id);

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> updateCategory (Long id, CategoryDTO categoryDTO){
        if (categoryRepository.existsById(id)){
            Category category = categoryMapper.fromDTOtoEntity(categoryDTO);
            category.setId(id);

            categoryRepository.save(category);

            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<?> getCurrentTypes(){
        return new ResponseEntity<>(categoryRepository.findTypes(), HttpStatus.OK);
    }

}
