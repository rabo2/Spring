package kr.or.ddit.dto;
import java.util.Date;
import java.util.List;

public class PdsVO {
	
	private int pno;
	private String title;
	private String content;
	private String writer;
	private int viewcnt;
	private Date regDate;
	private Date updatedate;
	
	private List<AttachVO> attachList;
	
	public List<AttachVO> getAttachList() {
		return attachList;
	}
	public void setAttachList(List<AttachVO> attachList) {
		this.attachList = attachList;
	}
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getViewcnt() {
		return viewcnt;
	}
	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	@Override
	public String toString() {
		return "PdsVO [pno=" + pno + ", title=" + title + ", content=" + content + ", writer=" + writer + ", viewcnt="
				+ viewcnt + ", regDate=" + regDate + ", updatedate=" + updatedate + ", attachList=" + attachList
				+ ", getAttachList()=" + getAttachList() + ", getPno()=" + getPno() + ", getTitle()=" + getTitle()
				+ ", getContent()=" + getContent() + ", getWriter()=" + getWriter() + ", getViewcnt()=" + getViewcnt()
				+ ", getRegDate()=" + getRegDate() + ", getUpdatedate()=" + getUpdatedate() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
}
