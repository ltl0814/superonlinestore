package com.synnex.superonlinestore.dao.entity;

import java.io.Serializable;

public class GoPK implements Serializable {

    private int oid;
    private int gid;

    @Override
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final GoPK other = (GoPK) obj;
        if(this.gid == other.gid && this.oid == other.oid)
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result +  oid * gid;
        return result;
    }
}
