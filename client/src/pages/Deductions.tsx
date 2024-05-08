import { Label, TextInput, FormGroup, ErrorMessage, Textarea, Fieldset, Button, Checkbox, Grid, GridContainer, RequiredMarker, Select, DateRangePicker, DatePicker, ButtonGroup, ProcessListHeading, ProcessListItem, StepIndicator, StepIndicatorStep, Radio, TextInputMask } from "@trussworks/react-uswds";
import { Form, Link, useNavigate } from "react-router-dom";
import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { submitFullTaxInfo, updateDeductions } from '../redux/slices/taxSlice';
import { RootState } from "../redux/storeTypes";
import { AppDispatch } from "../redux/store";

export const Deductions = (): React.ReactElement => {
  const dispatch = useDispatch<AppDispatch>();
  const navigate = useNavigate();
  const currentTaxInfo = useSelector((state: RootState) => state.taxInfo);


  const [deductions, setDeductions] = useState(currentTaxInfo.deductions || {
    mortgageInterest: 0,
    donations: 0,
    propertyTax: 0,
    medical: 0,
    studentLoanInterest: 0,
    otherDeduction: 0,
    otherIncome: 0
});

useEffect(() => {
  if (currentTaxInfo.deductions) {
      setDeductions(currentTaxInfo.deductions);
  }
}, [currentTaxInfo.deductions]);
 
  


const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
  const { name, value } = e.target;
  setDeductions(prev => ({ ...prev, [name]: parseFloat(value) || 0 }));
};



  // When form is submitted, update deductions in Redux
  const handleSubmit = () => {
    dispatch(updateDeductions(deductions));
    navigate('/review');
  };



 return (

<div style={{ marginLeft: '2rem' }}>
    
  <GridContainer className="usa-section">

    <StepIndicator
      counters="default"
      headingLevel="h4"
      ofText="of"
      stepText="Step"
    >
      <StepIndicatorStep
        label="Personal information"
        status="complete"
      />
      <StepIndicatorStep
        label="Filing Status"
        status="complete"

      />
      <StepIndicatorStep
        label="W2 Income"
        status="complete"
      />
      <StepIndicatorStep
        label="1099 Income"
        status="complete"
      />
      <StepIndicatorStep label="Deductions"  status="current" />
      <StepIndicatorStep label="Review"  />
      <StepIndicatorStep label="Sign and Submit" />
    </StepIndicator>

    <Grid row gap>


    <Grid col={4}>
        {/*
    <Grid col={4} style={{
    marginLeft: '18rem'}}>
    */}






{/* <Form onSubmit={mockSubmit}>*/}
<Fieldset legend="Itemized Deductions" legendStyle="large">




<Label htmlFor="mortgageInterest">Mortgage Interest</Label>
              <TextInput id="mortgageInterest" name="mortgageInterest" type="number"
                value={deductions.mortgageInterest.toString()} onChange={handleChange} />
              
              <Label htmlFor="donations">Donations</Label>
              <TextInput id="donations" name="donations" type="number"
                value={deductions.donations.toString()} onChange={handleChange} />
              
              <Label htmlFor="propertyTax">Property Tax</Label>
              <TextInput id="propertyTax" name="propertyTax" type="number"
                value={deductions.propertyTax.toString()} onChange={handleChange} />

              <Label htmlFor="medical">Medical Expenses</Label>
              <TextInput id="medical" name="medical" type="number"
                value={deductions.medical.toString()} onChange={handleChange} />

              <Label htmlFor="studentLoanInterest">Student Loan Interest</Label>
              <TextInput id="studentLoanInterest" name="studentLoanInterest" type="number"
                value={deductions.studentLoanInterest.toString()} onChange={handleChange} />

              <Label htmlFor="otherDeduction">Other Deductions</Label>
              <TextInput id="otherDeduction" name="otherDeduction" type="number"
                value={deductions.otherDeduction.toString()} onChange={handleChange} />

              <ButtonGroup type="default" className="margin-top-4">
                <Link to="/income1099" className="usa-button usa-button--outline">Back</Link>
                
              </ButtonGroup>
              <Button type="button" onClick={handleSubmit}>Continue</Button>

  
</Fieldset>
</Grid>

<Grid col={1}></Grid>

<Grid col={4}>
{/* <Form onSubmit={mockSubmit}>*/}





      
      </Grid>
    </Grid>
  </GridContainer>






  {/*</Form>*/}
</div>

);
};
export default Deductions;