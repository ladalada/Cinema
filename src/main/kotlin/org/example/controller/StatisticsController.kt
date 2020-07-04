package org.example.controller

import org.example.service.StatisticsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class StatisticsController {

    @Autowired
    private var statisticsService: StatisticsService? = null

    @GetMapping("/statistics")
    fun statisticsList(model: Model): String {
        model.addAttribute("orders", statisticsService!!.getAllOrders())
        var allUsersGenresMovies = statisticsService!!.getAllUsersGenresMovies()
        model.addAttribute("genresMovies", allUsersGenresMovies)
        model.addAttribute("statUsersCount", statisticsService!!.getUsersCount(allUsersGenresMovies))
        model.addAttribute("statUsersGenresMoviesCount", statisticsService!!.getUsersGenresMoviesCount(allUsersGenresMovies))
//        model.addAttribute("genres", statisticsService!!.getOrdersGenres())
        return "statistics"
    }

    @GetMapping("/mystatistics")
    fun getUserOrdersList(@RequestParam(required = true, defaultValue = "") userName: String?,
                          model: Model?): String {
        model!!.addAttribute("orders", statisticsService!!.getOrdersByUserName(userName!!))
        model.addAttribute("genres", statisticsService!!.getOrdersGenresByUserName(userName))
        model.addAttribute("movies", statisticsService!!.getOrdersMoviesByUserName(userName))
        model.addAttribute("genresMovies", statisticsService!!.getOrdersGenresMoviesByUserName(userName))
        return "statistics"
    }

}