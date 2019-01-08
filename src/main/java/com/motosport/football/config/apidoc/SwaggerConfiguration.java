package com.motosport.football.config.apidoc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.DispatcherServlet;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.Servlet;
import java.nio.ByteBuffer;
import java.util.*;

@Configuration
@EnableSwagger2
@ConditionalOnWebApplication
@ConditionalOnClass({ApiInfo.class, Servlet.class, DispatcherServlet.class})
public class SwaggerConfiguration {

    public static final String STARTING_MESSAGE = "Starting Swagger";
    public static final String STARTED_MESSAGE = "Started Swagger in {} ms";
    public static final String MANAGEMENT_TITLE_SUFFIX = "Management API";
    public static final String MANAGEMENT_GROUP_NAME = "management";
    public static final String MANAGEMENT_DESCRIPTION = "Management endpoints documentation";

    private final Logger log = LoggerFactory.getLogger(SwaggerConfiguration.class);

    @Bean
    @ConditionalOnMissingBean(
            name = {"swaggerSpringfoxApiDocket"}
    )
    public Docket swaggerSpringfoxApiDocket(List<SwaggerCustomizer> swaggerCustomizers, ObjectProvider<AlternateTypeRule[]> alternateTypeRules) {
        log.debug(STARTING_MESSAGE);
        StopWatch watch = new StopWatch();
        watch.start();
        Docket docket = this.createDocket();
        swaggerCustomizers.forEach((customizer) -> customizer.customize(docket));
        Optional.ofNullable(alternateTypeRules.getIfAvailable()).ifPresent(docket::alternateTypeRules);
        watch.stop();
        log.debug(STARTED_MESSAGE, watch.getTotalTimeMillis());
        return docket;
    }

    @Bean
    public SwaggerCustomizer swaggerCustomizer() {
        return new SwaggerCustomizer();
    }

    @Bean
    @ConditionalOnClass(
            name = {"org.springframework.boot.actuate.autoconfigure.ManagementServerProperties"}
    )
    @ConditionalOnProperty({"management.endpoints.web.base-path"})
    @ConditionalOnExpression("'${management.endpoints.web.base-path}'.length() > 0")
    @ConditionalOnMissingBean(
            name = {"swaggerSpringfoxManagementDocket"}
    )
    public Docket swaggerSpringfoxManagementDocket(@Value("${spring.application.name:application}") String appName,
                                                   @Value("${management.endpoints.web.base-path}") String managementContextPath) {
        ApiInfo apiInfo = new ApiInfo(
                StringUtils.capitalize(appName) + " " + MANAGEMENT_TITLE_SUFFIX,
                MANAGEMENT_DESCRIPTION,
                "v1",
                "",
                ApiInfo.DEFAULT_CONTACT,
                "",
                "",
                new ArrayList<>()
        );
        return this.createDocket()
                .apiInfo(apiInfo)
                .useDefaultResponseMessages(true)
                .groupName(MANAGEMENT_GROUP_NAME)
                .host("localhost")
                .protocols(new HashSet<>(Collections.singletonList("http")))
                .forCodeGeneration(true)
                .directModelSubstitute(ByteBuffer.class, String.class)
                .genericModelSubstitutes(new Class[]{ResponseEntity.class})
                .select()
                .paths(PathSelectors.regex(managementContextPath + ".*"))
                .build();
    }

    protected Docket createDocket() {
        return new Docket(DocumentationType.SWAGGER_2);
    }
}