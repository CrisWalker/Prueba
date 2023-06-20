package com.example.demo.mapper;

import com.example.demo.domain.CategoryDTO;
import com.example.demo.exception.CustomException;
import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CategoryMapper {

    private final CategoryRepository categoryRepository;

    public CategoryDTO fromEntityToDTO (Category entity){
        CategoryDTO dto = new CategoryDTO();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setType(entity.getType());
        dto.setIsImportant(entity.getIsImportant());

        return dto;
    }

    public Category searchFromDTOtoEntity (CategoryDTO dto)  {
        Optional<Category> optionalCategory = categoryRepository.findById(dto.getId() != null ? dto.getId() : 0);
        if (optionalCategory.isPresent()){
            return optionalCategory.get();
        }
        else{
            throw new CustomException("No encontré la categoría", 400);
        }
    }

    public Category fromDTOtoEntity (CategoryDTO dto) {
        Category entity = new Category();

        entity.setName(dto.getName());
        entity.setType(dto.getType());
        entity.setIsImportant(dto.getIsImportant());

        return entity;
    }

    public List<CategoryDTO> fromEntityListToDTOList (List<Category> categories){
        List<CategoryDTO> categoryDTOS = new ArrayList<>();

        for (Category category : categories) {
            categoryDTOS.add(fromEntityToDTO(category));
        }

        return categoryDTOS;

    }
}
