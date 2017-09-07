package xin.liuyiq.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.liuyiq.taotao.pojo.EasyUIDataGridResult;
import xin.liuyiq.taotao.service.ItemService;

/**
 * @Author: Liuyiq
 * @Description: item的Controller.
 */
@RestController
@RequestMapping("/item")
@SuppressWarnings("all")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/list")
    public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
        EasyUIDataGridResult result = itemService.getItemList(page, rows);
        return result;
    }
}
