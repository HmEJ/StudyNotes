package com.mh.Service;

import com.mh.pojo.Dept;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DeptService {
    List<Dept> queryAllInfo();
}
