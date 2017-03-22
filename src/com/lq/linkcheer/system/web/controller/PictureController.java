package com.lq.linkcheer.system.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lq.common.util.custom.FileUtil;
import com.lq.common.util.custom.GlobalConstant;
import com.lq.common.util.custom.JacobUtil;
import com.lq.common.util.custom.ResponseUtil;
import com.lq.common.util.plugin.Result;
import com.lq.common.util.plugin.easyui.datagrid.PageDto;
import com.lq.common.util.plugin.easyui.datagrid.Pager;
import com.lq.common.util.plugin.json.JsonUtil;
import com.lq.common.util.spring.DateEditor;
import com.lq.config.AppConfigManager;
import com.lq.linkcheer.system.model.entity.Picture;
import com.lq.linkcheer.system.model.entity.User;
import com.lq.linkcheer.system.service.interfaces.IPictureService;


@Controller
@RequestMapping(value = "/system/picture")
public class PictureController {

	@Autowired private IPictureService pictureService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws ServletException {
			
        binder.registerCustomEditor(Date.class, new DateEditor());
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	
	@RequestMapping(value="/save")
	@ResponseBody
	public String saveOrUpdateR(HttpSession session,HttpServletRequest request,HttpServletResponse response,MultipartFile SC_file, Picture picture){
		response.addHeader("Access-Control-Allow-Origin", "*");
		Result result = new Result();
		User user = (User) session.getAttribute(GlobalConstant.SESSION_LOGIN_USER);
		//路径类型
		String pathType = "lq.sctp";
		Calendar cal = Calendar.getInstance();    
		String y = cal.get(Calendar.YEAR) + ""; 
		String m;
		if((cal.get(Calendar.MONTH)) + 1 < 10){
			m = "0" + (cal.get(Calendar.MONTH) + 1);
		}
		else{
			m = (cal.get(Calendar.MONTH) + 1)+"";
		}
		
		String d;
		if((cal.get(Calendar.DATE)) < 10){
			d = "0" + cal.get(Calendar.DATE);
		}
		else{
			d = cal.get(Calendar.DATE)+"";
		}
		//相对路径
		String fjdz = "/upload/"+user.getUsername()+"/"+y+m+d;
		//绝对路径
		//String path = AppConfigManager.getConfig(pathType)+fjdz;
		//request.getSession().getServletContext().getRealPath("/")+
		
		String path = request.getSession().getServletContext().getRealPath("/") + fjdz;//项目根目录下
		//文件名
		String filename = FileUtil.getFilename(SC_file);
		//int lastPointIndex = filename.lastIndexOf(".");
		String fileType = JacobUtil.getFileSufix(filename);
		if(!ResponseUtil.checkType(fileType)){
			//不符合文件上传格式
			result.setStatus("0");
			result.setMsg("上传图片格式错误！");
			return JsonUtil.fromObject(result);
		}
		
		/*if(StringUtils.isBlank(picture.getPath())){
			result.setData("");
			
		}*/
		try {
			boolean flag = pictureService.picIsExist(filename, fjdz, fileType);
			if(!flag){
				result.setData("");
				result.setMsg("图片已存在！");
				result.setStatus("0");
				return JsonUtil.fromObject(result);
			}
			Date date = new Date();
			picture.setPath(fjdz);
			picture.setName(filename);
			picture.setType(fileType);
			picture.setTime(date);
			picture.setUser_id(user.getId());
			picture.setUser_name(user.getUsername());
			pictureService.saveOrUpdate(picture);
			Picture picture2 = pictureService.getPicture(filename, fjdz, fileType);
			result.setData(picture2);
			result.setMsg("上传成功!");
			result.setStatus("1");
		} catch (Exception e) {
			e.printStackTrace();
			result.setData("");
			result.setMsg("保存图片信息错误");
			result.setStatus("0");
		}
		try {
			FileUtil.execute(SC_file, path);
		} catch (Exception e) {
			result.setData("");
			result.setMsg("上传图片错误");
			result.setStatus("0");
		}
		return JsonUtil.fromObject(result);
	}
	
	
	@RequestMapping(value="/edit")
	@ResponseBody
	public String editPicture(HttpSession session,HttpServletRequest request,HttpServletResponse response,String id, String activity_name,String isshow){
		response.addHeader("Access-Control-Allow-Origin", "*");
		Result result = new Result();
		User user = (User) session.getAttribute(GlobalConstant.SESSION_LOGIN_USER);
		//路径类型
		try {
			Picture picture = pictureService.getOne(id);
			if(picture == null){
				result.setData("");
				result.setMsg("error");
				result.setStatus("0");
				return JsonUtil.fromObject(result);
			}
			Date date = new Date();
			picture.setActivity_name(activity_name);
			picture.setIsshow(isshow);
			picture.setTime(date);
			picture.setUser_id(user.getId());
			picture.setUser_name(user.getUsername());
			pictureService.saveOrUpdate(picture);
			result.setData("");
			result.setMsg("修改成功!");
			result.setStatus("1");
		} catch (Exception e) {
			e.printStackTrace();
			result.setData("");
			result.setMsg("编辑信息错误");
			result.setStatus("0");
		}
		return JsonUtil.fromObject(result);
	}

	
	@RequestMapping(value="/list")
	@ResponseBody
	public String getNoticeList(HttpServletRequest request,HttpServletResponse response, Picture picture,Date startTime, Date endTime,Pager pager){
		response.addHeader("Access-Control-Allow-Origin", "*");
		Result result = new Result();
		PageDto pagedto = new PageDto();
		try {
			pagedto.setRows(pictureService.getPictureList(picture, startTime, endTime, pager));
			pagedto.setTotal(pictureService.getPictureCount(picture, startTime, endTime));
			result.setData(pagedto);
			result.setMsg("获取数据成功！");
			result.setStatus("1");
		} catch (Exception e) {
			e.printStackTrace();
			result.setData(e);
			result.setMsg("获取数据失败！");
			result.setStatus("0");
		}
		
		return JsonUtil.fromObject(result);
	}
	
	@RequestMapping(value="/del")
	@ResponseBody
	public String delOneByid(HttpServletRequest request,HttpServletResponse response, String id){
		response.addHeader("Access-Control-Allow-Origin", "*");
		Result result = new Result();
		if(StringUtils.isBlank(id)){
			result.setData("");
			result.setMsg("内容为空！");
			result.setStatus("0");
			return JsonUtil.fromObject(result);
		}
		
		
		try {
			Picture picture = pictureService.getOne(id);
			String fjdz = picture.getPath();
			//文件名
			String filename = picture.getName();
			//文件绝对路径
			//String path = AppConfigManager.getConfig("lq.sctp") + fjdz;
			String path =request.getSession().getServletContext().getRealPath("/") + fjdz;
			FileUtil.toDeleteFile(path,fjdz);
			
			pictureService.delOneById(id);
			result.setData("");
			result.setMsg("success");
			result.setStatus("1");
		} catch (Exception e) {
			e.printStackTrace();
			result.setData("");
			result.setMsg("error");
			result.setStatus("0");
		}
		return JsonUtil.fromObject(result);
		
	}
	
	
	
	@RequestMapping(value="/getone")
	@ResponseBody
	public String getOneById(HttpServletRequest request,HttpServletResponse response, String id){
		response.addHeader("Access-Control-Allow-Origin", "*");
		Result result = new Result();
		if(StringUtils.isBlank(id)){
			result.setData("");
			result.setMsg("内容为空！");
			result.setStatus("0");
			return JsonUtil.fromObject(result);
		}
		
		try {
			Picture picture = pictureService.getOne(id);
			
			result.setData(picture);
			if(picture == null){
				result.setMsg("查询结果为空！");
				result.setStatus("0");
			}else{
				result.setMsg("success");
				result.setStatus("1");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setData("");
			result.setMsg("error");
			result.setStatus("0");
		}
		return JsonUtil.fromObject(result);
		
	}
	
	
	
	//预览，获取图片流  
	@RequestMapping(value = "/readImage", produces = "text/plain;charset=UTF-8")  
    public void readImage(HttpServletRequest request, HttpServletResponse response,String id){
		/*
		 * Picture picture =
		 * pictureService.getOne("402881f056078bdb0156078c5c140001"); String
		 * fjdz = picture.getPath(); //文件名 String filename = picture.getName();
		 * String imagePath = AppConfigManager.getConfig("lq.sctp") + fjdz +"/"
		 * +filename; try{ File file = new File(imagePath); if (file.exists()) {
		 * //DataOutputStream temps = new
		 * DataOutputStream(response.getOutputStream()); FileInputStream in =
		 * new FileInputStream(new File(imagePath));
		 * response.setContentType("multipart/form-data");
		 * response.setContentType
		 * ("image/*");//等同于response.setHeader("Content-Type", "image/jpeg");
		 * //5.设置响应头控制浏览器不缓存图片数据 //response.setDateHeader("expries", -1);
		 * //response.setHeader("Cache-Control", "no-cache"); //
		 * response.setHeader("Pragma", "no-cache"); //6.将图片写给浏览器
		 * ServletOutputStream temps = response.getOutputStream(); byte[] b =
		 * new byte[2048]; while ((in.read(b)) != -1) { temps.write(b); //
		 * temps.flush(); } System.out.println("temps: "+temps); in.close();
		 * temps.flush(); temps.close(); } } catch (Exception e) {
		 * e.printStackTrace(); }
		 */
		if (id == null) {

		} else {
			Picture picture = pictureService.getOne(id);
			String fjdz = picture.getPath();
			// 文件名
			String filename = picture.getName();
			//String imagePath = AppConfigManager.getConfig("lq.sctp") + fjdz + "/" + filename;
			//request.getSession().getServletContext().getRealPath("/") + 
			String imagePath =request.getSession().getServletContext().getRealPath("/") + fjdz + "/" + filename;
			try {
				response.setContentType("image/jpeg"); // 设置返回内容格式
				File file = new File(imagePath); // 括号里参数为文件图片路径"d:/a.jpg"
				if (file.exists()) { // 如果文件存在
					InputStream in = new FileInputStream(imagePath); // 用该文件创建一个输入流
					OutputStream os = response.getOutputStream(); // 创建输出流
					byte[] b = new byte[1024];
					while (in.read(b) != -1) {
						os.write(b);
					}
					in.close();
					os.flush();
					os.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	} 
}
