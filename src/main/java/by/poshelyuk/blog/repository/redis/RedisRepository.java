package by.poshelyuk.blog.repository.redis;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class RedisRepository {

    private final String KEY = "CODE";
    private final RedisTemplate<String, String> redisTemplate;
    private final HashOperations hashOperations;

    public RedisRepository(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }

    public void save(String key, String value) {
        hashOperations.put(KEY, key, value);
        redisTemplate.expire(KEY, 1, TimeUnit.DAYS);
    }

    public String findByKey(String key) {
        return (String) hashOperations.get(KEY, key);
    }
}
