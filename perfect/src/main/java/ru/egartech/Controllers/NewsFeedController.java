package ru.egartech.Controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.egartech.Repository.CalendarPostRepository;


import ru.egartech.models.CalendarPost;
import ru.egartech.Repository.UserRepository;
import ru.egartech.models.User;

import java.util.ArrayList;
import java.util.List;

@Controller
public class NewsFeedController {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public CalendarPostRepository postRepository;

    @GetMapping("/news")
    public String news(Model model){
        List<CalendarPost> list_Calendar_post = postRepository.findAll();
        model.addAttribute("list_post", list_Calendar_post);
        return "news/news";
    }


    @PostMapping("/add-post")
    public String add_post(@Valid CalendarPost calendarPost){
        postRepository.save(calendarPost);
        return "news/news";
    }
    @GetMapping("/delete-post/{id}")
    public String delete_post(@PathVariable("id") long id){
        postRepository.deleteById(id);
        return "news/news";
    }
    @GetMapping("/edit-post/{id}")
    public String edit_post(@PathVariable("id") long id, Model model){
        CalendarPost calendarPost = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + id));;
        model.addAttribute("post", calendarPost);
        return "news/update-post";
    }
    @PostMapping("/update-post/{id}")
    public String update_post(@Valid CalendarPost calendarPost){
        postRepository.save(calendarPost);
        return "redirect:news/news";
    }
}
