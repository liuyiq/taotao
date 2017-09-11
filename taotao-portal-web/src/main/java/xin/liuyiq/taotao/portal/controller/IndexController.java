package xin.liuyiq.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import xin.liuyiq.taotao.content.service.ContentService;
import xin.liuyiq.taotao.pojo.TbContent;
import xin.liuyiq.taotao.portal.pojo.Ad1Node;
import xin.liuyiq.taotao.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Liuyiq
 * @Description: 前台页面跳转的Controller.
 */
@Controller
@SuppressWarnings("all")
public class IndexController {

    @Autowired
    private ContentService contentService;
    @Value("${AD1_CONTENT_CID}")
    private Long AD1_CONTENT_CID;
    @Value("${AD1_HEIGHT}")
    private String AD1_HEIGHT;
    @Value("${AD1_HEIGHT_B}")
    private String AD1_HEIGHT_B;
    @Value("${AD1_WIDTH}")
    private String AD1_WIDTH;
    @Value("${AD1_WIDTH_B}")
    private String AD1_WIDTH_B;

    /**
     * 跳转到前台页面首页.
     * @return index
     */
    @RequestMapping("/index")
    public String ShowIndex(Model model) {
        // 在页面加载的时候加载图片
        // 组装一个json格式的数据,把这个数据ad1放在request中返回
        List<TbContent> contents = contentService.getContentList(AD1_CONTENT_CID);
        ArrayList<Ad1Node> ad1Nodes = new ArrayList<>();
        for (TbContent content : contents) {
            Ad1Node node = new Ad1Node();
            node.setAlt(content.getTitle());
            node.setHref(content.getUrl());
            node.setSrc(content.getPic());
            node.setSrcB(content.getPic2());
            node.setHeight(AD1_HEIGHT);
            node.setHeightB(AD1_HEIGHT_B);
            node.setWidth(AD1_WIDTH);
            node.setWidthB(AD1_WIDTH_B);
            ad1Nodes.add(node);
        }
        // 把数据传给页面
        model.addAttribute("ad1",JsonUtils.objectToJson(ad1Nodes));
        return "index";
    }
}
