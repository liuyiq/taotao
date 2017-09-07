package xin.liuyiq.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.liuyiq.taotao.mapper.TbItemMapper;
import xin.liuyiq.taotao.pojo.EasyUIDataGridResult;
import xin.liuyiq.taotao.pojo.TbItem;
import xin.liuyiq.taotao.pojo.TbItemExample;
import xin.liuyiq.taotao.service.ItemService;

import java.util.List;

/**
 * @Author: Liuyiq
 * @Description: ItemService的实现类.
 */
@Service
@SuppressWarnings("all")
public class ItemServiceImpl implements ItemService {

    /**
     * 注入itemMapper.
     */
    @Autowired
    private TbItemMapper itemMapper;

    /**
     * 分页查询,将获取到的信息封装到EasyUIDataGridResult对象中.
     * @param page 当前页
     * @param rows 每页显示的数量
     * @return result 分页查询的结果
     */
    @Override
    public EasyUIDataGridResult getItemList(int page, int rows) {

        //设置分页信息
        PageHelper.startPage(page, rows);
        // 执行查询,当example是空的时候不设置条件,查询全部的item
        TbItemExample example = new TbItemExample();
        List<TbItem> items = itemMapper.selectByExample(example);
        // 获取到分页信息
        PageInfo<TbItem> pageInfo = new PageInfo<>(items);
        // 创建返回结果对象result
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal(pageInfo.getTotal());
        result.setRows(items);
        return result;
    }
}
