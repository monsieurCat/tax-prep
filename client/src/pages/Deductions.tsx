import { Label, TextInput, FormGroup, ErrorMessage, Textarea, Fieldset, Button, Checkbox, Grid, GridContainer, RequiredMarker, Select, DateRangePicker, DatePicker, ButtonGroup, ProcessListHeading, ProcessListItem, StepIndicator, StepIndicatorStep, Radio, TextInputMask } from "@trussworks/react-uswds";
import { Form, Link, useNavigate } from "react-router-dom";
import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { submitFullTaxInfo, updateDonations, updateMedical, updateMortgageInterest, updateOtherDeduction, updateOtherIncome, updatePropertyTax, updateStudentLoanInterest } from '../redux/slices/taxSlice';
import { RootState } from "../redux/storeTypes";
import { AppDispatch } from "../redux/store";
import { BarChart } from '@mui/x-charts/BarChart';

export const Deductions = (): React.ReactElement => {
  const dispatch = useDispatch<AppDispatch>();
  const navigate = useNavigate();
  const currentTaxInfo = useSelector((state: RootState) => state.taxInfo);


   // State for each deduction type
   const [mortgageInterest, setMortgageInterest] = useState<number>(currentTaxInfo.mortgageInterest);
   const [donations, setDonations] = useState<number>(currentTaxInfo.donations);
   const [propertyTax, setPropertyTax] = useState<number>(currentTaxInfo.propertyTax);
   const [medical, setMedical] = useState<number>(currentTaxInfo.medical);
   const [studentLoanInterest, setStudentLoanInterest] = useState<number>(currentTaxInfo.studentLoanInterest);
   const [otherDeduction, setOtherDeduction] = useState<number>(currentTaxInfo.otherDeduction);
  
 

useEffect(() => {
  
    setMortgageInterest(currentTaxInfo.mortgageInterest);
    setDonations(currentTaxInfo.donations);
    setPropertyTax(currentTaxInfo.propertyTax);
    setMedical(currentTaxInfo.medical);
    setStudentLoanInterest(currentTaxInfo.studentLoanInterest);
    setOtherDeduction(currentTaxInfo.otherDeduction);
   
  
}, [currentTaxInfo.mortgageInterest,
  currentTaxInfo.donations,
  currentTaxInfo.propertyTax,
  currentTaxInfo.medical,
  currentTaxInfo.studentLoanInterest,
  currentTaxInfo.otherDeduction,
 ]);
 
  


const handleChange = (setter: React.Dispatch<React.SetStateAction<number>>) => (e: React.ChangeEvent<HTMLInputElement>) => {
  setter(parseFloat(e.target.value) || 0);
};


  // When form is submitted, update deductions in Redux
  const handleSubmit = async (e: { preventDefault: () => void; }) => {
    e.preventDefault();
    
    dispatch(updateMortgageInterest(mortgageInterest));
    dispatch(updateDonations(donations));
    dispatch(updatePropertyTax(propertyTax));
    dispatch(updateMedical(medical));
    dispatch(updateStudentLoanInterest(studentLoanInterest));
    dispatch(updateOtherDeduction(otherDeduction));
   
    
    navigate('/review');
  };



  const bargraph = async (e: { preventDefault: () => void; }) => {
    e.preventDefault();
    
    dispatch(updateMortgageInterest(mortgageInterest));
    dispatch(updateDonations(donations));
    dispatch(updatePropertyTax(propertyTax));
    dispatch(updateMedical(medical));
    dispatch(updateStudentLoanInterest(studentLoanInterest));
    dispatch(updateOtherDeduction(otherDeduction));
   
    
    
  };






  
//bar chart
  const totalItemizedDeductions = currentTaxInfo.mortgageInterest +
                                  currentTaxInfo.donations +
                                  currentTaxInfo.propertyTax +
                                  currentTaxInfo.medical +
                                  currentTaxInfo.studentLoanInterest +
                                  currentTaxInfo.otherDeduction;

                                  const standardDeduction = 24800;
   // Update the chart data whenever tax info updates
   const deductionData = [
    { name: 'Itemized Deductions', value: totalItemizedDeductions },
    { name: 'Standard Deduction', value: standardDeduction }
  ];
  const categories = deductionData.map(d => d.name);
  const values = deductionData.map(d => d.value);

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
                value={mortgageInterest.toString()} onChange={handleChange(setMortgageInterest)} />
              
              <Label htmlFor="donations">Donations</Label>
              <TextInput id="donations" name="donations" type="number"
                value={donations.toString()} onChange={handleChange(setDonations)} />
              
              <Label htmlFor="propertyTax">Property Tax</Label>
              <TextInput id="propertyTax" name="propertyTax" type="number"
                value={propertyTax.toString()} onChange={handleChange(setPropertyTax)} />

              <Label htmlFor="medical">Medical Expenses</Label>
              <TextInput id="medical" name="medical" type="number"
                value={medical.toString()} onChange={handleChange(setMedical)} />

              <Label htmlFor="studentLoanInterest">Student Loan Interest</Label>
              <TextInput id="studentLoanInterest" name="studentLoanInterest" type="number"
                value={studentLoanInterest.toString()} onChange={handleChange(setStudentLoanInterest)} />

              <Label htmlFor="otherDeduction">Other Deductions</Label>
              <TextInput id="otherDeduction" name="otherDeduction" type="number"
                value={otherDeduction.toString()} onChange={handleChange(setOtherDeduction)} />

              <ButtonGroup type="default" className="margin-top-4" style={{
              display: 'flex',
                justifyContent: 'space-between', // Aligns buttons to the opposite ends
              }}>
                <Link to="/income1099" className="usa-button usa-button--outline">Back</Link>
               
              </ButtonGroup>
             
              <Link to="/review" className="usa-button usa-button--base">Skip to Review</Link>
             

  
</Fieldset>
</Grid>

<Grid col={1}></Grid>

<Grid col={6}>
{/* <Form onSubmit={mockSubmit}>*/}
<Button type="button" onClick={bargraph}>Compare Deductions</Button>

<BarChart
      xAxis={[{ scaleType: 'band', data: categories }]}
      series={[{ data: values }]}
      width={500}
      height={300}
    />





<div style={{
    display: 'flex',
    flexDirection: 'column', // Stacks children vertically
    alignItems: 'flex-end', // Aligns children to the right
    marginTop: '20px', // Adds space on the top for the whole block
    marginRight: '5px' // Ensures it doesn't touch the very edge of its container
}}>
<span style={{ color: '#000', fontWeight: 'bold',  fontSize: '45px',  marginBottom: '15px', marginTop: '20px'}}>
                                            We recommend {standardDeduction > totalItemizedDeductions? 'Standard' : 'Itemized'} deductions.
                                            </span> 
                                            
                                            <span style={{ color: '#000', fontWeight: 'bold',  marginBottom: '60px',fontSize: '60px'}}>
                                        ${standardDeduction > totalItemizedDeductions? standardDeduction: totalItemizedDeductions}  </span> </div>


                                        <Button type="button" secondary size="big" onClick={handleSubmit}>I choose Standard Deductions</Button>
                                        <span style={{ color: '#000', fontWeight: 'bold',  marginTop: '80px',marginBottom: '80px',fontSize: '60px'}}>
                                        <Button type="button" secondary size="big" onClick={handleSubmit}>I choose Itemized Deductions</Button>
                                        </span>
                                        <Grid col={12}></Grid>
                                       {/* <Button type="button" onClick={handleSubmit}>Continue</Button>*/}
      </Grid>
    </Grid>
  </GridContainer>






  {/*</Form>*/}
</div>

);
};
export default Deductions;