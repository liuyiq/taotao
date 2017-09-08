package xin.liuyiq.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xin.liuyiq.taotao.pojo.EasyUITreeNode;
import xin.liuyiq.taotao.service.ItemCatService;

import java.util.List;

/**
 * @Author: Liuyiq
 * @Description: 商品信息查询.
 */
@RestController
@RequestMapping("/item/cat")
@SuppressWarnings("all")
public class ItemCatController {
    /**
     * 注入ItemCatService.
     */
    @Autowired
    private ItemCatService itemCatService;

    /**
     * @param parentId 父节点的id
     * @return 将封装easyUITreeNode对象的集合转换成json格式,最终返回给页面
     */
    @RequestMapping("/list")
    public List<EasyUITreeNode> getCatList(@RequestParam(value="id", defaultValue = "0") Long parentId) {
        List<EasyUITreeNode> treeNodes = itemCatService.getCatList(parentId);
        return treeNodes;
    }
}
