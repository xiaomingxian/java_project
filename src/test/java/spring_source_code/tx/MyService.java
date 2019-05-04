package spring_source_code.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyService {

    @Autowired
    private MyDao myDao;

    @Transactional
    public void insert() {
        myDao.insert();

    }
}
