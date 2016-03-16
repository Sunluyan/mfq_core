package com.mfq.service;

import java.math.BigDecimal;
import java.util.*;

import javax.annotation.Resource;

import com.google.common.collect.Maps;
import com.mfq.bean.*;
import com.mfq.bean.app.ProductDetail2App;
import com.mfq.dao.*;
import com.mfq.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.mfq.bean.app.ProductInfo2App;
import com.mfq.bean.app.ProductListItem2App;
import com.mfq.bean.area.City;
import com.mfq.constants.Constants;
import com.mfq.constants.ProductFlag;
import com.mfq.constants.ProductType;
import com.mfq.constants.SortType;
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
    @Resource
    ProFqRecordMapper preFqRecordMapper;

    @Resource
    FavoritesService favoritesService;


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
    		sortType = SortType.fromId(1);
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
        //查找所有医院
        List<Hospital> hospitals = hospitalService.queryHospitals();
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

            for (Hospital hospital : hospitals) {
                if(pt.getHospitalId() == hospital.getId()){
                    bean.setHospitalName(hospital.getName());
                    bean.setHosName(hospital.getName());
                }
            }

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

    public BigDecimal selectFqPriceByPid(Long pid) {
        try{
            ProFqRecordExample example = new ProFqRecordExample();
            example.or().andPidEqualTo(pid.intValue());
            BigDecimal price = BigDecimal.valueOf(preFqRecordMapper.selectByExample(example).get(0).getPeriodPay());
            return price;
        }catch(Exception e){ //如果没有的话返回null
            return null;
        }
    }

    public BigDecimal selectPriceOrFqPrice(Long pid) throws Exception {
        try{
            ProFqRecordExample example = new ProFqRecordExample();
            example.or().andPidEqualTo(pid.intValue());
            List<ProFqRecord> fqs =preFqRecordMapper.selectByExample(example);
            if(fqs.size()>0){
                return null;
            }
            BigDecimal price = BigDecimal.valueOf(fqs.get(0).getPeriodPay());
            return price;
        }catch(Exception e){ //如果没有的话返回产品价格
            Product p = findById(pid);
            return p.getPrice();
        }
    }

    public List<Product> selectByPids(String pids){
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < pids.split(",").length; i++) {
            list.add(Long.parseLong(pids.split(",")[i]));
        }
        return mapper.selectByPids(list);
    }

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring.xml");
        ProductService service = ac.getBean(ProductService.class);
        List<Product> list = service.selectByPids("223,222,221,");
        for (Product product : list) {
            System.out.println(product.toString());
        }
    }


    public String getProductDetail(long uid, long pid) throws Exception {

        Product product = productMapper.findById(pid);
        if(product == null){
            return JsonUtil.toJson(1001,"无此产品...",null);
        }
        List<ProductImg> productImgs = productImgMapper.findByPid(pid);
        Hospital hospital = hospitalMapper.findById(product.getHospitalId());


        ProductDetail2App app = new ProductDetail2App();

        if(uid == 0){
            app.setIs_collect(0);
        }else {
            Favorites favorites = favoritesService.findByPidAndUid(pid, uid);
            if(favorites == null){
                app.setIs_collect(0);
            }else {
                app.setIs_collect(1);
            }
        }


        List<String> Imgs = Lists.newArrayList();
        for(ProductImg img:productImgs){
            Imgs.add(img.getImg());
        }

        app.setProduct_imgs(Imgs);
        app.setProduct_name(product.getName());
        app.setProduct_detail("此产品....");
        app.setCheap_reason("八一特惠.");
        app.setProduct_price(product.getPrice());
        app.setOriginal_price(product.getMarketPrice());

        Map<Integer, BigDecimal> fq = FQUtil.fenqiCompute(selectPriceOrFqPrice(pid));// 分期得计算规则
        if(fq == null){
            app.setIs_fq(1);
        }
        app.setFqs(fq);

        Map<String, String> diary = Maps.newHashMap();
        diary.put("对比前","http://7xl0ie.com2.z0.glb.qiniucdn.com/img1%2Fp%2F20150930%2F14435889570384P5.jpg");
        diary.put("对比后","http://7xl0ie.com2.z0.glb.qiniucdn.com/img1%2Fp%2F20150930%2F14435889570384P5.jpg");
        diary.put("水光针","http://7xl0ie.com2.z0.glb.qiniucdn.com/img1%2Fp%2F20150930%2F14435889570384P5.jpg");
        diary.put("水光证","http://7xl0ie.com2.z0.glb.qiniucdn.com/img1%2Fp%2F20150930%2F14435889570384P5.jpg");
        app.setDiary(diary);

        Map<String, String> documentary = Maps.newHashMap();
        documentary.put("对比前","http://7xl0ie.com2.z0.glb.qiniucdn.com/img1%2Fp%2F20150930%2F14435889570384P5.jpg");
        documentary.put("对比后","http://7xl0ie.com2.z0.glb.qiniucdn.com/img1%2Fp%2F20150930%2F14435889570384P5.jpg");
        documentary.put("第一日","http://7xl0ie.com2.z0.glb.qiniucdn.com/img1%2Fp%2F20150930%2F14435889570384P5.jpg");
        documentary.put("第二日","http://7xl0ie.com2.z0.glb.qiniucdn.com/img1%2Fp%2F20150930%2F14435889570384P5.jpg");
        app.setDocumentary(documentary);

        Map<String, String> notice = Maps.newHashMap();
        notice.put("第一注意事项","号网络快点撒拉飞机萨菲拉斯的疯狂就爱上");
        notice.put("第二注意事项","奥斯卡了对方就撒了附加赛飞进去我尽量克服决赛的");
        notice.put("第三注意事项","阿斯蒂芬金坷垃是否能想自驾来上课的减肥");
        notice.put("第四注意事项","阿斯顿离开房间我去哦放进来撒打开就飞洒");

        app.setNotice(notice);

        List<String> details = Lists.newArrayList();
        details.add("http://7xl0ie.com2.z0.glb.qiniucdn.com/images%2Fchristmas_new.png");


        app.setHospital_icon(hospital.getImg());
        app.setHospital_name(hospital.getName());
        app.setHospital_url("http://m.5imfq.com/hospital/"+hospital.getId());

        return JsonUtil.successResultJson(app);
    }
}