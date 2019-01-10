package com.txs.springboottest.actuator;

import java.util.Date;
  
import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.stereotype.Component;

/**
 * springboot 2.x 已经把Endpoint改为了注解
 */
@Component
public class MyEndPoint implements Endpoint<MemInfo> {
      
    /** 
     * (1) getId是EndPoint的唯一标识， 
     * (2)MVC接口对外暴露的路径:http://localhost:8080/myendpoint 
     */  
    @Override  
    public String getId() {  
        return "myendpoint";  
    }  
  
    @Override  
    public boolean isEnabled() {  
        return true;  
    }  
  
    @Override  
    public boolean isSensitive() {  
        return false;  
    }

    /**
     * 请求http://localhost:8080/myendpoint 调用该方法
     * @return
     */
    @Override  
    public MemInfo invoke() {  
        MemInfo memInfo = new MemInfo();  
        Runtime runtime = Runtime.getRuntime();  
          
        memInfo.setRecordTime(new Date());  
        memInfo.setMaxMemory(runtime.maxMemory());  
        memInfo.setTotalMemory(runtime.totalMemory());  
        return memInfo;  
    }  
  
}  