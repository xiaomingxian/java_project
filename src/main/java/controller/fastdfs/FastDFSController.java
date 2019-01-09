package controller.fastdfs;

import org.csource.fastdfs.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import utils.fastdfs.FastDFSClient;

import java.util.HashMap;
import java.util.Map;

@Controller
public class FastDFSController {

    // @Value("${read_path}")
    private String url = "http://192.168.203.131/";

    @RequestMapping("getPage.do")
    public String getPage() {
        return "page/fastdfs/shangchaun";
    }

    @RequestMapping("fileUpload")
    @ResponseBody
    public Map upload(MultipartFile file) {

        HashMap<Object, Object> map = new HashMap<>();

        // 读取文件的全路径
        String of = file.getOriginalFilename();
        String extName = of.substring(of.lastIndexOf(".") + 1);

        System.out.println("------ name : " + of + "-----" + extName);

        try {
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:properties/fdfs_client.conf");
            String uploadFile = fastDFSClient.uploadFile(file.getBytes(), extName);
            map.put(true, url + uploadFile);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put(false, "上传失败");
            return map;
        }

    }

    @RequestMapping("remove")
    @ResponseBody
    public Map remove(String url) {
        HashMap<Object, Object> map = new HashMap<>();

        // http://192.168.203.137/group1/M00/00/04/wKjLiVtZV8WAXkHiADYk5OiIjCc513.GIF
        // String s =
        // "http://192.168.203.137/group1/M00/00/04/wKjLiVtZV8WAXkHiADYk5OiIjCc513.GIF";
        String g = url.substring(23);
        String f = g.substring(7);

        String group = g.substring(0, 6);
        String file = g.substring(7);

        try {
            ClientGlobal.init("D:\\develop\\ideaworkspeace\\learn\\src\\main\\resources\\properties\\fdfs_client.conf");

            TrackerClient tcl = new TrackerClient();
            TrackerServer trackerSever = tcl.getConnection();
            StorageServer storageSever = null;
            StorageClient client = new StorageClient(trackerSever, storageSever);

            int x = client.delete_file(group, file);
            if (x == 0) {
                map.put(true, "删除成功");
            } else {
                map.put(true, "删除失败");
            }

            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}
