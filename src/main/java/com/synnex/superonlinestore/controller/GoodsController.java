package com.synnex.superonlinestore.controller;

import com.synnex.superonlinestore.service.GoodsService;
import com.synnex.superonlinestore.util.JsonEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import com.synnex.superonlinestore.dao.entity.Goods;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.*;
import java.util.List;
import java.util.UUID;

/**
 * @Auther: dustinl
 * @Date: 11/16/18 17:15
 * @Description:
 */
@RestController
@Slf4j
@Api(value = "商品管理", description = "商品的crud")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @ApiOperation(value = "后台查询全部商品", produces = "application/json")
    @GetMapping("/public/api/backend/goods")
    public JsonEntity getAllGoods(@RequestParam(value = "start",defaultValue = "0")int start,
                                  @RequestParam(value = "size",defaultValue = "5")int size){
        log.info("进入分页查询");
        start = start < 0 ? 0:start;
        Sort sort = new Sort(Sort.Direction.ASC,"gid"); //设置根据id倒序排列
        Pageable pageable = new PageRequest(start, size,sort);
        Specification<Goods> spec=new Specification<Goods>() {
            @Override
            public Predicate toPredicate(Root<Goods> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                  Predicate predicate=null;
                  predicate=criteriaBuilder.equal(root.get("status"),"1");
                  query.where(predicate);
                return null;
            }
        };
        return new JsonEntity("查询全部商品成功",true,goodsService.getAllGoods(spec,pageable));
    }

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
        return new JsonEntity( "添加成功",true, goodsService.addGoods(goods));
    }

    @ApiOperation(value = "后台查询单个商品", produces = "application/json")
    @GetMapping("/public/api/backend/goods/{gid}")
    public JsonEntity getGoods(@PathVariable("gid") int gid){
        log.info("查询商品："+gid);
        return new JsonEntity("查询单个商品成功",true, goodsService.findone(gid));
    }

    @ApiOperation(value = "后台商品编辑", produces = "application/json")
    @PutMapping("/public/api/backend/goods")
    public JsonEntity setGoods(Goods goods){
        return new JsonEntity("商品编辑成功",true,goodsService.saveGoods(goods));
    }


    @ApiOperation(value = "根据gIdList查询商品列表", produces = "application/json")
    @GetMapping("/public/api/goods")
    public JsonEntity GetGoodsListByGid(@RequestParam List<Integer> gIdList){
        return new JsonEntity("根据gIdList查询商品列表"
                ,true
                ,goodsService.findAllByGidList(gIdList));
    }

    @ApiOperation(value = "查询最新的12个商品", produces = "application/json")
    @GetMapping("/public/api/goods/recent")
    public JsonEntity getGoodsListByRecent(){
        return new JsonEntity("查询最新的12个商品成功"
                ,true, goodsService.getRecentGoods());
    }

    @ApiOperation(value = "根据商品名字模糊查询商品", produces = "application/json")
    @GetMapping("/public/api/goods/title")
    public JsonEntity getGoodsListByTitle(@RequestParam String name){
        return new JsonEntity("根据商品名字模糊查询商品成功"
                ,true,goodsService.getByLikeName(name));
    }

    @ApiOperation(value = "查询最热的12个商品", produces = "application/json")
    @GetMapping("/public/api/goods/stock")
    public JsonEntity getGoodsListByStock(){
        log.info("进入查询");
        return new JsonEntity("查询最热的12个商品",true, goodsService.getHotGoods());
    }

    @ApiOperation(value = "根据商品gid删除商品", produces = "application/json")
    @DeleteMapping("/public/api/goods/{gid}")
    public JsonEntity deleteGoodsById(@PathVariable("gid") Integer gid){
        goodsService.deleteGoods(gid);
        log.info("删除商品："+gid);
        JsonEntity jsonEntity=new JsonEntity("删除商品"+gid+"成功",true, null);
        return jsonEntity;
    }
    @ApiOperation("后台管理界面映射")
    @GetMapping("/backend")
    public ModelAndView backend(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/Administer/index");
        return mv;
    }
}
