package es.test;

import org.elasticsearch.client.Client;
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
}
