import { Label, TextInput, FormGroup, ErrorMessage, Textarea, Fieldset, Button, Checkbox, Grid, GridContainer, RequiredMarker, Select, DateRangePicker, DatePicker, ButtonGroup, ProcessListHeading, ProcessListItem, StepIndicator, StepIndicatorStep, Radio } from "@trussworks/react-uswds";
import { Form, Link } from "react-router-dom";

export const FilingStatus = (): React.ReactElement => (<div style={{
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
        status="current"

      />
         <StepIndicatorStep
        label="W2 Income"
      />
      <StepIndicatorStep
        label="1099 Income"
      />
      <StepIndicatorStep label="Deductions" />
      <StepIndicatorStep label="Review"  />
      <StepIndicatorStep label="Sign and Submit" />
    </StepIndicator>

    <Grid row gap>


      {/* <Form onSubmit={mockSubmit}>*/}
      <Fieldset legend="Filing Status" legendStyle="large">


        <Radio id="input-radio" name="input-radio" label="Single" />
        <Radio id="input-radio" name="input-radio" label="Married filing jointly" />
        <Radio id="input-radio" name="input-radio" label="Married filing seperately" />
        <Radio id="input-radio" name="input-radio" label="Head of Household" />

      </Fieldset>

      {/* <Form onSubmit={mockSubmit}>*/}


      <ButtonGroup type="default">

        <Link to="/personal-form" className="usa-button usa-button--outline">Back </Link>
        <Link to="/w2" className="usa-button">Continue </Link>

      </ButtonGroup>

    </Grid>
  </GridContainer>



  {/*</Form>*/}
</div>

);
export default FilingStatus;