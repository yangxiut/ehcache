package org.yxt.ehcache;

import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.xml.XmlConfiguration;

import java.net.URL;


/**
 * 缓存工具类
 * java 缓存 ehcache 需要依赖 slf4j-api-1.7.25.jar maven在自动引入
 *
 * @author helloword
 * @date 2018/12/25
 */
public class EhcacheManagerUtil {

    private CacheManager cacheManager;

    private static volatile EhcacheManagerUtil instance;

    private EhcacheManagerUtil() {
    }

    private URL EHCACHE_XML_PATH = this.getClass().getResource("/ehcache.xml");

    /**
     * 单例模式 xml
     *
     * @return
     */
    public static EhcacheManagerUtil getInstance() {
        if (null == instance) {
            synchronized (EhcacheManagerUtil.class) {
                if (null == instance) {
                    instance = new EhcacheManagerUtil();
                    URL url = instance.EHCACHE_XML_PATH;
                    CacheManager cManager = CacheManagerBuilder.newCacheManager(new XmlConfiguration(url));
                    cManager.init();
                    instance.setCacheManager(cManager);
                }
            }
        }
        return instance;
    }

    public CacheManager getCacheManager() {
        return cacheManager;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }
/**
 *
 * 使用xml
 */
//    public EhcacheManagerUtil(){
//        System.out.println("[Ehcache配置初始化<开始>]");
//        // 配置默认缓存属性
//        cacheManager = CacheManagerBuilder.newCacheManager(new XmlConfiguration(getClass().getResource("/ehcache.xml")));
//        cacheManager.init();
//        System.out.println("[Ehcache配置初始化<完成>]");
//    }


    /**
     * 初始化Ehcache缓存对象无xml
     */
//    public EhcacheUtil() {
//        System.out.println("[Ehcache配置初始化<开始>]");
//
//        // 配置默认缓存属性
//        CacheConfiguration<String, String> cacheConfiguration = CacheConfigurationBuilder.newCacheConfigurationBuilder(
//                // 缓存数据K和V的数值类型
//                // 在ehcache3.3中必须指定缓存键值类型,如果使用中类型与配置的不同,会报类转换异常
//                String.class, String.class,
//                ResourcePoolsBuilder
//                        .newResourcePoolsBuilder()
//                        //设置缓存堆容纳元素个数(JVM内存空间)超出个数后会存到offheap中
//                        .heap(1000L, EntryUnit.ENTRIES)
//                        //设置堆外储存大小(内存存储) 超出offheap的大小会淘汰规则被淘汰
//                        .offheap(100L, MemoryUnit.MB)
//                        // 配置磁盘持久化储存(硬盘存储)用来持久化到磁盘,这里设置为false不启用
//                        .disk(500L, MemoryUnit.MB, false)
//                // 缓存淘汰策略 默认策略是LRU（最近最少使用）。你可以设置为FIFO（先进先出）或是LFU（较少使用）。
//                // 这块还没看
//                /*.withEvictionAdvisor(
//                new OddKeysEvictionAdvisor<Long, String>())*/
//        ).build();
//        // CacheManager管理缓存
//        cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
//                // 硬盘持久化地址
//                .with(CacheManagerBuilder.persistence("e:/ehcacheData"))
//                // 设置一个默认缓存配置
//                .withCache("defaultCache", cacheConfiguration)
//                //创建之后立即初始化
//                .build(true);
//
//        System.out.println("[Ehcache配置初始化<完成>]");
//    }
}
