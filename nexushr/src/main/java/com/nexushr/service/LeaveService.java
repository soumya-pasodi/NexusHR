package com.nexushr.service;

import com.nexushr.entity.LeaveRequest;
import com.nexushr.repository.LeaveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveService {

    private final LeaveRepository leaveRepository;

    public LeaveRequest applyLeave(
            LeaveRequest leaveRequest) {

        leaveRequest.setStatus("PENDING");

        return leaveRepository.save(leaveRequest);
    }

    public List<LeaveRequest> getAllLeaves() {
        return leaveRepository.findAll();
    }

    public List<LeaveRequest> getLeavesByEmployee(
            Long employeeId) {

        return leaveRepository.findByEmployeeId(employeeId);
    }

    public LeaveRequest approveLeave(Long id) {

        LeaveRequest leave =
                leaveRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Leave request not found"));

        leave.setStatus("APPROVED");

        return leaveRepository.save(leave);
    }

    public LeaveRequest rejectLeave(Long id) {

        LeaveRequest leave =
                leaveRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Leave request not found"));

        leave.setStatus("REJECTED");

        return leaveRepository.save(leave);
    }
}