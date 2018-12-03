package javase.concurrent;

import org.springframework.stereotype.Service;

/**
 * @author: tangxiaoshuang
 * @date: 2018/12/3 14:44
 * @desc:
 */
@Service
public class ConFacadeImpl  implements ConFacade{

    private ConService conService;

    @Override
    public void setConService(ConService conService) {
        this.conService = conService;
    }

    @Override
    public String getString() {
        return conService.getString();
    }
}
