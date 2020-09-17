package fr.insy2s.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * VariableDePaieResource controller
 */
@RestController
@RequestMapping("/api/variable-de-paie-resource")
public class VariableDePaieResource {

    private final Logger log = LoggerFactory.getLogger(VariableDePaieResource.class);

    /**
    * GET defaultAction
    */
    @GetMapping("/default-action")
    public String defaultAction() {
        return "defaultAction";
    }

}
