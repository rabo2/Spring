package kr.or.ddit.view;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.servlet.View;

import kr.or.ddit.util.MakeFileName;

public class FileDownloadView implements View {
	
	//View의 성격 규정
	private String contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@Override
	public String getContentType() {
		return this.contentType;
	}

	@SuppressWarnings("resource")
	@Override
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String savedPath = (String) model.get("savedPath");
		String fileName = (String) model.get("fileName");
		
		File downloadFile = new File(savedPath + File.separator + fileName);
		FileInputStream inStream = new FileInputStream(downloadFile);
		
		// 파일 포맷으로 MIME를 결정한다.
		ServletContext context = request.getServletContext();
		String mimeType = context.getMimeType(downloadFile.getName());
		
		if(mimeType == null) {
			mimeType = "application/octet-stream";
		}
		
		this.contentType = mimeType;
		
		response.setContentType(mimeType);
		response.setContentLength((int)downloadFile.length());
	
		String headerKey = "Content-Disposition";
		
		//한글깨짐 방지 : ISO-8859-1 파일명은 request나 responser의 header에 존재하는데, filter의 영향을 받지 않아서 인코딩을 해줘야한다.
		String sendFileName = MakeFileName.parseFileNameFromUUID(downloadFile.getName(), "\\$\\$");
		
		String header = request.getHeader("User-Agent");
		if(header.contains("MSIE")) {
			sendFileName = URLEncoder.encode(sendFileName, "UTF-8");
		}else {
			sendFileName = new String(sendFileName.getBytes("utf-8"), "ISO-8859-1");
		}
		
		String headerValue = String.format("attachment; filename=\"%s\"",sendFileName);
		response.setHeader(headerKey, headerValue);
		
		ServletOutputStream outputStream = response.getOutputStream();
		byte[] buffer = new byte[4096];
		int bytesRead = -1;
		
		while((bytesRead = inStream.read(buffer))!= -1) {
			outputStream.write(buffer,0,bytesRead);
		}
		
		inStream.close();
		outputStream.close();
	}
}
