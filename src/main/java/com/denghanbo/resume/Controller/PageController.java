package com.denghanbo.resume.Controller;


import com.denghanbo.resume.model.Page;
import com.denghanbo.resume.repository.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

//当需要看文章细节时就用这个类来做
@Controller
public class PageController {

    @Autowired
    PageRepository pageRepository;







    //根据文章的路径返回文章的内容
    @GetMapping("/page")
    public String showPages(Model model,@RequestParam(name="path") Integer pathId){


        //根据路径得到要的数据
       Optional<Page> pages = pageRepository.findById(pathId);


       Page page = pages.get();

        model.addAttribute("page",page);

        return "detail";
    }








}
