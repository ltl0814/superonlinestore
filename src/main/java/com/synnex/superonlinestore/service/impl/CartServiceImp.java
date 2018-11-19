package com.synnex.superonlinestore.service.impl;

import com.synnex.superonlinestore.dao.entity.Db_Cart;
import com.synnex.superonlinestore.dao.entity.Goods;
import com.synnex.superonlinestore.dao.repository.CartRepository;
import com.synnex.superonlinestore.dao.repository.GoodsRepository;
import com.synnex.superonlinestore.service.CartService;
import com.synnex.superonlinestore.util.JsonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImp implements CartService {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    GoodsRepository goodsRepository;

    @Override
    public int addToCart(int uid, int gid) {
        Db_Cart cart = new Db_Cart();
        Goods goods = goodsRepository.findByGid(gid);
        return 0;
    }

    @Override
    public int editCount(int uid, int gid,int newCount) {
        Db_Cart cart = cartRepository.findByUidAndGid(uid,gid);
        return 1;
    }



    @Override
    public void deletaOneFromCart(int uid ,int gid) {
        cartRepository.deleteByUidAndGid(uid,gid);
    }

    @Override
    public void deleteAllFromCart(int uid) {
        cartRepository.deleteByUid(uid);
    }

    @Override
    public int payCart(int uid) {
        return 0;
    }

    @Override
    public JsonEntity queryCart(int uid) {
        return null;
    }
}
