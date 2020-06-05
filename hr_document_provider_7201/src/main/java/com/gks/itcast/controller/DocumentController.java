package com.gks.itcast.controller; /**
 *
 */


import com.gks.itcast.Document;
import com.gks.itcast.PageBean;
import com.gks.itcast.Result;
import com.gks.itcast.User;
import com.gks.itcast.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;




/**
 * @author :月下独酌--WXY
 * @motto :Nothing is impossible
 * 2020年4月8日
 */
@Controller
@RequestMapping("/document")
public class DocumentController {
	@Autowired
	private DocumentService documentService;

	@RequestMapping(value = "test", method = RequestMethod.GET)
	public void test() {
		System.out.println("业务层" + documentService);
		documentService.test();

	}

	/**
	 * 分页条件查询
	 *
	 * @param conditionMap
	 * @param currentPage:当前页
	 * @param request
	 * @param document
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/searchDocument/{currentPage}", method = { RequestMethod.POST, RequestMethod.GET })
	public PageBean searchDocument(@RequestParam Map<Object, Object> conditionMap,
								 @PathVariable("currentPage") Integer currentPage, HttpServletRequest request, Document document) {
		PageBean pageBean = documentService.searchDocument(conditionMap, currentPage);
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("document", document);
		return pageBean;
	}

	@RequestMapping(value = "/toUpdateDocument", method = RequestMethod.GET)
	public String toUpdateDocument(Integer id, HttpServletRequest request) {
		Document document = documentService.getDocumentById(id);
		request.setAttribute("document", document);

		return "document/showUpdateDocument";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(MultipartFile file, Document document,HttpServletRequest request) throws IllegalStateException, IOException {


		String path1 = request.getServletContext().getRealPath("/WEB-INF/upload") ;
		System.out.println("修改之前的路径"+path1);
		String path = path1.replace("\\", "\\\\");
		System.out.println("修改之后的路径"+path);
//		将文件路径变成实例,进行操作:
		File dir = new File(path);
//		判断该路径是否存在:
		if(!dir.exists()) {
//			如果不存在批量创建:
			dir.mkdirs();

		}
//		获取上传文件的源文件名:
		String fileName = file.getOriginalFilename();
		String salt = UUID.randomUUID().toString().replace("-", "").substring(17);
		String finalName=salt+fileName;
//		创建空文件夹:(疑惑:为什么这样就能够创建空的文件夹?)
		File f = new File(path,finalName);
//		拷贝:
		file.transferTo(f);
		document.setFilename(finalName);
		documentService.update(document);


		return "redirect:searchDocument/1";
	}
	@RequestMapping(value="/findDownlaodFile",method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Result findDownlaodFile(Integer id, HttpServletRequest request ) {
		System.out.println("查询文件");
		Document document = documentService.getDocumentById(id);
		Result result = new Result();
		if (document != null && document.getFilename() != null) {
			String path1 = request.getServletContext().getRealPath("/WEB-INF/upload");
			String path = path1.replace("\\", "\\\\");
			File file = new File(path + "\\\\" + document.getFilename());
			if(file.exists()) {
				result.setFlag(true);
				result.setMessage("下载");
				return result;

			}else {
				result.setFlag(false);
				result.setMessage("抱歉,您重启服务器导致文件自动释放!!");
				return result;

			}


		}else{
			result.setFlag(false);
			result.setMessage("文件不能存在");
			return result;

		}

	}
	@RequestMapping(value="/download",method = {RequestMethod.GET,RequestMethod.POST})
	public  void download( Integer id,HttpServletRequest request ,HttpServletResponse response) {
		System.out.println("下载!!!!!!!!!!!!!");
		Document document = documentService.getDocumentById(id);
		if (document != null && document.getFilename() != null) {
			String path1 = request.getServletContext().getRealPath("/WEB-INF/upload");
			String path = path1.replace("\\", "\\\\");
			File file = new File(path + "\\\\" + document.getFilename());
			com.wxy.utils.DownloadUtil02 downloadUtil02 = new com.wxy.utils.DownloadUtil02();
			downloadUtil02.download(file, document.getFilename(), response, false);
		}

	}
	@RequestMapping(value="/tips")
	@ResponseBody
	public Result tips() {
		Result result = new Result();
		result.setFlag(true);
		result.setMessage("文件下载成功,注意查收哟!!!");
		return result;
	}

	@RequestMapping(value="delete" ,method = RequestMethod.GET)
	public String delete(String ids) {
		System.out.println("需要删除的编号" + ids);
		String[] idArray = ids.split(",");
		System.out.println("=========================++++++++++++");
		System.out.println(idArray);
		for (int id = 0; id < idArray.length; id++) {

			documentService.delete(new Integer(idArray[id]));

		}
		return "redirect:searchDocument/1";
	}
	@RequestMapping(value="/toAddDocument",method = RequestMethod.GET)
	public String toAddDocument() {

		return "document/showAddDocument";
	}
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public String add(MultipartFile file, Document document,HttpServletRequest request,HttpSession session) throws IllegalStateException, IOException {
		User user = (User) session.getAttribute("respUser");
		if(user!=null&& user.getId()!=null) {
			document.setUser(user);

		}
		String path1 = request.getServletContext().getRealPath("/WEB-INF/upload") ;
		System.out.println("修改之前的路径"+path1);
		String path = path1.replace("\\", "\\\\");
		System.out.println("修改之后的路径"+path);
//		将文件路径变成实例,进行操作:
		File dir = new File(path);
//		判断该路径是否存在:
		if(!dir.exists()) {
//			如果不存在批量创建:
			dir.mkdirs();

		}
//		获取上传文件的源文件名:
		String fileName = file.getOriginalFilename();
//		创建空文件夹:(疑惑:为什么这样就能够创建空的文件夹?)
		String salt = UUID.randomUUID().toString().replace("-", "").substring(17);
		String finalName=salt+fileName;
		File f = new File(path,finalName);
//		拷贝:
		file.transferTo(f);
		document.setFilename(finalName);
		documentService.add(document);


		return "redirect:searchDocument/1";
	}




}
