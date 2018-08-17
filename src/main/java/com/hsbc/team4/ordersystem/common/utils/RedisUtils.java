package com.hsbc.team4.ordersystem.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.common.utils
 * @Description :
 * @Date : 2018/8/16
 */
@Component
public class RedisUtils {
    @Autowired
    private  RedisTemplate<String, Object> redisTemplate;
    /**
     * HashMap操作
     */
    @Autowired
    private  HashOperations<String, String, Object> hashOperations;
    /**
     * Object操作
     */
    @Autowired
    private  ValueOperations<String, Object> valueOperations;
    /**
     * List操作
     */
    @Autowired
    private  ListOperations<String, Object> listOperations;

    /**
     * set操作
     */
    @Autowired
    private  SetOperations<String, Object> setOperations;
    /**
     * ZSet操作
     */
    @Autowired
    private  ZSetOperations<String, Object> zSetOperations;

    /**
     * 判断key是否存在
     * @param key
     */
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除key
     * @param key
     */
    public void delete(String key){
        redisTemplate.delete(key);
    }

    /**
     * 判断指定key的hashKey是否存在
     * @param key
     * @param hashKey
     * @return
     */
    public boolean hasKey(String key, String hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    /**
     * 设置超时时间
     * @param key
     * @param timeout
     * @param unit
     */
    public void setExpire(String key, final long timeout, final TimeUnit unit) {
        redisTemplate.expire(key, timeout, unit);
    }

    /**
     * 获取过期时间
     * @param key
     * @return
     */
    public long getExpire(String key){
        return redisTemplate.getExpire(key);
    }

    /**
     * 获取指定pattern的key
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 删除多个key
     * @param keys
     */
    public void delete(Set<String> keys) {
        redisTemplate.delete(keys);
    }

    /**
     * 设置过期时间
     * @param key
     * @param expire
     */
    private void setExpire (String key,long expire){
        if (expire != -1) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    /**
     * 设置key-value值
     */
    public void addValue(String key, Object value,long expire){
        valueOperations.set(key, value);
        setExpire(key,expire);
    }

    /**
     * 设置key-value值,传入时间单位
     */
    public void addValue(String key, Object value,long expire, TimeUnit timeUnit){
        valueOperations.set(key, value, expire, timeUnit);

    }

    /**
     * 设置key-value值, 无过期时间
     */
    public void addValue(String key, Object value){
        valueOperations.set(key, value);
    }

    /**
     * 获取key的值
     *
     */
    public Object getValue(String key){
        return valueOperations.get(key);
    }

    /**
     * 向redis 中添加内容
     * @param key       保存key
     * @param hashKey   hashKey
     * @param data      保存对象 data
     * @param expire    过期时间    -1：表示不过期
     */
    public void addHashValue(String key,String hashKey, Object data, long expire) {
        hashOperations.put(key, hashKey, data);

        setExpire(key,expire);
    }

    /**
     * Hash 添加数据
     * @param key   key
     * @param map   data
     */
    public void addAllHashValue(String key, Map<String, Object> map, long expire) {
        hashOperations.putAll(key, map);

        setExpire(key,expire);
    }

    /**
     * 删除hash key
     * @param key       key
     * @param hashKey   hashKey
     */
    public long deleteHashValue(String key, String hashKey) {
        return hashOperations.delete(key, hashKey);
    }

    /**
     * 获取数据
     */
    public Object getHashValue(String key, String hashKey) {
        return hashOperations.get(key, hashKey);
    }

    /**
     * 批量获取数据
     */
    public List<Object> getHashAllValue(String key) {
        return hashOperations.values(key);
    }

    /**
     * 批量获取指定hashKey的数据
     */
    public List<Object> getHashMultiValue(String key, List<String> hashKeys) {
        return  hashOperations.multiGet(key, hashKeys);
    }

    /**
     * 获取hash数量
     */
    public Long getHashCount(String key) {
        return hashOperations.size(key);
    }

    /**
     * 设置zset值
     */
    public boolean addZSetValue(String key, Object member, long score){
        return zSetOperations.add(key, member, score);
    }

    /**
     * 设置zset值
     */
    public boolean addZSetValue(String key, Object member, double score){
        return zSetOperations.add(key, member, score);
    }

    /**
     * 批量设置zset值
     */
    public long addBatchZSetValue(String key, Set<ZSetOperations.TypedTuple<Object>> tuples){
        return zSetOperations.add(key, tuples);
    }

    /**
     * 自增zset值
     */
    public void incZSetValue(String key, String member, long delta){
        zSetOperations.incrementScore(key, member, delta);
    }

    /**
     * 获取zset数量
     */
    public long getZSetScore(String key, String member){
        Double score = zSetOperations.score(key, member);
        if(score==null){
            return 0;
        }else{
            return score.longValue();
        }
    }

    /**
     * 获取有序集 key 中成员 member 的排名 。其中有序集成员按 score 值递减 (从小到大) 排序。
     */
    public Set<ZSetOperations.TypedTuple<Object>> getZSetRank(String key, long start, long end){
        return zSetOperations.rangeWithScores(key, start, end);
    }

    /**
     * 添加list列表
     */
    public void addListValue(String key,Object list){
        listOperations.leftPush(key,list);
    }

    /**
     * 获取指定Key对应的list
     */
    public Object getListValue(String key){
        return listOperations.leftPop(key);
    }

    /**
     * 添加Set集合集合
     */
    public void addSetValue(String key,Object list){
        setOperations.add(key,list);
    }

    /**
     * 获取指定Key对应的set
     */
    public Object getSetValue(String key){
        return setOperations.members(key);
    }

    /**
     * 获取并移除指定key的值
     */
    public Object popSetValue(String key){
        return setOperations.pop(key);
    }

}
