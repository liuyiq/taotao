package xin.liuyiq.taotao.search.service;

import xin.liuyiq.taotao.pojo.SearchResult;

/**
 * @Author: Liuyiq
 * @Description: 搜索商品.
 */
public interface SearchService {

    /**
     * 搜索商品的接口
     * @param queryString 搜索条件.
     * @param page 当前页.
     * @param rows 每页显示的数量.
     * @return SearchResult.
     * @throws Exception
     */
    SearchResult search(String queryString, int page, int rows) throws Exception;
}
