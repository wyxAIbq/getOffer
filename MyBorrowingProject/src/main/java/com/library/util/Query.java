package com.library.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 查询参数
 * @author 王彦鑫
 * @date 2018-09-27
 */
public class Query extends LinkedHashMap<String, Object> {
    private static final long serialVersionUID = 1L;
    //当前页码
    private int page;
    //每页条数
    private int limit = 10;

    public Query(Map<String, Object> params) {
        this.putAll(params);

        //分页参数
        this.page = Integer.parseInt(params.get("page").toString());
        this.limit = Integer.parseInt(params.get("limit").toString());
        //当前开始记录索引
        this.put("offset", (page - 1) * limit);
        this.put("page", page);
        this.put("limit", limit);

        String sidx = params.get("sidx").toString();
        String order = params.get("order").toString();
        this.put("sidx", sidx);
        this.put("order", order);
    }


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}

