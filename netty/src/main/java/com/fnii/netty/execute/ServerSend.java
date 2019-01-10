package com.fnii.netty.execute;

import ch.qos.logback.classic.Logger;
import com.fnii.netty.protocol.SmartCarProtocol;
import com.fnii.netty.server.ServerHandler;
import com.fnii.netty.server.TcpServer;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;

/**
 * @author: tangxiaoshuang
 * @date: 2018/9/15 15:07
 * @desc:
 */
@Component
public class ServerSend {

    org.slf4j.Logger logger = LoggerFactory.getLogger(ServerSend.class);
    public void execute(String msg) {
        logger.info("execute|{}",msg);
        byte[] b = null;
        try {
            b = msg.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (b == null || b.length == 0) {
            return;
        }
        if(CollectionUtils.isEmpty(ServerHandler.channelGroup)) {
            throw new RuntimeException("不存在客户端");
        }
        SmartCarProtocol smartCarProtocol = new SmartCarProtocol(b.length, b);

        ServerHandler.channelGroup.writeAndFlush(smartCarProtocol);
    }

    public static void main(String[] args) {
        String str = "{\"taskId\":\"0\",\"link\":[{\"id\":1,\"inIp\":\"1.1.5.4/22\",\"outIp\":\"127.0.0.11\",\"lastHop\":\"172.0.0.22\",\"nextHop\":\"1.1.5.4/13\",\"onOff\":1},{\"id\":2,\"inIp\":\"1.1.5.4/13\",\"outIp\":\"127.0.0.33\",\"lastHop\":\"172.0.1.44\",\"nextHop\":\"1.1.5.4/12\",\"onOff\":1}],\"dnsSettings\":{\"dns\":\"1.1.1.1\",\"defaultDns\":\"114.114.114.114\"},\"appRules\":[{\"id\":1,\"domain\":[\"kugou.com\",\"v.qingcdn.com\",\"kugou.xdwscache.ourwebpic.com\",\"kugou.com.wsglb0.com\",\"kgimg.com\",\"kugou.net\"]},{\"id\":2,\"domain\":[\"iqiyi.com\",\"qiyipic.com\",\"qiyi.com\",\"71.am\",\"qpic.cn\",\"ppstream.com\",\"pps.tv\"]},{\"id\":3,\"domain\":[\"qianqian.com\",\"music.baidu.com\",\"qlogo.cn\",\"showstart.com\",\"kuwo.cn\"]},{\"id\":4,\"domain\":[\"kuwo.cn\",\"kuwoget.fastcdn.com\",\"igexin.com\",\"koowo.com\"]},{\"id\":5,\"domain\":[\"pptv.com\",\"pplive.com\",\"pplive.cn\",\"synacast.com\"]},{\"id\":6,\"domain\":[\"cnsuning.com\",\"pptv.com\",\"pplive.cn      \",\"pplive.com\",\"synacast.com\",\"u966.v.qingcdn.com\",\"suning.com.qingcdn.com\",\"ppsport.com\",\"suning.com.wswebcdn.com\",\"suning.com.wscdns.com\",\"suning.cn.cdn20.com\",\"suning.xdwscache.ourwebcdn.com\",\"pptvyun.com\",\"oss.suning.com \",\"ssac.suning.com\",\"snsis.suning.com\",\"sportlive.suning.com \",\"sportenjoy.suning.com\",\"tysq.suning.com\",\"snsisop.suning.com\",\"isports.suning.com\",\"ppsports.suning.com\",\"pgoods.suning.com\",\"sportmarket.suning.com\",\"dt.suning.com \",\"yxgather.suning.com\",\"ipservice.suning.com\"]},{\"id\":7,\"domain\":[\"qq.com\",\"gtimg.com\",\"gtimg.cn\",\"tencentmind.com    \",\"QQ音乐\",\"y.qq.com\",\"tcdn.qq.com\",\"music.qq.com\",\"qqmusic.qq.com\",\"wnsmusic.qq.com\",\"tc.qq.com\",\"l.qq.com\",\"3g.qq.com\",\"mdt.qq.com\",\"gdt.qq.com\",\"dp3.qq.com\",\"mtrace.qq.com\",\"btrace.qq.com\",\"rqd.qq.com\"]},{\"id\":8,\"domain\":[\"music.126.net\",\"live.126.net\",\"vod.126.net\",\"nstool.netease.com\",\"music.163.com\",\"netease.com.mshome.net\",\"in-addr.arpa\",\"nosdn.127.net\",\"dun.163yun.com\"]},{\"id\":9,\"domain\":[\"youku.com\",\"alicdn.com\",\"m.taobao.com\",\"ykimg.com\",\"youku.gentags.net\",\"soku.com\",\"tudou.com\"]},{\"id\":10,\"domain\":[\"cctv.com\",\"cctvpic.com\",\"cntv.wrating.com\",\"cntv.cn\",\"cctv.cn\",\"cpolive.com\",\"citvc.com\",\"tv.cn\",\"cntvwb.cn\",\"cntv.wscdns.com\",\"cctv.conviva.com\",\"cntv.dnion.com\",\"cntv.qingcdn.com\",\"cctvcdn.net\",\"cntv.lxdns.com\",\"cntv.hls.cdn.myqcloud.com\",\"cctv.video.ourdvs.com\",\"cntv.cn.wscdns.com\",\"cctv.xdwscache.ourglb0.com\",\"cntv.dnion.com.fastcdn.com\",\"zcntvlv.v.qingcdn.com\",\"cntv.liveplay.myqcloud.com\",\"cntv.myalicdn.com\",\"cnr.cn\",\"cntv.ru\",\"hunantv.com\",\"zjstv.com\",\"jstv.com\",\"dragontv.cn\",\"sdtv.com.cn\",\"cws-cctv.conviva.com\",\"v.live.qingcdn.com\",\"cntv.d.qingcdn.com\",\"v.dnion.com\",\"v.kcdnvip.com\",\"cntv.kcdnvip.com\",\"v.myalicdn.com\",\"v.myalicdn.com.m.alikunlun.net\",\"cctv5.vb.5213.liveplay.myqcloud.com\"]},{\"id\":11,\"domain\":[\"cntv.cn\",\"cctv.cn\",\"cntvwb.cn\",\"cctv.com\",\"cntv.wscdns.com\",\"cctv.conviva.com\",\"cntv.dnion.com\",\"cntv.qingcdn.com\",\"v.live.qingcdn.com\",\"cntv.d.qingcdn.com\",\"v.dnion.com\",\"v.kcdnvip.com\",\"cntv.kcdnvip.com\",\"v.myalicdn.com\",\"v.myalicdn.com.m.alikunlun.net\",\"cctv5.vb.5213.liveplay.myqcloud.com\"]},{\"id\":12,\"domain\":[\"v.bsclink.cn\",\"migu.cn\",\"migucloud.com\",\"cmvideo.cn\",\"miguvideo.com\"]}]}";
        System.out.println(str.getBytes().length);
    }
}
