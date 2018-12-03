package javase.concurrent;

import org.springframework.stereotype.Service;

/**
 * @author: tangxiaoshuang
 * @date: 2018/12/3 14:44
 * @desc:
 */

public interface ConFacade {


    public void setConService(ConService conService);

    String getString();
}
