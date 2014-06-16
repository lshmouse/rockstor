RockStor
========

An Object Storage System implementation based on Hadoop and HBase, with similar features like S3 (Amazon Simple Storage Service). 

# Introduction 
  [Introduction to RockStor](http://www.slideshare.net/schubertzhang/rockstor-a-cloud-object-system-based-on-hadoop)   
  [A Training of RockStor](http://www.slideshare.net/hanborq/how-to-build-cloud-storage-service-systems-13526441)   

# TODO
1. 增加单元测试和集成测试

2. RockStor客户端自动从ZK上获取RockServer的地址。自动的负载均衡

3. MultiPart Pebble upload api.
   大文件Pebble被分为多个Part，
   InitMultiPartTask(将Pebble放在临时表MultiPartPebble里面) -> UploadPartTask -> CompleteMultiPartTask(临时表MultiPartPebble移动到正式表Pebble)

4. List性能恶化。（List操作的性能与Pebble个数成正比）
   使用hbase filter的getNextKeyHint加速上层目录List

5. Data duplication（可选）.
   数据去重。可以基于trunk来做

6. Mapreduce Integeration.
   (使用Mapreduce来分析数据)
