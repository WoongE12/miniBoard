package com.board.util;

import java.io.File;
import java.util.Iterator;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class fileUtil {
	private static final String filePath = "C:\\Users\\tj-bu-702-10\\Desktop\\exFinder\\src\\main\\webapp\\resources\\profile_img\\"; // 파일이 저장될 위치
	
	public static String updateImg(MultipartHttpServletRequest mpRequest, String oldImgPath) throws Exception{
		
		// 기존 이미지 삭제 로직
        if (oldImgPath != null && !oldImgPath.isEmpty()) {
            File oldFile = new File(filePath + oldImgPath);
            if (oldFile.exists()) {
                oldFile.delete();
            }
        }
		
		
		Iterator<String> iterator = mpRequest.getFileNames();
		
		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;
		
		String user_Img = "";
		
		
		File file = new File(filePath);
		if(file.exists() == false) {
			file.mkdirs();
		}
		
		while(iterator.hasNext()) {
			multipartFile = mpRequest.getFile(iterator.next());
			if(multipartFile.isEmpty() == false) {
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName = getRandomString() + originalFileExtension;
				file = new File(filePath + storedFileName);
				multipartFile.transferTo(file);
				user_Img = storedFileName;
			}
		}
		
		
		
		return user_Img;
	}
	
	// getRandomString() 메서드의 구현이 필요합니다.
    private static String getRandomString() {
        // 임의의 문자열 생성 로직 구현 (예: UUID 기반)
        return java.util.UUID.randomUUID().toString().replaceAll("-", "");
    }
    
    
}
