package com.mobiquityinc.controller;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HomeControllerTest {

    @Test
    void happyDay() {
        HomeController homeController = new HomeController();
        assertEquals("redirect:swagger-ui.html", homeController.home());
    }

}
