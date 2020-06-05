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
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/toUpdateNotice",method = RequestMethod.GET)
	public String toUpdateNotice(Integer id,HttpServletRequest request) {
		System.out.println("�޸ı��"+id);
		Notice notice = noticeService.getNoticeById(id);
		request.setAttribute("notice", notice);
		
		
		return "notice/showUpdateNotice";
		
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
	public PageBean searchNotice (@RequestParam Map<Object,Object> conditionMap,HttpServletRequest request,@PathVariable("currentPage") Integer currentPage,@RequestParam Map<String, String> tipMap) {
		request.setAttribute("tipMap", tipMap);
		PageBean pageBean = noticeService.searchNotice(conditionMap, currentPage);
		request.setAttribute("pageBean", pageBean);
		return pageBean;
		 
	}
	@RequestMapping(value="/updateNotice",method = RequestMethod.POST)
	public String updateNotice(Notice notice) {
		noticeService.update(notice);
		return "redirect:searchNotice/1";
		
		
	}
	@RequestMapping(value="/addNotice",method = RequestMethod.POST)
	public String add(Notice notice,HttpSession session) {
		User sessionUser = (User) session.getAttribute("respUser");
		if(sessionUser!=null&&sessionUser.getId()!=null) {
			 notice.setUser(sessionUser);
		}
		noticeService.add(notice);
		return "redirect:searchNotice/1";
	}
	@RequestMapping(value="/previewNotice",method = RequestMethod.GET)
	public String previewNotice(Integer id,HttpServletRequest request) {
		Notice notice = noticeService.getNoticeById(id);
		request.setAttribute("notice", notice);
		return "notice/previewNotice";
	}

	@RequestMapping(value="/delete",method = RequestMethod.GET)
	public String delete(String ids) {
		String[] idArray = ids.split(",");
		System.out.println("=========================++++++++++++");
		System.out.println(idArray);
		for (int id = 0; id < idArray.length; id++) {

			noticeService.delete(new Integer(idArray[id]));

		}
		return "redirect:searchNotice/1";
	}
	@RequestMapping(value="/toAddNotice" ,method = RequestMethod.GET)
	public String toAddNotice() {
		
		return "notice/showAddNotice";
	}
}
