package com.hlsofttech.configuration.shiro.cache;

import com.bjucloud.redis.client.RedisAccessException;
import com.bjucloud.redis.client.RedisTemplate;
import com.hlsofttech.constant.JWTConstant;
import com.hlsofttech.util.JwtUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Set;

/**
 * 功能描述: 重写Shiro的Cache保存读取
 *
 * @author suntf
 * @date 2019/2/27 21:14
 */
public class CustomCache<K, V> implements Cache<K, V> {
    @Resource
    RedisTemplate redisTemplate;
    /**
     * redis-key-前缀-shiro:cache:
     */
    public final static String PREFIX_SHIRO_CACHE = "shiro:cache:";

    /**
     * 缓存的key名称获取为shiro:cache:account
     *
     * @param key
     * @return java.lang.String
     * @author suntf
     * @date 2018/9/4 18:33
     */
    private String getKey(Object key) {
        return PREFIX_SHIRO_CACHE + JwtUtil.getClaim(key.toString(), JWTConstant.ACCOUNT);
    }

    /**
     * 获取缓存
     */
    @Override
    public Object get(Object key) throws CacheException {
        try {
            if (!redisTemplate.exists(this.getKey(key))) {
                return null;
            }
            return redisTemplate.getObject(this.getKey(key));
        } catch (RedisAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 保存缓存
     */
    @Override
    public Object put(Object key, Object value) throws CacheException {
        // 设置Redis的Shiro缓存
        try {
            return redisTemplate.setObject(this.getKey(key), value, JWTConstant.REFRESH_TIME);
        } catch (RedisAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 移除缓存
     */
    @Override
    public Object remove(Object key) throws CacheException {
        try {
            if (!redisTemplate.exists(this.getKey(key))) {
                return null;
            }
            redisTemplate.del(this.getKey(key));

        } catch (RedisAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 清空所有缓存
     */
    @Override
    public void clear() throws CacheException {

    }

    /**
     * 缓存的个数
     */
    @Override
    public int size() {
        return 0;
    }

    /**
     * 获取所有的key
     */
    @Override
    public Set keys() {
        return null;
    }

    /**
     * 获取所有的value
     */
    @Override
    public Collection values() {

        return null;
    }
}
