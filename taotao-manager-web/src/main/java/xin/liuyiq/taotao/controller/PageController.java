package xin.liuyiq.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: Liuyiq
 * @Description: 页面跳转用的controllor
 */
@Controller
public class PageController {

    /**
     * 跳转到主页面.
     * @return index
     */
    @RequestMapping("/")
    public String showIndex() {
        return "index";
    }

    /**
     * 跳转到其他页面.
     * @param page 页面传递的参数,是要跳转的jsp页面的名称
     * @return page 找到page.jsp页面进行跳转
     */
    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page) {
        return page;
    }
}
