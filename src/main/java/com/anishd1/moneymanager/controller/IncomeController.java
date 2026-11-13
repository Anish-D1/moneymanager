
package com.anishd1.moneymanager.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.anishd1.moneymanager.dto.IncomeDTO;
import com.anishd1.moneymanager.service.IncomeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/incomes")
public class IncomeController {

    private final IncomeService incomeService;

    // Add income
    @PostMapping
    public ResponseEntity<IncomeDTO> addIncome(
            @RequestBody IncomeDTO dto
    ) {
        IncomeDTO saved = incomeService.addIncome(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // Get all incomes for the current user
    @GetMapping
    public ResponseEntity<List<IncomeDTO>> getIncomes() {
        List<IncomeDTO> incomes =
                incomeService.getAllIncomesForCurrentUser();

        return ResponseEntity.ok(incomes);
    }

    // Update income
    @PutMapping("/{id}")
    public ResponseEntity<IncomeDTO> updateIncome(
            @PathVariable Long id,
            @RequestBody IncomeDTO dto
    ) {
        IncomeDTO updated = incomeService.updateIncome(id, dto);
        return ResponseEntity.ok(updated);
    }

    // Delete income
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncome(
            @PathVariable Long id
    ) {
        incomeService.deleteIncome(id);
        return ResponseEntity.noContent().build();
    }
}
