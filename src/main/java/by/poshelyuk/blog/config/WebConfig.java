package by.poshelyuk.blog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan("by.poshelyuk.blog")
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
}
