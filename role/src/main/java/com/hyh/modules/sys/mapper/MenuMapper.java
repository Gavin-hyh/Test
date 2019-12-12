/**
 * Copyright &copy; Edwin All rights reserved.
 */
package com.hyh.modules.sys.mapper;

import com.hyh.core.persistence.BaseMapper;
import com.hyh.modules.sys.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单MAPPER接口
 * @author Edwin
 * @version 2019-08-29
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

	public int updateParentIds(Menu menu);
	
	public int updateSort(Menu menu);

    int deleteRoleMenu(Menu menu);

}
