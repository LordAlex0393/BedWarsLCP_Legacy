package org.lordalex.bedwarslcp.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

public class YmlPaser {
    public static MapConfig parseMapConfig(File file){
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            MapConfig mapConfig = mapper.readValue(file, MapConfig.class);
            return mapConfig;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
