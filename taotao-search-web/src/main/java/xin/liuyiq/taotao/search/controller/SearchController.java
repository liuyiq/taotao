package xin.liuyiq.taotao.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xin.liuyiq.taotao.pojo.SearchResult;
import xin.liuyiq.taotao.search.service.SearchService;
/**
 * @Author: Liuyiq
 * @Description: 搜索商品的Controller.
 */
@Controller
@SuppressWarnings("all")
public class SearchController {
    @Value("${ITEM_ROWS}")
    private Integer ITEM_ROWS;
    @Autowired
    private SearchService searchService;

    @RequestMapping("/search")
    public String search(Model model, @RequestParam("q") String queryString, @RequestParam(defaultValue = "1") Integer page) throws Exception {

        queryString = new String(queryString.getBytes("iso8859-1"),"utf-8");
        SearchResult searchResult = searchService.search(queryString, page, ITEM_ROWS);
        model.addAttribute("query",queryString);
        model.addAttribute("totalPages",searchResult.getPageCount());
        model.addAttribute("itemList",searchResult.getItemList());
        model.addAttribute("page",page);
        return "search";
    }
}
