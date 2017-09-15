package xin.liuyiq.taotao.search.dao;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xin.liuyiq.taotao.pojo.SearchItem;
import xin.liuyiq.taotao.pojo.SearchResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class SearchDao {

    @Autowired
    private SolrServer solrServer;

    public SearchResult search(SolrQuery query) throws Exception {
        QueryResponse response = solrServer.query(query);
        //获取商品列表
        SolrDocumentList solrDocumentList = response.getResults();
        List<SearchItem> itemList = new ArrayList<>();
        for (SolrDocument document : solrDocumentList) {
            SearchItem item = new SearchItem();
            item.setId(Long.parseLong((String) document.get("id")));
            item.setCategoryName((String) document.get("item_category_name"));
            item.setImage((String) document.get("item_image"));
            item.setPrice((Long) document.get("item_price"));
            item.setSellPoint((String) document.get("item_sell_point"));
            // 获取高亮
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            List<String> list = highlighting.get(document.get("id")).get("item_title");
            String itemTitle = "";
            if(list != null && list.size() > 0) {
                itemTitle = list.get(0);
            } else {
                itemTitle = (String) document.get("item_title");
            }
            item.setTitle(itemTitle);
            // 添加到商品列表
            itemList.add(item);
        }
        SearchResult searchResult = new SearchResult();
        // 商品列表
        searchResult.setItemList(itemList);
        // 总记录数
        searchResult.setRecordCount(solrDocumentList.getNumFound());

        return  searchResult;
    }
}
