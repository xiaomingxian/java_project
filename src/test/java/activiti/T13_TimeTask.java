package activiti;

import java.util.Date;
import java.util.HashMap;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.python.antlr.PythonParser.print_stmt_return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext-acitiviti.cfg.xml" })
public class T13_TimeTask {

	@Autowired
	private ProcessEngine processEngine;

	public static void main(String[] args) {
		System.out.println("----------------");
	}

	@Test
	public void deploy() {

		RepositoryService repositoryService = processEngine.getRepositoryService();
		Deployment deploy = repositoryService.createDeployment().addClasspathResource("bpmn/time_task/TimeTask.bpmn")
				.addClasspathResource("bpmn/time_task/TimeTask.png").deploy();
		System.out.println("---->发布成功|name:" + deploy.getName() + " " + deploy.getId());
	}

	/**
	 * 开启流程
	 */
	@Test
	public void start() {
		RuntimeService runtimeService = processEngine.getRuntimeService();
		String key = "TimeTask";
		// 流程变量
		HashMap<String, Object> map = new HashMap<String, Object>();
		// 定时任务接受的是Date或者yyyy-MM-dd hh:mm:ss
		Date date = new Date(new Date().getTime() + 1000);
		map.put("timeOut", date);
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key, map);

		System.out.println("---------->流程实例：" + processInstance);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
