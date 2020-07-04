package org.example.service

import org.example.entity.Services
import org.example.repository.OrderRepository
import org.example.repository.ServiceRepository
import org.example.repository.ServicesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CinemaService {

    @Autowired
    var serviceRepository: ServiceRepository? = null

    @Autowired
    var servicesRepository: ServicesRepository? = null

    @Autowired
    var orderRepository: OrderRepository? = null


    fun getServices(): MutableList<Services?>? {
        return this.servicesRepository!!.findAll()
    }

    fun getServicesByGenre(genre: String): MutableList<Services> {
        var allMovies = servicesRepository!!.findAll()
        var moviesByGenre = mutableListOf<Services>()
        for (movie in allMovies) {
            if (movie != null) {
                if (movie.genre == genre) {
                    moviesByGenre.add(movie)
                }
            }
        }
        return moviesByGenre
    }

    fun getServicesByGenresMoviesMap(genresMoviesMap: MutableMap<String, MutableList<String>>): MutableMap<String, MutableList<Services>> {
        var genresServicesMap = mutableMapOf<String, MutableList<Services>>()

        for (genresMovies in genresMoviesMap) {
            var moviesServices = mutableListOf<Services>()

            for (movieName in genresMovies.value) {
                var servicesWithName: Services = getServicesByName(movieName)
                moviesServices.add(servicesWithName)
            }
            genresServicesMap[genresMovies.key] = moviesServices
        }
        return genresServicesMap
    }

    fun getServicesById(movieId: Long?): Optional<Services?> {
        return this.servicesRepository!!.findById(movieId)
    }

    fun getServicesByName(name: String): Services {
        var allServices = servicesRepository!!.findAll()
        var servicesByName = Services()
        for (services in allServices) {
            if (services != null) {
                if (services.name == name) {
                    servicesByName = services
                }
            }
        }
        return servicesByName
    }

    //добавление или обновление товара
    fun addService(newProduct: Services) {
        servicesRepository!!.save(newProduct)
    }

    fun getService(serviceId: Long?): Services {
        return this.servicesRepository!!.findById(serviceId).get()
    }

    //удаление фильма из каталога
    fun removeService(id: Long) {
        val serviceList = serviceRepository!!.findServiceListByServicesId(id)
        for(serviceItem in serviceList) {
            val orderMutList = orderRepository!!.findAllByServiceId(serviceItem!!.id)
            for(orderItem in orderMutList!!) {
                orderRepository!!.deleteById(orderItem.id)
            }
        }

        for (serviceItem in serviceList!!) {
            serviceRepository!!.deleteById(serviceItem!!.id)
        }

        servicesRepository!!.deleteById(id)
    }
}