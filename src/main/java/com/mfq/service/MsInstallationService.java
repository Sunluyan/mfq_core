package com.mfq.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mfq.bean.Installation;
import com.mfq.dao.MsInstallationMapper;

@Service
public class MsInstallationService {

    @Resource
    MsInstallationMapper mapper;
    
    public Installation queryInstallationByDeviceTypeAndDeviceToken(String deviceType,
            String deviceToken){
        return mapper.queryInstallationByDeviceTypeAndDeviceToken(deviceType, deviceToken);
    }

    public Installation createInstallation(Installation installation){
        long id = mapper.createInstallation(installation);
        if(id <= 0){
            return null;
        }
        installation.setId(id);
        return installation;
    }

    public boolean updateInstallations(Installation installation){
        return mapper.updateInstallations(installation);
    }    
}