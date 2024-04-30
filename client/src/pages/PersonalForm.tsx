import { Label, TextInput, FormGroup, ErrorMessage, Textarea, Fieldset, Button, Checkbox, Grid, GridContainer, RequiredMarker, Select, DateRangePicker, DatePicker, ButtonGroup, ProcessListHeading, ProcessListItem, StepIndicator, StepIndicatorStep } from "@trussworks/react-uswds";
import { Form, Link } from "react-router-dom";

export const PersonalForm = (): React.ReactElement => (<div style={{
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
    status="current"
  />
  <StepIndicatorStep
    label="Filing Status"
    
  />
  <StepIndicatorStep
    label="W2 and 1099"
    
  />
  <StepIndicatorStep label="Deductions and Taxable Income" />
  <StepIndicatorStep label="Review and Submit" />
</StepIndicator>

    <Grid row gap>


    <Grid col={4}>



 





       {/* <Form onSubmit={mockSubmit}>*/}
     <Fieldset legend="Personal Info" legendStyle="large">
    
   
    <Label htmlFor="first-name">First name</Label>
    <TextInput id="first-name" name="first-name" type="text" />
    <Label htmlFor="middle-name" hint=" ">
      Middle initial
    </Label>
    <TextInput id="middle-name" name="middle-name" type="text" />
    <Label htmlFor="last-name">Last name</Label>
    <TextInput id="last-name" name="last-name" type="text" />

    <Label htmlFor="brithdate">Date of birth</Label>
    <DatePicker id="birthdate" name="birthdate" />
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
        <option value="AL">Alabama</option>
        <option value="AK">Alaska</option>
        <option value="AZ">Arizona</option>
        <option value="AR">Arkansas</option>
        <option value="CA">California</option>
        <option value="CO">Colorado</option>
        <option value="CT">Connecticut</option>
        <option value="DE">Delaware</option>
        <option value="DC">District of Columbia</option>
        <option value="FL">Florida</option>
        <option value="GA">Georgia</option>
        <option value="HI">Hawaii</option>
        <option value="ID">Idaho</option>
        <option value="IL">Illinois</option>
        <option value="IN">Indiana</option>
        <option value="IA">Iowa</option>
        <option value="KS">Kansas</option>
        <option value="KY">Kentucky</option>
        <option value="LA">Louisiana</option>
        <option value="ME">Maine</option>
        <option value="MD">Maryland</option>
        <option value="MA">Massachusetts</option>
        <option value="MI">Michigan</option>
        <option value="MN">Minnesota</option>
        <option value="MS">Mississippi</option>
        <option value="MO">Missouri</option>
        <option value="MT">Montana</option>
        <option value="NE">Nebraska</option>
        <option value="NV">Nevada</option>
        <option value="NH">New Hampshire</option>
        <option value="NJ">New Jersey</option>
        <option value="NM">New Mexico</option>
        <option value="NY">New York</option>
        <option value="NC">North Carolina</option>
        <option value="ND">North Dakota</option>
        <option value="OH">Ohio</option>
        <option value="OK">Oklahoma</option>
        <option value="OR">Oregon</option>
        <option value="PA">Pennsylvania</option>
        <option value="RI">Rhode Island</option>
        <option value="SC">South Carolina</option>
        <option value="SD">South Dakota</option>
        <option value="TN">Tennessee</option>
        <option value="TX">Texas</option>
        <option value="UT">Utah</option>
        <option value="VT">Vermont</option>
        <option value="VA">Virginia</option>
        <option value="WA">Washington</option>
        <option value="WV">West Virginia</option>
        <option value="WI">Wisconsin</option>
        <option value="WY">Wyoming</option>
        <option value="AA">AA - Armed Forces Americas</option>
        <option value="AE">AE - Armed Forces Africa</option>
        <option value="AE">AE - Armed Forces Canada</option>
        <option value="AE">AE - Armed Forces Europe</option>
        <option value="AE">AE - Armed Forces Middle East</option>
        <option value="AP">AP - Armed Forces Pacific</option>
      </Select>

      <Label htmlFor="zip">ZIP Code</Label>
      <TextInput id="zip" name="zip" type="text" inputSize="medium" pattern="[\d]{5}(-[\d]{4})?" />

      
    </Fieldset>

    <ButtonGroup type="default">
  
  <Link to="/" className="usa-button usa-button--outline">Back </Link>
  <Link to="/" className="usa-button">Continue </Link>
  
</ButtonGroup>
  </Grid>
  </Grid>
  </GridContainer>





  <main id="main-content">
          <div >
            <GridContainer className="usa-section">
              <Grid row className="margin-x-neg-205 flex-justify-center">
                <Grid col={12} mobileLg={{
                col: 10
              }} tablet={{
                col: 8
              }} desktop={{
                col: 6
              }} className="padding-x-205 margin-bottom-4">
                  
                  <div className="bg-white padding-y-8 padding-x-5 border border-base-lighter">
                    <h1 className="margin-bottom-0">Create account</h1>
                  { /* <Form onSubmit={mockSubmit}>*/}
                  <Fieldset legend="Mailing address" legendStyle="large">
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
        <option value="AL">Alabama</option>
        <option value="AK">Alaska</option>
       
        
      </Select>

      <Label htmlFor="zip">ZIP Code</Label>
      <TextInput id="zip" name="zip" type="text" inputSize="medium" pattern="[\d]{5}(-[\d]{4})?" />

      <Label htmlFor="urbanization">Urbanization (Puerto Rico only)</Label>
      <TextInput id="urbanization" name="urbanization" type="text" />
    </Fieldset>
                    {/*</Form>*/}
                  </div>
  
                 
                </Grid>
  
           
              </Grid>
            </GridContainer>
          </div>
        </main>

      {/*</Form>*/}
    </div>

);
