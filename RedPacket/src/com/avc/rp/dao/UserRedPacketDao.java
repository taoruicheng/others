package com.avc.rp.dao;

import org.springframework.stereotype.Repository;

import com.avc.rp.model.UserRedPacket;

@Repository
public interface UserRedPacketDao {
    
    /**
     * 插入抢红包信息
     * @param userRedPacket 抢红包信息
     * @return 影响的记录数
     * */
    public int grapRedPacket(UserRedPacket userRedPacket);
}