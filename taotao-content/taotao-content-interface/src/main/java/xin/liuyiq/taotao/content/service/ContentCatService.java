package xin.liuyiq.taotao.content.service;

import xin.liuyiq.taotao.pojo.EasyUITreeNode;
import xin.liuyiq.taotao.pojo.TaotaoResult;

import java.util.List;

/**
 * @Author: Liuyiq
 * @Description: ContentCatService,内容分类接口.
 */
public interface ContentCatService {

    /**
     * 获取内容分类列表的方法.
     * @param parentId 父节点的id.
     * @return 返回封装了EasyUITreeNode的一个集合.
     */
    List<EasyUITreeNode> getContentCatList(Long parentId);

    /**
     * 新增节点的方法.
     * @param parentId 父节点的id.
     * @param name 新增节点的名称.
     * @return TaotaoResult.
     */
    TaotaoResult addContentCat(Long parentId, String name);
}
