package freak.controller;

import com.fasterxml.jackson.databind.JsonNode;
import freak.common.Result;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestController
public class Controller {


//    @RequestMapping("/login")
//    public Object login(String userName, String pwd) {
//        return toData(userName, pwd);
//    }

    @RequestMapping("/login")
    public Object login(String lversion,String token,String userName, String pwd) {
        org.slf4j.Logger logger=LoggerFactory.getLogger(Controller.class);
        logger.debug("lversion"+lversion);
        logger.debug("token"+token);
        return toData(lversion,token,userName, pwd);
    }
    public Result toData(String lversion,String token,String userName, String pwd) {
        Result result = new Result();
        if (!"freak".equals(userName)) {
            result.setCode(48);
            Map map = new HashMap();
            result.setData(map);
            result.setMsg("用户不存在");
            return result;
        } else if (!pwd.equals("123456")) {
            result.setCode(4000);
            result.setData("");
            result.setMsg("密码错误");
            return result;
        } else {
            result.setCode(200);
            result.setMsg("成功");
            Map map = new HashMap();
            map.put("userName", userName);
            map.put("pwd", pwd);
            map.put("abc", "");
            map.put("token",token);
            map.put("lversion",lversion);
            result.setData(map);
            return result;
        }
    }

    @RequestMapping("/api/notToken")
    public Object text() {
        return toText();
    }

    private Result toText() {
        Result result = new Result();
        result.setCode(200);
        result.setData("");
        result.setMsg("测试成功");
        return result;
    }

    @RequestMapping("/api/error")
    public Object error() {
        return toError();
    }

    private Result toError() {
        Result result = new Result();
        result.setCode(200);
        result.setData("data");
        result.setMsg("测试失败");
        return result;
    }

}
