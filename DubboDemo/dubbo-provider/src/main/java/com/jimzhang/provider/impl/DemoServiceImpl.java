package com.jimzhang.provider.impl;

import com.jimzhang.DemoService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jimzhang
 * <></>
 * @version V1.0.0
 * @description 服务实现
 * @date 2018-03-09 12:40
 */
public class DemoServiceImpl implements DemoService {
    @Override
    public List<String> getPermissions(Long id) {
        List<String> demo = new ArrayList<String>();
        demo.add(String.format("Permission_%d", id - 1));
        demo.add(String.format("Permission_%d", id));
        demo.add(String.format("Permission_%d", id + 1));
        return demo;
    }
}
