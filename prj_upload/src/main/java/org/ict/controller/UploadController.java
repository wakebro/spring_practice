package org.ict.controller;


import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class UploadController {
	
	@GetMapping("/uploadForm")
	public void uploadForm() {
		log.info("upload form");
	}
	
	@PostMapping("/uploadFormAction")
	public void uploadFormPost(MultipartFile[] uploadFile, Model model) {
		
		// 저장 경로
		String uploadFolder = "C:\\upload_data\\temp";
		
		for (MultipartFile file : uploadFile) {
			log.info("------------------------------");
			log.info("파일 이름 : " + file.getOriginalFilename());
			log.info("파일 크기 : " + file.getSize());
			
			// 파일 경로 + 어떤 파일을 저장할 지
			File saveFile = new File(uploadFolder, file.getOriginalFilename());
			
			try {
				// 파일 실제 저장
				file.transferTo(saveFile);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		
	}
	
	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		log.info("upload ajax");
	}
}
