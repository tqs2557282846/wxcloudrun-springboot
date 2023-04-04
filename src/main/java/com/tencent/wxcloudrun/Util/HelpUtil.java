package com.tencent.wxcloudrun.Util;

import com.tencent.wxcloudrun.model.OpenPidResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

public class HelpUtil {
    @Autowired
    RestTemplate restTemplate;

    public String getOpenPid(String code){
        HttpHeaders headers = new HttpHeaders();
        LinkedMultiValueMap<String, Object> params= new LinkedMultiValueMap<>();
        params.add("code", code);
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(params, headers);
        OpenPidResponse response = restTemplate.postForObject("https://api.weixin.qq.com/wxa/getpluginopenpid",request, OpenPidResponse.class);
        assert response != null;
        if(response.getErrcode()==0){
            return response.getOpenpid();
        }
        else return null;
    }

}
