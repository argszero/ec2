package org.argszero.ec2.controller;

import info.aduna.io.ByteArrayUtil;
import org.openrdf.sail.nativerdf.btree.BTree;
import org.openrdf.sail.nativerdf.btree.RecordComparator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;

/**
 * Created by shaoaq on 2/8/15.
 */
@Controller
@RequestMapping("/gistalk")
public class GisTalkControler {


    @RequestMapping("/say")
    public boolean say(String msg,double latitude,double longitude){
        return true;
    }
    @RequestMapping("/list")
    public boolean say(double latitude,double longitude){
        return true;
    }

    public static void main2BTree(String[] args) throws IOException {
        BTree bTree = new BTree(new File("/home/shaoaq/tmp/t8/t"), "aaaa", 1024, 256, new RecordComparator() {
            @Override
            public int compareBTreeValues(byte[] key, byte[] data, int offset, int length) {
                ByteArrayUtil.compareRegion(key,0,data,offset,8);
                return new String(key).substring(0,8).compareTo(new String(data,offset));
            }
        });
        for (int i = 0; i < 100; i++) {
            bTree.insert(("i:"+i+",value sssssssssssssss").getBytes());
        }
        bTree.close();
    }
}
