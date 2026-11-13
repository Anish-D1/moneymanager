
package com.anishd1.moneymanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.anishd1.moneymanager.dto.ExpenseDTO;
import com.anishd1.moneymanager.entity.CategoryEntity;
import com.anishd1.moneymanager.entity.ExpenseEntity;
import com.anishd1.moneymanager.entity.ProfileEntity;
import com.anishd1.moneymanager.repository.CategoryRepository;
import com.anishd1.moneymanager.repository.ExpenseRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final CategoryRepository categoryRepository;
    private final ExpenseRepository expenseRepository;
    private final ProfileService profileService;

    // Adds a new expense to the database
    public ExpenseDTO addExpense(ExpenseDTO dto) {

        ProfileEntity profile = profileService.getCurrentProfile();

        CategoryEntity category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        ExpenseEntity newExpense = toEntity(dto, profile, category);

        newExpense = expenseRepository.save(newExpense);

        return toDTO(newExpense);
    }

    // Updates an existing expense belonging to the current user
    public ExpenseDTO updateExpense(Long expenseId, ExpenseDTO dto) {

        ProfileEntity profile = profileService.getCurrentProfile();

        ExpenseEntity expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        if (!expense.getProfile().getId().equals(profile.getId())) {
            throw new RuntimeException("Unauthorized to update this expense");
        }

        CategoryEntity category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        expense.setName(dto.getName());
        expense.setIcon(dto.getIcon());
        expense.setAmount(dto.getAmount());
        expense.setDate(dto.getDate());
        expense.setCategory(category);

        ExpenseEntity updatedExpense = expenseRepository.save(expense);

        return toDTO(updatedExpense);
    }

    // Retrieves all expenses for the current user, regardless of date
    public List<ExpenseDTO> getAllExpensesForCurrentUser() {

        ProfileEntity profile = profileService.getCurrentProfile();

        List<ExpenseEntity> list =
                expenseRepository.findByProfileIdOrderByDateDesc(profile.getId());

        return list.stream().map(this::toDTO).toList();
    }

    // Retrieves expenses for the current month
    public List<ExpenseDTO> getCurrentMonthExpensesForCurrentUser() {

        ProfileEntity profile = profileService.getCurrentProfile();

        LocalDate now = LocalDate.now();
        LocalDate startDate = now.withDayOfMonth(1);
        LocalDate endDate = now.withDayOfMonth(now.lengthOfMonth());

        List<ExpenseEntity> list =
                expenseRepository.findByProfileIdAndDateBetween(
                        profile.getId(), startDate, endDate
                );

        return list.stream().map(this::toDTO).toList();
    }

    // Deletes an expense belonging to the current user
    public void deleteExpense(Long expenseId) {

        ProfileEntity profile = profileService.getCurrentProfile();

        ExpenseEntity entity = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        if (!entity.getProfile().getId().equals(profile.getId())) {
            throw new RuntimeException("Unauthorized to delete this expense");
        }

        expenseRepository.delete(entity);
    }

    // Gets the latest five expenses for the current user
    public List<ExpenseDTO> getLatest5ExpensesForCurrentUser() {

        ProfileEntity profile = profileService.getCurrentProfile();

        List<ExpenseEntity> list =
                expenseRepository.findTop5ByProfileIdOrderByDateDesc(profile.getId());

        return list.stream().map(this::toDTO).toList();
    }

    // Gets total expenses for the current user
    public BigDecimal getTotalExpenseForCurrentUser() {

        ProfileEntity profile = profileService.getCurrentProfile();

        BigDecimal total =
                expenseRepository.findTotalExpenseByProfileId(profile.getId());

        return total != null ? total : BigDecimal.ZERO;
    }

    // Filters expenses
    public List<ExpenseDTO> filterExpenses(
            LocalDate startDate,
            LocalDate endDate,
            String keyword,
            Sort sort
    ) {

        ProfileEntity profile = profileService.getCurrentProfile();

        List<ExpenseEntity> list =
                expenseRepository.findByProfileIdAndDateBetweenAndNameContainingIgnoreCase(
                        profile.getId(), startDate, endDate, keyword, sort
                );

        return list.stream().map(this::toDTO).toList();
    }

    // Gets expenses for a user on a specific date
    public List<ExpenseDTO> getExpensesForUserOnDate(
            Long profileId,
            LocalDate date
    ) {

        List<ExpenseEntity> list =
                expenseRepository.findByProfileIdAndDate(profileId, date);

        return list.stream().map(this::toDTO).toList();
    }

    // Converts DTO to entity
    private ExpenseEntity toEntity(
            ExpenseDTO dto,
            ProfileEntity profile,
            CategoryEntity category
    ) {

        return ExpenseEntity.builder()
                .name(dto.getName())
                .icon(dto.getIcon())
                .amount(dto.getAmount())
                .date(dto.getDate())
                .profile(profile)
                .category(category)
                .build();
    }

    // Converts entity to DTO
    private ExpenseDTO toDTO(ExpenseEntity entity) {

        return ExpenseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .icon(entity.getIcon())
                .categoryId(entity.getCategory() != null
                        ? entity.getCategory().getId() : null)
                .categoryName(entity.getCategory() != null
                        ? entity.getCategory().getName() : "N/A")
                .amount(entity.getAmount())
                .date(entity.getDate())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
