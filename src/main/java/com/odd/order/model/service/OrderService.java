package com.odd.order.model.service;

import static com.odd.common.JDBCTemplate.*;

import java.sql.Connection;

import com.odd.order.model.dao.OrderDao;
import com.odd.product.model.vo.UserProduct;

public class OrderService {

	public UserProduct productFoodOrder (int productNo) {
	
		Connection conn = getConnection();
		UserProduct p = new OrderDao().productFoodOrder(conn,productNo);
		close(conn);
		return p;
		
	}
}
