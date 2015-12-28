package com.mfq.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.google.common.collect.Maps;
import com.mfq.helper.SignHelper;
import com.mfq.utils.HttpUtil;
import com.mfq.utils.JsonUtil;

public class RressureTest {
	
	
	public static void main(String[] args) {
		

//		
//		System.out.println();
		RressureTest t= new RressureTest();
//		String ret = t.getSeckillingProduct();
//		Map<String, Object> m= JsonUtil.getMapFromJsonStr(ret);
//		System.out.println(m.get("data").toString());
//		List<Map<String,Object>> js = (ArrayList<Map<String,Object>>)m.get("data");
		
		int [] st= {48,49,50,51,52,53,54,55,56,57,58,59,60,103};
//		for(Map<String,Object> p:js){
//			System.out.println(p.get("id")+"====="+p.get("name"));
////			st[i]=p.get("id");
//		}
		
		try{
		ExecutorService pool = Executors.newFixedThreadPool(2000);
		// 创建多个有返回值的任务  
		   List<Future> list = new ArrayList<Future>();  
		   for (int i = 0; i < 2000; i++) {  
		    Callable c = new MyCallable(i + " ");  
		    // 执行任务并获取Future对象  
		    Future f = pool.submit(c);  
		    // System.out.println(">>>" + f.get().toString());  
		    list.add(f);  
		   }  
		   // 关闭线程池  
		   pool.shutdown(); 
		   
		   Date date1 = new Date(); 
		   
		// 获取所有并发任务的运行结果  
		   for (Future f : list) {  
		    // 从Future对象上获取任务的返回值，并输出到控制台  
		    System.out.println(">>>" + f.get().toString());  
		   }  
		  
		   Date date2 = new Date();  
		   System.out.println("----程序结束运行----，程序运行时间【"  
		     + (date2.getTime() - date1.getTime()) + "毫秒】");  
		  
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	
	public int getRem(){
		int y=0;
		Random r=new Random();
		y = r.nextInt(14);
		return y;
	}
	
	public String createSeckillingOrder(String uid, String id){
		
        String url = "http://i.5imfq.com/activity/doSeckilling?uid="+uid+"&code=234534&id="+id;
        Map<String, Object> params = Maps.newHashMap();
        params.put("uid", 201);
        params.put("c", 58.6);
        params.put("pid", 7);
        params.put("coupon_num","200");
        String sign = SignHelper.makeSign(params);
        params.put("sign", sign);
        String body = JsonUtil.writeToJson(params);
        String resp = HttpUtil.get(url, false);
        System.out.println(resp);
		
		return null;
	}
	
	public String getSeckillingProduct(){
		
		 String url = "http://i.5imfq.com/product/product_type";
	        Map<String, Object> params = Maps.newHashMap();
	        params.put("uid", 201);
	        params.put("type", 3);
	        String sign = SignHelper.makeSign(params);
	        params.put("sign", sign);
	        String body = JsonUtil.writeToJson(params);
	        String resp = HttpUtil.post(url, body, true);
	        System.out.println(resp);
	        
		return resp;
	}

}

class MyCallable implements Callable<Object> {  
	private String taskNum;  
	private int [] st= {48,49,50,51,52,53,54,55,56,57,58,59,60,103};
	MyCallable(String taskNum) {  
	   this.taskNum = taskNum;  
	}  
	  
	public Object call() throws Exception {  
	   System.out.println(">>>" + taskNum + "任务启动");  
	   Date dateTmp1 = new Date();  
//	   Thread.sleep(1000);
		RressureTest t = new RressureTest();


		int y=t.getRem();
		System.out.println(st[y]);
		t.createSeckillingOrder("201", st[y]+"");
		
	   Date dateTmp2 = new Date();  
	   long time = dateTmp2.getTime() - dateTmp1.getTime();  
	   System.out.println(">>>" + taskNum + "任务终止");  
	   return taskNum + "任务返回运行结果,当前任务时间【" + time + "毫秒】";  
	}  
}
