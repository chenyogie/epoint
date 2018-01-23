package com.epoint.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

@WebServlet("/fileloadupaction")
public class FileloadupAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FileloadupAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uploadPath = "files\\upload";
		String saveDirectory = request.getSession().getServletContext().getRealPath(uploadPath);
		try {
			File file = new File("E:/temp");
			if(!file.exists()){
				file.mkdirs();
			}
			//设置缓存大小为10K，缓存目录为E:/temp
			DiskFileItemFactory factory = new DiskFileItemFactory(1024, file);
			//使用ServletFileUpload解析文件
			ServletFileUpload upload = new ServletFileUpload(factory);
			//修改文件编码
			upload.setHeaderEncoding("utf-8");
			//解析文件
			List<FileItem> items = upload.parseRequest(request);
			if (items.size() != 0) {

				FileItem fileItem = items.get(0);
				String filename = fileItem.getName();
				//不同的浏览器getName的返回值不同，有的返回的是文件的全路径，有的返回的是文件名。
				//为了统一，对getName的返回值做以下处理。
				int index = filename.lastIndexOf("\\");
				if (index != -1) {
					filename = filename.substring(index + 1);
				}
				//获取文件内容
				InputStream in = fileItem.getInputStream();
				//使用IO工具,将文件写到服务器中去
				File savePath = new File(saveDirectory+"\\"+ filename);
				FileUtils.copyInputStreamToFile(in, savePath);
				//文件上传完成之后，将临时文件删除
				fileItem.delete();
				File filePath = new File(uploadPath,filename);
				response.getWriter().write(filePath.getPath());
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
