package org.codehaus.mojo.wagon;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

import java.util.Iterator;
import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.wagon.Wagon;
import org.apache.maven.wagon.WagonException;

/**
 * Lists the content of the specified directory (remoteResource) under a specified repository (url) 
 * 
 * @author Sherali Karimov
 * @goal list
 */
public class ListMojo
    extends AbstractWagonMojo
{
    /**
     * Path after the url, can be a file or directory
     * 
     * @parameter expression="${wagon.remoteResource}" default-value=""
     */
    private String remoteResource;

    /**
     * 
     * @parameter expression="${wagon.recursive}" default-value="true"
     */
    private boolean recursive;

    
    protected void execute( Wagon wagon )
        throws MojoExecutionException, WagonException
    {
        if ( remoteResource == null )
        {
            remoteResource = "";
        }

        List files = wagonHelpers.getFileList( wagon, remoteResource, recursive, this.getLog() );

        for ( Iterator iterator = files.iterator(); iterator.hasNext(); )
        {
            String file = (String) iterator.next();
            getLog().info( "\t" + file );
        }
    }
}
