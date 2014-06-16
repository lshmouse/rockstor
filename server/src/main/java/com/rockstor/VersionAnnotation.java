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

package com.rockstor;

/**
 * @author terry
 *
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A package attribute that captures the version of rockstor that was compiled.
 * Copied down from hadoop. All is same except name of interface.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PACKAGE)
public @interface VersionAnnotation {

    /**
     * Get the Hadoop version
     * 
     * @return the version string "0.2.0-dev"
     */
    String version();

    /**
     * Get the username that compiled RockStor.
     */
    String user();

    /**
     * Get the date when Hadoop was compiled.
     * 
     * @return the date in unix 'date' format
     */
    String date();

    /**
     * Get the url for the subversion repository.
     */
    String url();

    /**
     * Get the subversion revision.
     * 
     * @return the revision number as a string (eg. "451451")
     */
    String revision();
}
