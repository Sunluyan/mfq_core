package com.mfq.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.mfq.bean.ProductClassify;
import com.mfq.dao.ClassifyMapper;

@Service
public class ClassifyService {
    
    @Resource
    ClassifyMapper mapper;
    
    public void buildClassifyInfo(int rootId, Map<Integer, List<ProductClassify>> lm, Map<Integer, ProductClassify> sm){
        List<ProductClassify> list = findByRootId(rootId);
        if(CollectionUtils.isEmpty(list)){
            return ;
        }
        if(lm.get(rootId) == null){
            lm.put(rootId, list);
        }
        for(ProductClassify pc : list){
            if(sm.get(pc.getId()) == null){
                sm.put(pc.getId(), pc);
            }
            buildClassifyInfo(pc.getId(), lm, sm);
        }
    }

    public long insertOne(ProductClassify pc){
        return mapper.insertOne(pc);
    }
    
    public ProductClassify findById(int id){
        return mapper.findById(id);
    }
    
    public List<ProductClassify> findByRootId(int rootId){
        return  mapper.findByRootId(rootId);
    }

	public List<Integer> findClassIdsByRootId(int rootId) {
		return mapper.findClassIdsByRootId(rootId);
	}


}
