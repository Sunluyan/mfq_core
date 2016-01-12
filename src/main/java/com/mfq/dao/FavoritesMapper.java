package com.mfq.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mfq.annotation.MFQDao;
import com.mfq.bean.Favorites;
import org.springframework.stereotype.Component;

@MFQDao
@Component
public interface FavoritesMapper {

	public List<Favorites> findByUid(@Param("uid") long uid);
    
	public long insertFavorite(Favorites favorites);
	
	public long insertFavorites(@Param("list")List<Favorites> favorites);
	
	public long deleteByPids(@Param("uid") long uid, @Param("list") List<Long> list);

	public Favorites findByUidAndPid(@Param("uid") long uid,@Param("pid") long pid);

	public long updateUpdateTime(Favorites favorites);

	
}
