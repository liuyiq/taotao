package xin.liuyiq.taotao.search.service;

import xin.liuyiq.taotao.pojo.TaotaoResult;
/**
 * @Author: Liuyiq
 * @Description: 导入商品到索引库.
 */
public interface SearchItemService {

    /**
     * 将搜索到的商品信息导入到索引库.
     * @return TaotaoResult
     * @throws Exception
     */
    TaotaoResult ImportAllItemTOIndex() throws Exception;
}
