/**
 * Copyright (C) 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.google.common.io.Files;
import ninja.*;
import ninja.exceptions.InternalServerErrorException;
import ninja.i18n.Lang;
import ninja.params.Param;
import ninja.uploads.DiskFileItemProvider;
import ninja.uploads.FileItem;
import ninja.uploads.FileProvider;
import ninja.utils.MimeTypes;
import ninja.utils.ResponseStreams;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;

import com.google.common.io.ByteStreams;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@FileProvider(DiskFileItemProvider.class)
@Singleton
public class UploadController {

    /**
     * This is the system wide logger. You can still use any config you like. Or
     * create your own custom logger.
     *
     * But often this is just a simple solution:
     */
    @Inject
    public Logger logger;

    @Inject
    Lang lang;

    private final MimeTypes mimeTypes;

    @Inject
    public UploadController(MimeTypes mimeTypes) {
        this.mimeTypes = mimeTypes;
    }

    public Result upload() {
        // simply renders the default view for this controller
        return Results.html();
    }

    /**
     *
     * This upload method expects a file and simply displays the file in the
     * multipart upload again to the user (in the correct mime encoding).
     *
     * @param context
     * @return
     * @throws Exception
     */
    /*public Result uploadFinish( Context context) throws Exception {//final @Param("upfile") File upfile,

        // we are using a renderable inner class to stream the input again to
        // the user
        Renderable renderable = new Renderable() {

            @Override
            public void render(Context context, Result result) {

                try {
                    // make sure the context really is a multipart context...
                    if (context.isMultipart()) {

                        // This is the iterator we can use to iterate over the
                        // contents
                        // of the request.
                        FileItemIterator fileItemIterator = context
                                .getFileItemIterator();

                        while (fileItemIterator.hasNext()) {

                            FileItemStream item = fileItemIterator.next();

                            String name = item.getFieldName();
                            InputStream stream = item.openStream();

                            String contentType = item.getContentType();

                            if (contentType != null) {
                                result.contentType(contentType);
                            } else {
                                contentType = mimeTypes.getMimeType(name);
                            }

                            ResponseStreams responseStreams = context
                                    .finalizeHeaders(result);

                            if (item.isFormField()) {

                                System.out.println("Form field " + name
                                        + " with value " + Streams.asString(stream)
                                        + " detected.");
                            } else {
                                //String path =  context.getContextPath() + "/" + AssetsController.ASSETS_DIR + "/img" + name;
                               // Files.copy(item.openStream(), new File(path));
                                //FileItem upfile = context.getParameterAsFileItem("upfile");
                                *//*System.out.println("++++++++++++++++++++++==============hello" + context.getContextPath());
                                String path =  context.getContextPath() + "/" + AssetsController.ASSETS_DIR + "/img" + name;
                                System.out.println("testtttttttttttttttttttttt" + path);
                                Files.copy(upfile, new File(path));*//*

                                System.out.println("File field " + name
                                        + " with file name " + item.getName()
                                        + " detected.");
                                // Process the input stream

                                ByteStreams.copy(stream,
                                        responseStreams.getOutputStream());

                            }
                        }

                    }

                } catch (IOException | FileUploadException exception) {

                    throw new InternalServerErrorException(exception);

                }

            }
        };

        return new Result(200).render(renderable);

    }*/
    /*public Result uploadFinish(Context context) throws Exception {

        // Make sure the context really is a multipart context...
        if (context.isMultipart()) {

            // This is the iterator we can use to iterate over the
            // contents of the request.
            FileItemIterator fileItemIterator = context
                    .getFileItemIterator();

            while (fileItemIterator.hasNext()) {

                FileItemStream item = fileItemIterator.next();

                String name = item.getFieldName();
                InputStream stream = item.openStream();

                String contentType = item.getContentType();

                if (item.isFormField()) {

                    // do something with the form field

                } else {
                    //FileItem upfile = context.getParameterAsFileItem("upfile");
                    //System.out.println("++++++++++++++++++++++==============hello" + context.getContextPath());
                    //String path =  context.getContextPath() + "/" + AssetsController.ASSETS_DIR + "/img" + name;
                    //Files.copy(upfile.getFile(), new File(path));
                    // process file as input stream

                }
            }

        }

        // We always return ok. You don't want to do that in production ;)
        return Results.ok();

    }*/
    public Result uploadFinish(Context context, @Param("upfile") File upfile) throws Exception {
        Result html = Results.html();
        String root = context.getRemoteAddr();
        String path =  root + "/" + AssetsController.ASSETS_DIR + "/"+ upfile.getName();
        System.out.println("testtttttttttttttttttttttt" + root);
        //Files.copy(upfile, new File("/Users/xi/Sites/DB-PROJECT/1.jpg"));
        Files.copy(upfile, new File(root));
        return html;
    }
}
