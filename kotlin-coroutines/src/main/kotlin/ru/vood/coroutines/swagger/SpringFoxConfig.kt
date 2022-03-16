package ru.vood.coroutines.swagger

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter


//@Configuration
//@EnableSwagger2
class SpringFoxConfig : WebMvcConfigurerAdapter() {
    /*   @Bean
       fun api(): Docket = Docket(DocumentationType.SWAGGER_2)
           .select()
           .apis(RequestHandlerSelectors.any())
           .paths(PathSelectors.any())
           .build()*/

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        //enabling swagger-ui part for visual documentation
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/")
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/")
    }

}