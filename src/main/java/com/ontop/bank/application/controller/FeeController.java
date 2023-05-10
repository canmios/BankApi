package com.ontop.bank.application.controller;

import com.ontop.bank.application.dto.fee.FeeResponse;
import com.ontop.bank.application.mapper.FeeMapper;
import com.ontop.bank.service.fee.FeeService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/fees")
public class FeeController {

    private final FeeService feeService;

    private final FeeMapper feeMapper = FeeMapper.INSTANCE;

    @ApiOperation(value = "isRunning", notes = "To get Cost fee, calculated from transaction")
    @GetMapping(path = "/cost")
    public FeeResponse getCostFee(@RequestParam(name = "amount") Double amount) {
        return feeMapper.toTransactionFeeResponse(feeService.calculateFee(amount));
    }

}
