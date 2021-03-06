package com.shiro.demo.multirealms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import com.shiro.demo.multirealms.JdbcTemplateUtils;
import com.shiro.demo.multirealms.dao.PermissionDao;
import com.shiro.demo.multirealms.entity.Permission;

/**
 * @author AmVilCres
 * @desc 
 * @date 2018年10月7日
 */
public class PermissionDaoImpl implements PermissionDao {
	
	private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.jdbcTemplate();

	@Override
	public Permission createPermission(Permission permission) {
		final String sql = "insert into sys_permissions(permission, description, available) values(?,?,?)";
		
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement psst = connection.prepareStatement(sql, new String[] {"id"});
				psst.setString(1, permission.getPermission());
				psst.setString(2, permission.getDescription());
				psst.setBoolean(3, permission.getAvailable());
				return psst;
			}
		},keyHolder);
		permission.setId(keyHolder.getKey().longValue());
		return permission;
	}

	@Override
	public void deletePermission(Long permissionId) {
		// 首先把与permission关联的相关表数据删掉
		String sql = "delete from sys_roles_permissions where permission_id=?";
		jdbcTemplate.update(sql, permissionId);
		
		sql = "delete from sys_permissions where id=?";
		jdbcTemplate.update(sql, permissionId);
	}

}
