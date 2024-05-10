package com.skillstorm.taxprep.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.taxprep.server.exceptions.NotFoundException;
import com.skillstorm.taxprep.server.models.Income1099;
import com.skillstorm.taxprep.server.repositories.Income1099Repository;

import java.util.List;
import java.util.Optional;

@Service
public class Income1099Service {

    @Autowired
    private Income1099Repository income1099Repository;

    // Find all incomes
    public List<Income1099> getAllIncome() {
        return income1099Repository.findAll();
    }

    // Get income by income id
    public Income1099 getIncomeById(int id) {
        Optional<Income1099> income = income1099Repository.findById(id);

        if (income.isPresent()) {
          return income.get();
        }

        return null;
    }

    // Get the list of incomes associated with a tax info by id
    public List<Income1099> getIncomeByTaxInfoId(int taxInfoId) {
      Optional<List<Income1099>> incomes = income1099Repository.findByTaxInfoId(taxInfoId);

      if (incomes.isPresent()) {
        return incomes.get();
      } else {
        throw new NotFoundException("No 1099 incomes found.");
      }
    }

    // Save/update income
    public Income1099 saveOrUpdateIncome(Income1099 income) {
        return income1099Repository.save(income);
    }

    // Delete income by id
    public void deleteIncomeById(int id) {
        income1099Repository.deleteById(id);
    }
}
