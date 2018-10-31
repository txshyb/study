package test.controller;

import tspring.ioc.TAutowire;
import tspring.ioc.TController;

/**
 * @author: tangxiaoshuang
 * @date: 2018/10/31 13:50
 * @desc:
 */
@TController
public class TestController {

    @TAutowire
    private TestService testService;


}
