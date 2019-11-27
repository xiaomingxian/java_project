package es.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.util.*;

public class T0_Connection {
    //https://blog.csdn.net/linzhiqiang0316/article/details/80354898

    private TransportClient client;


    @Before
    public void c1() throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("cluster.name", "my-application");
        Settings.Builder settings = Settings.builder().put(map);
        client = TransportClient.builder().settings(settings).build()
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), Integer.parseInt("9300")))
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), Integer.parseInt("9301")))
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), Integer.parseInt("9302")));
    }


    /**
     * 查看集群信息
     */
    @Test
    public void testInfo() {
        List<DiscoveryNode> nodes = client.connectedNodes();
        for (DiscoveryNode node : nodes) {
            System.out.println(node.getHostAddress() + "---" + node.getName());
        }
    }

    //================================================ create  =========================================

    /**
     * 创建索引库
     * 索引库的名称必须为小写
     *
     * @throws Exception
     */
    @Test
    public void createIndex() throws Exception {
        IndexResponse response = client.prepareIndex("msg", "tweet", "1")
                .setSource(XContentFactory.jsonBuilder().startObject()
                        .field("name", "linzhiqiang")
                        //.field("date", new FieldStats.Date())
                        .field("msg", "hello world")
                        .endObject())
                .get();
        System.out.println("索引名称:" + response.getIndex() + "类型:" + response.getType()
                + "文档ID:" + response.getId() + "当前实例状态:");
    }

    /**
     * 创建索引2-传入Map对象
     *
     * @return void
     * @Title: addIndex3
     */
    @Test
    public void createIndex2() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "小妹");
        map.put("age", 18);
        map.put("sex", "女");
        map.put("address", "广东省广州市天河区上社");
        map.put("phone", "15521202233");
        map.put("height", "175");
        map.put("weight", "60");
        IndexResponse response = client.prepareIndex("species", "person").setSource(map).get();
        System.out.println("map索引名称:" + response.getIndex() + "\n map类型:" + response.getType()
                + "\n map文档ID:" + response.getId() + "\n当前实例map状态:" + response);
    }

    /**
     * 创建索引3--json对象
     */
    @Test
    public void createIndex3() {
        //这里只是个字符串--不是json
        String str = "{'name':'mac2','price':'1000002'}";

        JSONObject jsonObject = JSON.parseObject(str);

        System.out.println(jsonObject);

        IndexResponse response = client.prepareIndex("product", "computer").setSource(jsonObject, XContentType.JSON).get();
        System.out.println("map索引名称:" + response.getIndex() + "\n map类型:" + response.getType() + "\n map文档ID:" + response.getId());

    }

    //================================================ query  =========================================
    @Test
    public void query() {
        GetResponse getFields = client.prepareGet("product", "computer", "AWsq3y0MYioh13R7oy7j").get();
        System.out.println("---->" + getFields.getSourceAsString());

    }


    //================================================ update =========================================
    @Test
    public void update() {
        String str = "{'name':'mac-update','price':'5200'}";

        JSONObject jsonObject = JSON.parseObject(str);

        UpdateResponse updateResponse = client.prepareUpdate("product", "computer", "AWsq3y0MYioh13R7oy7j").setDoc(jsonObject).get();
        System.out.println("---->" + updateResponse.getIndex() + "\n");
    }

    //================================================ delete =========================================
    @Test
    public void delete() {
        DeleteResponse deleteResponse = client.prepareDelete("product", "computer", "AWsq6L1bYioh13R7oy7o").get();

        System.out.println("index:" + deleteResponse.getIndex() + "\ntype:" + deleteResponse.getType() + "\nid:" + deleteResponse.getId());

    }

    @Test
    public void t() {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(3);
        integers.add(2);
        integers.add(2);
        integers.add(1);


    }



}
