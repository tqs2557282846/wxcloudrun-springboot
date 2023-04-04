package com.tencent.wxcloudrun.model;

import lombok.Data;

@Data
public class OpenPidResponse {
    String errmsg;
    int errcode;
    String openpid;
}
