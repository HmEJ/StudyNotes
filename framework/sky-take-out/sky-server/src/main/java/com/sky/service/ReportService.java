package com.sky.service;

import com.sky.vo.TurnoverReportVO;

import java.time.LocalDate;

/**
 * @Classname ReportService
 * @Description
 * @Date 2024/1/30 下午10:21
 * @Created by joneelmo
 */
public interface ReportService {

    TurnoverReportVO getTurnOverStatistics(LocalDate begin,LocalDate end);
}
