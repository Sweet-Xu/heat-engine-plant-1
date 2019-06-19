package cc.hep.system.controller;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Random;


@Controller
@RequestMapping("generator")
public class GeneratorController {

    @RequestMapping("info")
    @RequiresPermissions("generator:list")
    public String index() {
        return "system/generator/monitor";
    }

    @RequestMapping("statistics")
    @RequiresPermissions("generator:statistics")
    public String statistics() {
        return "system/generator/statistics";
    }
/*
    @RequestMapping("getStatistics")
    @RequiresPermissions("generator:statistics")
    public String getStatistics() {
        return "system/generator/statistics";
    }*/

    @RequestMapping("generator1Info")
    @ResponseBody
    public String getGenerator1Info() {
        return getRandomGeneratorInfo();
    }

    @RequestMapping("generator2Info")
    @ResponseBody
    public String getGenerator2Info() {
        return getRandomGeneratorInfo();
    }

    @RequestMapping("generator3Info")
    @ResponseBody
    public String getGenerator3Info() {
        return getRandomGeneratorInfo();
    }

    @RequestMapping("generator4Info")
    @ResponseBody
    public String getGenerator4Info() {
        return getRandomGeneratorInfo();
    }

    private String getRandomGeneratorInfo() {
        long time = new Date().getTime();
        Random random = new Random();
        int generationCapacity = (int) (random.nextInt(300) + 1000);
        String randomTemperature = (int) (random.nextInt(60) + 70) + "";
        String res = "{\"create_time\":"+time+",\"generationCapacity\":\""+ generationCapacity +"\",\"temperature\":\""+ randomTemperature +"\"}";
        return res;
    }





}
