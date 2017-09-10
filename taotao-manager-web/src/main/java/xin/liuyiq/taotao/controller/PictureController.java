package xin.liuyiq.taotao.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xin.liuyiq.taotao.utils.FastDFSClient;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Liuyiq
 * @Description: 图片上传处理.
 */
@RestController
public class PictureController {

    @Value("${IMAGE_SERVER_URL}")
    private String IMAGE_SERVER_URL;

    private Map<String, Object> resultMap = new HashMap<String, Object>();

    /**
     * 图片上传的方法.
     *
     * @param uploadFile 页面中传递过来的图片名称.
     * @return map 将结果集封装到map中返回.
     */
    @RequestMapping("/pic/upload")
    public Map fileUpload(MultipartFile uploadFile) {
        try {
            // 获取到文件的扩展名
            String originalFilename = uploadFile.getOriginalFilename();
            String extName = originalFilename.substring(
                    originalFilename.lastIndexOf(".") + 1);
            // 上传图片到图片服务器
            FastDFSClient fastDFSClient = new FastDFSClient(
                    "classpath:resource/client.conf");
            String path = fastDFSClient.uploadFile(
                    uploadFile.getBytes(), extName);
            // 拼装URL
            String url = IMAGE_SERVER_URL + path;
            // 返回结果
            resultMap.put("error", 0);
            resultMap.put("url", url);
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
            // 返回结果
            resultMap.put("error", 1);
            resultMap.put("message", "图片上传失败");
            return resultMap;
        }
    }
}
