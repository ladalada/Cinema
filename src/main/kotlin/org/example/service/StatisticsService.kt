package org.example.service

import org.example.entity.Order
import org.example.entity.User
import org.example.repository.OrderRepository
import org.example.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class StatisticsService {

    @Autowired
    private val orderRepository: OrderRepository?=null

    @Autowired
    private val userRepository: UserRepository?=null

    fun getAllOrders(): MutableList<Order?>? {
        return orderRepository!!.findAll()
    }

    fun getOrdersByUserName(userName: String): MutableList<Order>? {
        val userId = userRepository!!.findByUsername(userName)!!.id
        return orderRepository!!.findAllByUserId(userId)
    }

    fun getUsersCount(allUsersGenresMovies: MutableMap<User, MutableMap<String, MutableList<String>>>): Int {
        return allUsersGenresMovies.size
    }

    fun getUsersGenresMoviesCount(allUsersGenresMovies: MutableMap<User, MutableMap<String, MutableList<String>>>):
        MutableMap<User, MutableMap<String, Int>> {
        var usersGenresMoviesCount = mutableMapOf<User, MutableMap<String, Int>>()
        for (map in allUsersGenresMovies) {
            var oldValue = map.value
            var newValue = mutableMapOf<String, Int>()
            for (g in oldValue) {
                newValue[g.key] = g.value.size
            }
            usersGenresMoviesCount[map.key] = newValue
        }
        return usersGenresMoviesCount
    }

    fun getAllUsersGenresMovies(): MutableMap<User, MutableMap<String, MutableList<String>>> {
        val orders = orderRepository!!.findAll()
        val usersWithOrders = mutableListOf<User>()
        var allUsersGenresMovies = mutableMapOf<User, MutableMap<String, MutableList<String>>>()

        if (orders != null) {
            for (ordersItem in orders) {
                if (ordersItem != null) {
                    var userWithOrder = ordersItem.getUser()
                    if (userWithOrder !in usersWithOrders) {
                        usersWithOrders.add(userWithOrder)
                    }
                }
            }
            for (userWithOrder in usersWithOrders) {
                var currentUsername = userWithOrder.getUsername()
                var usersGenresMovies = getOrdersGenresMoviesByUserName(currentUsername)
                allUsersGenresMovies[userWithOrder] = usersGenresMovies
            }
        }
        return allUsersGenresMovies
    }

    fun getOrdersGenresMoviesByUserName(userName: String): MutableMap<String, MutableList<String>> {
        val userId = userRepository!!.findByUsername(userName)!!.id
        val orders = orderRepository!!.findAllByUserId(userId)
        var genresMoviesMutMap = mutableMapOf<String, MutableList<String>>()

        if (orders != null) {
            for (ordersItem in orders) {
                var ordersServices = ordersItem.getService()

                for (orderServicesItem in ordersServices) {
                    var servicesGenre = orderServicesItem.getServiceGenre()
                    var servicesName = orderServicesItem.getServiceName()
                    var servicesNamesArray: MutableList<String>

                    if (servicesGenre !in genresMoviesMutMap) {
                        servicesNamesArray = mutableListOf(servicesName)
                        genresMoviesMutMap[servicesGenre] = servicesNamesArray
                    } else {
                        var currentNames = genresMoviesMutMap[servicesGenre]!!
                        if (servicesName != null) {
                            if (servicesName !in currentNames) {
                                currentNames!!.add(servicesName)
                                genresMoviesMutMap[servicesGenre] = currentNames
                            }
                        }
                    }
                }
            }
        }

        return genresMoviesMutMap
    }

    fun getOrdersMoviesByUserName(userName: String): MutableMap<String, String> {
        val userId = userRepository!!.findByUsername(userName)!!.id
        val orders = orderRepository!!.findAllByUserId(userId)

        var ordersMoviesList = mutableMapOf<String, String>()

        if (orders != null) {
            for (ordersItem in orders) {
                var ordersServices = ordersItem.getService()
                for (orderServicesItem in ordersServices) {
                    var servicesNameAndGenre = orderServicesItem.getServiceNameAndGenre()
                    ordersMoviesList[servicesNameAndGenre[0]] = servicesNameAndGenre[1]
                }
            }
        }
        return ordersMoviesList
    }

    fun getOrdersGenresByUserName(userName: String): MutableList<String>? {
        val userId = userRepository!!.findByUsername(userName)!!.id
        val orders = orderRepository!!.findAllByUserId(userId)

        var ordersGenresList = mutableListOf<String>()

        if (orders != null) {
            for (ordersItem in orders) {
                var ordersServices = ordersItem.getService()
                for (orderServicesItem in ordersServices) {
                    var servicesGenre = orderServicesItem.getServiceGenre()
                    if (servicesGenre !in ordersGenresList) {
                        ordersGenresList.add(servicesGenre)
                    }
                }
            }
        }

        return ordersGenresList
    }

    fun getOrder(id: Long): Optional<Order?>? {
        return orderRepository!!.findById(id)
    }

}