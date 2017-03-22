package com.lq.common.util.custom;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	
	private MultipartFile excelFile;// 得到上传的文件
	private String excelFileContentType;// 得到文件的类型
	private String excelFileFileName;// 得到文件的名称

	public MultipartFile getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(MultipartFile excelFile) {
		this.excelFile = excelFile;
	}

	public String getExcelFileContentType() {
		return excelFileContentType;
	}

	public void setExcelFileContentType(String excelFileContentType) {
		this.excelFileContentType = excelFileContentType;
	}

	public String getExcelFileFileName() {
		return excelFileFileName;
	}

	public void setExcelFileFileName(String excelFileFileName) {
		this.excelFileFileName = excelFileFileName;
	}
	
	public static String getFilename(MultipartFile excelFile){
		String filename = null;
		if (excelFile != null) {
			filename = excelFile.getOriginalFilename();
		}
		return filename;
	}
	
	public static void execute(MultipartFile excelFile,String path)
			throws Exception {// 使用MultipartFile 在SpringＭＶＣ下　　不能直接用File
		String filename = null;
		if (excelFile != null) {
			filename = excelFile.getOriginalFilename();
			SaveFileFromInputStream(excelFile.getInputStream(),
					path, filename);// 保存到服务器的路径
		}
	}

	 /**
	   * 删除一个文件
	   * @param fileUrl  需要删除的文件全路径名
	   */
	public static void toDeleteFile ( String filename,String path){
		String path_file = path+"/"+filename;
	    if ( StringUtils.isBlank(path_file))
	      return;
	    
	    File dFile = new File(path_file);
	    if ( dFile.exists() )
	    {
	      dFile.delete();
	    }
	    else{
	    	System.out.println("路径错误");
	    }
	  }
	
	public static void download(HttpServletResponse response,String filename,String path){
		try {
		response.addHeader("Content-Disposition", "attachment;filename="+new String((filename).getBytes("GB2312"),"iso8859-1"));  
        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());  
        int lastPointIndex = filename.lastIndexOf(".");
        String fileType = filename.substring(lastPointIndex);
        response.setContentType(ResponseUtil.MatchResponseContentType(fileType)+";charset=UTF-8");
        //文件路径+文件名
        String path_file = path+"/"+filename;
        InputStream fis = new BufferedInputStream(new FileInputStream(path_file));
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);  
        fis.close(); 
			toClient.write(buffer);
			toClient.flush();  
			toClient.close(); 
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
	
	// 将MultipartFile 转换为File
	public static void SaveFileFromInputStream(InputStream stream, String path,
			String savefile) throws IOException {
		File headPath = new File(path);//获取文件夹路径
        if(!headPath.exists()){//判断文件夹是否创建，没有创建则创建新文件夹
        	headPath.mkdirs();
        }
		FileOutputStream fs = new FileOutputStream(path + "/" + savefile);
		//System.out.println("------------" + path + "/" + savefile);
		byte[] buffer = new byte[1024 * 1024];
		//int bytesum = 0;
		int byteread = 0;
		while ((byteread = stream.read(buffer)) != -1) {
			//bytesum += byteread;
			fs.write(buffer, 0, byteread);
			fs.flush();
		}
		fs.close();
		stream.close();
	}
	
	//复制文件
	public static void copyFile(String file1,String file2)throws IOException{
  	  FileInputStream fis=new FileInputStream(file1);
  	  FileOutputStream fos=new FileOutputStream(file2);
  	  int temp;
  	  while((temp=fis.read())!=-1){
  	   fos.write(temp);
  	  }
  	  fis.close();
  	  fos.close();
  	  //System.out.println("从"+file1+"到"+file2);
  	 }
}
