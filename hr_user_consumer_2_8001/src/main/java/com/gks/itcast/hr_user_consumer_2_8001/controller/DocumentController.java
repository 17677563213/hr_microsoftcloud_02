package com.gks.itcast.hr_user_consumer_2_8001.controller;

import com.gks.itcast.Document;
import com.gks.itcast.PageBean;
import com.gks.itcast.Result;
import com.gks.itcast.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * @author: 月下独酌
 * @version: 1.0
 * @QQ: 3268761517
 */
@Controller
@RequestMapping("/document")
public class DocumentController {
    @Autowired
    private RestTemplate restTemplate;

    private final static  String REST_URL_PREFIX="http://microservice-documentService";





    @RequestMapping(value = "/searchDocument/{currentPage}", method = { RequestMethod.POST, RequestMethod.GET })
    public String searchDocument(@RequestParam Map<Object, Object> conditionMap,
                                 @PathVariable("currentPage") Integer currentPage, Model model, Document document) {
        PageBean pagebean = restTemplate.postForObject(REST_URL_PREFIX + "/document/searchDocument/"+currentPage,conditionMap, PageBean.class);
        model.addAttribute("pageBean",pagebean);

        model.addAttribute("document", document);
        return "document/document";
    }

    @RequestMapping(value = "/toUpdateDocument/{id}", method = RequestMethod.GET)
    public String toUpdateDocument(@PathVariable("id") Integer id,Model model) {
        Document document= restTemplate.getForObject(REST_URL_PREFIX + "/document/toUpdateDocument/"+id,  Document.class);
        model.addAttribute("document",document);

        return "document/showUpdateDocument";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(MultipartFile file, Document document, HttpServletRequest request) throws IllegalStateException, IOException {


        String path1 = request.getServletContext().getRealPath("/") ;
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
         restTemplate.put(REST_URL_PREFIX + "/document/update/", document);



        return "redirect:/document/searchDocument/1";
    }


    @RequestMapping(value="/download/{id}",method = {RequestMethod.GET,RequestMethod.POST})
    public  void download( @PathVariable("id") Integer id,HttpServletRequest request ,HttpServletResponse response) {
        System.out.println("下载!!!!!!!!!!!!!");
        Document document= restTemplate.getForObject(REST_URL_PREFIX + "/document/toUpdateDocument/"+id,  Document.class);

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
    @RequestMapping(value="/findDownlaodFile/{id}",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Result  findDownlaodFile(@PathVariable("id") Integer id,HttpServletRequest request ) {
        System.out.println("查询文件");
        Document document= restTemplate.getForObject(REST_URL_PREFIX + "/document/toUpdateDocument/"+id,  Document.class);

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

    @RequestMapping(value="/toAddDocument",method = RequestMethod.GET)
    public String toAddDocument() {

        return "document/showAddDocument";
    }

    @RequestMapping(value="/add",method = RequestMethod.POST)
    public String add(MultipartFile file, Document document,Model model,HttpServletRequest request,HttpSession session) throws IllegalStateException, IOException {
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

        restTemplate.put(REST_URL_PREFIX + "/document/add",document);




        return "redirect:/document/searchDocument/1";
    }

    @RequestMapping(value="/delete/{ids}" ,method = RequestMethod.GET)
    public String delete(@PathVariable("ids") String ids) {
        System.out.println("需要删除的编号" + ids);
        restTemplate.delete(REST_URL_PREFIX + "/document/delete/"+ids);
        return "redirect:/document/searchDocument/1";
    }


}
