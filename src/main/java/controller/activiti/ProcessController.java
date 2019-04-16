package controller.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
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
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;


    /**
     * 发布流程【上传文件发布】
     * 名称读取上传文件的名称
     */
    @RequestMapping("deploy")
    public void deploy(@RequestParam(value="file",required=false) MultipartFile multipartFile) throws IOException {
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
     * 查询流程图
     */
    @RequestMapping("queryXY")
    public Map queryXY(String taskId) {

        HashMap map = new HashMap();
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        //    节点坐标的获取：获取到流程实例-得到节点坐标-通过定义实体来获取相关坐标
        String processInstanceId = task.getProcessInstanceId();
        Execution execution = processEngine.getRuntimeService().createExecutionQuery().processInstanceId(processInstanceId).singleResult();
        //活动节点
        String activityId = execution.getActivityId();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ProcessInstance processInstance = processEngine.getRuntimeService().createProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        String processDefinitionId = processInstance.getProcessDefinitionId();
        //通过流程定义id获取流程定义实体
        ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinitionId);
        ActivityImpl activity = processDefinitionEntity.findActivity(activityId);
        int height = activity.getHeight();
        int width = activity.getWidth();
        int x = activity.getX();
        int y = activity.getY();


        map.put("x", x);
        map.put("y", y);
        map.put("height", height);
        map.put("width", width);
        System.out.println(
                "长：" + height +
                        "宽：" + width +
                        "x：" + x +
                        "y：" + y
        );

        return map;

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


    @RequestMapping("queryImgByTaskkId")
    public void queryImgByTaskkId(String taskId, HttpServletResponse httpServletResponse) throws IOException {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(task.getProcessInstanceId())
                .singleResult();
        //根据发布Id获取流程定义id
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId(processInstance.getDeploymentId())
                .singleResult();

        InputStream processDiagram = repositoryService.getProcessDiagram(processDefinition.getId());

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
