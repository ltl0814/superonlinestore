package com.synnex.superonlinestore.service.impl;

import com.synnex.superonlinestore.dao.entity.Db_Cart;
import com.synnex.superonlinestore.dao.entity.Goods;
import com.synnex.superonlinestore.dao.repository.CartRepository;
import com.synnex.superonlinestore.service.CartService;
import com.synnex.superonlinestore.util.JsonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImp implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Override
    public int addToCart(int uid, int gid) {
        Db_Cart cart = new Db_Cart();
        return 0;
    }

    @Override
    public int editCount(int uid, int gid,int newCount) {
        Db_Cart cart = cartRepository.findByUidAndGid(uid,gid);
        return 1;
    }



    @Override
    public int deletaOneFromCart(int uid ,int gid) {
        int a = cartRepository.deleteByUidAndGid(uid,gid);
        if(a == 1) return 1;
        else return 0;
    }

    @Override
    public int deleteAllFromCart(int uid) {
        int a = cartRepository.deleteByUid(uid);
        if(a >= 1) return 1;
        else return 0;
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
