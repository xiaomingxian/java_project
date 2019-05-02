//package es.test;
//
//import dao.EsAticleDao;
//import org.elasticsearch.action.index.IndexResponse;
//import org.elasticsearch.client.Client;
//import org.elasticsearch.common.xcontent.XContentType;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import pojo.es.Aticle;
//
//import static org.apache.camel.model.rest.RestBindingMode.json;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:spring/applicationContext-es.xml"})
//public class T1_Base {
//    @Autowired
//    private ElasticsearchTemplate elasticsearchTemplate;
//
//    @Autowired
//    private EsAticleDao esAticleDao;
//
//    @Test
//    public void createIndex() {
//        elasticsearchTemplate.createIndex(Aticle.class);
//        elasticsearchTemplate.putMapping(Aticle.class);
//        System.out.println("---------ok---------");
//    }
//
//    @Test
//    public void insert() {
//        //
//        Client client = elasticsearchTemplate.getClient();
//
//        IndexResponse response = client.prepareIndex("itoo-user-whole", "ItooWebContentList",
//                "11")
//                .setSource(json, XContentType.JSON)//新版此处参数数量得是偶数
//                .execute().actionGet();
//        //多次index这个版本号会变
//        System.out.println("response.version():" + response.getVersion());
//        client.close();
//
//        //Aticle aticle = new Aticle();
//        //aticle.setContent("这是内容");
//        //aticle.setTitle("测试题目");
//        //aticle.setId(1);
//        //esAticleDao.save(aticle);
//
//
//        System.out.println("-------ok---------");
//
//    }
//
//
//}
