package ru.bellintegrator.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import ru.bellintegrator.practice.dictionaries.controller.impl.CountryCodeControllerImpl;
import ru.bellintegrator.practice.dictionaries.controller.impl.DocTypeControllerImpl;
import ru.bellintegrator.practice.dictionaries.dao.CountryCodeDAO;
import ru.bellintegrator.practice.dictionaries.dao.impl.CountryCodeDAOImpl;
import ru.bellintegrator.practice.dictionaries.dao.impl.DocTypeDAOImpl;
import ru.bellintegrator.practice.dictionaries.service.impl.CountryCodeServiceImpl;
import ru.bellintegrator.practice.dictionaries.service.impl.DocTypeServiceImpl;
import ru.bellintegrator.practice.offices.controller.impl.OfficeControllerImpl;
import ru.bellintegrator.practice.offices.dao.impl.OfficeDAOImpl;
import ru.bellintegrator.practice.offices.service.OfficeService;
import ru.bellintegrator.practice.offices.service.impl.OfficeServiceImpl;
import ru.bellintegrator.practice.organizations.controller.impl.OrganizationControllerImpl;
import ru.bellintegrator.practice.organizations.dao.impl.OrganizationDAOImpl;
import ru.bellintegrator.practice.organizations.service.impl.OrganizationServiceImpl;
import ru.bellintegrator.practice.users.controller.impl.UserControllerImpl;
import ru.bellintegrator.practice.users.dao.impl.UserDAOImpl;
import ru.bellintegrator.practice.users.service.impl.UserServiceImpl;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Locale;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@ImportResource("spring_mvc_config.xml")
@SpringBootApplication
@ComponentScan(basePackageClasses = {CountryCodeControllerImpl.class, CountryCodeServiceImpl.class, CountryCodeDAOImpl.class,
        DocTypeControllerImpl.class, DocTypeServiceImpl.class, DocTypeDAOImpl.class, OfficeServiceImpl.class, OfficeControllerImpl.class,
        OfficeDAOImpl.class, OrganizationControllerImpl.class, OrganizationDAOImpl.class, OrganizationServiceImpl.class,
        UserDAOImpl.class, UserServiceImpl.class, UserControllerImpl.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.run(args);
    }

    @Bean
    public TaskExecutor controllerPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(Runtime.getRuntime().availableProcessors() + 1);
        executor.setQueueCapacity(25);
        return executor;
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.US);
        return sessionLocaleResolver;
    }

    @Bean
    public Docket postApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("persons")
                .apiInfo(apiInfo())
                .select()
                .paths(regex("/person.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring REST Sample with Swagger")
                .description("Spring REST Sample with Swagger")
                .contact("https://github.com/azEsm/empty_project")
                .version("1.0")
                .build();
    }
}