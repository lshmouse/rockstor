/**
 * Copyright 2012 Hanborq Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rockstor.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

import com.rockstor.client.AccessControlList;
import com.rockstor.client.AclEntry;
import com.rockstor.client.ListAllMyBucketsResult;
import com.rockstor.client.ListBucketResult;
import com.rockstor.client.RockStor;
import com.rockstor.client.RockStorException;

public class Examples {
    private final static String address = "localhost:48080";
    private final static String username = "testuser";
    private final static String bucket = "bucket";
    
    private static void testRockStor() {
        try {

            System.out.println("TEST getService");
            RockStor rs = new RockStor(address, username);
            ListAllMyBucketsResult r = rs.getService();
            System.out.println(r);

            System.out.println("TEST createBucket");
            rs.createBucket(bucket);
            r = rs.getService();
            System.out.println(r);

            System.out.println("TEST getBucketAcl");
            AccessControlList acl = rs.getBucketAcl(bucket);
            System.out.println(acl);

            System.out.println("TEST setBucketAcl");
            acl = new AccessControlList();
            AclEntry ae = new AclEntry();
            ae.setUser("schubert");
            ae.setAcl("READ");
            ArrayList<AclEntry> list = new ArrayList<AclEntry>();
            list.add(ae);
            acl.setAclEntrys(list);
            rs.setBucketAcl(bucket, acl);
            acl = rs.getBucketAcl(bucket);
            System.out.println(acl);

            System.out.println("TEST getBucket");
            ListBucketResult r2 = rs.getBucket(bucket);
            System.out.println(r2);

            System.out.println("TEST putObject");
            File testFile = new File("/home/liushaohui/client.log");
            FileInputStream fis = new FileInputStream(testFile);
            rs.putObject(bucket, "testobject", null, null, null,
                    (int) testFile.length(), fis);
            fis.close();
            r2 = rs.getBucket(bucket);
            System.out.println(r2);

            System.out.println("TEST headObject");
            Map<String, String> metas = rs.headObject(bucket, "testobject");
            for (Map.Entry<String, String> e : metas.entrySet()) {
                System.out.println(e.getKey() + " : " + e.getValue());
            }

            System.out.println("TEST setObjectMeta");
            metas.clear();
            metas.put("rockstor-meta-key1", "value1");
            metas.put("rockstor-meta-key2", "value2");
            metas.put("rockstor-meta-key3", "value3");
            rs.setObjectMeta(bucket, "testobject", metas);

            System.out.println("TEST getObjectMeta");
            ArrayList m = new ArrayList<String>();
            m.add("rockstor-meta-key1");
            metas = rs.getObjectMeta(bucket, "testobject", null);
            System.out.println(metas);
            metas = rs.getObjectMeta(bucket, "testobject", m);
            System.out.println(metas);

            System.out.println("TEST deleteObjectMeta");
            rs.deleteObjectMeta(bucket, "testobject", m);
            metas = rs.getObjectMeta(bucket, "testobject", null);
            System.out.println(metas);
            rs.deleteObjectMeta(bucket, "testobject", null);
            metas = rs.getObjectMeta(bucket, "testobject", null);
            System.out.println(metas);

            System.out.println("TEST getObjectAcl");
            acl = rs.getObjectAcl(bucket, "testobject");
            System.out.println(acl);

            System.out.println("TEST setObjectAcl");
            acl = new AccessControlList();
            ae = new AclEntry();
            ae.setUser("schubert");
            ae.setAcl("READ");
            list = new ArrayList<AclEntry>();
            list.add(ae);
            acl.setAclEntrys(list);
            rs.setObjectAcl(bucket, "testobject", acl);
            acl = rs.getObjectAcl(bucket, "testobject");
            System.out.println(acl);

            System.out.println("TEST getObject");
            InputStream is = rs.getObject(bucket, "testobject");
            FileOutputStream fos = new FileOutputStream(
                    new File("/home/liushaohui/client.log.backup"));
            byte[] buf = new byte[4096];
            int len = 0;
            while ((len = is.read(buf)) > 0) {
                fos.write(buf, 0, len);
            }
            fos.close();
            is.close();
            System.out.println("TEST deleteObject");
            rs.deleteObject(bucket, "testobject");
            System.out.println("TEST deleteBucket");
            rs.deleteBucket(bucket);
            r = rs.getService();
            System.out.println(r);

        } catch (RockStorException e) {
            System.out.println(e.getRockStorError());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        testRockStor();
    }
}
