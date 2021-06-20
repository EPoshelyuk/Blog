package by.poshelyuk.blog.repository;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;


//Хэши Redis могут содержать n несколько пар ключ-значение и предназначены для использования меньшего объема памяти,
// что делает их отличным способом хранения объектов в памяти.
// С помощью HashOperations вспомогательного класса мы можем ими управлять.
//Чтобы использовать любой из них, мы упаковываем возвращенные хеш-операции из RedisTemplate экземпляра
// в HashOperations интерфейс:HashOperations hashOperations = redisTemplate.opsForHash();

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
