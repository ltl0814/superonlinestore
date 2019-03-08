package com.synnex.superonlinestore.service;

import com.synnex.superonlinestore.dao.entity.Db_Cart;
import com.synnex.superonlinestore.dao.entity.DetailResult;
import com.synnex.superonlinestore.util.JsonEntity;
import org.springframework.stereotype.Service;


public interface CartService {

    void addToCart(int uid, int gid, int count);

    public void editCount (int uid, int gid , int newCount);

    public void deletaOneFromCart(int uid,int gid);

    public void deleteAllFromCart(int uid);

    public boolean payCart(int uid);

    public DetailResult queryCart(int uid);

}
