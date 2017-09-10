package xin.liuyiq.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.liuyiq.taotao.pojo.EasyUIDataGridResult;
import xin.liuyiq.taotao.pojo.TaotaoResult;
import xin.liuyiq.taotao.pojo.TbItem;
import xin.liuyiq.taotao.service.ItemService;

/**
 * @Author: Liuyiq
 * @Description: item的Controller.
 */
@RestController
@RequestMapping("/item")
@SuppressWarnings("all")
public class ItemController {

    /**
     * 注入itemService接口.
     */
    @Autowired
    private ItemService itemService;

    /**
     * 分页查询,将获取到的信息封装到EasyUIDataGridResult对象中.
     * @param page 当前页
     * @param rows 每页显示的数量
     * @return result 分页查询的结果
     */
    @RequestMapping("/list")
    public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
        EasyUIDataGridResult result = itemService.getItemList(page, rows);
        return result;
    }

    /**
     * 添加商品信息的接口.
     * @param tbItem tbItem封装页面传递的商品参数.
     * @param desc   desc接收商品描述信息.
     * @return 自定义响应结构.
     */
    @RequestMapping("/save")
    public TaotaoResult addItem(TbItem tbItem, String desc) {
        TaotaoResult taotaoResult = itemService.addItem(tbItem, desc);
        return taotaoResult;
    }
}
