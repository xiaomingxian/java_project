package controller.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipInputStream;

/**
 * 流程相关
 */
@RestController
@RequestMapping("process")
public class ProcessController {

    @Autowired
    private ProcessEngine processEngine;
    @Autowired
    private RepositoryService repositoryService;


    /**
     * 发布流程【上传文件发布】
     * 名称读取上传文件的名称
     */
    @RequestMapping("deploy")
    public void deploy(MultipartFile multipartFile) throws IOException {
        InputStream inputStream = multipartFile.getInputStream();

        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        String originalFilename = multipartFile.getOriginalFilename();
        //
        Deployment deployment = repositoryService.createDeployment()
                .name(originalFilename)//写入部署名称
                .addZipInputStream(zipInputStream)//流
                .deploy();
        System.out.println("---->流程部署成功，id：" + deployment.getId());

    }

    /**
     * 提交申请（开启流程）【关联业务表】
     */
    @RequestMapping("start")
    public void start() {

    }

    /**
     *
     * 流程部署【根据名称模糊查询】
     * 流程定义【先根据部署名称查询，再根据部署id查询流程定义】
     */

    /**
     * 根据部署ID
     * 查询流程【图】
     */
    @RequestMapping("getProessImg")
    public void getProessImg(String deployId, HttpServletResponse httpServletResponse) throws IOException {
        //根据发布id查询流程定义实体
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deployId)
                .singleResult();
        String processDefinitionId = processDefinition.getId();
        //根据流程定义Id查询图片流
        InputStream processDiagram = repositoryService.getProcessDiagram(processDefinitionId);
        //------------------------第二种方式获取图片资源流，根据部署id和资源名称查询---------------
        //String resourceName = processDefinition.getResourceName();
        //InputStream resourceAsStream = repositoryService.getResourceAsStream(deployId, resourceName);
        //------------------------------------------------------------------------------
        //将流转为图片流，输出
        BufferedImage read = ImageIO.read(processDiagram);
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        ImageIO.write(read, "JPG", outputStream);
        //    关闭流
        processDiagram.close();
        outputStream.close();
    }


    /**
     * 拾取任务【分组情况下】
     */


    /**
     * 办理流程
     */

}
