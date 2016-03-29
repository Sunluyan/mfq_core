package com.mfq.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.mfq.bean.Product;
import com.mfq.bean.app.HospitalDetail2App;
import com.mfq.bean.app.ProductListItem2App;
import com.mfq.utils.JsonUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.mfq.bean.Hospital;
import com.mfq.dao.HospitalMapper;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HospitalService {

    @Resource
    HospitalMapper mapper;
    @Resource
    ProductService productService;

    public Hospital findById(long id) {
        return mapper.findById(id);
    }

    public long insertHospital(Hospital h) {
        return mapper.insertHospital(h);
    }

    public List<Hospital> queryHospitals() {
        return mapper.findAll();
    }

    @Transactional(readOnly = true)
    public List<Map<String, Object>> findHospitalByCity(long cityId) {
        //  Auto-generated method stub
        List<Hospital> hospitals = mapper.findByCity(cityId);
        return findHospitalApp(hospitals);
    }


    public List<Map<String, Object>> findHospitalApp(List<Hospital> hospitals) {

        List<Long> names = new ArrayList<Long>();
        for (Hospital hos : hospitals) {
            names.add(hos.getId());
        }
        List<Map<String, Object>> hospitalProCount = findProCount(names);

        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        for (Hospital hos : hospitals) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", hos.getId());
            map.put("name", hos.getName());
            map.put("img", hos.getImg());
            map.put("address", hos.getAddress());
            for (Map<String, Object> pro : hospitalProCount) {
                long id = Long.parseLong(pro.get("id").toString());
                if (id == hos.getId()) {
                    map.put("total", pro.get("total"));
                    hospitalProCount.remove(pro);
                    break;
                }
            }
            result.add(map);
        }
        return result;
    }


    public List<Map<String, Object>> findAll() {
        //  Auto-generated method stub
        List<Hospital> hospitals = mapper.findAll();

        return findHospitalApp(hospitals);
    }

    public List<Map<String, Object>> findProCount(List<Long> hosid) {
        return mapper.findProCount(hosid);
    }

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring.xml");
        HospitalService service = ac.getBean(HospitalService.class);
        service.getDetailById(0);
    }
    public String getDetailById(Integer hosId) {

        if (hosId <= 0) {
            HospitalDetail2App h = new HospitalDetail2App("fuck");
            Product p = productService.findById(231);
            List<Product> list = new ArrayList<>();
            list.add(p);
            List<ProductListItem2App> app = productService.convert2AppList(list);
            h.setPros(app);
            return JsonUtil.successResultJson(h);
        } else {
            Hospital hospital = mapper.findById(hosId);
            HospitalDetail2App h = new HospitalDetail2App(hospital);

            List<Product> pros = productService.findHotByHospital(hosId.longValue());

            List<ProductListItem2App> app = productService.convert2AppList(pros);
            h.setPros(app);
            return JsonUtil.successResultJson(h);
        }
    }

    public HospitalDetail2App getDetailById2Web(Integer hosId) {

        if (hosId <= 0) {
            HospitalDetail2App h = new HospitalDetail2App("fuck");
            Product p = productService.findById(231);
            List<Product> list = new ArrayList<>();
            list.add(p);
            List<ProductListItem2App> app = productService.convert2AppList(list);
            h.setPros(app);
            return h;
        } else {
            Hospital hospital = mapper.findById(hosId);
            HospitalDetail2App h = new HospitalDetail2App(hospital);

            List<Product> pros = productService.findHotByHospital(hosId.longValue());

            List<ProductListItem2App> app = productService.convert2AppList(pros);
            h.setPros(app);
            return h;
        }
    }
}





