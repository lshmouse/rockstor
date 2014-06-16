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

package com.rockstor.compact.garbagecollect;

import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.mapreduce.Partitioner;

public class GarbageChunkPartition extends
        Partitioner<ImmutableBytesWritable, ImmutableBytesWritable> {

    @Override
    public int getPartition(ImmutableBytesWritable key,
            ImmutableBytesWritable value, int numPartitions) {
        byte[] keyBytes = key.get();
        long high = Bytes.toLong(keyBytes, 0, Bytes.SIZEOF_LONG);
        long low = Bytes.toLong(keyBytes, Bytes.SIZEOF_LONG, Bytes.SIZEOF_LONG);

        return Math.abs((int) ((high ^ low) % numPartitions));
    }
}
