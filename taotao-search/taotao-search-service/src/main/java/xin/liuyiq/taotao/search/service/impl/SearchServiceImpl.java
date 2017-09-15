package xin.liuyiq.taotao.search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.liuyiq.taotao.pojo.SearchResult;
import xin.liuyiq.taotao.search.dao.SearchDao;
import xin.liuyiq.taotao.search.service.SearchService;

/**
 * @Author: Liuyiq
 * @Description: 搜索商品接口的实现类.
 */
@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchDao searchDao;
    /**
     * 搜索商品的接口
     *
     * @param queryString 搜索条件.
     * @param page        当前页.
     * @param rows        每页显示的数量.
     * @return SearchResult.
     * @throws Exception
     */
    @Override
    public SearchResult search(String queryString, int page, int rows) throws Exception {
        SolrQuery solrQuery = new SolrQuery();

        solrQuery.setQuery(queryString);
        // 分页条件
        solrQuery.setStart((page - 1) * rows);
        solrQuery.setRows(rows);
        // 制定默认的搜索域
        solrQuery.set("df","item_title");
        // 设置高量
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlightSimplePre("<em style=\"color:red\">");
        solrQuery.setHighlightSimplePost("</em>");
        // 执行查询 调用dao
        SearchResult result = searchDao.search(solrQuery);
        long recordCount = result.getRecordCount();
        long pageCount = (long) Math.ceil(recordCount * 1.0 / rows);
        result.setPageCount(pageCount);

        return result;
    }
}
