import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisSentinelPool;

/**
 * @author tangxiaoshuang
 * @date 2018/7/16 10:22
 * @desc
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/*.xml"})
public class RedisTest {

    @Autowired
    private Jedis jedis;

    @Test
    public void test() {

        if(! jedis.isConnected()) {
            System.out.println(jedis.get("hdddddh"));
        }
        System.out.println(jedis.isConnected());
        System.out.println(jedis.get("hh"));
        jedis.close();
        jedis.disconnect();
        System.out.println(jedis.isConnected());
        if(! jedis.isConnected()) {
            jedis.close();
            System.out.println(jedis.get("hdddddh"));
        }
    }

    @Test
    public void test2() {

        Jedis jedis = new Jedis("127.0.0.1",6379);
        System.out.println(jedis.get("hh"));
        Client client1 = jedis.getClient();
        jedis.disconnect();
        jedis.close();
        System.out.println(jedis.isConnected());//false
        Client client2 = jedis.getClient();
        System.out.println(jedis.get("hh"));
        System.out.println(client1 == client2);//true
        System.out.println(client1.isConnected());//true
        System.out.println(client2.isConnected());//true
    }

    @Test
    public void test3() {

        Jedis jedis = new Jedis("127.0.0.1",6379);
        System.out.println(jedis.get("hh"));
        Client client = jedis.getClient();
        client.get("hh");

        client.disconnect();
        System.out.println(client.isConnected());//false
        client.get("hh");
    }

    @Test
    public void test4() {

        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.publish("test","hdhd");
    }

}
