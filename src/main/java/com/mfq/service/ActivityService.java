package com.mfq.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mfq.bean.Hospital;
import com.mfq.bean.OrderInfo;
import com.mfq.bean.Product;
import com.mfq.bean.app.OrderInfo2App;
import com.mfq.bean.app.ProductInfoItem;
import com.mfq.bean.user.User;
import com.mfq.bean.user.UserExtend;
import com.mfq.constants.ErrorCodes;
import com.mfq.constants.OrderType;
import com.mfq.constants.PayType;
import com.mfq.constants.ProductType;
import com.mfq.dao.UserExtendMapper;
import com.mfq.dataservice.context.UserIdHolder;
import com.mfq.payment.BasePaymentService;
import com.mfq.payment.PayAPIType;
import com.mfq.payment.PayFactory;
import com.mfq.service.user.UserExtendService;
import com.mfq.service.user.UserQuotaService;
import com.mfq.service.user.UserService;
import com.mfq.service.wechat.WeChatService;
import com.mfq.utils.JsonUtil;

@Service
public class ActivityService {
    
	private static final Logger logger = LoggerFactory
            .getLogger(ActivityService.class);
	
    @Resource
    UserExtendMapper userExtendMapper;
    @Resource
    UserService userService;
    @Resource
    UserExtendService extendService;
    @Resource
    ProductService productService;
    @Resource
    HospitalService hospitalService;
    @Resource
    WeChatService weChatService;
    @Resource
    OrderService orderService;
    @Resource
    UserQuotaService userQuotaService;
    @Resource
    PayService payService;
    @Resource
    PayRecordService payRecordService;

	public List<ProductInfoItem> getItemsByType(ProductType type) {
		List<Product> plist = productService.queryProductsByType(type);
		return convert2AppList(plist);
	}
	
	public List<ProductInfoItem> convert2AppList(List<Product> plist){
		List<ProductInfoItem> list = Lists.newArrayList();
		for(Product p: plist){
			Hospital hospital = hospitalService.findById(p.getHospitalId());
			ProductInfoItem item = new ProductInfoItem(p, hospital);
			list.add(item);
		}
		return list;
	}

	
	public String seckingProduct(String code, long uid, long pid) throws Exception {
		if(uid <= 0){
			return JsonUtil.toJson(ErrorCodes.CORE_NEED_LOGIN, "用户未登录", null);
		}
		User user = userService.queryUser(uid);
		if(user == null || user.getUid() <= 0){
			return JsonUtil.toJson(ErrorCodes.USER_NOT_FIND, "用户不存在", null);
		}
		UserExtend extend = extendService.getUserExtendByUid(uid);
		if(extend == null || extend.getUid() <=0){
			return JsonUtil.toJson(1001, "用户不是邀请用户", null);
		}
		code = code.toLowerCase();
		if(!code.equals(extend.getInviteCode().toLowerCase())){
			return JsonUtil.toJson(1002, "邀请码错误", null);
		}
		if(orderService.getValidBalance(uid).compareTo(BigDecimal.valueOf(1))<0){
			return JsonUtil.toJson(1003, "用户余额不足1元", null);
		}
		
		List<OrderInfo> orders = orderService.getSeckillingProduct(uid);
		logger.info("=======order size==={}={}==", orders.size(), uid);
		if(orders.size() > 0){
			return JsonUtil.toJson(1005, "秒杀活动只限参与一次", null);
		}
		Product product = productService.findById(pid);
		if(product.getRemainNum() < 1){
			return JsonUtil.toJson(1006, "产品已被强完！！！", null);
		}
		//秒杀产品
		OrderInfo2App order = orderService.createOrder(PayType.FULL, uid, pid, product.getPrice(), product.getPrice(), BigDecimal.valueOf(0), product.getPrice(), 0, BigDecimal.valueOf(0), "", 0, new Date());
		if(order == null || order.getOrder_no() == null){
			return JsonUtil.toJson(1004, "订单生成失败！！！", null);
		}
		String ret = goPay(order);

		return ret;
	}
	
	@Transactional
	private String goPay(OrderInfo2App order) throws Exception{
		logger.info("秒杀进入gopay order is {}|{}|{}", order.getOrder_no(), order.getPrice(), order.getPay_type());
		OrderType orderType = payService.getOrderType(order.getOrder_no());
		BasePaymentService service = PayFactory.getInstance(PayAPIType.INNER);
		payService.getOrderType(order.getOrder_no());
		long s = payRecordService.saveRecord(orderType, UserIdHolder.getLongUid(), order.getOrder_no(),
				order.getPrice());
		if(s>0){
			
		}
		Map<String, Object> params = Maps.newHashMap();
		params.put("order_no", order.getOrder_no());
		params.put("amount", order.getPrice());
		String result = service.goPay(null, null, params, orderType);
		return result;
	}


}
