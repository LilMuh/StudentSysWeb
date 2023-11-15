package com.tomh.studentspringboot.controller;

import com.github.pagehelper.PageInfo;
import com.tomh.studentspringboot.common.Result;
import com.tomh.studentspringboot.entity.Essay;
import com.tomh.studentspringboot.entity.Params;
import com.tomh.studentspringboot.entity.Player;
import com.tomh.studentspringboot.entity.PokerTable;
import com.tomh.studentspringboot.service.EssayService;
import com.tomh.studentspringboot.service.TexasService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/admin/TexasHoldem")
public class TexasController {

    @Resource
    private TexasService texasService;

    @PostMapping("/startGame")
    public Result startGame(@RequestBody Player player){
        PokerTable pt =  texasService.startGame(player);
        return Result.success(pt);
    }

}
