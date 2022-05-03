package com.lagou.edu.controller;

import com.lagou.edu.dao.ResumeDao;
import com.lagou.edu.pojo.Resume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author WangHaoCun
 * @since 2020-10-26
 **/
@RestController
@RequestMapping("/resume")
public class ResumeController {

    @Autowired
    private ResumeDao resumeDao;

   @RequestMapping("/list")
    public HashMap<String,Object> list() {
      List<Resume>  result=resumeDao.findAll();
       HashMap<String,Object> resultMap=new HashMap<>(16);
       resultMap.put("data",result);

        return resultMap;
    }

    @RequestMapping("/add")
    public String add(@RequestBody Resume r) {
       System.out.println(r.toString());
        resumeDao.save(r);
        return "success";
    }

    @RequestMapping("/edit")
    public String edit(@RequestBody  Resume r ){
        System.out.println(r.toString());
        resumeDao.save(r);
        return "success";
    }

    @RequestMapping("/delete")
    public String delete(@RequestBody Resume r) {
        System.out.println(r.toString());
       resumeDao.deleteById(r.getId());
        return "success";
    }

}

