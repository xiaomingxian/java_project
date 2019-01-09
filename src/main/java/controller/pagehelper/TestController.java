package controller.pagehelper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pojo.Test;
import service.TestService;
import utils.aop.Log;
import utils.mypagehelper.MyPage;
import utils.mypagehelper.MyPageHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {


    @Autowired
    private TestService testService;

    /**
     * github分页
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Log(function_id = "1", operate_type = "test", operate_content = "test_context")
    @RequestMapping("yuanShengPageHelper")
    public Map selectByPage(String currentPage, String pageSize) {
        HashMap<Object, Object> map = new HashMap<>();
        //
        PageHelper pageHelper = new PageHelper();
        //引入分页查询，使用PageHelper分页功能
        //在查询之前传入当前页，然后多少记录
        PageHelper.startPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
        //***********************************************************************************************************
        //startPage后紧跟的这个查询就是分页查询
        //***********************************************************************************************************
        List<Test> emps = testService.getAll();
        //使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以
        PageInfo pageInfo = new PageInfo<>(emps, 5);
        //pageINfo封装了分页的详细信息，也可以指定连续显示的页数

        map.put("pageInfo", pageInfo);

        return map;
    }

    @RequestMapping("myPageHelper")
    public Map myPageHelper(@ModelAttribute Test test, @RequestParam(required = false) String currentPage, @RequestParam(required = false) String pageSize) {
        HashMap<Object, Object> map = new HashMap<>();
        //查询总记录数
        int totalCount = testService.getCount(test);
        //封装分页查询条件
        MyPageHelper<Test> testMyPageHelper = new MyPageHelper<>();
        testMyPageHelper.setObject(test);
        //封转分页条件
        MyPage myPage = new MyPage();
        myPage.setFilter(true, Integer.parseInt(currentPage), Integer.parseInt(pageSize), totalCount);
        testMyPageHelper.setMypage(myPage);

        List<Test> list = testService.selectByMyPageHelper(testMyPageHelper);

        map.put("data", list);
        map.put("currentPage", testMyPageHelper.getMypage().getCurrentPage());
        map.put("totalCount", testMyPageHelper.getMypage().getTotalCount());
        map.put("totalPage", testMyPageHelper.getMypage().getTotolPage());

        return map;
    }


}
