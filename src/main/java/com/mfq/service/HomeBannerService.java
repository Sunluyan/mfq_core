package com.mfq.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mfq.bean.HomeBanner;
import com.mfq.dao.HomeBannerMapper;


@Service
public class HomeBannerService {

    @Resource
    HomeBannerMapper mapper;

    public List<HomeBanner> queryHomeBanners() {
        return mapper.queryAll();
    }

	public long deleteBanner(long id) {
		
		return mapper.deleteHomeBanner(id);
	}

	public HomeBanner queryHomeBanner(long id) {
		return mapper.findById(id);
	}
	
}


