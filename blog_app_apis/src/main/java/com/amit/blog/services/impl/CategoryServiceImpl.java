package com.amit.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amit.blog.entites.Category;
import com.amit.blog.exceptions.ResourceNotFoundException;
import com.amit.blog.payloads.CategoryDto;
import com.amit.blog.repositories.CategoryRepo;
import com.amit.blog.services.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
private CategoryRepo categoryRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
   Category cat=  this.modelMapper.map(categoryDto, Category.class);
   Category addedCat= this.categoryRepo.save(cat);
		return this.modelMapper.map(addedCat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		 Category cate= this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId));
		 cate.setCategoryDescription(categoryDto.getCategoryDescription());
		 cate.setCategoryTitle(categoryDto.getCategoryTitle());
		 Category updateCat= this.categoryRepo.save(cate);
		return this.modelMapper.map(updateCat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category cate= this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId));
		this.categoryRepo.delete(cate);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category cate=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId));
		return this.modelMapper.map(cate, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {
		List<Category> categories=this.categoryRepo.findAll();
		List<CategoryDto> catgoriesDto=  categories.stream().map((cat)->this.modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
		return catgoriesDto;
	}

}
