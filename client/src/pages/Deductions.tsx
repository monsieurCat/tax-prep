import { Label, TextInput, FormGroup, ErrorMessage, Textarea, Fieldset, Button, Checkbox, Grid, GridContainer, RequiredMarker, Select, DateRangePicker, DatePicker, ButtonGroup, ProcessListHeading, ProcessListItem, StepIndicator, StepIndicatorStep, Radio, TextInputMask } from "@trussworks/react-uswds";
import { Form, Link } from "react-router-dom";

export const Deductions = (): React.ReactElement => (<div style={{
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


<Label htmlFor="w2">Mortgage Interests</Label>
<TextInput id="w2" name="w2" type="text" />

  <Label htmlFor="first-name">Donations</Label>
  <TextInput id="first-name" name="first-name" type="text" />
  <Label htmlFor="middle-name" hint=" ">
    Property Tax
  </Label>
  <TextInput id="middle-name" name="middle-name" type="text" />
  <Label htmlFor="last-name">Student Loan Interest</Label>
  <TextInput id="last-name" name="last-name" type="text" />

  <Label htmlFor="last-name">Medical</Label>
  <TextInput id="last-name" name="last-name" type="text" />

  <Label htmlFor="last-name">Other</Label>
  <TextInput id="last-name" name="last-name" type="text" />

  
</Fieldset>
</Grid>

<Grid col={1}></Grid>

<Grid col={4}>
{/* <Form onSubmit={mockSubmit}>*/}






 







      {/* <Form onSubmit={mockSubmit}>*/}


      <ButtonGroup type="default">

        <Link to="/income1099" className="usa-button usa-button--outline">Back </Link>
        <Link to="/review" className="usa-button">Continue </Link>

      </ButtonGroup>
      </Grid>
    </Grid>
  </GridContainer>






  {/*</Form>*/}
</div>

);
export default Deductions;