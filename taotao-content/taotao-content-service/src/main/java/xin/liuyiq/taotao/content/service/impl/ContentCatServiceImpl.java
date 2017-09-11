package xin.liuyiq.taotao.content.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.liuyiq.taotao.content.service.ContentCatService;
import xin.liuyiq.taotao.mapper.TbContentCategoryMapper;
import xin.liuyiq.taotao.pojo.EasyUITreeNode;
import xin.liuyiq.taotao.pojo.TaotaoResult;
import xin.liuyiq.taotao.pojo.TbContentCategory;
import xin.liuyiq.taotao.pojo.TbContentCategoryExample;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: Liuyiq
 * @Description: 内容分类接口的实现类.
 */
@Service
@SuppressWarnings("all")
public class ContentCatServiceImpl implements ContentCatService {

    /**
     * 注入TbContentCategoryMapper.
     */
    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;

    /**
     * 获取内容分类列表的方法.
     *
     * @param parentId 父节点的id.
     * @return 返回封装了EasyUITreeNode的一个集合.
     */
    @Override
    public List<EasyUITreeNode> getContentCatList(Long parentId) {

        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> contentCategoryList =
                contentCategoryMapper.selectByExample(example);

        List<EasyUITreeNode> treeNodeList = new ArrayList<EasyUITreeNode>();
        for (TbContentCategory tbContentCategory : contentCategoryList) {
            EasyUITreeNode treeNode = new EasyUITreeNode();
            treeNode.setId(tbContentCategory.getId());
            treeNode.setText(tbContentCategory.getName());
            if (tbContentCategory.getIsParent()) {
                treeNode.setState("closed");
            } else {
                treeNode.setState("open");
            }
            treeNodeList.add(treeNode);
        }
        return treeNodeList;
    }

    /**
     * 新增节点的方法.
     * @param parentId 父节点的id.
     * @param name     新增节点的名称.
     * @return TaotaoResult.
     */
    @Override
    public TaotaoResult addContentCat(Long parentId, String name) {

        TbContentCategory contentCategory = new TbContentCategory();
        contentCategory.setParentId(parentId);
        // 该类目是否为父类目，1为true，0为false
        contentCategory.setIsParent(false);
        contentCategory.setName(name);
        // 排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。
        // 取值范围:大于零的整数
        contentCategory.setSortOrder(1);
        // 状态。可选值:1(正常),2(删除)
        contentCategory.setStatus(1);
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(new Date());
        contentCategoryMapper.insert(contentCategory);
        TbContentCategory parentContentCategory = contentCategoryMapper.selectByPrimaryKey(parentId);
        if(!parentContentCategory.getIsParent()) {
            parentContentCategory.setIsParent(true);
            contentCategoryMapper.updateByPrimaryKey(parentContentCategory);
        }
        return TaotaoResult.ok(contentCategory);
    }
}
