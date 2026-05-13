package com.nexushr.service;

import com.nexushr.entity.Attendance;
import com.nexushr.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public Attendance markAttendance(
            Attendance attendance) {

        return attendanceRepository.save(attendance);
    }

    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    public List<Attendance> getAttendanceByEmployee(
            Long employeeId) {

        return attendanceRepository
                .findByEmployeeId(employeeId);
    }
}