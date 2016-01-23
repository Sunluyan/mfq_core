package com.mfq.service;

import java.math.BigDecimal;
import java.util.*;

import javax.annotation.Resource;

import com.mfq.dao.HospitalMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.mfq.bean.Hospital;
import com.mfq.bean.Product;
import com.mfq.bean.ProductDetail;
import com.mfq.bean.ProductImg;
import com.mfq.bean.app.ProductInfo2App;
import com.mfq.bean.app.ProductListItem2App;
import com.mfq.bean.area.City;
import com.mfq.constants.Constants;
import com.mfq.constants.ProductFlag;
import com.mfq.constants.ProductType;
import com.mfq.constants.SortType;
import com.mfq.dao.ProductDetailMapper;
import com.mfq.dao.ProductImgMapper;
import com.mfq.dao.ProductMapper;
import com.mfq.dao.area.CityMapper;
import com.mfq.utils.DateUtil;
import com.mfq.utils.FQUtil;
import com.mfq.utils.ListSortUtil;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory
            .getLogger(ProductService.class);

    @Resource
    ProductMapper mapper;

    @Resource
    ProductDetailMapper detailMapper;
    
    @Resource
    ClassifyService classifyService;

    @Resource
    HospitalService hospitalService;
    
    @Resource
    ProductImgMapper productImgMapper;
    
    @Resource
    CityMapper cityMapper;

    @Resource
    HospitalMapper hospitalMapper;
    @Resource
    ProductMapper productMapper;


    public ProductInfo2App buildInfo(long pid) {
        Product product = findById(pid);
        ProductDetail detail = findDetailById(pid);
        Hospital hospital = hospitalService.findById(product.getHospitalId());
        return new ProductInfo2App(product, detail, hospital);
    }

    public String buildAppUrl(long pid) {
        return "http://" + Constants.SITE_DOMAIN + "/product/app/detail?pid="+pid;
    }

    public Product findValidProduct(long pid) throws Exception {
        Product p = findById(pid);
        if (p!=null && DateUtil.getDayBetweenD(p.getDateStart(), new Date()) >= 0
                && DateUtil.getDayBetweenD(new Date(), p.getDateEnd()) >= 0) {
            return p;
        } else {
        	if(p!=null){
        		logger.error("产品有效期 is {}|{}", p.getDateStart(), p.getDateEnd());
        	}
        	logger.error("该产品售卖超过有效期限制！");
            throw new Exception("该产品售卖超过有效期限制！");
        }
    }

    public ProductDetail findDetailById(long pid) {
        return detailMapper.findByPid(pid);
    }

    public Product findById(long id) {
        return mapper.findById(id);
    }

    public long insertProduct(Product p) {
        return mapper.insertProduct(p);
    }

    public List<ProductListItem2App> findByClass(int city, int category,
            int sort, int type) {
    	SortType sortType = SortType.fromId(sort);
    	ProductType pType = ProductType.fromId(type);
    	if(sortType == null){
    		sortType = SortType.fromId(0);
    	}
    	if(pType == null){
    		pType = ProductType.fromId(0);
    	}
    	List<Integer> categorys = classifyService.findClassIdsByRootId(category);
    	categorys.add(category);
        List<Product> list = mapper.findByClass(city, categorys, null);  //productType传人null ，所有类型
        List<ProductListItem2App> lists=convert2AppList(list);
        
        try{
	        if(!CollectionUtils.isEmpty(lists)){
	        	ListSortUtil<ProductListItem2App> sortList = new ListSortUtil<ProductListItem2App>();
	        	sortList.sort(lists, sortType.getField(), sortType.getOrder()); 
	        }
        }catch(Exception e){
        	e.printStackTrace();
        }
        return lists;
        
    }


	public List<ProductListItem2App> findByFlag(int city, ProductFlag recommend, int type) {
		ProductType productType = ProductType.fromId(type);
		if(productType == null){
			productType = ProductType.fromId(0);
		}
        List<Product> list = mapper.findByFlag(city, recommend.getId(), null);
        return convert2AppList(list);
    }

    /**
     * 封装product
     * 
     * @param ps
     * @return
     */
    public List<ProductListItem2App> convert2AppList(List<Product> ps) {
        List<ProductListItem2App> list = Lists.newArrayList();
        if (CollectionUtils.isEmpty(ps)) {
            logger.warn("ProductList is empty!");
            return null;
        }
        for (Product pt : ps) {
            ProductListItem2App bean = new ProductListItem2App();
            BeanUtils.copyProperties(pt, bean);
            City city = cityMapper.findById(pt.getCityId());
            bean.setCity(city.getName());
  
            Map<Integer, BigDecimal> fq = FQUtil.fenqiCompute(pt.getPrice());
            bean.setFq(fq);
            bean.setMarketPrice(pt.getMarketPrice());
            bean.setSubsidy(pt.getMarketPrice().subtract(pt.getPrice()));
            bean.setUrl("http://" + Constants.SITE_DOMAIN + "/product/app/detail?pid="+pt.getId());
            list.add(bean);
        }
        return list;
    }

    /**
     * 通过关键字搜索 产品名称和医院名
     * 第一步,先通过key搜索医院名
     * 第二步,再通过 key,医院id 搜索产品名
     * @param keyword
     * @param page
     * @return
     *
     */
    public List<ProductListItem2App> findProductByKeyword(String keyword,long page){
        // 搜索产品分页
        long pagesize = 20;
        long start = (page-1)*pagesize;
        String[] keywords = keyword.split(" ");
        List<Hospital> hospitals = hospitalMapper.selectByKeywords(keywords);
        List<Product> productsByPro = productMapper.selectByKeywordsAndHospitalIds(keywords,hospitals,start,pagesize);
        List<ProductListItem2App> result = convert2AppList(productsByPro);
        return result;
    }

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring.xml");
        ProductService service = ac.getBean(ProductService.class);
        service.findProductByKeyword("水",1);
    }


    public long addSaleNum(long pid){
        return mapper.updateProcuctSaleNum(pid);
    }

	public long addViewNum(long pidNo) {
		return mapper.updateViewNum(pidNo);
	}

	public ProductDetail findProductDetailByPid(long id) {
		return detailMapper.findByPid(id);
	}

	public List<Product> queryProductsByType(ProductType type) {
		return mapper.queryProductsByType(type);
	}

	public long updateProductRemainNum(long uid, long pid, int num) throws Exception {
        
		Product product = findValidProduct(pid);
        if (product == null) {
            logger.error("Notice: product={}, operate num={}", product, num);
            throw new Exception("产品不存在");
        }
        // 判断剩余数量
        if (product.getRemainNum()+num < 0) {
            throw new Exception("产品数量不足。");
        }
        long l = mapper.updateProductRemain(pid, num, product.getRemainNum());
        logger.info("更新用户{}余额度由{}到{}", pid, product.getRemainNum(),
        		product.getRemainNum()+num);
        return l;
	}

	public List<ProductImg> findProductImg(long id) {
		return productImgMapper.findByPid(id);
	}

}