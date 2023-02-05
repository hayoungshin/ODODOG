package com.odd.order.model.service;

import static com.odd.common.JDBCTemplate.close;
import static com.odd.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import com.odd.common.model.vo.PageInfo;
import com.odd.order.model.dao.AdminOrderDao;
import com.odd.order.model.vo.AdminOrdSearch;
import com.odd.order.model.vo.AdminOrder;
import com.odd.order.model.vo.OrdPro;

public class AdminOrderService {
	
	public int selectListCount() {
		
		Connection conn = getConnection();
		
		int result = new AdminOrderDao().selectListCount(conn);
		close(conn);
		
		return result;
		
	}
	
	
	public ArrayList<AdminOrder> selectList(PageInfo pi, String order, String by){
		
		Connection conn = getConnection();
		
		ArrayList<AdminOrder> list = new AdminOrderDao().selectList(conn, pi, order, by);
		close(conn);
		
		return list;
		
	}
	
	public ArrayList<AdminOrder> searchList(AdminOrdSearch ordSearch){
		
		Connection conn = getConnection();
		
		ArrayList<AdminOrder> list = new AdminOrderDao().searchList(conn, ordSearch);
		close(conn);
		
		return list;
		
	}
	
	public AdminOrder selectOrder(int ordNo) {
		Connection conn = getConnection();
		
		AdminOrder o = new AdminOrderDao().selectOrder(conn, ordNo);
		close(conn);
		
		return o;
	}
	
	public ArrayList<OrdPro> selectOrdPro(int ordNo){
		Connection conn = getConnection();
		
		ArrayList<OrdPro> list = new AdminOrderDao().selectOrdPro(conn, ordNo);
		close(conn);
		
		return list;
	}

}
