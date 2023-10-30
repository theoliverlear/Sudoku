package org.theoliverlear.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class SudokuController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
