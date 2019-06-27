package com.library.controller;

import com.library.entity.Book;
import com.library.entity.Person;
import com.library.service.PersonService;
import com.library.util.JqgridPageResp;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * controller
 * @author //标识它是一个控制器
 * @Date2016年12月9日上午11:25:40
 */
@Controller                                   //使用该注解标志它是一个控制器
@RequestMapping(value = "/person")           //用来处理请求地址映射，value指请求的实际地址
public class PersonController {

    @Autowired
    public PersonService personService;

    public void addOrEdit(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> map) {
        if (id != null) {
            map.put("person", personService.getById(id));
        }
    }

    @RequestMapping("/login")
    public String dologin(String username, String password, Map<String, Object> map) {
        if ("admin".equals(username) && "admin".equals(password)) {
            //存放在request请求域中
            map.put("username", username);
            /**
             * 类上加上@SessionAttributes({"username"}) 同时也会存放在 session域中
             * @SessionAttributes 除了可以通过属性名指定需要存放到会话中的属性外(使用的是value属性值)
             * 还可以通过模型属性的对象类型指定哪些模型属性需要放到会话中(实际上使用的是types属性值),
             */
            //这里会自动加上前缀后缀
            return "/personList";
        }
        return "vue";
    }


    @RequestMapping(value = "/person", method = RequestMethod.POST)
    public String save(Person person) {
        personService.save(person);
        return "redirect:/person/persons";
    }

    /**
     * 显示添加的页面
     */
    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public String toAddPerson(Map<String, Object> map) {
        map.put("person", new Person());
        return "/addPerson";
    }

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public String findAll(Map<String, Object> map) {
        List<Person> persons = personService.findAll();
        map.put("persons", persons);
        return "/personList";
    }

    @RequestMapping(value = "/person/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id) {
        personService.delete(id);
        System.out.println("删除成功" + id);
        return "redirect:/person/persons";
    }

    /**
     * 显示更新页面
     */
    @RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
    public String getById(@PathVariable("id") Integer id, Map<String, Object> map) {
        Person person = personService.getById(id);
        map.put("person", person);
        System.out.println("www" + person);
        return "/addPerson";
    }

    @RequestMapping(value = "/person/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String getPerson(@PathVariable("id") Integer id) {
        Person person = personService.getById(id);
        System.out.println(person);
        return "bq521";
    }

    @RequestMapping(value = "/person", method = RequestMethod.PUT)
    public String update(Person person) {
        personService.update(person);
        return "redirect:/person/persons";
    }


    @RequestMapping(value = "wyx")
    @ResponseBody
    public List<Person> haha(@RequestBody String obj) {
        System.out.println(obj);
        List<Person> persons = personService.findAll();
        System.out.println(obj);
        return persons;
    }

    @RequestMapping(value = "/InJqgrid/{search}", method = RequestMethod.GET)
    public String InJqgrid(@PathVariable("search") String search, Map<String, Object> map) {
        try {
            URLDecoder.decode(search,"UTF-8");
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        map.put("search", search);
        return "Jqgrid";
    }

    /**
     * 手机号归属地查询，调用接口实例
     */
    public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

    /**
     * 配置您申请的KEY
     */
    public static final String APPKEY = "9484af1cd91413683d4cd193219b5854";

    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/phone/{phone}", method = RequestMethod.GET)
    /**
     * 1.手机归属地查询
     */
    public static String getRequest1(@PathVariable("phone") String phone, Map<String, Object> map) {
        String result = null;
        //请求接口地址
        String url = "http://apis.juhe.cn/mobile/get";
        //请求参数
        Map params = new HashMap();
        //需要查询的手机号码或手机号码前7位
        params.put("phone", phone);
        //应用APPKEY(应用详细页查询)
        params.put("key", APPKEY);
        //返回数据的格式,xml或json，默认json
        params.put("dtype", "json");


        try {
            result = net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            map.put("phone", object.get("result"));
            if (object.getInt("error_code") == 0) {
                System.out.println(object.get("result"));
            } else {
                System.out.println(object.get("error_code") + ":" + object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "phone";
    }


    /**
     * @param strUrl 请求地址
     * @param params 请求参数
     * @param method 请求方法
     * @return 网络请求字符串
     * @throws Exception
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static String net(String strUrl, Map params, String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if (method == null || "GET".equals(method)) {
                strUrl = strUrl + "?" + urlencode(params);
                System.out.println(urlencode(params));
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if (method == null || "GET".equals(method)) {
                conn.setRequestMethod("GET");
            } else {
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            //不允许使用缓存
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params != null && "POST".equals(method)) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.writeBytes(urlencode(params));
                    System.out.println(out);
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }

            }
            /*接收来自接口的数据封装成String*/
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }

    /**
     * 将map型转为请求参数型
     */
    @SuppressWarnings("rawtypes")
    public static String urlencode(Map<String, String> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
    *图书馆bib接口访问
    **/
    public static final String DEF_CHATSET1 = "UTF-8";
    public static final int DEF_CONN_TIMEOUT1 = 30000;
    public static final int DEF_READ_TIMEOUT1 = 30000;
    public static String Authorization = "Basic ZnFOK09tckNQZTMreGtLODBqTHU2K3dxNjJIYzpzeHNsbHh3eXg=";

    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/bib/{search}", method = RequestMethod.GET)
    @ResponseBody
    public static Object getRequest2(@PathVariable("search") String search) {
        String result = null;
        String token = null;
        Object access_token = null;
        Object token_type = null;

        JSONArray bibarray = null;

        String fields = "title,author";
        //请求接口地址
        String url = "https://webpac.uestc.edu.cn/iii/sierra-api/v3/token";
        ArrayList<String> strArray = new ArrayList<String>();

        String index = search;
        String text = search;
        System.out.println("查詢字符串="+search);
        //请求参数
        Map params = new HashMap();
        params.put("fields", fields);
        //需要查询的图书关键字
        params.put("index", index);
        //需要查询的图书文本
        params.put("text", text);
        try {
            token = gettoken(url, "POST");
            JSONObject token_object = JSONObject.fromObject(token);
            access_token = token_object.get("access_token");
            token_type = token_object.get("token_type");
            String str = token_type.toString();
            //第一个字母大写
            str = str.replace(str.substring(0, 1), str.substring(0, 1).toUpperCase());
            url = "https://webpac.uestc.edu.cn:443/iii/sierra-api/v4/bibs/search";
            result = net2(url, params, "GET", access_token, str);
            JSONObject bibobject = JSONObject.fromObject(result);
            String entries = bibobject.getString("entries");
            JSONArray entries_array = JSONArray.fromObject(entries);
            int size = entries_array.size();
            for (int i = 0; i < size; i++) {
                JSONObject bib = entries_array.getJSONObject(i);
                strArray.add(bib.getString("bib"));
            }
            bibarray = JSONArray.fromObject(strArray);
            System.out.println("查詢結果="+bibarray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JqgridPageResp<Book> resp = new JqgridPageResp<Book>();
        resp.setRows(bibarray);
        return resp;
    }

    public static String gettoken(String strUrl, String method) throws Exception {

        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if (method == null || "POST".equals(method)) {
                conn.setRequestMethod("POST");
            }
            conn.setRequestProperty("Authorization", Authorization);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT1);
            conn.setReadTimeout(DEF_READ_TIMEOUT1);
            conn.setInstanceFollowRedirects(false);
            conn.connect();

            /*接收来自接口的数据封装成String*/
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET1));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }

    /**
     * @param strUrl 请求地址
     * @param params2 请求参数
     * @param method 请求方法
     * @return 网络请求字符串
     * @throws Exception
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static String net2(String strUrl, Map params2, String method, Object access_token, String token_type) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if (method == null || "GET".equals(method)) {
                strUrl = strUrl + "?" + urlencode2(params2);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if (method == null || "GET".equals(method)) {
                conn.setRequestMethod("GET");
            } else {
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("Authorization", token_type + " " + access_token);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT1);
            conn.setReadTimeout(DEF_READ_TIMEOUT1);
            conn.setInstanceFollowRedirects(false);
            conn.connect();

            /*接收来自接口的数据封装成String*/
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET1));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);

            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }

    /**
     * 将map型转为请求参数型
     */
    @SuppressWarnings("rawtypes")
    public static String urlencode2(Map<String, String> data2) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data2.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
