package com.example.hospital.api.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import cn.hutool.core.bean.BeanUtil;
import com.example.hospital.api.common.R;
import com.example.hospital.api.controller.form.SearchDeptSubScheduleForm;
import com.example.hospital.api.service.DoctorWorkPlanScheduleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author WENJIN LI
 * @date 2023/2/28 17:31
 */
@RestController
@RequestMapping("/doctor/work_plan/schedule")
public class DoctorWorkPlanScheduleController {
    @Resource
    private DoctorWorkPlanScheduleService doctorWorkPlanScheduleService;

    @PostMapping("/searchDeptSubSchedule")
    @SaCheckLogin
    @SaCheckPermission(value = {"ROOT", "SCHEDULE:SELECT"}, mode = SaMode.OR)
    public R searchDeptSubSchedule(@RequestBody @Valid SearchDeptSubScheduleForm form) {
        Map param = BeanUtil.beanToMap(form);
        ArrayList list = doctorWorkPlanScheduleService.searchDeptSubSchedule(param);
        return R.ok().put("result", list);
    }
}

