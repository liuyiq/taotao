package xin.liuyiq.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.liuyiq.taotao.mapper.TbItemDescMapper;
import xin.liuyiq.taotao.mapper.TbItemMapper;
import xin.liuyiq.taotao.pojo.*;
import xin.liuyiq.taotao.service.ItemService;
import xin.liuyiq.taotao.utils.IDUtils;

import java.util.Date;
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
     * 注入itemDescMapper
     */
    @Autowired
    private TbItemDescMapper itemDescMapper;

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

    /**
     * 添加商品信息的接口.
     * @param tbItem tbItem封装页面传递的商品参数.
     * @param desc   desc接收商品描述信息.
     * @return 自定义响应结构.
     */
    @Override
    public TaotaoResult addItem(TbItem tbItem, String desc) {
        long id = IDUtils.genItemId();
        tbItem.setId(id);
        Date date = new Date();
        tbItem.setCreated(date);
        tbItem.setUpdated(date);
        // 商品状态，1-正常，2-下架，3-删除
        tbItem.setStatus((byte) 1);
        // 插入商品信息
        itemMapper.insert(tbItem);

        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(id);
        tbItemDesc.setCreated(date);
        tbItemDesc.setUpdated(date);
        tbItemDesc.setItemDesc(desc);
        // 向商品描述表中插入数据
        itemDescMapper.insert(tbItemDesc);

        return TaotaoResult.ok();
    }
}
