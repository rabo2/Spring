//package kr.or.ddit.util;
//
//import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Enumeration;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.tomcat.util.http.fileupload.FileItem;
//import org.apache.tomcat.util.http.fileupload.FileUploadException;
//import org.apache.tomcat.util.http.fileupload.RequestContext;
//import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
//
//
//import kr.or.ddit.exception.NotMultipartFormDataException;
//
//public class MultipartHttpServletRequestParser {
//
//	// 업로드 파일 환경 설정
//	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
//	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
//	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 200; // 200MB
//
//	Map<String, String[]> paramString = new HashMap<String, String[]>();
//	Map<String, List<FileItem>> paramFile = new HashMap<String, List<FileItem>>();
//
//	public MultipartHttpServletRequestParser(HttpServletRequest request)
//			throws NotMultipartFormDataException, UnsupportedEncodingException, FileUploadException {
//		this(request, MEMORY_THRESHOLD, MAX_FILE_SIZE, MAX_REQUEST_SIZE);
//	}
//
//	public MultipartHttpServletRequestParser(HttpServletRequest request, int memory_threshold, int max_file_size,
//			int max_request_size)
//			throws NotMultipartFormDataException, UnsupportedEncodingException, FileUploadException {
//
//		ServletFileUpload upload = ServletFileUploadBuilder.build(request, memory_threshold, max_file_size,
//				max_request_size);
//
//		List<FileItem> formItems = upload.parseRequest((RequestContext) request);
//
//		for (FileItem item : formItems) {
//			String paramName = item.getFieldName();
//
//			if (item.isFormField()) {
//				String[] paramValues = item.getString("utf-8").split(",");
//				this.paramString.put(paramName, paramValues);
//			} else {
//				List<FileItem> files = this.paramFile.get(paramName);
//
//				if (files == null) {
//					files = new ArrayList<FileItem>();
//					this.paramFile.put(paramName, files);
//				}
//
//				files.add(item);
//			}
//		}
//
//	}
//
//	public String getParameter(String paramName) {
//		String[] param = this.paramString.get(paramName);
//		String result = null;
//
//		if (param != null)
//			result = param[0];
//
//		return result;
//	}
//
//	public String getXSSParameter(String paramName) {
//		String result = HTMLInputFilter.htmlSpecialChars(getParameter(paramName));
//		
//		return result;
//	}
//	
//	public String[] getParameterValues(String paramName) {
//		return this.paramString.get(paramName);
//	}
//
//	public String[] getXSSParameterValues(String paramName) {
//		String[] paramValues = new String[this.paramString.get(paramName).length];
//		
//		for(int i = 0; i<paramValues.length; i++) {
//			paramValues[i] = HTMLInputFilter.htmlSpecialChars(paramValues[i]);
//		}
//		
//		return paramValues;
//	}
//
//	public Enumeration<String> getParameterNames() {
//		ArrayList<String> paraNames = new ArrayList<String>();
//
//		if (paramString.size() > 0) {
//			for (String paraName : paramString.keySet()) {
//				paraNames.add(paraName);
//			}
//		}
//
//		if (paramFile.size() > 0) {
//			for (String paraName : paramFile.keySet()) {
//				paraNames.add(paraName);
//			}
//		}
//
//		Enumeration<String> result = Collections.enumeration(paraNames);
//
//		return result;
//	}
//
//	public FileItem getFileItem(String paramName) {
//		List<FileItem> itemList = paramFile.get(paramName);
//		FileItem result = null;
//
//		if (itemList != null)
//			result = itemList.get(0);
//
//		return result;
//	}
//
//	public FileItem[] getFileItems(String paramName) {
//		List<FileItem> items = paramFile.get(paramName);
//		FileItem[] files = null;
//
//		if (items != null) {
//			files = new FileItem[items.size()];
//			items.toArray(files);
//		}
//
//		return files;
//	}
//
//}
