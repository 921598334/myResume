package com.denghanbo.resume.Controller;

import com.denghanbo.resume.model.Page;
import com.denghanbo.resume.provider.FileUpload;
import com.denghanbo.resume.repository.PageRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


//发布文章相关,这个是我操作的，不对外开放，需要登陆权限才可以用
@Controller
public class PublishController {


    @Autowired
    PageRepository pageRepository;

    @Autowired
    FileUpload fileUpload;


    //是get请求时渲染页面
    @GetMapping("/publish")
    public String greeting(Model model){

        return "publish";
    }



    //用户点击修改时,需要把数据都显示在界面上
    @GetMapping("/publish/{id}")
    public String modified(@PathVariable(name = "id") Integer id,
                         Model model,
                         HttpServletRequest request
    ){

        Optional<Page> pages = pageRepository.findById(id);

        Page page = pages.get();


        //id是隐藏属性，自由更新页面都时候才有
        model.addAttribute("id",page.id);
        model.addAttribute("title",page.title);
        model.addAttribute("description",page.descript);
        model.addAttribute("content",page.content);
        model.addAttribute("type",page.type);
        model.addAttribute("imag",page.imag);



//        //标签
//        model.addAttribute("tags",TagCache.get());
//
//        //根据id从数据库中得到文章
//        Question question = questionMapper.getById(id);
//
//        //这个是一个隐藏属性
//        model.addAttribute("id",question.getId());
//        //把用户输入的信息先缓存起来,如果用户有什么没有输入，那重新回到这个页面的时候可以保存
//        model.addAttribute("title",question.getTitle());
//        model.addAttribute("description",question.getDescription());
//        model.addAttribute("tag",question.getTag());
//
//        //在进入该页面时，拦截器会首先进行判断，如果有用户了，用户信息会被放在session中
//        User user = (User)request.getSession().getAttribute("user");
//
//        //如果用户没有登陆，就返回
//        if(user == null){
//            model.addAttribute("error","用户名没有登陆");
//            return "publish";
//        }








        return "publish";
    }




    //用户发帖时的post请求时，添加或者更新问题到数据库
    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(name="title") String title,
            @RequestParam(name="description") String description,
            @RequestParam(name="type") Integer type,
            @RequestParam(name="content") String content,
            @RequestParam(name="file") MultipartFile imag,
            @RequestParam(name="id",defaultValue = "0")  Integer id,
            @RequestParam(name="filePath",defaultValue = "")  String filePath,

            Model model
    ){

        //把用户输入的信息先缓存起来,如果用户有什么没有输入，那重新回到这个页面的时候可以保存
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("type",type);
        model.addAttribute("content",content);
        model.addAttribute("file",imag);
        model.addAttribute("id",id);
        //如果是创建，filepath应该是不存在的，更新的时候才存在
        model.addAttribute("filePath",filePath);





        //验证用户输入的是不是为空
        if(title==null || title == ""){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }

        if(description==null || description == ""){
            model.addAttribute("error","描述不能为空");
            return "publish";
        }

        if(type==null){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }

        if(content==null || content == ""){
            model.addAttribute("error","内容不能为空");
            return "publish";
        }

        if(imag==null ){
            model.addAttribute("error","图像不能为空");
            return "publish";
        }





//        //在进入该页面时，拦截器会首先进行判断，如果有用户了，用户信息会被放在session中
//        User user = (User)request.getSession().getAttribute("user");
//
//        //如果用户没有登陆，就返回
//        if(user == null){
//            model.addAttribute("error","用户名没有登陆");
//            return "publish";
//        }
//
//        //存在用户时就把用户的提问添加到数据库中,然后返回首页
//        Long time = System.currentTimeMillis();
//
//        Question question = new Question(title,description,tag,time,time,user.getId(),0,0,0);
//        //表明该用户存在
//        if(id!=null && id!=0){
//            question.setId(id);
//        }
//
//
//        questionService.createOrUpdate(question);



        String profilePath = null;


        //根据id得到老的文章
        Optional<Page> oldPages = pageRepository.findById(id);
        Page oldPage = oldPages.get();

        //如果现在的文件如今与之前的一样，那就不更新图片
        if(oldPage.getImag().equals(filePath)){
            profilePath = oldPage.getImag();
        }else {
            //否则要重新上传新图片
            profilePath = fileUpload.uploadImage(imag);
        }



        Page page = new Page();
        page.setContent(content);
        page.setDescript(description);
        page.setTitle(title);
        page.setType(type);
        page.setImag(profilePath);



        if(id == 0){
            //如果是0表示是第一次创建，如果有其他值表示这是更新

        }else {

            //表示之后的更新操作
            page.setId(id);
        }

        pageRepository.save(page);



        return  "redirect:/";
    }

}