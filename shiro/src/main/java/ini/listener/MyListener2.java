package ini.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;

/**
 * @auther: tangxiaoshuang
 * @date: 2018/7/24 10:00
 * @desc: 会话监听器用于监听会话创建、过期及停止事件   可任意监听一个
 */
public class MyListener2 extends SessionListenerAdapter {
    @Override
    public void onStart(Session session) {
        System.out.println("MyListener2 会话创建：" + session.getId());
    }

    @Override
    public void onStop(Session session) {
        System.out.println("MyListener2 会话结束：" + session.getId());
    }

    @Override
    public void onExpiration(Session session) {
        System.out.println("MyListener2 会话过期：" + session.getId());
    }
}
