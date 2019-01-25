package freak.controller;

import com.fasterxml.jackson.databind.JsonNode;
import freak.common.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Controller {


    @RequestMapping("/login")
    public Object login(String userName, String pwd) {
        return toData(userName, pwd);
    }

    public Result toData(String userName, String pwd) {
        Result result = new Result();
        result.setCode("2000");
        result.setMsg("成功");
        Map map = new HashMap();
        map.put("userName", userName);
        map.put("pwd", pwd);
        result.setData(map);
        return result;
    }
}
