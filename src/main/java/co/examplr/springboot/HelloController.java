/*
 * Copyright (c) 2015-2022 Rocket Partners, LLC
 * https://github.com/inversion-api
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package co.examplr.springboot;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class HelloController {

    @GetMapping(value = "/s3/**")
    public String getURLValue(HttpServletRequest request) {
        StringBuilder out    = new StringBuilder("");
        String        bucket = null;
        String        key    = null;
        String        uri    = null;
        try {
            uri = request.getRequestURI();
            bucket = uri.substring(4, uri.indexOf('/', 4));
            key = uri.substring(uri.indexOf('/', 5) + 1);

            AmazonS3 s3   = AmazonS3ClientBuilder.defaultClient();
            String   text = new String(s3.getObject(bucket, key).getObjectContent().readAllBytes(), StandardCharsets.UTF_8);
            out.append(text);

        } catch (Exception ex) {
            out.append("\r\n<br> uri    = ").append(uri);
            out.append("\r\n<br> bucket = ").append(bucket);
            out.append("\r\n<br> key    = ").append(key);
            out.append("\r\n<br>error = " + ExceptionUtils.getStackTrace(ex));
        }
        return out.toString();
    }


    @GetMapping("/**")
    public String index() {

        StringBuilder       b   = new StringBuilder("Hello World!!!\r\n");
        b.append("---------------------\r\n");
        Map<String, String> env = System.getenv();

        List<String> keys = new ArrayList(env.keySet());
        Collections.sort(keys);
        for(String key : keys){
            b.append(key).append(" = ").append(env.get(key)).append("\r\n");
        }
        return b.toString();
    }

}