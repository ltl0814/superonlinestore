package com.synnex.superonlinestore.service;

import com.synnex.superonlinestore.dao.entity.Db_Cart;
import com.synnex.superonlinestore.util.JsonEntity;
import org.springframework.stereotype.Service;


public interface CartService {
    public int addToCart(int uid, int gid);
    public int editCount (int uid,int gid ,int newCount);
    public void deletaOneFromCart(int uid,int gid);
    public void deleteAllFromCart(int uid);
    public int payCart(int uid);
    public JsonEntity queryCart(int uid);

}
