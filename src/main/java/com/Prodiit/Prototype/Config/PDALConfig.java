package com.Prodiit.Prototype.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PDALConfig {
    @Value("${pdal.pipeline.json}")
    private String pdalPipelineJson;

    public String getPDALPipelineJson() {
        return pdalPipelineJson;
    }
}
