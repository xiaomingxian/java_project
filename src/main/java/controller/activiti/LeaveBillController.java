package controller.activiti;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.activiti.LeaveBill;

import java.util.HashMap;
import java.util.Map;

/**
 * 请假业务
 */
@RestController
@RequestMapping("leaveBill")
public class LeaveBillController {
    /**
     * 填写请假单
     */
    @RequestMapping("add")
    public Map addLeaveBill(@ModelAttribute LeaveBill leaveBill) {
        HashMap<Object, Object> map = new HashMap<>();


        return map;
    }

    /**
     * 修改请假单
     */

    /**
     * 查询请假单--根据用户【区分普通用户与管理员】
     */

    /**
     * 删除请假单
     */
}
