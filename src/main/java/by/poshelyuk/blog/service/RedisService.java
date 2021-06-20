package by.poshelyuk.blog.service;


public interface RedisService {

    void save(String key, String value);

   String findByKey(String key);
}
