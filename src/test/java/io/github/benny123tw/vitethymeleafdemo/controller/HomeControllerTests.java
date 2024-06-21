package io.github.benny123tw.vitethymeleafdemo.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HomeControllerTests {

    private final HomeController homeController;

    @Autowired
    public HomeControllerTests(HomeController homeController) {
        this.homeController = homeController;
    }

    @Test
    void testHomeController() {
        assertThat(homeController).isNotNull();
    }
}
