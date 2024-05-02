package com.skillstorm.taxprep.server.utilities;

import org.springframework.beans.factory.annotation.Autowired;

import com.skillstorm.taxprep.server.services.TaxBracketService;

public class TaxCalculator {
  
  @Autowired
  TaxBracketService taxBracketService;

}
