package xin.liuyiq.taotao.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: Liuyiq
 * @Description: 前台页面跳转的Controller.
 */
@Controller
public class IndexController {

    /**
     * 跳转到前台页面首页.
     * @return index
     */
    @RequestMapping("/index")
    public String ShowIndex() {
        return "index";
    }
}
