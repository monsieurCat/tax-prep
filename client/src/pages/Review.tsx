import { Label, TextInput, FormGroup, ErrorMessage, Textarea, Fieldset, Button, Checkbox, Grid, GridContainer, RequiredMarker, Select, DateRangePicker, DatePicker, ButtonGroup, ProcessListHeading, ProcessListItem, StepIndicator, StepIndicatorStep, Radio, TextInputMask } from "@trussworks/react-uswds";
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
      <StepIndicatorStep label="Deductions"  status="complete" />
      <StepIndicatorStep label="Review" status="current" />
      <StepIndicatorStep label="Sign and Submit" />
    </StepIndicator>

    <Grid row gap>


    <Grid col={4}>
        {/*
    <Grid col={4} style={{
    marginLeft: '18rem'}}>
    */}



{/* <Form onSubmit={mockSubmit}>*/}
<Fieldset legend="Confirm your information" legendStyle="large">


<Label htmlFor="w2">1099 Income</Label>
<TextInput id="w2" name="w2" type="text" />

  <Label htmlFor="first-name">Employer name</Label>
  <TextInput id="first-name" name="first-name" type="text" />
  <Label htmlFor="middle-name" hint=" ">
    Middle initial
  </Label>
  <TextInput id="middle-name" name="middle-name" type="text" />
  <Label htmlFor="last-name">Last name</Label>
  <TextInput id="last-name" name="last-name" type="text" />

  <Label id="first-name" htmlFor="first-name">
      Employer Fed ID Number
    </Label>
    <span id="hint-fed-id" className="usa-hint">
      For example, 12 3456789
    </span>
    <TextInputMask id="input-type-ssn" name="input-type-ssn" type="text" aria-labelledby="first-name" aria-describedby="hint-ssn" mask="__ _______" pattern="^(?!(000|666|9))\d{3} (?!00)\d{2} (?!0000)\d{4}$" />
    
</Fieldset>
</Grid>

<Grid col={1}></Grid>

<Grid col={4}>
{/* <Form onSubmit={mockSubmit}>*/}
<Fieldset legend="" legendStyle="large">
  <p>
    Required fields are marked with an asterisk (<RequiredMarker />
    ).
  </p>
  <Label htmlFor="mailing-address-1">Street address</Label>
  <TextInput id="mailing-address-1" name="mailing-address-1" type="text" />

  <Label htmlFor="mailing-address-2">Street address line 2</Label>
  <TextInput id="mailing-address-2" name="mailing-address-2" type="text" />

  <Label htmlFor="city" requiredMarker>
    City
  </Label>
  <TextInput id="city" name="city" type="text" required />

  <Label htmlFor="state" requiredMarker>
    State, territory, or military post
  </Label>
  <Select id="state" name="state" required>
    <option>- Select -</option>
   
  
  </Select>

  <Label id="zip" htmlFor="first-name">
      ZIP Code
    </Label>
    <span id="hint-zip" className="usa-hint">
      For example, 12345-6789
    </span>
    <TextInputMask id="input-type-zip" name="input-type-zip" type="text" aria-labelledby="zip" aria-describedby="hint-zip" mask="_____-____" pattern="^[0-9]{5}(?:-[0-9]{4})?$" />


</Fieldset>






 







      {/* <Form onSubmit={mockSubmit}>*/}


      <ButtonGroup type="default">

        <Link to="/deductions" className="usa-button usa-button--outline">Back </Link>
        <Link to="/breakdown" className="usa-button">Done </Link>

      </ButtonGroup>
      </Grid>
    </Grid>
  </GridContainer>






  {/*</Form>*/}
</div>

);
export default Review;