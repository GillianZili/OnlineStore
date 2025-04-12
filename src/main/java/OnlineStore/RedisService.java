package OnlineStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

  @Autowired
  private StringRedisTemplate redisTemplate;

  // save caching
  public void setCache(String key, String value) {
    redisTemplate.opsForValue().set(key, value);
  }

  // get caching
  public String getCache(String key) {
    return redisTemplate.opsForValue().get(key);
  }

  // delete caching
  public void deleteCache(String key) {
    redisTemplate.delete(key);
  }
}
