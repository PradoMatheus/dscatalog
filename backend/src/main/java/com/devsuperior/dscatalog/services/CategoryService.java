package com.devsuperior.dscatalog.services;

import com.devsuperior.dscatalog.dto.CategoryDto;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Page<CategoryDto> findAllPaged(Pageable pageable) {
        var list = categoryRepository.findAll(pageable);
        return list.map(x -> new CategoryDto(x));
    }

    @Transactional(readOnly = true)
    public CategoryDto findById(Long id) {
        var obj = categoryRepository.findById(id);
        var entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new CategoryDto(entity);
    }

    @Transactional
    public CategoryDto insert(CategoryDto categoryDto) {
        var category = new Category();
        BeanUtils.copyProperties(categoryDto, category);
        category = categoryRepository.save(category);
        return new CategoryDto(category);
    }

    @Transactional
    public CategoryDto update(Long id, CategoryDto categoryDto) {
        try {
            var entity = categoryRepository.getReferenceById(id);
            entity.setName(categoryDto.getName());
            entity = categoryRepository.save(entity);
            return new CategoryDto(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(String.format("Id not found %d", id));
        }
    }

    public void delete(Long id) {
        try {
            categoryRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(String.format("Id not found %d", id));
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Integrity Violation");
        }
    }
}
