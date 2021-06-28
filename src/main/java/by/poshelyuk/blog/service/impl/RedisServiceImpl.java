package by.poshelyuk.blog.service.impl;

import by.poshelyuk.blog.repository.redis.RedisRepository;
import by.poshelyuk.blog.service.RedisService;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl implements RedisService {

    private final RedisRepository redisRepository;

    public RedisServiceImpl(RedisRepository redisRepository) {
        this.redisRepository = redisRepository;
    }

    @Override
    public void save(String key, String value) {
        redisRepository.save(key, value);
    }

    @Override
    public String findByKey(String key) {
        return redisRepository.findByKey(key);
    }
}
