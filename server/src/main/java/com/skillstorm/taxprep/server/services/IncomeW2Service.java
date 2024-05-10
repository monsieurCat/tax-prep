package com.skillstorm.taxprep.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.taxprep.server.exceptions.NotFoundException;
import com.skillstorm.taxprep.server.models.IncomeW2;
import com.skillstorm.taxprep.server.repositories.IncomeW2Repository;

import java.util.List;
import java.util.Optional;

@Service
public class IncomeW2Service {

    @Autowired
    private IncomeW2Repository incomeW2Repository;

    // Get all incomes
    public List<IncomeW2> getAllIncome() {
        return incomeW2Repository.findAll();
    }

    // Get specific income by income id
    public IncomeW2 getIncomeById(int id) {
      Optional<IncomeW2> income = incomeW2Repository.findById(id);

      if (income.isPresent()) {
        return income.get();
      }

      return null;
    }

    // Get incomes by tax info id
    public List<IncomeW2> getIncomeByTaxInfoId(int taxInfoId) {
      Optional<List<IncomeW2>> incomes = incomeW2Repository.findByTaxInfoId(taxInfoId);

      if (incomes.isPresent()) {
        return incomes.get();
      } else {
        throw new NotFoundException("No W2 incomes found.");
      }
    }

    // Save/update income
    public IncomeW2 saveOrUpdateIncome(IncomeW2 income) {
        return incomeW2Repository.save(income);
    }

    // Delete income by id
    public void deleteIncomeById(int id) {
        incomeW2Repository.deleteById(id);
    }
}
