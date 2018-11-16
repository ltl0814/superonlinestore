package com.synnex.superonlinestore.dao.entity;

import java.io.Serializable;

public class CartPK implements Serializable {

    private int uid;
    private int gid;

    @Override
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final CartPK other = (CartPK) obj;
        if(this.gid == other.gid && this.uid == other.uid)
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result +  uid * gid;
        return result;
    }
}
