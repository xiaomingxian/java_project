package es.test;

import org.elasticsearch.action.fieldstats.FieldStats;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
