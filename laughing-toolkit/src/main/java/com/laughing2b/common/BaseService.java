package com.laughing2b.common;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @ClassName: BaseService
 * @Description: 所有服务类的超级父类
 * @author lifei.pan
 * @email plfnet@163.com
 * @date 2015年8月25日 下午4:35:41
 * 
 * @param <D>
 * @param <T>
 */
public class BaseService<D extends BaseMapper<T>, T extends BaseEntity> {

	@Autowired
	protected D mapper;

	/**
	 * @Title: queryById
	 * @Description: 根据ID查询一条数据
	 * @param @param id
	 * @param @return 设定文件
	 * @return T 返回类型
	 * @email plfnet@163.com
	 * @throws
	 */
	public T queryById(String id) {
		return mapper.queryById(id);
	}

	/**
	 * @Title: queryList
	 * @Description:查询列表
	 * @param @param entity
	 * @param @return 设定文件
	 * @return List<T> 返回类型
	 * @email plfnet@163.com
	 * @throws
	 */
	public List<T> queryList(T entity) {
		return mapper.queryList(entity);
	}

	/**
	 * 
	 * @Title: queryListWithPage
	 * @Description: 分页查询列表 pageHelper PageInfo 分页参数，起始页码 每一页的大小， 排序
	 * @param @param entity
	 * @param @param pageParam
	 * @param @return 设定文件
	 * @return PageInfo<T> 返回类型
	 * @email plfnet@163.com
	 * @throws
	 */
	public PageInfo<T> queryListWithPage(T entity, PageParam pageParam) {
		// 判断是否含有排序的字符串
		if (StringUtils.isNotBlank(pageParam.getOrderBy())) {
			PageHelper.orderBy(pageParam.getOrderBy());
		}
		PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
		List<T> resultList = mapper.queryList(entity);
		return new PageInfo<T>(resultList);//(resultList,8)加数字表示显示几页
	}

	/**
	 * @Title: save
	 * @Description: 保存数据 id为空时，执行新增，id不为空，修改
	 * @param @param entity
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @email plfnet@163.com
	 * @throws
	 */
	public boolean save(T entity) {
		// 判断Id是否为空
		// "               "
		int res = 0;
		if (StringUtils.isBlank(entity.getId())) {
			entity.preInsert();
			res = mapper.insert(entity);
		} else {
			entity.preUpadate();
			res = mapper.update(entity);
		}
		return res > 0;
	}

	/**
	 * @Title: delete
	 * @Description:删除数据
	 * @param @param entity
	 * @param @return 设定文件
	 * @return boolean 返回类型
	 * @email plfnet@163.com
	 * @throws
	 */
	public boolean delete(T entity) {
		int res = mapper.delete(entity);
		return res > 0;
	}
}
