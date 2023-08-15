package com.amit.blog.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amit.blog.services.FileService;

@Service
public class FileServiceImpl implements FileService{

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		
//	   File Name
		String name= file.getOriginalFilename();
		
		//random name generate file
		 
		String randomId=UUID.randomUUID().toString();
		String fileName1= randomId.concat(name.substring(name.lastIndexOf(".")));
		
//     Full Path
		String filePath= path+File.separator+fileName1;
//		here File.Separator work is to write a"/"
//	   Create folder if not created 
		File f = new File(path);
		if(!f.exists()) {
//			it creates the folder
			f.mkdir();
		}
//     FileCopy
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		
		return fileName1;
	}

	@Override
	public InputStream getResource(String path, String filename) throws FileNotFoundException {
		String fullPath=path+File.separator+filename;
		InputStream is= new FileInputStream(fullPath);
		return is;
	}

	

}
