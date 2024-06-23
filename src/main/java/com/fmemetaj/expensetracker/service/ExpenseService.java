package com.fmemetaj.expensetracker.service;

import com.fmemetaj.expensetracker.entity.Expense;
import com.fmemetaj.expensetracker.entity.User;
import com.fmemetaj.expensetracker.repository.ExpenseRepository;
import com.fmemetaj.expensetracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public ExpenseService(ExpenseRepository expenseRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Optional<Expense> getExpenseById(String id) {
        return expenseRepository.findById(id);
    }

    public Expense createExpense(Expense expense) {
        if (expense.getUser() != null) {
            Optional<User> user = userRepository.findById(expense.getUser().getId());
            if (user.isPresent()) {
                expense.setUser(user.get());
            } else {
                throw new RuntimeException("User not found");
            }
        }
        return expenseRepository.save(expense);
    }

    public void deleteExpense(String id) {
        expenseRepository.deleteById(id);
    }
}
