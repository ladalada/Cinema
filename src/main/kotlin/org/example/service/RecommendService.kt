package org.example.service

import org.example.entity.Services
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RecommendService {

    @Autowired
    private val cinemaService: CinemaService? = null

    fun getRecommendServicesByServices(genresServicesMap: MutableMap<String, MutableList<Services>>): MutableList<Services> {
        var recommend = mutableListOf<Services>()

        for (genreServices in genresServicesMap) {
            var servicesByGenre = mutableListOf<Services>()
            servicesByGenre = cinemaService?.getServicesByGenre(genreServices.key)!!

            for (services in genreServices.value) {
                servicesByGenre.remove(services)
            }

            for (recommendServices in servicesByGenre) {
                recommend.add(recommendServices)
            }

        }
        return recommend
    }

    fun getRecommendServicesMapByMovies(genresMoviesMap: MutableMap<String, MutableList<String>>): MutableMap<String, MutableList<Services>> {
        var recommend = mutableMapOf<String, MutableList<Services>>()

        for (genreMovies in genresMoviesMap) {
            var servicesByGenre: MutableList<Services> = cinemaService?.getServicesByGenre(genreMovies.key)!!

            for (movieName in genreMovies.value) {
                if (servicesByGenre != null) {
                    for (servicesName in servicesByGenre) {
                        if (movieName == servicesName.name) {
                            servicesByGenre.remove(servicesName)
                        }
                    }

                }
            }

            if (servicesByGenre != null) {
                recommend[genreMovies.key] = servicesByGenre
            }

        }
        return recommend
    }

}