package com.akirolabs.hometask.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

/**
 * The Class CorsConfig.
 */
@Configuration
public class CorsConfig {

    /** The allowed origins. */
    @Value(value = "${app.access-control.allow-origin}")
    private String[] allowedOrigins;

    /** The allowed methods. */
    @Value(value = "${app.access-control.allow-methods}")
    private String[] allowedMethods;

    @Value(value = "${app.access-control.allow-headers}")
    private String[] allowedHeaders;

    /**
     * Cors filter.
     *
     * @return the cors filter
     */
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList(allowedOrigins));
        config.setAllowedMethods(Arrays.asList(allowedMethods));
        final List<String> exposeHeaders = Arrays.asList("Content-disposition");
        config.setAllowedHeaders(Arrays.asList(allowedHeaders));
        config.setExposedHeaders(exposeHeaders);

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
