package com.mfq.constants;

public enum SortType {
    
    DEFAULT(0, "默认排序", "viewNum", "desc"), // 默认按浏览量排序
    PRICE_LOW(1, "价格最低", "price", "asc"),
    PRICE_HIGH(2, "价格最高", "price", "desc"),
    SALES_HIGH(3, "销量最高", "saleNum", "desc");
    
    int id;
    String name;
    String field;
    String order;
    
    public static SortType fromId(int id){
        for(SortType s : SortType.values()){
            if(s.getId() == id){
                return s;
            }
        }
        return null;
    }
    
    SortType(int id, String name, String field, String order){
        this.id = id;
        this.name = name;
        this.field = field;
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
    
    
}
