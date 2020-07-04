package org.example.controller

import org.example.entity.News
import org.example.service.NewsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import javax.validation.Valid

@Controller
class NewsController{

    @Autowired
    private var newsService: NewsService? = null

    @GetMapping("/news")
    fun newsList(model: Model): String {
        model.addAttribute("news", newsService!!.getAllNews())
        return "news"
    }

    @GetMapping("/add_news")
    fun addNews(model: Model): String{
        model.addAttribute("newNote", News())
        return "newnote"
    }

    @PostMapping("/addNews")
    fun addInformation(@Valid @ModelAttribute("news") note: News?, bindingResult: BindingResult): String{
        newsService!!.addNews(note!!)
        return "redirect:news"
    }

}