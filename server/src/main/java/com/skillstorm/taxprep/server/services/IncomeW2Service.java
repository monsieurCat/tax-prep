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

    public List<IncomeW2> getAllIncome() {
        return incomeW2Repository.findAll();
    }

    public IncomeW2 getIncomeById(int id) {
      Optional<IncomeW2> income = incomeW2Repository.findById(id);

      if (income.isPresent()) {
        return income.get();
      }

      return null;
    }

    public List<IncomeW2> getIncomeByTaxInfoId(int taxInfoId) {
      Optional<List<IncomeW2>> incomes = incomeW2Repository.findByTaxInfoId(taxInfoId);

      if (incomes.isPresent()) {
        return incomes.get();
      } else {
        throw new NotFoundException("No W2 incomes found.");
      }
    }

    public IncomeW2 saveOrUpdateIncome(IncomeW2 income) {
        return incomeW2Repository.save(income);
    }

    public void deleteIncomeById(int id) {
        incomeW2Repository.deleteById(id);
    }
}
