package kr.or.ddit.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.dto.AttachVO;

public class GetAttachesAsMultipartFiles {

	public static List<AttachVO> save(List<MultipartFile> multiFiles, String savePath) throws Exception {
		ArrayList<AttachVO> attachList = new ArrayList<AttachVO>();

		if(multiFiles != null) {
			for (MultipartFile multi : multiFiles) {
			AttachVO attach = new AttachVO();
			String fileName = MakeFileName.toUUIDFileName(multi.getOriginalFilename(), "$$");

			File target = new File(savePath, fileName);
			
			target.mkdirs();
			
			multi.transferTo(target);

			attach.setFileName(fileName);
			attach.setUploadPath(savePath);
			attach.setFileType(fileName.substring(fileName.lastIndexOf(".") + 1).toUpperCase());
			attachList.add(attach);
		}
	}
		return attachList;
	}
}
