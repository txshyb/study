package cn.txs.dubboconsumer;

import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.rpc.Protocol;

public class Main {

    public static void main(String[] args) {
        Protocol PROTOCOL = ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();

        System.out.println(PROTOCOL);
    }
}
