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

import java.util.List;

//这个是主界面
@Controller
public class IndexController {

    @Autowired
    PageRepository pageRepository;

    @GetMapping("/")
    public String index(Model model)
    {
        //从数据库得到教育背景相关的
        List<Page> backPages = pageRepository.findByType(PageType.BACK.getType());
        model.addAttribute("backPages",backPages);


        //从数据库中得到项目相关的文章并且显示
        List<Page> projectPages = pageRepository.findByType(PageType.PROJECT.getType());
        model.addAttribute("projectPagess",projectPages);


        //从数据库中得社会活动相关的文章并且显示
        List<Page> socialPages = pageRepository.findByType(PageType.SOCIRYACTIVE.getType());
        model.addAttribute("socialPages",socialPages);



        return "index";
    }

}
