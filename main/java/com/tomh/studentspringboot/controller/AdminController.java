package com.tomh.studentspringboot.controller;

import com.github.pagehelper.PageInfo;
import com.tomh.studentspringboot.common.Result;
import com.tomh.studentspringboot.entity.Admin;
import com.tomh.studentspringboot.entity.Params;
import com.tomh.studentspringboot.service.AdminService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    @PostMapping
    public Result save(@RequestBody Admin admin){
        if(admin.getId()==0){
            adminService.add(admin);
        }else{
            adminService.update(admin);
        }
        return Result.success();
    }

    //login method
    @PostMapping("/login")
    public Result login(@RequestBody Admin admin){
        Admin userLogin = adminService.login(admin);
        return Result.success(userLogin);
    }

    //register method
    @PostMapping("/register")
    public Result register(@RequestBody Admin admin){
        adminService.add(admin);
        return Result.success();
    }

    @GetMapping("/getAdmin")
    public Result getAdmin(){
        List<Admin> list = adminService.getAdmin();
        return Result.success(list);
    }

    @GetMapping("/search")
    public Result search(Params params){
        PageInfo<Admin> info =  adminService.search(params);
        return Result.success(info);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable int id){
        adminService.delete(id);
        return Result.success();
    }
}
