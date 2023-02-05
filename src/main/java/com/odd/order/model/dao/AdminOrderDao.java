package com.odd.order.model.dao;

import static com.odd.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.odd.common.model.vo.PageInfo;
import com.odd.order.model.vo.AdminOrdSearch;
import com.odd.order.model.vo.AdminOrder;
import com.odd.order.model.vo.OrdPro;
import com.odd.product.model.vo.ProAtt;
import com.odd.product.model.vo.Product;

public class AdminOrderDao {
	
	private Properties prop = new Properties();
	
	public AdminOrderDao() {
		try {
			prop.loadFromXML(new FileInputStream(AdminOrderDao.class.getResource("/db/sql/adminOrder-mapper.xml").getPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int selectListCount(Connection conn){
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return listCount;
		
	}
	
	public ArrayList<AdminOrder> selectList(Connection conn, PageInfo pi, String order, String by){
		
		ArrayList<AdminOrder> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;	
		
		String sql = prop.getProperty("selectList1") + " ORDER BY " + order +" " + by + " " + prop.getProperty("selectList2"); ;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() + -1;
		
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
	        while(rset.next()){	
								list.add(
										new AdminOrder(rset.getInt("주문번호")											
												
												, rset.getString("유저이름")
												, rset.getString("배송지")
												, rset.getString("휴대전화")
												, rset.getString("이메일")
												
												, rset.getInt("최종결제금액")
												
												, rset.getString("결제여부")
												, rset.getString("배송여부")
												
												, rset.getString("배송시요청사항")
												
												)
										);
	         				  }
						
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public ArrayList<AdminOrder> searchList(Connection conn, AdminOrdSearch ordSearch){

		ArrayList<AdminOrder> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		

		String userName = ordSearch.getUserName();
		String lowPrice = ordSearch.getLowPrice();
		String highPrice = ordSearch.getHighPrice();
		
		String sql = prop.getProperty("searchList");
		
	
		if(!userName.equals("")) {
			sql += " AND 유저이름 LIKE '%" + userName + "%'"; 
		}
		if(!lowPrice.equals("")) {
			sql += " AND 최종결제금액 >= " + lowPrice; 
		}
		if(!highPrice.equals("")) {
			sql += " AND 최종결제금액 <= " + highPrice;
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(
							new AdminOrder(
									rset.getInt("주문번호"),
								
									rset.getInt("유저번호"),
									rset.getString("유저이름"),
									rset.getString("배송지"),
									rset.getString("휴대전화"),
									rset.getString("이메일"),
									
									rset.getInt("사용적립금"),
									rset.getInt("최종결제금액"),
									
									
									rset.getString("배송여부"),
									rset.getString("배송시요청사항")									
									)
						);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return list;
		
		
	}
	
	public AdminOrder selectOrder(Connection conn, int ordNo) {
			
			AdminOrder o = null;
			ResultSet rset = null;
			PreparedStatement pstmt= null;
			
			String sql = prop.getProperty("selectOrder");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, ordNo);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()){	
							o = new AdminOrder(rset.getInt("ord_no")
									, rset.getInt("user_no")
									, rset.getString("user_name")
									, rset.getString("del_add")
									, rset.getString("phone")
									, rset.getString("email")
									, rset.getInt("discount")
									, rset.getInt("total_Price")
									, rset.getString("delivery")
									, rset.getString("require")
									);
					
							o.setPayment(rset.getString("payment"));
							o.setDeliveryPrice(rset.getInt("배송비"));
							o.setAddress(rset.getString("address"));
							o.setOrderDate(rset.getString("order_Date"));
							
								
								}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			
			return o;
			
		}
		
	
		public ArrayList<OrdPro> selectOrdPro(Connection conn, int ordNo){
			
			ResultSet rset = null;
			ArrayList<OrdPro> list = new ArrayList<>();
			PreparedStatement pstmt = null;
			OrdPro op = null;
			
			String sql = prop.getProperty("selectOrdPro");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, ordNo);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()){	
							op = new OrdPro(rset.getInt("ord_Pro_No")
									, rset.getInt("ord_No")
									, rset.getInt("pro_No")
									, rset.getInt("amount")
									);
							
							op.setProName(rset.getString("pro_name"));
							op.setPrice(rset.getInt("price"));
							op.setThumbImg(rset.getString("thumb_Img"));
							op.setCategory(rset.getString("category"));
							op.setSave(rset.getDouble("save"));
							
							list.add(op);
							
						}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			
			return list;
		}

}
