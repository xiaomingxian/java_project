package controller.activiti;


import dao.RoleMapper;
import dao.activiti.ActReProcdefMapper;
import dao.activiti.ActRuTaskMapper;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import pojo.User;
import pojo.UserExample;
import pojo.activiti.ActReProcdef;
import pojo.activiti.ActReProcdefExample;
import pojo.activiti.ActRuTask;
import pojo.activiti.ActRuTaskExample;
import pojo.shiro.Role;
import service.UserService;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("activiti")
public class ActivitiController {

    //核心类
    @Autowired
    private ProcessEngine processEngine;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private ActRuTaskMapper actRuTaskMapper;
    @Resource
    private ActReProcdefMapper actReProcdefMapper;

    @Autowired
    private UserService userService;


    /**
     * 获取页面
     *
     * @return
     */
    @RequestMapping("getPage")
    public ModelAndView getPage() {
        return new ModelAndView("page/activiti/task_public");
    }


    /**
     * 查看流程定义---角色待定
     */
    @RequestMapping("getActReProcdefs")
    @ResponseBody
    public Map getActReProcdefs() {
        HashMap map = new HashMap();

        List<ActReProcdef> actReProcdefs = actReProcdefMapper.selectByExample(new ActReProcdefExample());
        map.put("actReProcdefs", actReProcdefs);
        return map;
    }

    /**
     * 启动流程实例
     * task_key--
     *
     * @return
     */
    @RequestMapping("process")
    @ResponseBody
    public Map process(String processInstanceId) {
        HashMap map = new HashMap();
        try {
            //开启流程
            ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceById(processInstanceId);
            //map.put("processInstance", processInstance);
            map.put("status", "流程开启成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", "流程开启失败");
        }
        return map;
    }

    @RequestMapping("getTaskList")
    @ResponseBody
    public Map getTaskList() {

        HashMap map = new HashMap();
        List<ActRuTask> actRuTasks = actRuTaskMapper.selectByExample(new ActRuTaskExample());
        map.put("tasklist", actRuTasks);
        return map;


    }

    /**
     * 审批
     */
    @RequestMapping("pass")
    @ResponseBody
    public Map pass(String task_id) {

        HashMap map = new HashMap();

        try {
            Boolean flag = false;
            //查询用户角色与任务角色表进行匹配
            Subject subject = SecurityUtils.getSubject();
            Object principal = subject.getPrincipal();

            //封装查询条件
            UserExample example = new UserExample();
            UserExample.Criteria criteria = example.createCriteria();
            criteria.andUserNameEqualTo(principal.toString());
            User user = userService.getAll(example).get(0);



            //获取角色
            List<Role> roles = roleMapper.getRoles(user);

            //查询流程审批角色
            ActRuTaskExample actRuTaskExample = new ActRuTaskExample();
            ActRuTaskExample.Criteria criteria = actRuTaskExample.createCriteria().andIdEqualTo(task_id);
            List<ActRuTask> actRuTasks = actRuTaskMapper.selectByExample(new ActRuTaskExample());
            for (ActRuTask actRuTask : actRuTasks) {
                for (Role role : roles) {
                    String[] assigns = actRuTask.getAssignee().split(",");
                    if (Arrays.asList(assigns).contains(role.getKeyWords())) {
                        flag = true;
                        //    有权限
                        break;
                    }
                }
                if (flag) {
                    break;
                }
            }
            if (!flag) {
                //无权限
                map.put("status", "无权限");
                return map;
            }
            //有权限--进行审批
            processEngine.getTaskService().complete(task_id);
            map.put("success", "审批成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", "审批失败");
        }

        return map;

    }

}
