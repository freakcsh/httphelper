package freak.controller;

import com.fasterxml.jackson.databind.JsonNode;
import freak.common.Result;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

@RestController
public class Controller {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(Controller.class);

//    @RequestMapping("/login")
//    public Object login(String userName, String pwd) {
//        return toData(userName, pwd);
//    }

    @RequestMapping("/login")
    public Object login(String lversion, String token, String userName, String pwd) {
        org.slf4j.Logger logger = LoggerFactory.getLogger(Controller.class);
        logger.debug("lversion" + lversion);
        logger.debug("token" + token);
        return toData(lversion, token, userName, pwd);
    }

    public Result toData(String lversion, String token, String userName, String pwd) {
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
            map.put("token", token);
            map.put("lversion", lversion);
            Map listMap = new HashMap();
            List<Map> list = new ArrayList<Map>();

            listMap.put("userName", userName);
            listMap.put("pwd", pwd);
            listMap.put("abc", "");
            listMap.put("token", token);
            listMap.put("lversion", lversion);
            list.add(listMap);
            map.put("list", list);
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

    @PostMapping("/uploading")
    public Result uploading(@RequestParam("tip") String tip, @RequestParam("tip1") String tip1, @NotNull(message = "图片不能为空") @RequestParam("image") MultipartFile image) {

        //身份证正反面保存路径
        String positiveReverse = "/usr/local/java/resources/xiaopiaoimage/" + "/" + getYmd() + "/identify/";
        // /判断路径是否存在不存在就创建
        String paths[] = positiveReverse.split("/");
        String dir = paths[0];
        String positiveUrl = null;
        String reverseUrl = null;
        for (int i = 0; i < paths.length - 1; i++) {
            try {
                dir = dir + "/" + paths[i + 1];
                File file = new File(dir);
                if (!file.exists()) {
                    file.mkdir();
                }
            } catch (Exception err) {
                logger.debug("ELS - Chart : 文件夹创建发生异常");
            }
        }

        Long time = new Date().getTime();
        try {
            byte[] bytePositive = image.getBytes();
            //获取上传图片的后缀名
            String prefix = image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf("."));
            //正面保存路径
            Path pathPositive = Paths.get(positiveReverse + time + "Positive" + prefix);
            positiveUrl = "192.168.1.119" + "/" + getYmd() + "/identify/" + time + "Positive" + prefix;
            logger.debug("正面保存路径" + pathPositive);
            logger.debug("访问路径" + positiveUrl);
            //反面保存路径
            Path pathReverse = Paths.get(positiveReverse + time + "Reverse" + prefix);
            reverseUrl = "192.168.1.119" + "/" + getYmd() + "/identify/" + time + "Reverse" + prefix;
            logger.debug("反面保存路径" + pathReverse);
            logger.debug("访问路径" + reverseUrl);
            Files.write(pathPositive, bytePositive);


        } catch (IOException e) {
            e.printStackTrace();
        }
        Result result = new Result();
        Map map = new HashMap();
        map.put("positiveUrl", positiveUrl);
        map.put("tip", tip);
        map.put("tip1", tip1);
        result.setCode(200);
        result.setData(map);
        result.setMsg("上传成功");
        return result;
    }

    /**
     * 获得年月日
     *
     * @return
     */
    public static String getYmd() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(new Date());
    }

    @PostMapping("/uploading1")
    public Result uploading(@NotNull(message = "图片不能为空") @RequestParam("image") MultipartFile image) {

        //身份证正反面保存路径
        String positiveReverse = "/usr/local/java/resources/xiaopiaoimage/" + "/" + getYmd() + "/identify/";
        // /判断路径是否存在不存在就创建
        String paths[] = positiveReverse.split("/");
        String dir = paths[0];
        String positiveUrl = null;
        String reverseUrl = null;
        for (int i = 0; i < paths.length - 1; i++) {
            try {
                dir = dir + "/" + paths[i + 1];
                File file = new File(dir);
                if (!file.exists()) {
                    file.mkdir();
                }
            } catch (Exception err) {
                logger.debug("ELS - Chart : 文件夹创建发生异常");
            }
        }

        Long time = new Date().getTime();
        try {
            byte[] bytePositive = image.getBytes();
            //获取上传图片的后缀名
            String prefix = image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf("."));
            //正面保存路径
            Path pathPositive = Paths.get(positiveReverse + time + "Positive" + prefix);
            positiveUrl = "192.168.1.119" + "/" + getYmd() + "/identify/" + time + "Positive" + prefix;
            logger.debug("正面保存路径" + pathPositive);
            logger.debug("访问路径" + positiveUrl);
            //反面保存路径
            Path pathReverse = Paths.get(positiveReverse + time + "Reverse" + prefix);
            reverseUrl = "192.168.1.119" + "/" + getYmd() + "/identify/" + time + "Reverse" + prefix;
            logger.debug("反面保存路径" + pathReverse);
            logger.debug("访问路径" + reverseUrl);
            Files.write(pathPositive, bytePositive);


        } catch (IOException e) {
            e.printStackTrace();
        }
        Result result = new Result();
        Map map = new HashMap();
        map.put("positiveUrl", positiveUrl);
        result.setCode(200);
        result.setData(map);
        result.setMsg("上传成功");
        return result;
    }

}
