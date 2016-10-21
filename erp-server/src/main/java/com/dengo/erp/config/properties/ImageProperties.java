package com.dengo.erp.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * MailProperties
 * Properties class for images
 *
 * @author Taras Polishchuk
 */

@Component
@ConfigurationProperties(prefix = "img")
public class ImageProperties {

    private String originalImagePath;
    private String previewImagePath;
    private Integer previewSize;
    private String maxUploadFile;

    public String getOriginalImagePath() {
        return originalImagePath;
    }

    public void setOriginalImagePath(String originalImagePath) {
        this.originalImagePath = originalImagePath;
    }

    public String getPreviewImagePath() {
        return previewImagePath;
    }

    public void setPreviewImagePath(String previewImagePath) {
        this.previewImagePath = previewImagePath;
    }

    public Integer getPreviewSize() { return previewSize; }

    public void setPreviewSize(Integer previewSize) {
        this.previewSize = previewSize;
    }

    public String getMaxUploadFile() { return maxUploadFile; }

    public void setMaxUploadFile(String maxUploadFile) {
        this.maxUploadFile = maxUploadFile;
    }
}
