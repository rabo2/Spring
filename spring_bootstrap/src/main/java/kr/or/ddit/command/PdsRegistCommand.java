package kr.or.ddit.command;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.dto.PdsVO;

public class PdsRegistCommand {
	private String title;
	private String writer;
	private String content;
	private List<MultipartFile> uploadFile;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<MultipartFile> getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(List<MultipartFile> uploadFile) {
		this.uploadFile = uploadFile;
	}

	public PdsVO toPdsVO() {
		PdsVO pds = new PdsVO();
		pds.setTitle(title);
		pds.setWriter(writer);
		pds.setContent(content);

		return pds;
	}

}
