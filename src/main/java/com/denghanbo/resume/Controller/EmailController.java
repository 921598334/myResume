package com.denghanbo.resume.Controller;



import com.denghanbo.resume.eunm.PageType;
import com.denghanbo.resume.model.Message;
import com.denghanbo.resume.model.Page;
import com.denghanbo.resume.repository.MessageRepository;
import com.denghanbo.resume.repository.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
//对联系我对处理
public class EmailController {


    @Autowired
    MessageRepository messageRepository;

    @Autowired
    PageRepository pageRepository;

    @PostMapping("/contact")
    public String contact(@RequestParam(name="name") String name,
                          @RequestParam(name="email") String email,
                          @RequestParam(name="subject") String subject,
                          @RequestParam(name="message") String message,
                          Model model,
                          HttpServletResponse response,
                          HttpServletRequest request)

    {

        Message messageObject = new Message();
        messageObject.setEmail(email);
        messageObject.setMessage(message);
        messageObject.setName(name);
        messageObject.setSubject(subject);
        messageRepository.save(messageObject);


        model.addAttribute("info","成功发送");

        request.getSession().setAttribute("info","发送成功");

        return "redirect:/";

    }
}
