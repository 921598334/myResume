package com.denghanbo.resume.Controller;

import com.denghanbo.resume.eunm.PageType;
import com.denghanbo.resume.model.Page;
import com.denghanbo.resume.repository.PageRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

//这个是主界面
@Controller
public class IndexController {


    int reFreshTime = 0;

    @Autowired
    PageRepository pageRepository;

    @GetMapping("/")
    public String index(Model model, HttpServletResponse response, HttpServletRequest request)
    {

        //发送邮件成功时给用户的的反馈
        String info = (String) request.getSession().getAttribute("info");
        if(info!=null && !info.equals("")){
            model.addAttribute("info",info);
            request.getSession().removeAttribute("info");
        }


        //从数据库得到教育背景相关的
        List<Page> backPages = pageRepository.findByType(PageType.BACK.getType());
        model.addAttribute("backPages",backPages);


        //从数据库中得到项目相关的文章并且显示
        List<Page> projectPages = pageRepository.findByType(PageType.PROJECT.getType());
        model.addAttribute("projectPagess",projectPages);


        //从数据库中得社会活动相关的文章并且显示
        List<Page> socialPages = pageRepository.findByType(PageType.SOCIRYACTIVE.getType());
        model.addAttribute("socialPages",socialPages);



        //不知道为什么第一次刷新页面的时候界面会出现一些问题，需要多次刷新，所以这每当访问主页面时我刷新2次
//        if(reFreshTime<=2){
//
//            ++reFreshTime;
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return "redirect:/";
//        }else {
//            reFreshTime = 0;
//        }

        return "index";
    }

}
