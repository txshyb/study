package test.excel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: tangxiaoshuang
 * @date: 2018/11/12 16:38
 * @desc:
 */
@Controller
@RequestMapping("/excel")
public class ExcelController {

    @RequestMapping("export")
    public Object export() {

        return null;
    }
}
