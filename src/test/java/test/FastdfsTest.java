package test;

import org.junit.Test;


// @RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(locations = "classpath:applicationContext-solr.xml")
public class FastdfsTest {

    @Test
    public void shangchaun() {


        String x = "a.jpg";
        String extName = x.substring(x.lastIndexOf(".") + 1);
        System.out.println(extName);

    }


}


//
// package cn.itcast.controller;
//
//         import org.csource.fastdfs.ClientGlobal;
//         import org.csource.fastdfs.StorageClient;
//         import org.csource.fastdfs.StorageServer;
//         import org.csource.fastdfs.TrackerClient;
//         import org.csource.fastdfs.TrackerServer;
//         import org.springframework.beans.factory.annotation.Value;
//         import org.springframework.web.bind.annotation.RequestMapping;
//         import org.springframework.web.bind.annotation.RestController;
//         import org.springframework.web.multipart.MultipartFile;
//
//         import com.pinyougou.returnmsg.ReturnMsg;
//
//         import cn.pinyougou.utils.FastDFSClient;
//
// @RestController
// public class Upload {
//
//     @Value("${read_path}")
//     private String url;
//
//     @RequestMapping("upload")
//     public ReturnMsg upload(MultipartFile file) {
//
//         // 读取文件的全路径
//         String of = file.getOriginalFilename();
//         String extName = of.substring(of.lastIndexOf(".") + 1);
//
//         try {
//             FastDFSClient fastDFSClient = new FastDFSClient("classpath:fdfs_client.conf");
//             String uploadFile = fastDFSClient.uploadFile(file.getBytes(), extName);
//
//             return new ReturnMsg(true, url + uploadFile);
//         } catch (Exception e) {
//             e.printStackTrace();
//             return new ReturnMsg(false, "上传失败");
//         }
//
//     }
//
//     @RequestMapping("remove")
//     public ReturnMsg remove(String url) {
//
//         // http://192.168.203.137/group1/M00/00/04/wKjLiVtZV8WAXkHiADYk5OiIjCc513.GIF
//         // String s =
//         // "http://192.168.203.137/group1/M00/00/04/wKjLiVtZV8WAXkHiADYk5OiIjCc513.GIF";
//         String g = url.substring(23);
//         String f = g.substring(7);
//
//         String group = g.substring(0, 6);
//         String file = g.substring(7);
//
//         try {
//             ClientGlobal.init("D:\\stsworkspace\\pinyougou\\FastDfs\\src\\main\\resources\\fdfs_client.conf");
//
//             TrackerClient tcl = new TrackerClient();
//             TrackerServer trackerSever = tcl.getConnection();
//             StorageServer storageSever = null;
//             StorageClient client = new StorageClient(trackerSever, storageSever);
//
//             int x = client.delete_file(group, file);
//             ReturnMsg result = x == 0 ? new ReturnMsg(true, "删除成功") : new ReturnMsg(true, "删除失败");
//
//             return result;
//         } catch (Exception e) {
//             // TODO: handle exception
//         }
//
//         return null;
//     }
//
// }
//
