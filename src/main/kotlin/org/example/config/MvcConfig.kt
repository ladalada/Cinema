package org.example.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.ViewResolver
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.resource.PathResourceResolver
import org.springframework.web.servlet.view.InternalResourceViewResolver

@Configuration
open class MvcConfig : WebMvcConfigurer {

    @Bean
    open fun getViewResolver(): ViewResolver? {
        val resolver = InternalResourceViewResolver()
        resolver.setPrefix("/jsp/")
        resolver.setSuffix(".jsp")
        return resolver
    }

    override fun configureDefaultServletHandling(
            configurer: DefaultServletHandlerConfigurer) {
        configurer.enable()
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/webapp/** ** ")
                .addResourceLocations("/webapp/").setCachePeriod(3600)
                .resourceChain(true).addResolver(PathResourceResolver())
    }

    //то, что доступно
    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addViewController("/login").setViewName("login")
        registry.addViewController("/news").setViewName("news")
    }
}