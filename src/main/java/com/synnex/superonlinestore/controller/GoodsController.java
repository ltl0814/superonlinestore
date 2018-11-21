package com.synnex.superonlinestore.controller;

import com.synnex.superonlinestore.service.GoodsService;
import com.synnex.superonlinestore.util.JsonEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import com.synnex.superonlinestore.dao.entity.Goods;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.UUID;

/**
 * @Auther: kobef
 * @Date: 11/16/18 17:15
 * @Description:
 */
@RestController
@Api(value = "商品管理", description = "curd")
public class GoodsController {
    @Autowired
    GoodsService goodsService;

    //后台查询全部商品
    @ApiOperation(value = "后台查询全部商品", produces = "application/json")
    @GetMapping("/public/api/backend/goods")
    public JsonEntity getAllGoods(@RequestParam(value = "start",defaultValue = "0")int start,
                                  @RequestParam(value = "size",defaultValue = "5")int size){
        start = start < 0 ? 0:start;
        Sort sort = new Sort(Sort.Direction.ASC,"gid"); //设置根据id倒序排列
        Pageable pageable = new PageRequest(start, size,sort);
        return goodsService.getAllGoods(pageable);
    }

    //后台添加商品
    @ApiOperation(value = "后台添加商品", produces = "application/json")
    @PostMapping("/public/api/backend/goods")
    public JsonEntity addGoods(Goods goods, @RequestParam(value = "file",required = false)MultipartFile file) throws IOException {
        if (!file.isEmpty()){
            String str="src/main/resources/static/products/hao/";
            String fileName=file.getOriginalFilename();
            //文件重命名
            fileName= UUID.randomUUID().toString().replace("-","")
            + fileName.substring(fileName.lastIndexOf("."));

            File saveFile=new File(str+fileName);
            if (!saveFile.getParentFile().exists()){
                saveFile.getParentFile().mkdirs();
            }
            BufferedOutputStream out=new BufferedOutputStream(new FileOutputStream(saveFile));
            out.write(file.getBytes());
            out.flush();
            out.close();
            goods.setPic(fileName);
        }
        return goodsService.addGoods(goods);
    }

    //后台查询单个商品
    @ApiOperation(value = "后台查询单个商品", produces = "application/json")
    @GetMapping("/public/api/backend/goods/{gid}")
    public JsonEntity getGoods(@PathVariable("gid") Integer gid){
        return goodsService.findone(gid);
    }

    //后台编辑单个商品
    @ApiOperation(value = "后台商品编辑", produces = "application/json")
    @PutMapping("/public/api/backend/goods")
    public JsonEntity setGoods(Goods goods){
        return goodsService.saveGoods(goods);
    }

    //查询商品列表，通过LIST
    @ApiOperation(value = "根据gIdList查询商品列表", produces = "application/json")
    @GetMapping("/public/api/goods")
    public JsonEntity GetGoodsListByGid(@RequestParam List<Integer> gIdList){
        return goodsService.findAllByGidList(gIdList);
    }

    @ApiOperation(value = "查询最新的10个商品", produces = "application/json")
    @GetMapping("/public/api/goods/recent")
    public JsonEntity getGoodsListByRecent(){
        return goodsService.getRecentGoods();
    }

    @ApiOperation(value = "根据商品名字模糊查询商品", produces = "application/json")
    @GetMapping("/public/api/goods/title")
    public JsonEntity getGoodsListByTitle(@RequestParam String name){
        return  goodsService.getByLikeName(name);
    }

    @ApiOperation(value = "查询最热的10个商品", produces = "application/json")
    @GetMapping("/public/api/goods/stock")
    public JsonEntity getGoodsListByStock(){
        return goodsService.getHotGoods();
    }
}
