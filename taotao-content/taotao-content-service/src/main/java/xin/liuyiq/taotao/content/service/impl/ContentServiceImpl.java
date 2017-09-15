package xin.liuyiq.taotao.content.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xin.liuyiq.taotao.content.jedis.JedisClient;
import xin.liuyiq.taotao.content.jedis.JedisClientCluster;
import xin.liuyiq.taotao.content.service.ContentService;
import xin.liuyiq.taotao.mapper.TbContentMapper;
import xin.liuyiq.taotao.pojo.EasyUIDataGridResult;
import xin.liuyiq.taotao.pojo.TaotaoResult;
import xin.liuyiq.taotao.pojo.TbContent;
import xin.liuyiq.taotao.pojo.TbContentExample;
import xin.liuyiq.taotao.utils.JsonUtils;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * @Author: Liuyiq
 * @Description: 展示商品内容接口的实现类.
 */
@Service
@SuppressWarnings("all")
public class ContentServiceImpl implements ContentService {

    /**
     * 自动注入TbContentMapper
     */
    @Autowired
    private TbContentMapper contentMapper;

    /**
     * 自动注入JedisClient
     */
    @Autowired
    private JedisClient jedisClient;

    @Value("${CONTENT_KEY}")
    private String CONTENT_KEY;

    /**
     * 网站内容的查询.
     *
     * @param categoryId 内容的id.
     * @param page       当前页.
     * @param rows       每页的数量.
     * @return EasyUIDataGridResult.
     */
    @Override
    public EasyUIDataGridResult getContentList(Long categoryId, int page, int rows) {
        // 设置分页信息
        PageHelper.startPage(page, rows);
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
     *
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
     *
     * @param categoryId categoryId.
     * @return TbContent的集合.
     */
    @Override
    public List<TbContent> getContentList(Long categoryId) {
        // 从redis中取出信息
        try {
            String json = jedisClient.hget(CONTENT_KEY, categoryId + "");
            // 判断是否空
            if(StringUtils.isNotBlank(json)) {
                // 把json转换成list后return
                return JsonUtils.jsonToList(json,TbContent.class);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);

        // 执行查询
        List<TbContent> contents = contentMapper.selectByExample(example);
        // 存储查询到的信息到redis
        try {
            jedisClient.hset(CONTENT_KEY, categoryId + "", JsonUtils.objectToJson(contents));
        }catch (Exception e) {
            e.printStackTrace();
        }
        return contents;
    }
}
