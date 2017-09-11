package xin.liuyiq.taotao.content.service;

import xin.liuyiq.taotao.pojo.EasyUIDataGridResult;
import xin.liuyiq.taotao.pojo.TaotaoResult;
import xin.liuyiq.taotao.pojo.TbContent;

import java.util.List;

/**
 * @Author: Liuyiq
 * @Description: 展示网站内容的Service.
 */
public interface ContentService {

    /**
     * 网站内容的查询.
     * @param categoryId 内容的id.
     * @param page 当前页.
     * @param rows 每页的数量.
     * @return EasyUIDataGridResult.
     */
    EasyUIDataGridResult getContentList(Long categoryId, int page, int rows);

    /**
     * 保存content.
     * @param content 封装content的pojo对象.
     * @return TaotaoResult.
     */
    TaotaoResult addContent(TbContent content);

    /**
     * 通过内容分类的id(categoryId),获取内容的集合.
     * @param categoryId categoryId.
     * @return TbContent的集合.
     */
    List<TbContent> getContentList(Long categoryId);
}
