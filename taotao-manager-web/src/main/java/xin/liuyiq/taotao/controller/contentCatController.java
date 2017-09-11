package xin.liuyiq.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xin.liuyiq.taotao.content.service.ContentCatService;
import xin.liuyiq.taotao.pojo.EasyUITreeNode;
import xin.liuyiq.taotao.pojo.TaotaoResult;

import java.util.List;

/**
 * @Author: Liuyiq
 * @Description: tb_content_category, 内容类别.
 */
@RestController
@RequestMapping("/content/category")
@SuppressWarnings("all")
public class contentCatController {

    /**
     * 注入ContentCatService.
     */
    @Autowired
    private ContentCatService contentCatService;

    /**
     * 获取内容分类列表的方法.
     *
     * @param parentId 父节点的id.
     * @return 将封装easyUITreeNode对象的集合转换成json格式, 最终返回给页面.
     */
    @RequestMapping("/list")
    public List<EasyUITreeNode> getContentCatList(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
        List<EasyUITreeNode> easyUITreeNodes = contentCatService.getContentCatList(parentId);
        return easyUITreeNodes;
    }

    /**
     * 新增节点的方法.
     * @param parentId 父节点的id.
     * @param name 新增节点的名称.
     * @return TaotaoResult.
     */
    @RequestMapping("/create")
    public TaotaoResult addContentCat(Long parentId, String name) {
        TaotaoResult taotaoResult = contentCatService.addContentCat(parentId, name);
        return taotaoResult;
    }
}
