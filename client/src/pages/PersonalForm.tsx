import { Label, TextInput, FormGroup, ErrorMessage, Textarea, Fieldset, Form, Button, Checkbox, Grid, GridContainer, RequiredMarker, Select, DateRangePicker, DatePicker, ButtonGroup, ProcessListHeading, ProcessListItem, StepIndicator, StepIndicatorStep, TextInputMask } from "@trussworks/react-uswds";
import { Link, useNavigate } from "react-router-dom";
import { useSelector, useDispatch } from 'react-redux';
import { PersonalInfo, updatePersonalInfo, fetchTaxInfo, updateTaxAddress, updateUserInformation, updateAddress} from '../redux/slices/taxSlice';
import React, { useEffect } from 'react';
import { RootState } from '../redux/storeTypes';
import { AppDispatch } from '../redux/store';

const PersonalForm = (): React.ReactElement => {


  const dispatch: AppDispatch = useDispatch(); 
const navigate = useNavigate(); 
const { personalInfo, address, loading, error } = useSelector((state: RootState) => state.taxInfo);

/*
useEffect(() => {
  // Consider dispatching fetchTaxInfo() if necessary here to load initial form data
  dispatch(fetchTaxInfo());
}, [dispatch]);

const handleFormSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
  e.preventDefault();
  dispatch(updateTaxAddress({ addressData: address }));
  // You may want to navigate on successful update
  navigate('/next-page-route');
};

*/

const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
  const { name, value } = e.target;
  if (name.startsWith("address-")) {
    // This removes the 'address-' prefix and uses the rest as the key for the address state
    dispatch(updateAddress({ [name.replace("address-", "")]: value }));
  } else {
    // Handles personal info fields without any prefix
    dispatch(updatePersonalInfo({ ...personalInfo, [name]: value }));
  }
};
/*
    const handleChange = (e: { target: { name: any; value: any; }; }) => {
        const { name, value } = e.target;
        // Update the Redux state
        dispatch(updatePersonalInfo({ ...personalInfo, [name]: value }));
    };
*/

/*
    const handleForm = (event: React.FormEvent) => {
      event.preventDefault(); // Prevent the default form submission behavior
      dispatch(updatePersonalInfo(personalInfo));
  };
  */


  const handleForm = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    try {
        await dispatch(updateUserInformation(personalInfo));
        await dispatch(updateTaxAddress({ addressData: address }));
        navigate('/filing-status'); 
    } catch (error) {
        console.error('Update failed:', error);
    }
};




    return (<>

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
        status="current"
      />
      <StepIndicatorStep
        label="Filing Status"

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


      <Grid col={4}>


      <Form onSubmit={handleForm}>
   
        <Fieldset legend="Personal Info" legendStyle="large">


          <Label htmlFor="first-name">First name</Label>
          <TextInput id="first-name" name="firstName" type="text" onChange={handleChange} value={personalInfo.firstName} />
          <Label htmlFor="middle-name" hint=" ">
            Middle initial
          </Label>
          <TextInput id="middle-name" name="middleName" type="text" onChange={handleChange} value={personalInfo.middleName}/>
          <Label htmlFor="last-name">Last name</Label>
          <TextInput id="last-name" name="lastName" type="text" onChange={handleChange} value={personalInfo.lastName} />

          <Label htmlFor="birthdate">Date of birth</Label>
          <DatePicker id="birthdate" name="birthdate"  value={personalInfo.birthdate} onChange={(e: any) => dispatch(updatePersonalInfo({ ...personalInfo, birthdate: e.target.value }))} />

          <Label id="first-name" htmlFor="first-name">
      Social Security Number
    </Label>
    <span id="hint-ssn" className="usa-hint">
      For example, 123 45 6789
    </span>
    <TextInputMask id="input-type-ssn" name="ssn" type="text" aria-labelledby="first-name" aria-describedby="hint-ssn" mask="___ __ ____" pattern="^(?!(000|666|9))\d{3} (?!00)\d{2} (?!0000)\d{4}$" onChange={handleChange}  value={personalInfo.ssn}/>

        </Fieldset>
        </Form>
      </Grid>

      <Grid col={1}></Grid>

      <Grid col={4}>
        <Form onSubmit={handleForm}>
        <Fieldset legend="" legendStyle="large">
          <p>
            Required fields are marked with an asterisk (<RequiredMarker />
            ).
          </p>
          <Label htmlFor="mailing-address-1">Street address</Label>
          <TextInput id="mailing-address-1" name="address-street1" type="text" onChange={handleChange} value={address.street1} />

          <Label htmlFor="mailing-address-2">Street address line 2</Label>
          <TextInput id="mailing-address-2" name="address-street2" type="text"onChange={handleChange} value={address.street2}/>

          <Label htmlFor="city" requiredMarker>
            City
          </Label>
          <TextInput id="city" name="address-city" type="text" required onChange={handleChange} value={address.city}/>

          <Label htmlFor="state" requiredMarker>
            State, territory, or military post
          </Label>
          <Select id="state" name="address-state" required onChange={handleChange} value={address.state}>
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

          <Label id="zip" htmlFor="first-name">
      ZIP Code
    </Label>
    <span id="hint-zip" className="usa-hint">
      For example, 12345-6789
    </span>
    <TextInputMask id="input-type-zip" name="address-zip" type="text" aria-labelledby="zip" aria-describedby="hint-zip" mask="_____-____" pattern="^[0-9]{5}(?:-[0-9]{4})?$" onChange={handleChange} value={address.zip}/>


        </Fieldset>
        

        <ButtonGroup type="default">

          <Link to="/" className="usa-button usa-button--outline">Back </Link>

        </ButtonGroup>
        <Button type="submit" >Continue</Button>
        </Form>
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
            
            </div>
          </Grid>

        </Grid>
      </GridContainer>
    </div>
                </main>


</div>
 </>
    );

                
              };

export default PersonalForm;