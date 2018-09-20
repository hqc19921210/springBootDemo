package com.heqichao.springBootDemo.base.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by heqichao on 2018-2-20.
 */
@Controller
public class IndexController extends BaseController {

    @GetMapping("/")
    String index() {
        return "/index.html";
    }
    @GetMapping("/liang")
    String liang() {
    	return "/liang.html";
    }

}
