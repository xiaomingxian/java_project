package dao.EsDao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import pojo.es.Aticle;

@Repository
public interface EsAticleDao extends ElasticsearchRepository<Aticle, Integer> {
}
