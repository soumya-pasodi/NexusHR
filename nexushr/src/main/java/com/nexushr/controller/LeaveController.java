package com.nexushr.controller;

import com.nexushr.entity.LeaveRequest;
import com.nexushr.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leave")
@RequiredArgsConstructor
public class LeaveController {

    private final LeaveService leaveService;

    @PostMapping("/apply")
    public LeaveRequest applyLeave(
            @RequestBody LeaveRequest leaveRequest) {

        return leaveService.applyLeave(leaveRequest);
    }

    @GetMapping
    public List<LeaveRequest> getAllLeaves() {

        return leaveService.getAllLeaves();
    }

    @GetMapping("/employee/{employeeId}")
    public List<LeaveRequest> getLeavesByEmployee(
            @PathVariable Long employeeId) {

        return leaveService
                .getLeavesByEmployee(employeeId);
    }

    @PutMapping("/{id}/approve")
    public LeaveRequest approveLeave(
            @PathVariable Long id) {

        return leaveService.approveLeave(id);
    }

    @PutMapping("/{id}/reject")
    public LeaveRequest rejectLeave(
            @PathVariable Long id) {

        return leaveService.rejectLeave(id);
    }
}