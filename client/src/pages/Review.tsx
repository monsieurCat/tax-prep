import { Label, TextInput, Form, FormGroup, ErrorMessage, Textarea, Alert, Fieldset, Button, Checkbox, Grid, GridContainer, RequiredMarker, Select, DateRangePicker, DatePicker, ButtonGroup, ProcessListHeading, ProcessListItem, StepIndicator, StepIndicatorStep, Radio, TextInputMask, Accordion, Table } from "@trussworks/react-uswds";
import { Link, useNavigate } from "react-router-dom";
import React from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { submitFullTaxInfo } from '../redux/slices/taxSlice';
import { AppDispatch } from "../redux/store";
import { RootState } from "../redux/storeTypes";

export const Review = (): React.ReactElement => {

  const dispatch = useDispatch<AppDispatch>();
  const navigate = useNavigate();
  const taxInfo = useSelector((state: RootState) => state.taxInfo);
 

  const handleSubmit = () => {
    dispatch(submitFullTaxInfo(taxInfo));
    navigate('/breakdown');
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
        label="Personal Information"
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
      <StepIndicatorStep label="Deductions"  status="complete" />
      <StepIndicatorStep label="Review" status="current" />
      <StepIndicatorStep label="Sign and Submit" />
    </StepIndicator>

    <Grid row gap>


    <Grid col={8}style={{
    marginLeft: '14rem'}}>
        {/*
    <Grid col={4} style={{
    marginLeft: '18rem'}}>
    */}



{/* <Form onSubmit={mockSubmit}>*/}
<Fieldset legend="Confirm your information" legendStyle="large">
<h1>Review Your Information</h1>

<Alert type="info" headingLevel={"h1"}>Please review your information carefully before submitting.</Alert>




<Grid row gap>
        <Grid col={12}>
          <h2>Personal Information</h2>
          <ButtonGroup type="default" >
                <Link to="/personal-form" className="usa-button usa-button--outline">Edit</Link>
                
              </ButtonGroup>
          <p><strong>First Name:</strong> {taxInfo.personalInfo.firstName}</p>
          <p><strong>Last Name:</strong> {taxInfo.personalInfo.lastName}</p>
          <p><strong>SSN:</strong> {taxInfo.personalInfo.ssn}</p>
        </Grid>

        <Grid col={12}>
          <h2>W2 Income</h2>
          <ButtonGroup type="default" >
                <Link to="/w2" className="usa-button usa-button--outline">Edit</Link>
                
              </ButtonGroup>
          {taxInfo.w2Income.map((w2, index) => (
            <div key={index}>
              <h3>W2 Form {index + 1}</h3>
              <p>Income: {w2.income}</p>
              <p>Withholdings: {w2.withholdings}</p>
              <p>Employer EIN: {w2.employerEin}</p>
            </div>
          ))}
        </Grid>

        <Grid col={12}>
          <h2>1099 Income</h2>
          <ButtonGroup type="default" >
                <Link to="/income1099" className="usa-button usa-button--outline">Edit</Link>
                
              </ButtonGroup>
          {taxInfo.income1099.map((income, index) => (
            <div key={index}>
              <h3>1099 Form {index + 1}</h3>
              <p>Income: {income.income}</p>
              <p>Withholdings: {income.withholdings}</p>
            </div>
          ))}
        </Grid>

        <Grid col={12}>
          <h2>Deductions</h2> 
          <ButtonGroup type="default" >
                <Link to="/deductions" className="usa-button usa-button--outline">Edit</Link>
                
              </ButtonGroup>
          <p>Mortgage Interest: {taxInfo.deductions.mortgageInterest}</p>
          <p>Donations: {taxInfo.deductions.donations}</p>
          <p>Property Tax: {taxInfo.deductions.propertyTax}</p>
          <p>Medical Expenses: {taxInfo.deductions.medical}</p>
          <p>Student Loan Interest: {taxInfo.deductions.studentLoanInterest}</p>
          <p>Other Deductions: {taxInfo.deductions.otherDeduction}</p>
          <p>Other Income: {taxInfo.deductions.otherIncome}</p>
        </Grid>

        <Grid col={12}>
          <Button type="button" onClick={handleSubmit}>Submit All Information</Button>
          <ButtonGroup type="default" className="margin-top-4">
                <Link to="/deductions" className="usa-button usa-button--outline">Back</Link>
                
              </ButtonGroup>
        </Grid>
        
      </Grid>
      </Fieldset>
      </Grid>
      
      </Grid>
      
    </GridContainer>
  



{/*
<Accordion  bordered={false}  items={
                                [
                                    {
                                        title: <span style={{ color: '#000', fontWeight: 'bold' }}>Personal Information</span>,
                                        content: (
                                            <p style={{ color: '#000' }}>
                                               
          <Label htmlFor="first-name">Name</Label>
          <TextInput id="input-disabled" name="first-name" type="text"  defaultValue="Alice Smith" disabled/>
          

          <Label htmlFor="birthdate">Date of birth</Label>
          <DatePicker id="birthdate" name="birthdate" disabled/>

          <Label id="first-name" htmlFor="first-name">
      Social Security Number
    </Label>
   
    <TextInputMask id="input-type-ssn" name="input-type-ssn" type="text" aria-labelledby="first-name" aria-describedby="hint-ssn" mask="___ __ ____" pattern="^(?!(000|666|9))\d{3} (?!00)\d{2} (?!0000)\d{4}$"  disabled />

                                            </p>
                                        ),
                                        expanded: false,
                                        id: '123',
                                        headingLevel: 'h4',
                                    }]
                            } />
                            <Accordion  bordered={false}  items={
                                [
                                    {
                                        title: <span style={{ color: '#000', fontWeight: 'bold' }}>Filing Status</span>,
                                        content: (
                                            <p style={{ color: '#000' }}>
                                               
                                            </p>
                                        ),
                                        expanded: false,
                                        id: '123',
                                        headingLevel: 'h4',
                                    }]
                            } />
                            <Accordion  bordered={false}  items={
                                [
                                    {
                                        title: <span style={{ color: '#000', fontWeight: 'bold' }}>Income</span>,
                                        content: (
                                            <p style={{ color: '#000' }}>
                                               
                                            </p>
                                        ),
                                        expanded: false,
                                        id: '123',
                                        headingLevel: 'h4',
                                    }]
                            } />
                            <Accordion  bordered={false}  items={
                                [
                                    {
                                        title: <span style={{ color: '#000', fontWeight: 'bold' }}>Deductions</span>,
                                        content: (
                                            <p style={{ color: '#000' }}>
                                               
                                            </p>
                                        ),
                                        expanded: false,
                                        id: '123',
                                        headingLevel: 'h4',
                                    }]
                            } />
</Fieldset>
</Grid>

<Grid col={4}></Grid>


      <ButtonGroup type="default">

        <Link to="/deductions" className="usa-button usa-button--outline">Back </Link>
        <Link to="/breakdown" className="usa-button">Done </Link>

      </ButtonGroup>
      
    </Grid>
  </GridContainer>

                          */}




  {/*</Form>*/}
</div>

);
};
export default Review;