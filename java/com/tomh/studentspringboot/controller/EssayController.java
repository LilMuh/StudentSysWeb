package com.tomh.studentspringboot.controller;

import com.github.pagehelper.PageInfo;
import com.tomh.studentspringboot.common.Result;
import com.tomh.studentspringboot.entity.Essay;
import com.tomh.studentspringboot.entity.Params;
import com.tomh.studentspringboot.service.EssayService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/essay")
public class EssayController {

    @Resource
    private EssayService essayService;

    @GetMapping("/search")
    public Result search(Params params){
        PageInfo<Essay> info =  essayService.search(params);
        return Result.success(info);
    }

    @PostMapping
    public Result save(@RequestBody Essay essay){
        if(essay.getId()==0){
            essayService.add(essay);
        }else{
            essayService.update(essay);
        }
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable int id){
        essayService.delete(id);
        return Result.success();
    }

}
