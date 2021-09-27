package kr.or.ddit.scheduler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.dto.MemberVO;
import kr.or.ddit.service.MemberService;

public class RemoveMemberPictureScheduler {
	
	private MemberService memberSerivce;
	private String picturePath;

	public void setMemberSerivce(MemberService memberSerivce) {
		this.memberSerivce = memberSerivce;
	}
	
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public void removePicture() throws Exception{
		
		File dir = new File(picturePath);
		File[] files = dir.listFiles();
		
		List<String> pictureFiles = new ArrayList<String>();
		
		List<MemberVO> memberList = memberSerivce.getMemberList();
		for (MemberVO member : memberList) {
			pictureFiles.add(member.getPicture());
		}
		
		if(files!=null) {
			for(File file : files) {
				if(!pictureFiles.contains(file.getName())) file.delete();
			}
		}
	}
	
}
