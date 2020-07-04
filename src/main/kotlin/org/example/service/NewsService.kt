package org.example.service

import org.example.entity.News
import org.example.repository.NewsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class NewsService {

    @Autowired
    var newsRepository: NewsRepository

    @Autowired
    constructor(newsRepository: NewsRepository){
        this.newsRepository = newsRepository
    }

    fun getAllNews(): MutableList<News?>? {
        return this.newsRepository.findAll()
    }

    fun addNews(news: News){
        newsRepository.save(news)
    }
}