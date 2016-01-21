package com.mfq.service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.mfq.bean.Device;
import com.mfq.bean.DeviceExample;
import com.mfq.controller.AppController;
import com.mfq.dao.DeviceMapper;
import org.apache.commons.collections.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by liuzhiguo1 on 16/1/19.
 */
@Service
public class AppService {
    private static final Logger logger = LoggerFactory
            .getLogger(AppController.class);

    @Resource
    DeviceMapper mapper;

    @Transactional
    public void addIOS(String uuid) throws Exception{
        DeviceExample example = new DeviceExample();
        example.or().andUuidEqualTo(uuid);
        List<Device> list = mapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(list)){
            //说明已经有了,已经有了就更新一下时间
            Device device = list.get(0);
            device.setLastLoginTime(new Date());
            mapper.updateByPrimaryKey(device);
        }else{
            Device device = new Device();
            device.setUuid(uuid);
            mapper.insertSelective(device);
        }
    }

    public static void main(String[] args) throws Exception {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring.xml");
        AppService service = ac.getBean(AppService.class);
        service.addIOS("i am a imei ,motherfucker");
    }
    @Transactional
    public void addAndroid(String imei) throws Exception{
        DeviceExample example = new DeviceExample();
        example.or().andImeiEqualTo(imei);
        List<Device> list = mapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(list)){
            //说明已经有了,已经有了就更新一下时间
            Device device = list.get(0);
            device.setLastLoginTime(new Date());
            mapper.updateByPrimaryKey(device);
        }else{
            Device device = new Device();
            device.setUuid(imei);
            mapper.insertSelective(device);
        }

    }
}
