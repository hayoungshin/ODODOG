package com.odd.recipe.model.service;

import static com.odd.common.JDBCTemplate.close;
import static com.odd.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import com.odd.common.model.vo.PageInfo;
import com.odd.recipe.model.dao.RecipeDao;
import com.odd.recipe.model.vo.Recipe;


public class RecipeService {
	
	public int selectListCount() {
		Connection conn = getConnection();
		int count = new RecipeDao().selectListCount(conn);
		close(conn);
		return count;
	}
	
	public ArrayList<Recipe> selectList(PageInfo pi) {
		Connection conn = getConnection();
		ArrayList<Recipe> list = new RecipeDao().selectList(conn, pi);
		close(conn);
		return list;
	}

}
