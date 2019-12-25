package springx.aop.config;

import lombok.Data;

@Data
public class AopConfigX {

    private String pointCut;
    private String aspectBefore;
    private String aspectAfter;
    private String aspectClass;
    private String aspectAfterThrow;
    private String aspectAfterThrowing;//类型

}
