package hash;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
/**
 * @author tangxiaoshuang
 * @date 2018/6/5 14:45
 *
 * 海量日志数据,提取出某日访问百度次数最多的那个IP
 *
 *1、IP地址最多有2^32=4G种取值情况，所以不能完全加载到内存中处理；
 *2、可以考虑采用分而治之的思想，按照IP地址的Hash(IP) % 1024值，把海量IP日志分别存储到1024个小文件中，这样，每个小文件最多包含4MB个IP地址；
 *
 */
public class MassiveIP {
    //先产生10000000个随机ip   模拟给你的一个IP大文件数据
    public void generateIP(String fileName){
        PrintWriter out =null;
        try {

            out=new PrintWriter(fileName);
            String s;
            Random r=new Random();

            for(int i=0;i<100000000;i++){
                s="159.227.";
                s+=r.nextInt(256)+"."+r.nextInt(256);
                out.println(s);
            }


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally{
            if (out != null)
                out.close( );
        }

    }
    //利用Hash算法把IP大文件拆分成多个小文件，则同一个Hash会被分到一个文件中，当然不同的文件也可能分到一个文件中
    public void FileSplit(String fileName){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader (fileName));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        PrintWriter[] out=new PrintWriter[100];
        for(int i=0;i<100;i++)
            try {
                //specify split file name
                out[i]=new PrintWriter(fileName+i);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        String IP = null;
        try {
            while((IP =reader.readLine())!= null ) {
                IP=reader.readLine();
                int fileNum=IP.hashCode()%100;
                fileNum=(fileNum>=0?fileNum:fileNum+100);
                //	System.out.println(fileNum);
                out[fileNum].println(IP);

            }
            for(int i=0;i<100;i++)
                out[i].close();

            //}
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            reader.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
    //把每个文件里的IP都放到一个HashMap里
    public Map.Entry<String,Integer>  statitics(String fileName){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader (fileName));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        HashMap<String,Integer> map=new HashMap<String,Integer>();
        String IP = null;
        try {
            while((IP =reader.readLine())!= null){
                //to judge whether the IP is already
                //existed in the HashMap
                if(map.containsKey(IP)){
                    map.put(IP, map.get(IP)+1);
                }
                else
                    map.put(IP,1);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //Entry in HashMap with the maximum value
        //which means the IP with the largest occurrence
        Map.Entry<String,Integer>  maxEntry=null;
        for (Map.Entry<String,Integer> entry : map.entrySet()){
            if (maxEntry == null || entry.getValue()>maxEntry.getValue()) {
                maxEntry = entry;
            }
        }
        try {
            reader.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return maxEntry;
    }
    public static void main(String[] args){
        MassiveIP m=new MassiveIP();
        String FileName="D://Data//test.txt";
        m.generateIP(FileName);
        m.FileSplit(FileName);
        List<Map.Entry<String,Integer>>l
                =new ArrayList<Map.Entry<String,Integer>>();
        for(int i=0;i<100;i++)
            l.add(m.statitics(FileName+i));
        Map.Entry<String,Integer>maxEntry=l.get(0);
        for(int j=1;j<100;j++){
            if(l.get(j).getValue()>maxEntry.getValue())
                maxEntry=l.get(j);
        }
        System.out.println(maxEntry.getKey());
        System.out.println(maxEntry.getValue());


    }

}
