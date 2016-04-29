package com.mfq.service.activity;

import com.mfq.bean.Didi;
import com.mfq.bean.DidiExample;
import com.mfq.controller.ActivityController;
import com.mfq.dao.DidiMapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by liuzhiguo1 on 16/4/29.
 */
@Service
public class DidiService {
    private static final Logger logger = LoggerFactory
            .getLogger(DidiService.class);

    @Resource
    DidiMapper mapper;

    public void insertMobile(String mobile, long uid, String pids) throws Exception {
        String[] pidArray = pids.split(",");
        for (String s : pidArray) {
            if(StringUtils.isBlank(s)){
                continue;
            }
            Didi didi = new Didi();
            didi.setCreatedAt(new Date());
            didi.setUid(uid);
            didi.setMobile(mobile);
            didi.setUpdatedAt(new Date());
            didi.setPid(Integer.parseInt(s));
            int count = mapper.insertSelective(didi);
            if (count != 1) {
                throw new RuntimeException("插入出错");
            }
        }
    }

    public boolean selectByMobile(String mobile, int pid) throws Exception {
        DidiExample example = new DidiExample();
        example.or().andMobileEqualTo(mobile).andPidEqualTo(pid);
        List<Didi> list = mapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(list)) {
            return true;
        } else {
            return false;
        }
    }
    public boolean selectByUid(long uid, int pid) throws Exception {
        DidiExample example = new DidiExample();
        example.or().andUidEqualTo(uid).andPidEqualTo(pid);
        List<Didi> list = mapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(list)) {
            return true;
        } else {
            return false;
        }
    }


    public void updateByMobile(String mobile, int pid) throws Exception {
        DidiExample example = new DidiExample();
        example.or().andMobileEqualTo(mobile).andPidEqualTo(pid);
        List<Didi> list = mapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(list)) {
            Didi didi = list.get(0);
            didi.setStatus(1);
            int count = mapper.updateByPrimaryKey(didi);
            if (count != 1) {
                throw new RuntimeException("更新优惠券状态出错");
            }
        } else {
            throw new RuntimeException("用户没有此优惠券");
        }
    }
    public void updateByUid(long uid , int pid) throws Exception {
        DidiExample example = new DidiExample();
        example.or().andUidEqualTo(uid).andPidEqualTo(pid);
        List<Didi> list = mapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(list)) {
            Didi didi = list.get(0);
            didi.setStatus(1);
            int count = mapper.updateByPrimaryKey(didi);
            if (count != 1) {
                throw new RuntimeException("更新优惠券状态出错");
            }
        } else {
           throw new RuntimeException("用户没有此优惠券");
        }
    }



}
