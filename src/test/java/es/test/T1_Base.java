package es.test;

import dao.EsDao.EsAticleDao;
import pojo.es.Aticle;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring/applicationContext-es.xml",
        //"classpath:spring/applicationContext-scan.xml",
        //"classpath:spring/applicationContext-tx.xml",
        //"classpath:spring/springmvc.xml",
        //"classpath:spring/applicationContext-dao.xml",
        //"classpath:spring/applicationContext-activemq.xml",
        //"classpath:spring/applicationContext-redis.xml",
        //"classpath:spring/applicationContext-shiro.xml",
        //"classpath:spring/applicationContext-acitiviti.cfg.xml",
        //"classpath:spring/applicationContext-freemarker.xml",
        //"classpath:spring/applicationContext-quartz.xml",
})

public class T1_Base {

    @Autowired
    private ElasticsearchTemplate template;



    @Test
    public void createIndex() {
        template.createIndex(Aticle.class);


        System.out.println("------------------- 操作成功 ----------------------");
    }

    @Test
    public void write() {


    }

    @Test
    public void query() {
        boolean b = template.indexExists(Aticle.class);
        System.out.println("索引是否存在：" + b);
        TransportClient client = (TransportClient) template.getClient();
        List<DiscoveryNode> discoveryNodes = client.connectedNodes();
        System.out.println("------- 集群节点信息遍历 -----------");
        discoveryNodes.stream().forEach(System.out::println);
        //------------
        System.out.println("------- 索引查询 -----------");
        Pageable pageable = new PageRequest(1, 10);
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(queryStringQuery("")).withPageable(pageable).build();
        List<Aticle> aticles = template.queryForList(searchQuery, Aticle.class);
        aticles.stream().forEach(System.out::println);

    }

}
