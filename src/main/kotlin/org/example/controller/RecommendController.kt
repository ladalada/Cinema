package org.example.controller

import org.example.entity.Services
import org.example.service.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class RecommendController {

    @Autowired
    private var statisticsService: StatisticsService? = null

    @Autowired
    private var recommendService: RecommendService? = null

    @Autowired
    private var cinemaService: CinemaService? = null

    @Autowired
    var basketService: BasketService? = null

    @Autowired
    var userService: UserService? = null

    @GetMapping("/recommend")
    fun getRecommend(@RequestParam(required = true, defaultValue = "") userName: String?,
                     model: Model?): String {
        var genresMoviesMap: MutableMap<String, MutableList<String>> = statisticsService!!.getOrdersGenresMoviesByUserName(userName!!)
        var genresServicesMap: MutableMap<String, MutableList<Services>> = cinemaService!!.getServicesByGenresMoviesMap(genresMoviesMap)
//        model?.addAttribute("recommend", recommendService!!.getRecommendServicesMapByMovies(genresMoviesMap))
        model?.addAttribute("recommend", recommendService!!.getRecommendServicesByServices(genresServicesMap))
        return "recommend"
    }

    @GetMapping("to_basketFromRecommend")
    fun addProductToBasketFromRecommend(@RequestParam(required = true, defaultValue = "") serviceId: Long?, userName: String, model: Model): String {
        val user = userService!!.findUserByName(userName!!)
        val product = cinemaService!!.getService(serviceId)
        basketService!!.addProduct(product, user!!)

        var genresMoviesMap: MutableMap<String, MutableList<String>> = statisticsService!!.getOrdersGenresMoviesByUserName(userName!!)
        var genresServicesMap: MutableMap<String, MutableList<Services>> = cinemaService!!.getServicesByGenresMoviesMap(genresMoviesMap)
//        model?.addAttribute("recommend", recommendService!!.getRecommendServicesMapByMovies(genresMoviesMap))
        model?.addAttribute("recommend", recommendService!!.getRecommendServicesByServices(genresServicesMap))
        return "recommend"
    }

}