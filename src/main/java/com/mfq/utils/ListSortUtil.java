package com.mfq.utils;

import com.mfq.bean.area.City;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *  List按照指定字段排序工具类
 *
 * @param <T>
 */
public class ListSortUtil<T> {

    /**
	 * @param targetList 目标排序List
	 * @param sortField 排序字段(实体类属性名)
	 * @param sortMode 排序方式（asc or  desc）
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void sort(List<T> targetList, final String sortField, final String sortMode) {
	
		Collections.sort(targetList, new Comparator() {
			public int compare(Object obj1, Object obj2) { 
				int retVal = 0;
				try {
					//首字母转大写
					String newStr=sortField.substring(0, 1).toUpperCase()+sortField.replaceFirst("\\w",""); 
					String methodStr="get"+newStr;
					
					//特殊处理
					if(sortField.length()>2&&sortField.substring(1, 2).toUpperCase().equals(sortField.substring(1, 2))){
						methodStr = "get"+sortField;
					}
					Method method1 = ((T)obj1).getClass().getMethod(methodStr, null);
					Method method2 = ((T)obj2).getClass().getMethod(methodStr, null);
					
					Object m2 = method2.invoke(((T) obj2), null);
					Object m1 = method1.invoke(((T) obj1), null);
					if (sortMode != null && "desc".equals(sortMode)) {
						
						if(m2 instanceof BigDecimal){
							retVal = ((BigDecimal) m2).compareTo((BigDecimal) m1);
						}else if(m2 instanceof Date){
							retVal = ((Date) m2).compareTo((Date) m1);
						}else if(m2 instanceof Map){
							retVal = getMinValue(m2).compareTo(getMinValue(m1));
						}else{
							retVal = m2.toString().compareTo(m1.toString()); // 倒序
						}
					} else {
						if(m1 instanceof BigDecimal){
							retVal = ((BigDecimal) m1).compareTo((BigDecimal) m2); // 正序
						}else if(m1 instanceof Date){
							retVal = ((Date) m1).compareTo((Date) m2);
						}else if(m2 instanceof Map){
							retVal = getMinValue(m1).compareTo(getMinValue(m1));
						}else{
							retVal = m1.toString().compareTo(m2.toString()); // 正序
						}
						
					}
				} catch (Exception e) {
					
					throw new RuntimeException();
				}
				return retVal;
			}
		});
	}
	
	/**
	* 求Map<K,V>中Value(值)的最小值
	* @param map
	* @return
	*/
	public static BigDecimal getMinValue(Object o) {
		Map<Integer, BigDecimal> map = (Map<Integer, BigDecimal>)o;
		if (map == null) 
			return null;
		Collection<BigDecimal> c = map.values();
		Object[] obj = c.toArray();
		Arrays.sort(obj);
		return (BigDecimal)obj[0];
	}

	public static List<City> qcf(List<City> list){
		for (int i = 0; i < list.size() - 1; i++) {                             //循环遍历集体中的元素
			for (int j = list.size() - 1; j > i; j--) {                         //这里非常巧妙，这里是倒序的是比较
				if (list.get(j).getId()==list.get(i).getId()) {
					list.remove(j);
				}
			}
		}
		return list;
	}
	
	
}
