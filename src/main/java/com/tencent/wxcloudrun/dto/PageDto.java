package com.tencent.wxcloudrun.dto;

import lombok.Data;
import org.springframework.data.domain.Sort;
@Data
public class PageDto {
    int page;
    int size;
    //排序，0升1降
    int array;
    //排序字段
    String arrayType;
}
