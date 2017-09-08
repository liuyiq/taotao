package xin.liuyiq.taotao.service;

import xin.liuyiq.taotao.pojo.EasyUITreeNode;

import java.util.List;

/**
 * @Author: Liuyiq
 * @Description: 商品信息列表选择.
 */
public interface ItemCatService {

    /**
     * @param parentId 父节点的id
     * @return 返回封装easyUITreeNode对象的一个集合
     */
    List<EasyUITreeNode> getCatList(Long parentId);
}
