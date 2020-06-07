/**
 * 
 */
package com.gks.itcast.controller;

import com.gks.itcast.Notice;
import com.gks.itcast.PageBean;
import com.gks.itcast.User;
import com.gks.itcast.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;



/**
 * @author :���¶���--WXY
 * @motto  :Nothing is impossible
 * 2020��4��8��
 */
@Controller
@RequestMapping("/notice")
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	@RequestMapping("/test")
	public void test() {
		System.out.println("ҵ���"+noticeService);
		noticeService.test();
	}
	/**
	 * ���޸�ҳ��
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/toUpdateNotice/{id}",method = RequestMethod.GET)
	public Notice toUpdateNotice(@PathVariable("id") Integer id) {

		Notice notice = noticeService.getNoticeById(id);



		return notice;

	}
	
	/**
	 * ��ҳ������ѯ
	 * @param conditionMap
	 * @param request
	 * @param currentPage
	 * @param tipMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/searchNotice/{currentPage}",method = {RequestMethod.GET,RequestMethod.POST})
	public PageBean searchNotice (@RequestBody Map<Object,Object> conditionMap,@PathVariable("currentPage") Integer currentPage) {

		PageBean pageBean = noticeService.searchNotice(conditionMap, currentPage);

		return pageBean;
		 
	}
	@ResponseBody
	@RequestMapping(value="/updateNotice",method = RequestMethod.PUT)
	public void updateNotice(@RequestBody Notice notice) {
		noticeService.update(notice);



	}
	@RequestMapping(value="/addNotice",method = RequestMethod.PUT)
	@ResponseBody
	public void add(@RequestBody Notice notice) {


		noticeService.add(notice);

	}
	@RequestMapping(value="/previewNotice",method = RequestMethod.GET)
	public String previewNotice(Integer id,HttpServletRequest request) {
		Notice notice = noticeService.getNoticeById(id);
		request.setAttribute("notice", notice);
		return "notice/previewNotice";
	}

	@ResponseBody
	@RequestMapping(value="/delete/{ids}",method = RequestMethod.DELETE)
	public void delete(@PathVariable("ids") String ids) {
		String[] idArray = ids.split(",");
		System.out.println("=========================++++++++++++");
		System.out.println(idArray);
		for (int id = 0; id < idArray.length; id++) {

			noticeService.delete(new Integer(idArray[id]));

		}

	}
	@RequestMapping(value="/toAddNotice" ,method = RequestMethod.GET)
	public String toAddNotice() {
		
		return "notice/showAddNotice";
	}

	@ResponseBody
	@RequestMapping(value = "/deleteByUid/{uid}",method = RequestMethod.DELETE)
	public void deleteByUid(@PathVariable("uid") String uid){

		String[] idArray = uid.split(",");
		System.out.println("=========================++++++++++++");
		System.out.println(idArray);
		for (int id = 0; id < idArray.length; id++) {

			noticeService.deleteByUid(new Integer(idArray[id]));

		}




	}
}
