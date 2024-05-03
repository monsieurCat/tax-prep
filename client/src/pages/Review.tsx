import { Label, TextInput, FormGroup, ErrorMessage, Textarea, Fieldset, Button, Checkbox, Grid, GridContainer, RequiredMarker, Select, DateRangePicker, DatePicker, ButtonGroup, ProcessListHeading, ProcessListItem, StepIndicator, StepIndicatorStep, Radio, TextInputMask, Accordion, Table } from "@trussworks/react-uswds";
import { Form, Link } from "react-router-dom";

export const Review = (): React.ReactElement => (<div style={{
    marginLeft: '2rem'
  }}>

    
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


{/* <Form onSubmit={mockSubmit}>*/}







 







      {/* <Form onSubmit={mockSubmit}>*/}


      <ButtonGroup type="default">

        <Link to="/deductions" className="usa-button usa-button--outline">Back </Link>
        <Link to="/breakdown" className="usa-button">Done </Link>

      </ButtonGroup>
      
    </Grid>
  </GridContainer>






  {/*</Form>*/}
</div>

);
export default Review;