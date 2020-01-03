package springx.service.impl;

import springx.annotation.ServiceX;
import springx.service.AopService;

@ServiceX
public class AopServiceImpl implements AopService {
    @Override
    public void test() {
    }

    @Override
    public void exceptionTest() {

        int x = 1 / 0;

    }

    @Override
    public void exceptionThrow() throws Exception {
        throw new Exception("故意抛出的异常");

    }
}
