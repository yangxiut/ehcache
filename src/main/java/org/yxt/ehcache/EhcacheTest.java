package org.yxt.ehcache;

import com.alibaba.fastjson.JSONObject;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.yxt.ehcache.pojo.UserInfo;


/**
 * java 缓存 ehcache 需要依赖 slf4j-api-1.7.25.jar maven在自动引入
 *
 * @author helloword
 * @date 2018/12/25
 */
public class EhcacheTest {

    public static void main(String[] args) {
        CacheManager cacheManager = EhcacheManagerUtil.getInstance().getCacheManager();
        //defaultCache为ehcache.xml 配置
        Cache<String, String> myCache = cacheManager.getCache("cache_user_info", String.class, String.class);
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1);
        userInfo.setUserName("杨秀腾");
        userInfo.setHeight(174);
        myCache.put("userInfoCache", JSONObject.toJSONString(userInfo));

        System.out.println(myCache.get("userInfoCache"));
        cacheManager.close();
    }
}
