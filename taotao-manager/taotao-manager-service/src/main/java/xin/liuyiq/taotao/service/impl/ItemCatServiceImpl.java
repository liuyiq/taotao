package xin.liuyiq.taotao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.liuyiq.taotao.mapper.TbItemCatMapper;
import xin.liuyiq.taotao.pojo.EasyUITreeNode;
import xin.liuyiq.taotao.pojo.TbItemCat;
import xin.liuyiq.taotao.pojo.TbItemCatExample;
import xin.liuyiq.taotao.service.ItemCatService;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Liuyiq
 * @Description: ItemCatService接口实现类.
 */
@Service
@SuppressWarnings("all") // 一直了不能自动注入的警告,该工具的一个判定规则,但是不报错
public class ItemCatServiceImpl implements ItemCatService {

    /**
     * 注入TbItemCatMapper来实现商品信息查询.
     */
    @Autowired
    private TbItemCatMapper itemCatMapper;

    /**
     * @param parentId 父节点的id
     * @return 返回封装easyUITreeNode对象的一个集合
     */
    @Override
    public List<EasyUITreeNode> getCatList(Long parentId) {

        // 组装查询条件
        TbItemCatExample examlpe = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = examlpe.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        // 执行查询
        List<TbItemCat> itemCats = itemCatMapper.selectByExample(examlpe);

        ArrayList<EasyUITreeNode> treeNodes = new ArrayList<>();
        for (TbItemCat itemCat : itemCats) {
            EasyUITreeNode easyUITreeNode = new EasyUITreeNode();
            easyUITreeNode.setId(itemCat.getId());
            easyUITreeNode.setText(itemCat.getName());
            if (itemCat.getIsParent()) {
                easyUITreeNode.setState("closed");
            } else {
                easyUITreeNode.setState("open");
            }

            treeNodes.add(easyUITreeNode);
        }
        return treeNodes;
    }
}
