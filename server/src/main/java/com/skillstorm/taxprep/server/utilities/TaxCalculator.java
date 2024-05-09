package com.skillstorm.taxprep.server.utilities;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skillstorm.taxprep.server.dtos.TaxResultsDTO;
import com.skillstorm.taxprep.server.models.FilingStatus;
import com.skillstorm.taxprep.server.models.Income1099;
import com.skillstorm.taxprep.server.models.IncomeW2;
import com.skillstorm.taxprep.server.models.TaxBracket;
import com.skillstorm.taxprep.server.models.TaxInfo;
import com.skillstorm.taxprep.server.services.FilingStatusService;
import com.skillstorm.taxprep.server.services.StandardizedDeductionService;
import com.skillstorm.taxprep.server.services.TaxBracketService;
@Component
public class TaxCalculator {

  @Autowired
  FilingStatusService filingStatusService;
  
  @Autowired
  private TaxBracketService taxBracketService;

  @Autowired
  StandardizedDeductionService standardizedDeductionService;


  public TaxResultsDTO calculate(TaxInfo taxInfo, List<IncomeW2> incomesW2, List<Income1099> incomes1099) {

    // First get filing status
    FilingStatus curStatus = taxInfo.getFilingStatus();

    // Calculate total W2 and 1099 income, and total tax withheld from incomes
    double taxWithheld = 0;
    double totalIncomeW2 = 0;
    double totalIncome1099 = 0;

    for (IncomeW2 income : incomesW2) {
      totalIncomeW2 += income.getIncome().doubleValue();
      taxWithheld = income.getWithholdings().doubleValue();
    }

    for (Income1099 income : incomes1099) {
      totalIncome1099 += income.getIncome().doubleValue();
      taxWithheld = income.getWithholdings().doubleValue();
    }

    // Calculate total income
    double totalIncome = totalIncomeW2 + totalIncome1099;

    // Calculate deductions
    double deductions = 0;

    // Sum inputted deductions
    deductions += taxInfo.getMortgageInterest().doubleValue();
    deductions += taxInfo.getDonations().doubleValue();
    deductions += taxInfo.getPropertyTax().doubleValue();
    deductions += taxInfo.getMedical().doubleValue();
    deductions += taxInfo.getStudentLoanInterest().doubleValue();
    deductions += taxInfo.getOtherDeduction().doubleValue();

    // If no deductions inputted, assume user chose standardized deductions
    if (deductions == 0) {
      deductions = standardizedDeductionService
                    .getByFilingStatusId(curStatus.getId())
                    .getDeductionAmount();
    }

    // Calculate taxable income
    double taxableIncome = totalIncome - deductions;

    List<TaxBracket> taxBrackets = taxBracketService.findByFilingStatusID(curStatus.getId());
    
    // Calculate Federal Taxes
    double totalTaxes = 0.0;
    double remainingIncome = taxableIncome;

    for (TaxBracket bracket : taxBrackets) {
        double minIncome = bracket.getMinIncome();
        double maxIncome = bracket.getMaxIncome();
        double taxRate = bracket.getRate().doubleValue();

        if (remainingIncome <= 0) {
            break; // No more income to tax
        }

        double taxableAmountInBracket = Math.min(maxIncome - minIncome, remainingIncome);
        double taxInBracket = taxableAmountInBracket * taxRate;
        totalTaxes += taxInBracket;
        remainingIncome -= taxableAmountInBracket;
    }

    // Calculate marginal tax rate
    double marginalTaxRate = 0.0;

    for (TaxBracket bracket : taxBrackets) {
        double minIncome = bracket.getMinIncome();
        double maxIncome = bracket.getMaxIncome();

        if (taxableIncome >= minIncome && taxableIncome <= maxIncome) {
            marginalTaxRate = bracket.getRate().doubleValue();
            break;
        }
    }

    // Calculate effective tax rate
    double effectiveTaxRate = (totalTaxes / totalIncome);

    // Deduct taxWithheld
    double finalTaxAmount = totalTaxes - taxWithheld;

    // Calculate and deduct child tax credit
    double childTaxCredit = 0;

    for (int i = 0; i < taxInfo.getNumDependents(); i++) {
      if (finalTaxAmount <= 0) {
        childTaxCredit += 1600;
        finalTaxAmount -= 1600;
        continue;
      }
      childTaxCredit += 2000;
      finalTaxAmount -= 2000;
    }

    // Calculate and deduct earned income tax credit
    double earnedIncomeTaxCredit = calculateEarnedIncomeCredit(taxableIncome, taxInfo.getNumDependents());
    finalTaxAmount -= earnedIncomeTaxCredit;

    // Setup and return tax results
    TaxResultsDTO result = new TaxResultsDTO();
    result.setIncomeW2(totalIncomeW2);
    result.setIncome1099(totalIncome1099);
    result.setTotalIncome(totalIncome);
    result.setDeductions(deductions);
    result.setTaxableIncome(taxableIncome);
    result.setTotalTaxAmount(totalTaxes);
    result.setTaxWithheld(taxWithheld);
    result.setChildTaxCredit(childTaxCredit);
    result.setEarnedIncomeTaxCredit(earnedIncomeTaxCredit);
    result.setFinalTaxAmount(finalTaxAmount);
    result.setMarginalTaxRate(marginalTaxRate);
    result.setEffectiveTaxRate(effectiveTaxRate);

    return result;
  }

  private double calculateEarnedIncomeCredit(double taxableIncome, int numDependents) {
    double eicAmount = 0.0;

    // Determine the appropriate AGI income limits and credit amounts based on the number of qualifying children
    double agiIncomeLimit = 0.0;
    double creditAmount = 0.0;

    if (numDependents >= 3) {
        agiIncomeLimit = 63414;         // AGI income limit for three or more qualifying children
        creditAmount = 6728;            // EIC amount for three or more qualifying children
    } else if (numDependents == 2) {
        agiIncomeLimit = 59608;         // AGI income limit for two qualifying children
        creditAmount = 5980;            // EIC amount for two qualifying children
    } else if (numDependents == 1) {
        agiIncomeLimit = 54108;         // AGI income limit for one qualifying child
        creditAmount = 3618;            // EIC amount for one qualifying child
    } else {
        agiIncomeLimit = 27380;         // AGI income limit for no qualifying children
        creditAmount = 1502;            // EIC amount for no qualifying children
    }

    // Check if taxableIncome falls within the AGI income limits
    if (taxableIncome <= agiIncomeLimit) {
        // Calculate the EIC amount based on the provided credit amount
        eicAmount = creditAmount;
    }

    return eicAmount;
  }

}
