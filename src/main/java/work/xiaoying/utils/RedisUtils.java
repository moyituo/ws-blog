package work.xiaoying.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 *
 * @author 小樱
 * @date 2022/11/23
 */
@Component
public class RedisUtils {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 设置过期时间，以秒为单位
     *
     * @param key  key
     * @param time 时间
     * @return 返回true表示设置成功
     */
    public boolean expireOfSeconds(String key, long time) {
        if (time > 0) {
            redisTemplate.expire(key, time, TimeUnit.SECONDS);
        }
        return true;
    }

    /**
     * 设置过期时间，以小时为单位
     *
     * @param key  key
     * @param time 时间
     * @return 返回true表示设置成功
     */
    public Boolean expireOfHours(String key, long time) {
        if (time > 0) {
            redisTemplate.expire(key, time, TimeUnit.HOURS);
        }
        return true;
    }

    /**
     * 获取过期时间
     *
     * @param key 关键
     * @return 时间(秒) 返回0代表为永久有效
     */
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除缓存
     *
     * @param key 可以传一个或多个值
     */
    public void del(String... key) {
        if (key != null && key.length != 0) {
            for (String s : key) {
                redisTemplate.delete(s);
            }
        }
    }

//    =============================================String=======================================================

    /**
     * 缓存获取
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }


    /**
     * 存入String
     *
     * @param key   键
     * @param value 值
     * @return 返回true保存成功
     */
    public Boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 可加入秒为单位的时间
     *
     * @param time  以秒为单位
     * @param key   键
     * @param value 值
     * @return boolean
     */
    public Boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 递增或递减
     *
     * @param key   键
     * @param delta +/-
     * @return long
     */
    public Long incrOrdecr(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }
//========================================hash================================================

    /**
     * 获得hash的值
     *
     * @param key  键 不能为空
     * @param item 项 不能为空
     * @return 获得的值
     */
    public Object hGet(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取hash键中所有键值对
     *
     * @param key 键不能为空
     * @return map键值集合
     */
    public Map<Object, Object> hMget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * HashSet
     *
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    public Boolean hMset(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * HashSet 并设置时间
     *
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    public Boolean hMset(String key, Map<String, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expireOfSeconds(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    public Boolean hSet(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    public Boolean hSet(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expireOfSeconds(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public void hDel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public Boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * hash递增递减 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     * @return {@link Double}
     */
    public Double hIncr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

//==================================set=========================================

    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     * @return {@link Set}<{@link Object}>
     */
    public Set<Object> sGet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptySet();
        }
    }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    public Boolean sHasKey(String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public Long sSet(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将set数据放入缓存
     *
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功存储个数
     */
    public Long sSetTime(String key, long time, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0)
                expireOfSeconds(key, time);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取set缓存的长度
     *
     * @param key 键
     * @return {@link Long}
     */
    public Long sGetSetSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 移除值为value的
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public Long setRemove(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().remove(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // ===============================List(列表)=================================

    /**
     * 获取list缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束 0 到 -1代表所有值
     * @return {@link List}<{@link Object}>
     */
    public List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * 获取list缓存的长度
     *
     * @param key 键
     * @return {@link Long}
     */
    public Long lSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 通过索引 获取list中的值
     *
     * @param key   键
     * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return {@link Object}
     */
    public Object lGetIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return {@link Boolean}
     */
    public Boolean rSet(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean lSet(String key, Object value) {
        try {
            redisTemplate.opsForList().leftPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return {@link Boolean}
     */
    public Boolean rSet(String key, Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0) {
                expireOfSeconds(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean lSet(String key, Object value, long time) {
        try {
            redisTemplate.opsForList().leftPush(key, value);
            if (time > 0) {
                expireOfSeconds(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return {@link Boolean}
     */
    public Boolean rSet(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean lSet(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().leftPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * r设置
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return {@link Boolean}
     */
    public Boolean rSet(String key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0)
                expireOfSeconds(key, time);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean LSet(String key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().leftPushAll(key, value);
            if (time > 0)
                expireOfSeconds(key, time);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return {@link Boolean}
     */
    public Boolean lUpdateIndex(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 移除N个值为value
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public Long lRemove(String key, long count, Object value) {
        try {
            return redisTemplate.opsForList().remove(key, count, value);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
//    =========================================zset====================================

    /**
     * 获取个数
     *
     * @param key 键
     * @return 返回值
     */
    public Long zSize(String key) {
        return redisTemplate.opsForZSet().size(key);
    }

    /**
     * 将数据放入zSet缓存
     *
     * @param key    键
     * @param values 值
     * @return 成功个数
     */
    public Boolean zAdd(String key,  int score, Object... values) {
        try {
            return redisTemplate.opsForZSet().add(key, values, score);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 加分减分
     *
     * @param key   键
     * @param value 价值
     * @param inc   公司
     * @return {@link Double}
     */
    public Double zIncrementScore(String key, Object value, int inc) {
        try {
            return redisTemplate.opsForZSet().incrementScore(key, value, inc);
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    /**
     * 移除值为value的
     *
     * @param key   键
     * @param value 值 可以是多个
     * @return 移除的个数
     */
    public Long zSetRemove(String key, Object value) {
        try {
            return redisTemplate.opsForZSet().remove(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取指定元素的排名，从小到大排序
     *
     * @param key   关键
     * @param value 价值
     * @return {@link Long}
     */
    public Long zRank(String key, Object value) {
        try {
            return redisTemplate.opsForZSet().rank(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 获取指定元素的排名，从大到小排序
     *
     * @param key   关键
     * @param value 价值
     * @return {@link Long}
     */
    public Long zReverseRank(String key, Object value) {
        try {
            return redisTemplate.opsForZSet().reverseRank(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 返回集合内元素的排名，以及分数（从小到大）
     *
     * @param min max 最小值最大值
     * @param key key
     * @param max 最大
     * @return {@link Set}<{@link ZSetOperations.TypedTuple}<{@link Object}>>
     */
    public Set<ZSetOperations.TypedTuple<Object>> angeWithScores(String key, long min, long max) {
        try {
            return redisTemplate.opsForZSet().rangeWithScores(key, min, max);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptySet();
        }

    }

    /**
     * 返回集合内元素在指定分数范围内的排名（从小到大）
     *
     * @param min max 最小值最大值
     * @param key 键
     * @param max 最大
     * @return {@link Set}<{@link Object}>
     */
    public Set<Object> zRangeByScore(String key, int min, int max) {
        try {
            return redisTemplate.opsForZSet().rangeByScore(key, min, max);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptySet();
        }

    }

    /**
     * 返回集合内元素在指定分数范围内的排名（从小到大）
     * 带偏移量和个数
     * 从index下标开始，个位数为count
     *
     * @param min   max 最小值最大值
     * @param key   关键
     * @param max   最大
     * @param index 索引
     * @param count 数
     * @return {@link Set}<{@link Object}>
     */
    public Set<Object> zRangeByScore(String key, int min, int max, int index, int count) {
        try {
            return redisTemplate.opsForZSet().rangeByScore(key, min, max, index, count);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptySet();
        }
    }

    /**
     * z计数
     * 返回集合内指定分数范围的成员个数
     *
     * @param min max 最小值最大值
     * @param key 键
     * @param max max
     * @return {@link Long}
     */
    public Long zCount(String key, int min, int max) {
        try {
            return redisTemplate.opsForZSet().count(key, min, max);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 获得指定元素的分数
     *
     * @param value 指定元素
     * @param key   关键
     * @return 分数
     */
    public Double zCount(String key, Object value) {
        try {
            return redisTemplate.opsForZSet().score(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }

    }

    /**
     * 删除指定索引范围的元素
     *
     * @param min max 指定范围
     * @param key 键
     * @param max max
     * @return 分数
     */
    public Long zRemoveRange(String key, int min, int max) {
        try {
            return redisTemplate.opsForZSet().removeRange(key, min, max);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 删除范围得分
     * 删除指定分数范围内的元素
     *
     * @param min max 指定范围
     * @param key 关键
     * @param max max
     * @return 分数
     */
    public Long zRemoveRangeByScore(String key, int min, int max) {
        try {
            return redisTemplate.opsForZSet().removeRangeByScore(key, min, max);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * z范围
     * 按照排名先后(从小到大)打印指定区间内的元素, -1为打印全部
     *
     * @param min max 指定范围
     * @param key 键
     * @param max max
     * @return 分数
     */
    public Set<Object> zRangeAll(String key, int min, int max) {
        try {
            return redisTemplate.opsForZSet().range(key, min, max);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptySet();
        }

    }

}

