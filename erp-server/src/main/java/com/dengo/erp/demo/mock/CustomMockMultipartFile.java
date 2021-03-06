/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dengo.erp.demo.mock;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * Custom Mock implementation of the {@link org.springframework.web.multipart.MultipartFile}
 * interface. Totally equivalent of original Spring realization. Actual custom realization uses for avoid
 * JAR-hell and don't change scope of org.springframework.boot:spring-boot-starter-test lib (test-compile -> compile)
 *
 * @author Sergey Petrovsky
 * @see org.springframework.mock.web.MockMultipartFile
 */
public class CustomMockMultipartFile implements MultipartFile {

    private final String name;

    private String originalFilename;

    private String contentType;

    private final byte[] content;


    /**
     * Create a new MockMultipartFile with the given content.
     * @param name the name of the file
     * @param content the content of the file
     */
    public CustomMockMultipartFile(String name, byte[] content) {
        this(name, "", null, content);
    }

    /**
     * Create a new MockMultipartFile with the given content.
     * @param name the name of the file
     * @param contentStream the content of the file as stream
     * @throws IOException if reading from the stream failed
     */
    public CustomMockMultipartFile(String name, InputStream contentStream) throws IOException {
        this(name, "", null, FileCopyUtils.copyToByteArray(contentStream));
    }

    /**
     * Create a new MockMultipartFile with the given content.
     * @param name the name of the file
     * @param originalFilename the original filename (as on the client's machine)
     * @param contentType the content type (if known)
     * @param content the content of the file
     */
    public CustomMockMultipartFile(String name, String originalFilename, String contentType, byte[] content) {
        Assert.hasLength(name, "Name must not be null");
        this.name = name;
        this.originalFilename = originalFilename != null ? originalFilename : "";
        this.contentType = contentType;
        this.content = content != null ? content : new byte[0];
    }

    /**
     * Create a new MockMultipartFile with the given content.
     * @param name the name of the file
     * @param originalFilename the original filename (as on the client's machine)
     * @param contentType the content type (if known)
     * @param contentStream the content of the file as stream
     * @throws IOException if reading from the stream failed
     */
    public CustomMockMultipartFile(String name, String originalFilename, String contentType, InputStream contentStream)
            throws IOException {

        this(name, originalFilename, contentType, FileCopyUtils.copyToByteArray(contentStream));
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getOriginalFilename() {
        return this.originalFilename;
    }

    @Override
    public String getContentType() {
        return this.contentType;
    }

    @Override
    public boolean isEmpty() {
        return (this.content.length == 0);
    }

    @Override
    public long getSize() {
        return this.content.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return this.content;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(this.content);
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        FileCopyUtils.copy(this.content, dest);
    }

}
