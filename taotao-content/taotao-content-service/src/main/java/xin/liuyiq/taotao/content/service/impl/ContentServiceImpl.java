package xin.liuyiq.taotao.content.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.liuyiq.taotao.content.service.ContentService;
import xin.liuyiq.taotao.mapper.TbContentMapper;
import xin.liuyiq.taotao.pojo.EasyUIDataGridResult;
import xin.liuyiq.taotao.pojo.TaotaoResult;
import xin.liuyiq.taotao.pojo.TbContent;
import xin.liuyiq.taotao.pojo.TbContentExample;

import java.util.Date;
import java.util.List;

/**
 * @Author: Liuyiq
 * @Description: 展示商品内容接口的实现类.
 */
@Service
@SuppressWarnings("all")
public class ContentServiceImpl implements ContentService{

    /**
     * 自动注入TbContentMapper
     */
    @Autowired
    private TbContentMapper contentMapper;
    /**
     * 网站内容的查询.
     * @param categoryId 内容的id.
     * @param page      当前页.
     * @param rows      每页的数量.
     * @return EasyUIDataGridResult.
     */
    @Override
    public EasyUIDataGridResult getContentList(Long categoryId, int page, int rows) {
        // 设置分页信息
        PageHelper.startPage(page,rows);
        // 执行查询,当example是空的时候不设置条件,查询全部的item
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        // 执行查询
        // 查询到的结果是个page
        List<TbContent> contents = contentMapper.selectByExample(example);
        // 获取到分页信息
        PageInfo<TbContent> contentPageInfo = new PageInfo<>(contents);

        EasyUIDataGridResult easyUIDataGridResult = new EasyUIDataGridResult();
        easyUIDataGridResult.setTotal(contentPageInfo.getTotal());
        easyUIDataGridResult.setRows(contents);
        return easyUIDataGridResult;
    }

    /**
     * 保存content.
     * @param content 封装content的pojo对象.
     * @return TaotaoResult.
     */
    @Override
    public TaotaoResult addContent(TbContent content) {
        content.setCreated(new Date());
        content.setUpdated(new Date());
        contentMapper.insert(content);
        return TaotaoResult.ok();
    }

    /**
     * 通过内容分类的id(categoryId),获取内容的集合.
     * @param categoryId categoryId.
     * @return TbContent的集合.
     */
    @Override
    public List<TbContent> getContentList(Long categoryId) {

        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        // 执行查询
        List<TbContent> contents = contentMapper.selectByExample(example);

        return contents;
    }
}
