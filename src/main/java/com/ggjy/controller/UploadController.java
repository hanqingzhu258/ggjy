package com.ggjy.controller;

import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
import org.json.simple.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class UploadController {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "fileUpload", method = RequestMethod.POST)
	public void fileUpload(MultipartHttpServletRequest  request,
			HttpServletResponse response) throws ServletException, IOException,
			FileUploadException {
		
		ServletContext application = request.getSession().getServletContext();
		String savePath = application.getRealPath("/") + "attached/";

		// 文件保存目录URL
		String saveUrl = request.getContextPath() + "/attached/";

		PrintWriter writer = response.getWriter();

		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

		// 最大文件大小
		long maxSize = 1000000;

		response.setContentType("text/html; charset=UTF-8");
		/*Object objectMapper;*/

		if (!ServletFileUpload.isMultipartContent(request)) {

			writer.println(getError("please select file。"));
			return;
		}
		// 检查目录
		File uploadDir = new File(savePath);
		if (!uploadDir.isDirectory()) {
			uploadDir.mkdir();
			writer.println(getError("upload file doesn't exist。"));
			return;
		}
		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			writer.println(getError("upload file doesn't have the access to write。"));
			return;
		}

		String dirName = request.getParameter("dir");
		if (dirName == null) {
			dirName = "image";
		}
		if (!extMap.containsKey(dirName)) {
			writer.println(getError("category name is incorrect。"));
			return;
		}
		// 创建文件夹
		savePath += dirName + "/";
		saveUrl += dirName + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}

		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		
		List<MultipartFile> files = request.getFiles("imgFile");

        Iterator<MultipartFile> itr = files.iterator();
		
		/*List items = upload.parseRequest(request);
		Iterator itr = items.iterator();*/
		while (itr.hasNext()) {
			
			MultipartFile item = itr.next();
            String fileName = item.getOriginalFilename();
			
			/*FileItem item = (FileItem) itr.next();
			String fileName = item.getName();*/
			long fileSize = item.getSize();
//			if (!item.isFormField()) {
			if (!item.isEmpty()) {
				// 检查文件大小
				if (item.getSize() > maxSize) {
					writer.println(getError("size of upload file out of range。"));
					return;
				}
				// 检查扩展名
				String fileExt = fileName.substring(
						fileName.lastIndexOf(".") + 1).toLowerCase();
				if (!Arrays.<String> asList(extMap.get(dirName).split(","))
						.contains(fileExt)) {
					writer.println(getError("extensable name of upload file is illegal。\n只允许"
							+ extMap.get(dirName) + "格式。"));
					return;
				}

				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				String newFileName = df.format(new Date()) + "_"
						+ new Random().nextInt(1000) + "." + fileExt;
				try {
					File uploadedFile = new File(savePath, newFileName);
					/*item.write(uploadedFile);*/
					item.transferTo(uploadedFile);
					
				} catch (Exception e) {
					writer.println(getError("uploading is fail。"));
					return;
				}

				JSONObject obj = new JSONObject();
				obj.put("error", 0);
				obj.put("url", saveUrl + newFileName);
				writer.println(obj.toJSONString());
			}
		}
	}

	@SuppressWarnings("unchecked")
	String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "fileManage", method = RequestMethod.GET)  
    public void fileManager(HttpServletRequest request,  
            HttpServletResponse response) throws ServletException, IOException {  
        ServletContext application = request.getSession().getServletContext();  
        ServletOutputStream out = response.getOutputStream();  
        // 根目录路径，可以指定绝对路径，比如 /var/www/attached/  
        String rootPath = application.getRealPath("/") + "attached/";  
        // 根目录URL，可以指定绝对路径，比如 http://www.yoursite.com/attached/  
        String rootUrl = request.getContextPath() + "/attached/";  
	//图片扩展名
	String[] fileTypes = new String[]{"gif", "jpg", "jpeg", "png", "bmp"};

	String dirName = request.getParameter("dir");
	if (dirName != null) {
		if(!Arrays.<String>asList(new String[]{"image", "flash", "media", "file"}).contains(dirName)){
			out.println("Invalid Directory name.");
			return;
		}
		rootPath += dirName + "/";
		rootUrl += dirName + "/";
		File saveDirFile = new File(rootPath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
	}
	//根据path参数，设置各路径和URL
	String path = request.getParameter("path") != null ? request.getParameter("path") : "";
	String currentPath = rootPath + path;
	String currentUrl = rootUrl + path;
	String currentDirPath = path;
	String moveupDirPath = "";
	if (!"".equals(path)) {
		String str = currentDirPath.substring(0, currentDirPath.length() - 1);
		moveupDirPath = str.lastIndexOf("/") >= 0 ? str.substring(0, str.lastIndexOf("/") + 1) : "";
	}

	//排序形式，name or size or type
	String order = request.getParameter("order") != null ? request.getParameter("order").toLowerCase() : "name";

	//不允许使用..移动到上一级目录
	if (path.indexOf("..") >= 0) {
		out.println("Access is not allowed.");
		return;
	}
	//最后一个字符不是/
	if (!"".equals(path) && !path.endsWith("/")) {
		out.println("Parameter is not valid.");
		return;
	}
	//目录不存在或不是目录
	File currentPathFile = new File(currentPath);
	if(!currentPathFile.isDirectory()){
		out.println("Directory does not exist.");
		return;
	}

	//遍历目录取的文件信息
	List<Hashtable> fileList = new ArrayList<Hashtable>();
	if(currentPathFile.listFiles() != null) {
		for (File file : currentPathFile.listFiles()) {
			Hashtable<String, Object> hash = new Hashtable<String, Object>();
			String fileName = file.getName();
			if(file.isDirectory()) {
				hash.put("is_dir", true);
				hash.put("has_file", (file.listFiles() != null));
				hash.put("filesize", 0L);
				hash.put("is_photo", false);
				hash.put("filetype", "");
			} else if(file.isFile()){
				String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
				hash.put("is_dir", false);
				hash.put("has_file", false);
				hash.put("filesize", file.length());
				hash.put("is_photo", Arrays.<String>asList(fileTypes).contains(fileExt));
				hash.put("filetype", fileExt);
			}
			hash.put("filename", fileName);
			hash.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
			fileList.add(hash);
		}
	}

	if ("size".equals(order)) {
		Collections.sort(fileList, new SizeComparator());
	} else if ("type".equals(order)) {
		Collections.sort(fileList, new TypeComparator());
	} else {
		Collections.sort(fileList, new NameComparator());
	}
	JSONObject result = new JSONObject();
	result.put("moveup_dir_path", moveupDirPath);
	result.put("current_dir_path", currentDirPath);
	result.put("current_url", currentUrl);
	result.put("total_count", fileList.size());
	result.put("file_list", fileList);

	response.setContentType("application/json; charset=UTF-8");
	out.println(result.toJSONString());
	
	}	
	@SuppressWarnings("rawtypes")
	public class NameComparator implements Comparator {
		public int compare(Object a, Object b) {
			
			Hashtable hashA = (Hashtable)a;
			Hashtable hashB = (Hashtable)b;
			if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String)hashA.get("filename")).compareTo((String)hashB.get("filename"));
			}
		}
	}
	@SuppressWarnings("rawtypes")
	public class SizeComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable)a;
			Hashtable hashB = (Hashtable)b;
			if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
				return 1;
			} else {
				if (((Long)hashA.get("filesize")) > ((Long)hashB.get("filesize"))) {
					return 1;
				} else if (((Long)hashA.get("filesize")) < ((Long)hashB.get("filesize"))) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}
	@SuppressWarnings("rawtypes")
	public class TypeComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable)a;
			Hashtable hashB = (Hashtable)b;
			if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String)hashA.get("filetype")).compareTo((String)hashB.get("filetype"));
			}
		}
	}
		
	
	
	
}
