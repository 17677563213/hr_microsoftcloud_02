package com.gks.itcast.controller; /**
 *
 */


import com.gks.itcast.Document;
import com.gks.itcast.PageBean;
import com.gks.itcast.Result;
import com.gks.itcast.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Map;


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
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/searchDocument/{currentPage}", method = { RequestMethod.POST, RequestMethod.GET })
	public PageBean searchDocument(@RequestBody Map<Object, Object> conditionMap,
								   @PathVariable("currentPage") Integer currentPage  ) {
		PageBean pageBean = documentService.searchDocument(conditionMap, currentPage);

		return pageBean;
	}

	@ResponseBody
	@RequestMapping(value = "/toUpdateDocument/{id}", method = RequestMethod.GET)
	public Document toUpdateDocument(@PathVariable("id") Integer id) {
		Document document = documentService.getDocumentById(id);
		 return document;
	}

	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public void update(MultipartFile file, @RequestBody Document document,HttpServletRequest request) throws IllegalStateException, IOException {



		documentService.update(document);



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

	@ResponseBody
	@RequestMapping(value="/delete/{ids}" ,method = RequestMethod.DELETE)
	public void delete(@PathVariable("ids") String ids) {
		System.out.println("需要删除的编号" + ids);
		String[] idArray = ids.split(",");
		System.out.println("=========================++++++++++++");
		System.out.println(idArray);
		for (int id = 0; id < idArray.length; id++) {

			documentService.delete(new Integer(idArray[id]));

		}

	}
	@RequestMapping(value="/toAddDocument",method = RequestMethod.GET)
	public String toAddDocument() {

		return "document/showAddDocument";
	}
	@ResponseBody
	@RequestMapping(value="/add",method = RequestMethod.PUT)
	public void add(MultipartFile file,@RequestBody Document document) {

		documentService.add(document);



	}
	@ResponseBody
	@RequestMapping(value = "/deleteByUid/{ids}",method = RequestMethod.GET)
	public boolean deleteByUid(@PathVariable("ids") String ids){
		String[] idArray = ids.split(",");
		System.out.println("=========================++++++++++++");
		System.out.println(idArray);
		for (int id = 0; id < idArray.length; id++) {

			documentService.deleteByUid(new Integer(idArray[id]));


		}



		return true;




	}





}
