package xin.liuyiq.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.liuyiq.taotao.pojo.TaotaoResult;
import xin.liuyiq.taotao.search.service.SearchItemService;

/**
 * @Author: Liuyiq
 * @Description: 导入商品到索引库的Controller
 */
@RestController
@SuppressWarnings("all")
public class IndexManagerController {

    /**
     * 注入searchItemService.
     */
    @Autowired
    private SearchItemService searchItemService;

    /**
     * 导入方法.
     * @return TaotaoResult.
     * @throws Exception
     */
    @RequestMapping("/index/import")
    public TaotaoResult ImportAllItemTOIndex() throws Exception {
        TaotaoResult taotaoResult = searchItemService.ImportAllItemTOIndex();
        return taotaoResult;
    }

}
