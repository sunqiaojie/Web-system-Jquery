package com.lq.common.util.custom;

import java.io.File;
import java.io.IOException;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class JacobUtil {
	 public static final int WORD_HTML = 8;   
	  
	    public static final int WORD_TXT = 7;   
	  
	    public static final int EXCEL_HTML = 44;   
	  
	    public static String conver(String yCFJBM,String path,String filename) {
	    	int lastPointIndex = filename.lastIndexOf(".");
			String fileType = filename.substring(lastPointIndex);
			String htmlname = null;
			String htmlPath = null;
			String sourcePath = path + "/" + filename;
			if(ResponseUtil.coverType(fileType)){
				htmlname = yCFJBM+fileType;
				htmlPath = GlobalConstant.FILE_VIEW_PATH+"/" +htmlname;
				File htmlFile = new File(htmlPath);
				if(htmlFile.exists()){
					return htmlname;
				}
				try {
					FileUtil.copyFile(sourcePath, htmlPath);
					return htmlname;
				} catch (IOException e) {
					e.printStackTrace();
					return "false";
				}
			}
			else{
				htmlname = yCFJBM+".html";
			}
	        htmlPath = GlobalConstant.FILE_VIEW_PATH+"/" +htmlname;
	    	File htmlFile = new File(htmlPath);
	    	if(htmlFile.exists()){
				return htmlname;
			}
	    	if(JacobUtil.convert2HTML(sourcePath,htmlPath)){
	    		if(htmlFile.exists()){
	    			System.out.println(htmlFile.getPath());
					return htmlname;
				}
	    		return "false";
	    	}
	    	return "false";
	    }  
	      
	    public static boolean convert2HTML(String inputFile, String pdfFile) {  
	        String suffix = getFileSufix(inputFile);  
	        File file = new File(inputFile);  
	        if (!file.exists()) {  
	           // System.out.println("文件不存在！");  
	            return false;  
	        }  
	        if (suffix.equals(".pdf")) {  
	           // System.out.println("PDF not need to convert!");  
	            return false;  
	        }  
	        if (suffix.equals(".doc") || suffix.equals(".docx") || suffix.equals(".txt")) {  
	            wordToHtml(inputFile, pdfFile); 
	            return true;
	        } else if (suffix.equals(".xls") || suffix.equals(".xlsx")) {  
	            excelToHtml(inputFile, pdfFile); 
	            return true;
	        } else {  
	           // System.out.println("文件格式不支持转换!");  
	            return false;  
	        }  
	    }  
	    
	    /** 
	     * 获取文件后缀 
	     *  
	     * @param fileName 
	     * @return 
	     * @author SHANHY 
	     */  
	    public static String getFileSufix(String fileName) {  
	        int splitIndex = fileName.lastIndexOf(".");  
	        return fileName.substring(splitIndex);  
	    }  
	    
	    public static void wordToHtml(String docfile, String htmlfile)   
	    {   
	    	ComThread.InitSTA();
	        ActiveXComponent app = new ActiveXComponent("Word.Application"); // 启动word   
	        try  
	        {   
	        	//System.out.println("1");
	            app.setProperty("Visible", new Variant(false)); 
	            //System.out.println("2");
	            Dispatch docs = app.getProperty("Documents").toDispatch();   
	           // System.out.println("3");
	            Dispatch doc = Dispatch.invoke(   
	                    docs,   
	                    "Open",   
	                    Dispatch.Method,   
	                    new Object[] { docfile, new Variant(false),   
	                            new Variant(true) }, new int[1]).toDispatch();   
	            //System.out.println("4");
	            Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[] {   
	                    htmlfile, new Variant(WORD_HTML) }, new int[1]);   
	            Variant f = new Variant(false);   
	            //System.out.println("5"+htmlfile);
	            Dispatch.call(doc, "Close", f);
	            //File file = new File(htmlfile);
	            /*if(file.exists()){
	            	System.out.print(file.getPath());
	            }
	            else{
	            	System.out.print("没生成");
	            }*/
	        }   
	        catch (Exception e)   
	        {   
	        	//System.out.println("运行出错了啊");
	        	//System.out.println(e.toString());
	            e.printStackTrace();
	            //return false;
	        }   
	        finally  
	        {   
	        	//System.out.println("一定执行的");
	            app.invoke("Quit", new Variant[] {}); 
	            ComThread.Release();
	        } 
	        //return true;
	    }   
	  
	      
	    public static void excelToHtml(String xlsfile, String htmlfile)   
	    {   
	    	ComThread.InitSTA();
	        ActiveXComponent app = new ActiveXComponent("Excel.Application"); // 启动word   
	        try  
	        {   
	            app.setProperty("Visible", new Variant(false));   
	            Dispatch excels = app.getProperty("Workbooks").toDispatch();   
	            Dispatch excel = Dispatch.invoke(   
	                    excels,   
	                    "Open",   
	                    Dispatch.Method,   
	                    new Object[] { xlsfile, new Variant(false),   
	                            new Variant(true) }, new int[1]).toDispatch();   
	            Dispatch.invoke(excel, "SaveAs", Dispatch.Method, new Object[] {   
	                    htmlfile, new Variant(EXCEL_HTML) }, new int[1]); 
	            Variant f = new Variant(false);   
	            Dispatch.call(excel, "Close", f);   
	        }   
	        catch (Exception e)   
	        {   
	            e.printStackTrace(); 
	            //return false;
	        }   
	        finally  
	        {   
	            app.invoke("Quit", new Variant[] {});
	            ComThread.Release();
	        } 
	        //return true;
	    }   
}
