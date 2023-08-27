package org.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/i")
public class HealthController {

    @RequestMapping("/health")
    public String health() {
        return "this is health";
    }
}
