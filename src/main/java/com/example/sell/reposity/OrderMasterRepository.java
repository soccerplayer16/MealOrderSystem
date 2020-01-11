package com.example.sell.reposity;

import com.example.sell.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

    /**
     * 通过openid查找订单
     * @param buyerOpenid
     * @param pageable
     * @return
     */
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
