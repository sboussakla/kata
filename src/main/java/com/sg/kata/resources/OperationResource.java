package com.sg.kata.resources;

import com.sg.kata.dto.OperationDto;
import com.sg.kata.entities.Account;
import com.sg.kata.entities.TransactionHistory;
import com.sg.kata.exceptions.AccountNotFoundException;
import com.sg.kata.services.operation.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/bank")
public class OperationResource {
    @Autowired
    private OperationService operationService;

    @PostMapping
    public Account executeOperation(@Valid @RequestBody() OperationDto operationDto) throws AccountNotFoundException {
        return this.operationService.createOperation(operationDto);
    }

    @GetMapping(value = "/history/{accountId}")
    public List<TransactionHistory> getAccountHistory(@PathVariable(value = "accountId") @NotNull Long accountId) throws  AccountNotFoundException {
        return this.operationService.getAccountHistory(accountId);
    }
}
