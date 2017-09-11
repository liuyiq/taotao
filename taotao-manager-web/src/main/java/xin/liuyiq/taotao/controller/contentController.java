package xin.liuyiq.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.liuyiq.taotao.content.service.ContentService;
import xin.liuyiq.taotao.pojo.EasyUIDataGridResult;
import xin.liuyiq.taotao.pojo.TaotaoResult;
import xin.liuyiq.taotao.pojo.TbContent;


/**
 * @Author: Liuyiq
 * @Description: 内容管理的Controller.
 */
@RestController
@RequestMapping("/content")
@SuppressWarnings("all")
public class contentController {

    @Autowired
    private ContentService contentService;

    @RequestMapping("/query/list")
    public EasyUIDataGridResult getContentList(Long categoryId, int page, int rows) {
        EasyUIDataGridResult result = contentService.getContentList(categoryId, page, rows);
        return result;
    }

    @RequestMapping("/save")
    public TaotaoResult saveContent(TbContent content) {
        TaotaoResult result = contentService.addContent(content);
        return result;
    }
}
