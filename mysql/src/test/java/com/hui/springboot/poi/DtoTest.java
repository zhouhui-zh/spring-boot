package com.hui.springboot.poi;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhouhui
 * @Description desc
 * @date 2022-04-18 16:36
 */
public class DtoTest {

    static int maxLine = 3;

    public static void main(String[] args) {


        List<Dto> dtos = intDtos();
        dtos = dtos.stream().sorted(Comparator.comparing(Dto::getSort))
                .collect(Collectors.toList());
        for (int i = 0; i < dtos.size(); i++) {
            getwidth(dtos.get(i), i);
        }
        dtos = dtos.stream().sorted(Comparator.comparing(Dto::getSort))
                .collect(Collectors.toList());
        System.out.println(JSON.toJSONString(dtos));
    }

    private static int getwidth(Dto dto, int index) {
        if (CollectionUtils.isEmpty(dto.getChildren())) {
            dto.setWidth(1);
            dto.setLineEnd(maxLine);
            dto.setLineStart(dto.getLevel());
            if (index == 0 && dto.getSort() == 1) {
                dto.setCellStart(dto.getSort());
                dto.setCellEnd(dto.getSort());
            }
            return 1;
        }
        List<Dto> chiles = dto.getChildren().stream().sorted(Comparator.comparing(Dto::getSort))
                .collect(Collectors.toList());
        int i = 0;
        for (Dto chile : chiles) {
            i = i + getwidth(chile, index);
        }
        dto.setLineEnd(dto.getLineStart() + chiles.get(chiles.size() - 1).getWidth());
        if (index == 0 && dto.getSort() == 1) {
            dto.setCellStart(dto.getSort());
            dto.setCellEnd(dto.getSort());
        }
        dto.setWidth(i);
        return i;
    }


    public static List<Dto> intDtos() {
        Dto l3r2 = new Dto();
        l3r2.setLevel(3);
        l3r2.setName("l3r2");
        l3r2.setSort(1);

        Dto l3r1 = new Dto();
        l3r1.setLevel(3);
        l3r1.setName("l3r1");
        l3r1.setSort(2);

        Dto l2r1 = new Dto();
        l2r1.setLevel(2);
        l2r1.setName("l2r1");
        l2r1.setSort(1);
        l2r1.setChildren(Lists.newArrayList(l3r1, l3r2));


        Dto l2r2 = new Dto();
        l2r2.setLevel(2);
        l2r2.setName("l2r2");
        l2r2.setSort(2);

        Dto l1r1 = new Dto();
        l1r1.setLevel(1);
        l1r1.setName("l1r1");
        l1r1.setSort(1);
        l1r1.setChildren(Lists.newArrayList(l2r2, l2r1));


        return Lists.newArrayList(l1r1);
    }
}
