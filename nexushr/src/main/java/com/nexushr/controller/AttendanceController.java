package com.nexushr.controller;

import com.nexushr.entity.Attendance;
import com.nexushr.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping
    public Attendance markAttendance(
            @RequestBody Attendance attendance) {

        return attendanceService
                .markAttendance(attendance);
    }

    @GetMapping
    public List<Attendance> getAllAttendance() {

        return attendanceService
                .getAllAttendance();
    }

    @GetMapping("/employee/{employeeId}")
    public List<Attendance> getAttendanceByEmployee(
            @PathVariable Long employeeId) {

        return attendanceService
                .getAttendanceByEmployee(employeeId);
    }
}