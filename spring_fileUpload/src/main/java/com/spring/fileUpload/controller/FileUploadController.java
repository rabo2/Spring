package com.spring.fileUpload.controller;

import java.io.File;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.fileUpload.command.FileUploadCommand;

@Controller
public class FileUploadController {

	@RequestMapping("/fileUploadForm")
	public void fileUploadForm() {
	}

	@RequestMapping(value = "/multipartFile", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String uploadByMultipartFile(@RequestParam(value = "title", defaultValue = "제목없음") String title,
			@RequestParam("file") MultipartFile multi, HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {
		String url = "fileUploaded";

		if (multi.isEmpty()) {
			response.setContentType("utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('파일이 없습니다.'); history.go(-1);");
			out.println("</script>");
			return null;
		}

		if (multi.getSize() > 1024 * 1024 * 5) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('용량 초과입니다.'); history.go(-1);");
			out.println("</script>");
			return null;
		}

		String uploadPath = request.getSession().getServletContext().getRealPath("resources/upload");
		
		String fileName = UUID.randomUUID().toString().replace("-", "")+"$$"+multi.getOriginalFilename();
		
		File file = new File(uploadPath, fileName);
		
		if(!file.exists()) {
			file.mkdirs();
		}
		
		multi.transferTo(file);
		
		model.addAttribute("title",title);
		model.addAttribute("originFileName", multi.getOriginalFilename());
		model.addAttribute("uploadedFileName", file.getName());
		model.addAttribute("uploadPath", file.getAbsoluteFile());
		
		return url;
	}
	
	
	@RequestMapping(value="/multipartHttpServletRequest", method=RequestMethod.POST, produces="text/plain;charset=utf-8")
	public String multipartHttpServletRequest(MultipartHttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		String title = request.getParameter("title");
		MultipartFile multi = request.getFile("file");
		
		return uploadByMultipartFile(title, multi, request, response, model);
	}
	
	@RequestMapping(value="/commandObject", method=RequestMethod.POST)
	public String uploadByCommandObject(FileUploadCommand command, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		MultipartFile multi = command.getFile();
		String title = command.getTitle();
		
		return uploadByMultipartFile(title, multi, request, response, model);
	}
	
}
