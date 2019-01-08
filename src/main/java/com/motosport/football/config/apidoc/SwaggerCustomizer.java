package com.motosport.football.config.apidoc;

import org.springframework.core.Ordered;
import org.springframework.http.ResponseEntity;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spring.web.plugins.Docket;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class SwaggerCustomizer implements Ordered {

    private int order = 0;

    public void customize(Docket docket) {
        Contact contact = new Contact(
                "contact name",
                "contact URL",
                "contact email"
        );
        ApiInfo apiInfo = new ApiInfo(
                "moto title",
                "moto description",
                "moto version",
                "moto termsOfService",

                contact,
                "moto license",
                "moto url",
                new ArrayList()
        );
        docket.host("localhost")
                .protocols(new HashSet(Collections.singletonList("http")))
                .apiInfo(apiInfo)
                .useDefaultResponseMessages(true)
                .forCodeGeneration(true)
                .directModelSubstitute(ByteBuffer.class, String.class)
                .genericModelSubstitutes(new Class[]{ResponseEntity.class})
                .select()
                .build();
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getOrder() {
        return this.order;
    }
}
