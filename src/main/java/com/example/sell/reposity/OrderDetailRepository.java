package com.example.sell.reposity;

import com.example.sell.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
    // master --> multiple rows in order detail repo, so return a list
    List<OrderDetail> findByOrderId(String openId);
}
