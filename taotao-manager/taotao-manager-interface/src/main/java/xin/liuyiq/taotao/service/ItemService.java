package xin.liuyiq.taotao.service;

import org.springframework.stereotype.Service;
import xin.liuyiq.taotao.pojo.EasyUIDataGridResult;
/**
 * @Author: Liuyiq
 * @Description: itemService的接口.
 */
public interface ItemService {

    /**
     * 分页查询,将获取到的信息封装到EasyUIDataGridResult对象中.
     * @param page 当前页
     * @param rows 每页显示的数量
     * @return result 分页查询的结果
     */
    EasyUIDataGridResult getItemList(int page, int rows);
}
