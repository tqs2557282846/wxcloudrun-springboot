package com.tencent.wxcloudrun.Util;

import com.tencent.wxcloudrun.dto.merchandiseDto;
import com.tencent.wxcloudrun.model.Merchandise;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MapStruct {
    /**
     * 获取该类自动生成的实现类的实例
     * 接口中的属性都是 public static final 的 方法都是public abstract的
     */
    MapStruct INSTANCES = Mappers.getMapper(MapStruct.class);

    /**
     * 这些方法就是用于实现对象属性复制的方法
     *
     * @Mapping 用来定义属性复制规则 source 指定源对象属性 target指定目标对象属性     *
     * @param  page 这个参数就是源对象，也就是需要被复制的对象
     * @return 返回的是目标对象，就是最终的结果对象
     */
    /*
    @Mappings({})
    StudentsInfoController roomstudentTostudentinfo(Student student);

    @Mappings({
            @Mapping(target = "xuehao",source = "studentId"),
            @Mapping(target = "classId",source = "roomName")
    })
    Student studentinfoToroomstudent(StudentsInfoController studentsInfoController);

     */

    @Mappings({
            @Mapping(target = "pictures",ignore = true)
    })
    Merchandise merchandiseDtoToMerchandise(merchandiseDto merchandiseDto);
}
