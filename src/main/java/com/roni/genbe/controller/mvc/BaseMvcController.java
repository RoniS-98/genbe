package com.roni.genbe.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class BaseMvcController {

    @GetMapping("/biodata")
    public String biodata() {
        return "dashboard/biodata";
    }

    @GetMapping("/pendidikan")
    public String pendidikan() {
        return "dashboard/pendidikan";
    }

    @GetMapping("/tabelperson")
    public String tabelperson() {
        return "dashboard/tabelPerson";
    }

    @GetMapping("/formModals")
    public String formModals() {
        return "dashboard/formModals";
    }
}
