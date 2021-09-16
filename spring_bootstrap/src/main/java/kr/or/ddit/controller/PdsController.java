package kr.or.ddit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.josephoconnell.html.HTMLInputFilter;
import com.sun.java.swing.plaf.motif.resources.motif_de;

import kr.or.ddit.command.PdsModifyCommand;
import kr.or.ddit.command.PdsRegistCommand;
import kr.or.ddit.command.SearchCriteria;
import kr.or.ddit.dto.AttachVO;
import kr.or.ddit.dto.PdsVO;
import kr.or.ddit.service.PdsService;
import kr.or.ddit.util.GetAttachesAsMultipartFiles;

@Controller
@RequestMapping("/pds")
public class PdsController {

	@Autowired
	private PdsService service;

	@Resource(name = "fileUploadPath")
	private String fileUploadPath;

	@RequestMapping("/main")
	public void main() {
	}

	@RequestMapping("/list")
	public ModelAndView list(SearchCriteria cri, ModelAndView mnv) throws Exception {
		String url = "pds/list";

		Map<String, Object> dataMap = service.getList(cri);

		mnv.addObject("dataMap", dataMap);
		mnv.setViewName(url);
		return mnv;
	}

	@RequestMapping(value = "/registForm", method = RequestMethod.GET)
	public ModelAndView registForm(ModelAndView mnv) throws Exception {
		String url = "pds/regist";
		mnv.setViewName(url);
		return mnv;
	}

	@RequestMapping(value = "/regist", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String regist(PdsRegistCommand registReq, RedirectAttributes rttr) throws Exception {

		String url = "redirect:/pds/list.do";
		PdsVO pds = registReq.toPdsVO();

		List<AttachVO> attachList = GetAttachesAsMultipartFiles.save(registReq.getUploadFile(), fileUploadPath);

		pds.setTitle(HTMLInputFilter.htmlSpecialChars(pds.getTitle()));
		pds.setAttachList(attachList);

		service.regist(pds);
		rttr.addFlashAttribute("from", "regist");

		return url;
	}

	@RequestMapping(value = "/detail")
	public ModelAndView detail(int pno, String from, ModelAndView mnv) throws Exception {
		String url = "pds/detail";
		PdsVO pds = null;

		if (from != null && from.equals("list")) {
			pds = service.read(pno);
			url = "redirect:/pds/detail.do?pno=" + pno;
		} else {
			pds = service.getPds(pno);
		}

		if (pds != null) {
			List<AttachVO> attachList = pds.getAttachList();
			if (attachList != null)
				for (AttachVO attach : attachList) {
					String fileName = attach.getFileName().split("\\$\\$")[1];
					attach.setFileName(fileName);
				}
		}

		mnv.addObject("pds", pds);
		mnv.setViewName(url);
		return mnv;
	}

	@RequestMapping(value = "/modifyForm", method = RequestMethod.GET)
	public ModelAndView modifyForm(ModelAndView mnv, int pno) throws Exception {
		String url = "pds/modify";

		PdsVO pds = service.getPds(pno);

		List<AttachVO> attachList = pds.getAttachList();
		if (attachList != null)
			for (AttachVO attach : attachList) {
				String fileName = attach.getFileName().split("\\$\\$")[1];
				attach.setFileName(fileName);
			}

		mnv.addObject("pds", pds);
		mnv.setViewName(url);

		return mnv;
	}

	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(PdsModifyCommand modifyReq, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/pds/detail.do";

		String[] anoList = modifyReq.getDeleteFile();

		if (anoList != null && anoList.length > 0) {
			for (String anoStr : anoList) {
				int ano = Integer.parseInt(anoStr);
				AttachVO attach = service.getAttachByAno(ano);
				
				deleteFile(attach);
				service.removeAttachByAno(attach.getAno());
			}
		}

		List<AttachVO> attachList = GetAttachesAsMultipartFiles.save(modifyReq.getUploadFile(), this.fileUploadPath);
		PdsVO pds = modifyReq.toPdsVO();

		pds.setAttachList(attachList);
		pds.setTitle(HTMLInputFilter.htmlSpecialChars(pds.getTitle()));
		service.modify(pds);

		rttr.addFlashAttribute("from", "modify");
		rttr.addAttribute("pno", pds.getPno());

		return url;
	}

	
	@RequestMapping(value = "remove", method = RequestMethod.GET)
	public String remove(int pno, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/pds/detail.do";

		PdsVO pds = service.getPds(pno);
		List<AttachVO> attachList = pds.getAttachList();
		
		if(attachList!=null) for (AttachVO attach : attachList) {
			deleteFile(attach);
		}

		service.remove(pno);

		rttr.addFlashAttribute("from", "remove");
		rttr.addAttribute("pno", pno + "");

		return url;
	}
	
	@RequestMapping("/getFile")
	public String getFile(int ano, Model model) throws Exception{
		String url = "downloadFile";
		
		AttachVO attach = service.getAttachByAno(ano);
		
		model.addAttribute("savedPath", attach.getUploadPath());
		model.addAttribute("fileName", attach.getFileName());
		
		return url;
	}
	
	
	private void deleteFile(AttachVO attach) {
		String fileName = attach.getFileName();
		File file = new File(attach.getUploadPath(), fileName);

		if (file.exists()) {
			file.delete();
		}
	}
	
}
