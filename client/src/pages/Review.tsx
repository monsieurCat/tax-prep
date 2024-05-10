import { Label, TextInput, Form, FormGroup, ErrorMessage, Textarea, Alert, Fieldset, Button, Checkbox, Grid, GridContainer, RequiredMarker, Select, DateRangePicker, DatePicker, ButtonGroup, ProcessListHeading, ProcessListItem, StepIndicator, StepIndicatorStep, Radio, TextInputMask, Accordion, Table } from "@trussworks/react-uswds";
import { Link, useNavigate } from "react-router-dom";
import React, { useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { fetchTaxInfo, submitFullTaxInfo } from '../redux/slices/taxSlice';
import { AppDispatch } from "../redux/store";
import { RootState } from "../redux/storeTypes";
import { fetchAddress, fetchUserInfo } from "../redux/slices/userSlice";

export const Review = (): React.ReactElement => {

  const dispatch = useDispatch<AppDispatch>();
  const navigate = useNavigate();
  const taxInfo = useSelector((state: RootState) => state.taxInfo);
  const { personalInfo, address } = useSelector((state: RootState) => state.user);
  
  useEffect(() => {
    dispatch(fetchTaxInfo());
    dispatch(fetchUserInfo());
    dispatch(fetchAddress());
  }, [dispatch]);

  

  const handleSubmit = async (e: { preventDefault: () => void; }) => {
    e.preventDefault();
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
    marginLeft: 'rem'}}>
        {/*
    <Grid col={4} style={{
    marginLeft: '18rem'}}>
    */}

</Grid></Grid></GridContainer>


<GridContainer>
  <Grid row >
    
    <Grid col={11}>

{/* <Form onSubmit={mockSubmit}>*/}
<Fieldset legend="Confirm your information" legendStyle="large">
<Alert type="info" headingLevel={"h1"}>Please review your information carefully before submitting.</Alert>
<ButtonGroup type="default">          




<Grid col = {4}></Grid>

<Grid col = {2}    style={{
                                        marginLeft: '40rem'
                                    }}> 
                                        <Button type="button" onClick={handleSubmit}>Confirm</Button>


                                        </Grid>
</ButtonGroup> 





<Grid row >
        <Grid col={6}>

         
        
                               
                                <span style={{ color: "#003366", fontWeight: 'bold' ,  fontSize: '35px', marginTop: "50px"}} >Personal Information</span>
          <ButtonGroup type="default" >
                <Link to="/personal-form" className="usa-button usa-button--outline">Edit</Link>
                
              </ButtonGroup>
              <p><strong style={{ color: '#000', fontWeight: 'bold' ,  fontSize: '20px'}}>First Name: </strong> <span style={{ color: '#000', fontSize: '20px'}} >{personalInfo.firstName}</span></p>
          <p><strong style={{ color: '#000', fontWeight: 'bold' ,  fontSize: '20px'}}>Last Name: </strong> <span style={{ color: '#000',  fontSize: '20px'}} >{personalInfo.lastName}</span></p>
          <p><strong style={{ color: '#000', fontWeight: 'bold' ,  fontSize: '20px'}}>SSN: </strong> <span style={{ color: '#000',  fontSize: '20px'}} >{personalInfo.ssn}</span></p>
          <p><strong style={{ color: '#000', fontWeight: 'bold' ,  fontSize: '20px'}}>Email: </strong> <span style={{ color: '#000', fontSize: '20px'}} >{personalInfo.email}</span></p>
          
          <p><strong style={{ color: '#000', fontWeight: 'bold' ,  fontSize: '20px'}}>Birthday: </strong> <span style={{ color: '#000',   fontSize: '20px'}} >{personalInfo.birthday}</span></p>
       
          <p><strong style={{ color: '#000', fontWeight: 'bold' ,  fontSize: '20px'}}>Street 1: </strong> <span style={{ color: '#000',  fontSize: '20px'}} >{address.street1}</span></p>
          <p><strong style={{ color: '#000', fontWeight: 'bold' ,  fontSize: '20px'}}>Street 2: </strong> <span style={{ color: '#000',   fontSize: '20px'}} >{address.street2}</span></p>
          <p><strong style={{ color: '#000', fontWeight: 'bold' ,  fontSize: '20px'}}>City: </strong><span style={{ color: '#000',  fontSize: '20px'}} >{address.city}</span></p>
          <p><strong style={{ color: '#000', fontWeight: 'bold' ,  fontSize: '20px'}}>State: </strong> <span style={{ color: '#000',   fontSize: '20px'}} >{address.state}</span></p>
          <p><strong style={{ color: '#000', fontWeight: 'bold' ,  fontSize: '20px'}}>Zip Code: </strong> <span style={{ color: '#000',   fontSize: '20px'}} >{address.postalCode}</span></p>




       

        
          <h2  style={{ color: "#003366", fontWeight: 'bold' ,  fontSize: '35px'}} >Filing Status</h2>
          <ButtonGroup type="default" >
                <Link to="/filing-status" className="usa-button usa-button--outline">Edit</Link>
                
              </ButtonGroup>
              <p><strong style={{ fontSize: '20px'}}>Status: </strong>   <span style={{ color: '#000', fontSize: '20px'}} >{taxInfo.filingStatus.status}</span> </p>
                  <p style={{ color: '#000', fontWeight: 'bold' ,  fontSize: '20px'}}><strong>Dependents:</strong> {taxInfo.numDependents}</p>
          

        
          <h2  style={{ color: "#003366", fontWeight: 'bold' ,  fontSize: '35px'}}>W2 Income</h2>
          <ButtonGroup type="default" >
                <Link to="/w2" className="usa-button usa-button--outline">Edit</Link>
                
              </ButtonGroup>
              {taxInfo.incomeW2.map((w2, index) => (
                    <div key={index}>
                      <h3 style={{ color: '#000', fontWeight: 'bold' , fontSize: '25px'}}>W2 Form {index + 1}</h3>
                      <p style={{ color: '#000', fontSize: '20px'}}>Income: {w2.income}</p>
                      <p style={{ color: '#000', fontSize: '20px'}}>Withholdings: {w2.withholdings}</p>
                      <p style={{ color: '#000', fontSize: '20px'}}>Employer EIN: {w2.employerEin}</p>
                      <p style={{ color: '#000', fontSize: '20px'}}>Employer Address: {`${w2.employerStreet1}, ${w2.employerStreet2 ? w2.employerStreet2 + ', ' : ''}${w2.employerCity}, ${w2.employerState} ${w2.employerZipcode}`}</p>
            </div>
          ))}
         </Grid>

        <Grid col={6}>
          <h2  style={{ color: "#003366", fontWeight: 'bold' ,  fontSize: '35px'}}>1099 Income</h2>
          <ButtonGroup type="default" >
                <Link to="/income1099" className="usa-button usa-button--outline">Edit</Link>
                
              </ButtonGroup>
          {taxInfo.income1099.map((income, index) => (
            <div key={index}>
              <h3 style={{ color: '#000', fontWeight: 'bold' , fontSize: '25px'}}>1099 Form {index + 1}</h3>
              <p style={{ color: '#000', fontSize: '20px'}}>Income: {income.income}</p>
              <p style={{ color: '#000', fontSize: '20px'}}>Withholdings: {income.withholdings}</p>
              <p style={{ color: '#000', fontSize: '20px'}}>Employer EIN: {income.employerEin}</p>
              <p style={{ color: '#000', fontSize: '20px'}}>Employer Address: {`${income.employerStreet1}, ${income.employerStreet2 ? income.employerStreet2 + ', ' : ''}${income.employerCity}, ${income.employerState} ${income.employerZipcode}`}</p>
            </div>
          ))}
     

        
          <h2  style={{ color: "#003366", fontWeight: 'bold' ,  fontSize: '35px'}}>Deductions</h2> 
          <ButtonGroup type="default" >
                <Link to="/deductions" className="usa-button usa-button--outline">Edit</Link>
                
              </ButtonGroup>
              <p style={{ color: '#000', fontSize: '20px'}}>Mortgage Interest: {taxInfo.mortgageInterest}</p>
                  <p style={{ color: '#000', fontSize: '20px'}}>Donations: {taxInfo.donations}</p>
                  <p style={{ color: '#000', fontSize: '20px'}}>Property Tax: {taxInfo.propertyTax}</p>
                  <p style={{ color: '#000', fontSize: '20px'}}>Medical Expenses: {taxInfo.medical}</p>
                  <p style={{ color: '#000', fontSize: '20px'}}>Student Loan Interest: {taxInfo.studentLoanInterest}</p>
                  <p style={{ color: '#000', fontSize: '20px'}}>Other Deductions: {taxInfo.otherDeduction}</p>
                  <p style={{ color: '#000', fontSize: '20px'}}>Other Income: {taxInfo.otherIncome}</p>
                  <p style={{ color: '#000', fontSize: '20px'}}>Standard Deductions: {"28000"}</p>
        

    
         
          
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