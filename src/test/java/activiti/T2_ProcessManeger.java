package activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipInputStream;

/**
 * 流程管理
 * 【发布流程，流程定义】相关
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-acitiviti.cfg.xml"})
public class T2_ProcessManeger {

    @Autowired
    private ProcessEngine processEngine;

    /**
     * 部署流程zip
     */
    @Test
    public void deploy() {
        //路径最前面加/表示从classpath中读取文件 --不加/是从当前目录读取
        //文件是从classes中读取得先编译进去
        InputStream resourceAsStream = this.getClass().getResourceAsStream("/bpmn_work/LeaveBill.zip");
        ZipInputStream zipInputStream = new ZipInputStream(resourceAsStream);
        //
        Deployment deployment = processEngine.getRepositoryService().createDeployment()
                .name("Zip形式部署测试")//写入部署名称
                .addZipInputStream(zipInputStream)
                .deploy();
        //processEngine.getRepositoryService().createDeployment().addZipInputStream(zipInputStream).addString("名称", "textContetx");

    }

    /**
     * 查询发布流程【条件查询，排序，结果集展示】
     * //.listPage(1,10)//封装了分页
     * //.singleResult()//
     * //.count()//查询数量
     * .list();
     */
    @Test
    public void queryDeploymentQuery() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        List<Deployment> deployments = repositoryService.createDeploymentQuery()
                //.deploymentNameLike("%Zip形式部署%") //有多种条件查询
                //.orderByDeploymentId().desc()//默认是升序 asc
                //.listPage(1,10)//封装了分页
                //.singleResult()//
                //.count()//查询数量
                .list();
        System.out.println("-----流程数量:" + deployments.size());
        for (Deployment deployment : deployments) {
            System.out.println("id:" + deployment.getId() + "名称:" + deployment.getName() + "时间：" + deployment.getDeploymentTime().toLocaleString());
        }
    }

    /**
     * 查询流程定义 act_re_procdef【条件查询，排序，结果集展示】
     * //.listPage(1,10)//封装了分页
     * //.singleResult()//
     * //.count()//查询数量
     * .list();
     */
    @Test
    public void queryProcesInstance() {

        List<ProcessDefinition> list = processEngine.getRepositoryService().createProcessDefinitionQuery()
                //.deploymentId("1")//多条件查询
                //.deploymentIds()//参数为发布流程id集合[set集合]
                //.processDefinitionVersionGreaterThan(1)//流程版本大于1
                .processDefinitionVersionGreaterThanOrEquals(1)//流程版本大于=1.....
                .orderByDeploymentId().desc()//多条件排序
                .list();
        for (ProcessDefinition processDefinition : list) {
            System.out.println(
                    "流程定义Id:" + processDefinition.getId() +
                            "流程发布Id:" + processDefinition.getDeploymentId() +
                            "流程定义Key:" + processDefinition.getKey() +
                            "流程定义Name:" + processDefinition.getName() +
                            "流程定义文件名:" + processDefinition.getResourceName() +
                            "流程定义版本:" + processDefinition.getVersion() +
                            "流程定义描述:" + processDefinition.getDescription() +
                            "流程定义png:" + processDefinition.getDiagramResourceName()
            );

        }

    }

    /**
     * 删除流程定义
     */
    @Test
    public void deleteProcfef() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        String deployId = "192501";
        //repositoryService.deleteDeployment(deployId);
        //如果已经开启流程 ---其他的ru表中会有关联数据删除会失败--指定true会级联删除所有有关的数据
        repositoryService.deleteDeployment(deployId, true);//如果为false等同于以上
        //repositoryService.deleteDeploymentCascade(deployId);//等同于以上--true--Cascade级联
        System.out.println("删除成功");
    }
    /**
     * 修改流程定义【本质是重新部署】
     * act_re_procdef
     * 修改流程后重新部署重要key不变【整个流程的id】版本号就会+1
     * 如果未做改变重复的发布表中不会有新数据
     */


    /**
     * 查询流程图---根据流程定义id查询
     */
    @Test
    public void queryPng() {

        RepositoryService repositoryService = processEngine.getRepositoryService();
        String defId = "task_public:2:25004";
        InputStream inputStream = repositoryService.getProcessDiagram(defId);

        File file = new File("E:/public_test.png");
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            int len = 0;
            byte[] b = new byte[1024];
            while ((len = inputStream.read(b)) != -1) {
                bufferedOutputStream.write(b, 0, len);
                bufferedOutputStream.flush();
            }
            bufferedOutputStream.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据部署id获取流程定义id再查询png
     */
    @Test
    public void getPngByDeployId() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        String deployId = "25001";
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deployId).singleResult();
        String id = processDefinition.getId();
        //    查询图片
        InputStream inputStream = repositoryService.getProcessDiagram(id);
        //String path = "E:/" + processDefinition.getDiagramResourceName();
        String path = "E:/public_test2.png";
        File file = new File(path);
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            int len = 0;
            byte[] b = new byte[1024];
            while ((len = inputStream.read(b)) != -1) {
                bufferedOutputStream.write(b, 0, len);
                bufferedOutputStream.flush();
            }
            bufferedOutputStream.close();
            inputStream.close();
            System.out.println("读取图片成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询最新版本的流程定义【有多个流程定义的情况下】
     * 通过map取得每个流程的最新定义
     */
    @Test
    public void getPngLast() {
        HashMap<Object, Object> map = new HashMap<>();

        RepositoryService repositoryService = processEngine.getRepositoryService();
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery()
                .orderByProcessDefinitionVersion().asc()
                .list();
        for (ProcessDefinition processDefinition : list) {
            //同一个流程的不同版本key【图形界面中整个流程的id】是相同的
            map.put(processDefinition.getKey(), processDefinition);
        }
        Set<Map.Entry<Object, Object>> entries = map.entrySet();
        for (Map.Entry<Object, Object> m : entries) {
            System.out.println(m.getValue());
        }
    }

    /**
     * 删除只能根据部署id删除
     * 根据key删除流程定义只留下最新的---根据key查询到流程实例--根据实例得到部署id--删除
     */
    @Test
    public void deleteOtherInstance() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        String key = "task_public";
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(key)
                .orderByProcessDefinitionVersion().asc()
                .list();
        System.out.println(list);
        //根据上一条中的map思想进行过滤
        //留下最后一条数据其他数据全删除【根据发布id】
    }
}
