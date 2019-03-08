package com.synnex.superonlinestore.service;

import com.synnex.superonlinestore.dao.entity.DetailResult;

import java.util.List;

public interface OrderService {


    List<DetailResult> userOrder(int uid);

    List<DetailResult> allOrder();

    void editOrder(int uid,int oid);

    List<DetailResult> findOrder(int oid);

    void deleteOrder(int oid);

}
