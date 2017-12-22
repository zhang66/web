package com.blueocean.web.base;

import java.util.List;

public interface BaseDao<T> {
	long count(Filter filter);
	/**
	 * 保存
	 * @param t
	 * @return 
	 */
	void save(T t);
	/**
	 * 批量保存
	 * @param list
	 */
	long saveBatch(List<T> list);
	/**
	 * 更新
	 * @param t
	 */
	void update(T t);
	/**
	 * 删除
	 * @param id
	 */
	void delete(Integer id);
	/**
	 * 获取单个实体
	 * @param id
	 * @return
	 */
	T get(Integer id);
	/**
	 * 获取对象集合
	 * @param filter 
	 * @return
	 */
	List<T> getForList(Filter filter);
	/**
	 * 根据线索id获取对象
	 * @param clueId
	 * @return
	 */
	List<T> getByClueId(Integer clueId);
}
