package xin.liuyiq.taotao.search.service.impl;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.liuyiq.taotao.pojo.SearchItem;
import xin.liuyiq.taotao.pojo.TaotaoResult;
import xin.liuyiq.taotao.search.mapper.ItemMapper;
import xin.liuyiq.taotao.search.service.SearchItemService;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Liuyiq
 * @Description: 导入商品到索引库
 */
@Service
@SuppressWarnings("all")
public class SearchItemServiceImpl implements SearchItemService {

    @Autowired
    private SolrServer solrServer;

    @Autowired
    private ItemMapper itemMapper;

    /**
     * 将搜索到的商品信息导入到索引库.
     *
     * @return TaotaoResult
     * @throws Exception
     */
    @Override
    public TaotaoResult ImportAllItemTOIndex() throws Exception {
        // 查询所有商品数据
        List<SearchItem> itemList = itemMapper.getItemList();
        List<SolrInputDocument> documents = new ArrayList<SolrInputDocument>();
        // 2、创建一个SolrServer对象
        for (SearchItem searchItem : itemList) {
            // 3、为每个商品创建一个SolrInputDocument对象。
            SolrInputDocument document = new SolrInputDocument();
            // 4、为文档添加域
            document.addField("id", searchItem.getId());
            document.addField("item_title", searchItem.getTitle());
            document.addField("item_sell_point", searchItem.getSellPoint());
            document.addField("item_price", searchItem.getPrice());
            document.addField("item_image", searchItem.getImage());
            document.addField("item_category_name", searchItem.getCategoryName());
            document.addField("item_desc", searchItem.getItemDesc());

            documents.add(document);

        }
        // 5、向索引库中添加文档。
        solrServer.add(documents);
        solrServer.commit();
        // 6 返回结果
        return TaotaoResult.ok();
    }
}
