package com.mfq.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.google.common.collect.Lists;
import com.mfq.bean.Favorites;
import com.mfq.bean.Product;
import com.mfq.bean.app.ProductListItem2App;
import com.mfq.dao.FavoritesMapper;

/**
 * 收藏夹
 * 
 * @author hui
 *
 */
@Service
public class FavoritesService {

	@Resource
	HospitalService hospitalService;
	@Resource
	FavoritesMapper mapper;
	@Resource
	ProductService productService;

	public List<ProductListItem2App> findByUid(long uid) {
		List<Favorites> list = mapper.findByUid(uid);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		return convert2AppList(list);
	}

	public long deleteByUids(long uid, String[] pids) {
	    List<Long> list = Lists.newArrayList();
		for (String pid : pids) {
            if (!StringUtils.isNumeric(pid)) {
				return 0;
			}
			Long l = Long.parseLong(pid);
			list.add(l);
		}
		return mapper.deleteByPids(uid, list);
	}

	private List<ProductListItem2App> convert2AppList(List<Favorites> flist) {
		if (CollectionUtils.isEmpty(flist)) {
			return null;
		}
		LinkedList<Product> ps = Lists.newLinkedList();
		for (Favorites fone : flist) {
			Product pt = productService.findById(fone.getPid());
			ps.add(pt);
		}
		return productService.convert2AppList(ps);
	}

	public long insertByUidAndPid(long uid, long pid) {
		Product pt = productService.findById(pid);
		if(pt == null){
			return 0;
		}
		Favorites fav = mapper.findByUidAndPid(uid, pid);
		if(fav != null && fav.getUid() > 0){//如果已经有了,就删除
			List<Long> pids = new ArrayList();
			pids.add(pid);
			return mapper.deleteByPids(uid,pids);
		}else if(fav == null){//没有就加入
			fav = new Favorites();
		}
		fav.setUid(uid);
		fav.setPid(pid);
		fav.setUpdated(new Date());		
		return mapper.insertFavorite(fav);
	}

	public Favorites findByPidAndUid(long pid, long uid) {
		return mapper.findByUidAndPid(uid, pid);
	}

	public int isCollect(long pid, long uid){
		Favorites favorites = findByPidAndUid(pid,uid);
		if(favorites == null){
			return 0;
		}else{
			return 1;
		}
	}




}
