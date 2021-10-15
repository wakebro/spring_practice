package org.ict.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.ict.domain.BoardAttachVO;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Log4j
public class UploadController {
	
	// 썸네일 제작하기 위해 이미지 파일인지 판단하는 메서드
	private boolean checkImageType(File file) {
		try {
			// toPath - File 객체를 Path 객체로 변환
			// Path 객체는 전체 경로 뿐만 아니라 파일의 확장자까지 표시한다.
			// probeContentType - 어떤 MIME 타입인지 확인
			
			// MIME 타입 - 클라이언트에게 전송된 파일의 다양성을 알려주기 위한 메커니즘
			// 웹에서는 파일의 확장자는 별 의미가 없다
			// 서버에서는 파일 전송시 다양한 MIME 타입이 있으므로 잘 설정해야 한다.
			// MIME 타입 예시
			// audio/aac, application/x-csh 등등
			log.info("해당 파일 Path 객체화 : " + file.toPath());
			String contentType = Files.probeContentType(file.toPath());
			log.info("해당 파일 MIME 타입 : " + contentType);
			log.info("--------------------------------------------------");
			
			// startsWith - 문자열이 'image'로 시작하는지 판별 
			return contentType.startsWith("image");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// 날짜에 맞춰서 폴더형식을 만들어주는 함수
	// 파일 업로드시 업로드 날짜별로 폴더를 만들어 관리
	private String getFolder() {
		// 날짜 포맷
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 오늘 날짜 객체 생성
		Date date = new Date();
		// 설정한 날짜 포맷에 날짜 객체를 넣은 후 String으로 저장
		String str = sdf.format(date);
		// 문자열로 저장한 날짜 포맷의 '-'을 OS 규격에 맞추어 재조정 후 리턴
		return str.replace("-", File.separator);
	}


	@ResponseBody
	public void uploadFormPost(MultipartFile[] uploadFile, Model model) {
		
		// 저장 경로
		String uploadFolder = "C:\\upload_data\\temp";
		
		for (MultipartFile file : uploadFile) {
			log.info("------------------------------");
			log.info("파일 이름 : " + file.getOriginalFilename());
			log.info("파일 크기 : " + file.getSize());
			
			// 파일 경로 + 어떤 파일을 저장할 지정
			File saveFile = new File(uploadFolder, file.getOriginalFilename());
			
			try {
				// 파일 실제 저장
				file.transferTo(saveFile);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		
	}
	
	@PostMapping("/uploadAjaxAction")
	// 일반 컨트롤러가 .jsp대신 json데이터를 리턴하도록 사용
	// 일반 컨트롤러에서 rest요청을 처리시키는 경우에 @ResponseBody를 붙인다
	@ResponseBody
	public ResponseEntity<List<BoardAttachVO>> uploadFormPost(MultipartFile[] uploadFile){
	/*public void uploadAjaxPost(MultipartFile[] uploadFile) {*/
		
		log.info("ajax post update!");
		// 그림파일 여러개의 정보 AttachFileDTO를 중첩해 저장하는 list 선언
		List<BoardAttachVO> list = new ArrayList<>();
		
		// 저장 경로_고정
		String uploadFolder = "C:\\upload_data\\temp";
		
		// 저장 경로(날짜)_가변
		String uploadFolderPath = getFolder();
		
		// 날짜(년도, 월, 일)별 폴더 생성
		// 고정된 디렉토리 경로와 가변적으로 변하는(날짜)를 넣은 File 객체 생성
		// 폴더 생성_ new File("디렉토리의 경로", "파일명")
		File uploadPath = new File(uploadFolder, uploadFolderPath);
		log.info(uploadPath);
		
		// File객체인 uploadPath의 경로가 없을 경우
		// exists - File 객체가 참조하는 파일이나 디렉토리경로가 실존하면 true 리턴
		if(uploadPath.exists() == false)
			// mkdirs - 디렉토리를 생성하며 부모디렉토리까지 같이 생성한다.
			// mkdir - 디렉토리를 생성하되, 반드시 부모 디렉토리가 있어야 한다.
			uploadPath.mkdirs();
		
		
		for (MultipartFile multipartFile : uploadFile) {
//			log.info("---------------------------------------------------");
//			log.info("올린 파일 이름 : " + multipartFile.getOriginalFilename());
//			log.info("올린 파일 크기 : " + multipartFile.getSize());
			
			// 파일 이름, 폴더 경로(업로드 날짜), UUID, 그림 파일 여부를 모두
			// 이 반복문에서 처리하므로 제일 상단에 먼저 그림정보를 받는 AttacfhFileDTO를 생성한다.
			BoardAttachVO attachDTO = new BoardAttachVO();
			
			// 올린 파일의 이름 추출
			String uploadFileName = multipartFile.getOriginalFilename();
			
			// lastIndexOf - uploadFileName 안에 문자열 '\\'이 없으므로 -1을 반환
			// substring - lastIndexOf 반환값 -1과 1을 더한 0번째 인덱스에서부터의 문자열을 최종 저장
			// 즉, 오리지널의 이름을 uploadFileName에 저장
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			log.info("최종 파일 이름 : " + uploadFileName);
			
			
			// attachDTO에 파일의 이름 저장
			attachDTO.setFileName(uploadFileName);
			
			// 중복되는 파일 업로드를 막기 위해 UUID 생성
			UUID uuid = UUID.randomUUID();
			// 생성한 uuid를 최종 파일 이름에 붙여 고유 값을 설정
			// uuid로 만든 랜덤고유 값을 toString으로 문자열로 반환
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			
			
			// 폴더 생성_ new File("디렉토리의 경로", "파일명")
//			File saveFile = new File(uploadFolder, uploadFileName);
			
			// 가변적으로 변하는 날짜에 맞춘 파일 경로와 저장할 파일의 이름을 넣은 객체 생성 
//			File saveFile = new File(uploadPath, uploadFileName);
			
			try {
				// 경로를 고정폴더인 uploadFolder에서 날짜별 가변폴더인 uploadPath로 변경
				File saveFile = new File(uploadPath, uploadFileName);
				
				// transferTo - 업로드한 파일 중 하나(경로와 최종 이름을 결정 한 것)를 설정한 위치에 저장 
				multipartFile.transferTo(saveFile);
				
				
				// attachDTO에 파일의 UUID 저장
				attachDTO.setUuid(uuid.toString());
				// attachDTO에 파일의 경로 저장
				attachDTO.setUploadPath(uploadFolderPath);
				
				
				// 저장한 파일 타입에 따라 썸네일 생성
				if(checkImageType(saveFile)) {
					// 클래스 생성 후 boolean타입은 자료입력을하지 않으면
					// 자동으로 false로 간주됨
					// attachDTO에 파일 썸네일 true/false 저장
					attachDTO.setFileType(true);
					
					// 가변적으로 변하는 날짜 경로에 썸네일용(s_) File 객체를 새로 생성
					// 생성한 File 객체를 썸네일용으로 새로 생성하기 위해 바이트 스트림으로 저장
					FileOutputStream thumbnail = 
							new FileOutputStream(
									new File(uploadPath, "s_" + uploadFileName));
					
					// 라이브러리 Thumbnailator 사용
					// createThumbnail(InputStream, OutputStream, 가로크기, 세로크기)
					// InputStream으로 받은 파일을 가로, 세로 크기로 변환하여 OutputStream으로 출력
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), 
							thumbnail, 100, 100);
					thumbnail.close();
				}
				// attachDTO에 저장한 객체를 list에 저장
				list.add(attachDTO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}// End for문
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	// 썸네일 경로를 배정
	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName){
		log.info("파일 이름 : " + fileName);
		
		File file = new File("c:\\upload_data\\temp\\" + fileName);
		
		log.info("파일 전체 경로 : " + file);
		
		ResponseEntity<byte[]> result = null;
		
		try {
			// header 객체 생성
			HttpHeaders header = new HttpHeaders();
			
			// header의 'Content-Type' 정보를 기입_MIME 타입 정보
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			
			// FileCopyUtils - 파일 및 스트림 복사를 위한 유틸리티 메서드 집합체
			// .copyToByteArray(in) - 지정한 input파일을 새로운 byte[]로 복사한다.
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),
										header,
										HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// produces에서 다운로드를 시킬 수 있는 application/octet-stream인 MIME 타입이 설정됨
	// 그렇기때문에 heaaders에서 따로 지정해줄 필요가 없다.
	@GetMapping(value = "/download", produces = {MediaType.APPLICATION_OCTET_STREAM_VALUE})
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(String fileName){
		log.info("다운로드 파일 : " + fileName);
		Resource resource = new FileSystemResource("C:\\upload_data\\temp\\" + fileName);
		log.info("리소스 : " + resource);
		
		String resourceName = resource.getFilename();
		
		HttpHeaders headers = new HttpHeaders();
		
		try {
			headers.add("Content-Disposition", "attachment; filename=" + new String(resourceName.getBytes("UTF-8"), "ISO-8859-1"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
	
	@PostMapping("/deleteFile")
	@ResponseBody
	public ResponseEntity<String> deleteFile(String fileName, String type){
		log.info("삭제 파일 : " + fileName);
		File file = null;
		
		try {
			file = new File("C:\\upload_data\\temp\\" + URLDecoder.decode(fileName, "UTF-8"));
			file.delete();
			if (type.equals("image")) {
				String largeFileName = file.getAbsolutePath().replace("s_", "");
				log.info("원본 파일 : " + largeFileName);
				file = new File(largeFileName);
				file.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
}
