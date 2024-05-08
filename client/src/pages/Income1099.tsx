import { Label, TextInput, FormGroup, Form, ErrorMessage, Textarea, Fieldset, Button, Checkbox, Grid, GridContainer, RequiredMarker, Select, DateRangePicker, DatePicker, ButtonGroup, ProcessListHeading, ProcessListItem, StepIndicator, StepIndicatorStep, Radio, TextInputMask } from "@trussworks/react-uswds";
import { Link, useNavigate } from "react-router-dom";
import React, { ChangeEvent, useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { addIncome1099, deleteIncome1099, updateIncome1099 } from '../redux/slices/taxSlice';
import { RootState } from "../redux/storeTypes";

export const Income1099 = (): React.ReactElement => {

  const dispatch = useDispatch();
  const navigate = useNavigate();
  const currentTaxInfo = useSelector((state: RootState) => state.taxInfo);
  

  const [forms, setForms] = useState(currentTaxInfo.income1099 || [{
    income: 0, withholdings: 0, employerEin: '', employerStreet1: '', employerStreet2: '', employerCity: '', employerState: '', employerZipcode: ''}]);

    useEffect(() => {
      if (currentTaxInfo.income1099) {
        setForms(currentTaxInfo.income1099);
      }
    }, [currentTaxInfo.income1099]);
  

  const handleInputChange = (index: number, field: string, event: ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const value = event.target.value;
    const newForms = [...forms];
    newForms[index] = {
      ...newForms[index],
      [field]: (field === 'income' || field === 'withholdings') ? parseFloat(value) : value
    };
    setForms(newForms);
  };

  const handleAddForm = () => {
    setForms([...forms, { income: 0, withholdings: 0, employerEin: '', employerStreet1: '', employerStreet2: '', employerCity: '',  employerState: '', employerZipcode: '' }]);
  };

  const handleDeleteForm = (index: number) => {
    const newForms = [...forms];
    newForms.splice(index, 1);
    setForms(newForms);
    // Optionally dispatch a delete action to update the backend
    dispatch(deleteIncome1099(index));
  };

  const handleContinue = () => {

    dispatch(updateIncome1099({ forms: forms }));
    navigate('/deductions'); // Navigate to the next form
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
        status="current"
        

      />
      <StepIndicatorStep label="Deductions" />
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
<Fieldset legend="Other Income" legendStyle="large">

{forms.map((form, index) => (
                  <div key={index}>
                    <Label htmlFor={`income-${index}`}>Other Income</Label>
                    <TextInput id={`income-${index}`} name="income" type="number" value={form.income.toString()} onChange={e => handleInputChange(index, 'income', e)} />

                    <Label htmlFor={`withholdings-${index}`}>Federal Tax Withheld</Label>
                    <TextInput id={`withholdings-${index}`} name="withholdings" type="number" value={form.withholdings.toString()} onChange={e => handleInputChange(index, 'withholdings', e)} />

                    <Label htmlFor={`employerEin-${index}`}>Employer EIN</Label>
                    <TextInput id={`employerEin-${index}`} name="employerEin" type="text" value={form.employerEin} onChange={e => handleInputChange(index, 'employerEin', e)} />
                  </div>
                ))}

              </Fieldset>
            </Grid>

            <Grid col={1}></Grid>

            <Grid col={4}>

              <Fieldset legend="" legendStyle="large">
                <p>
                  Required fields are marked with an asterisk (<RequiredMarker />
                  ).
                </p>
                {forms.map((form, index) => (
                  <div key={index}>
                    <Label htmlFor={`employerStreet1-${index}`}>Street Address</Label>
                    <TextInput id={`employerStreet1-${index}`} name="employerStreet1" type="text" value={form.employerStreet1} onChange={e => handleInputChange(index, 'employerStreet1', e)} />

                    <Label htmlFor={`employerStreet2-${index}`}>Street Address Line 2</Label>
                    <TextInput id={`employerStreet2-${index}`} name="employerStreet2" type="text" value={form.employerStreet2} onChange={e => handleInputChange(index, 'employerStreet2', e)} />

                    <Label htmlFor={`employerCity-${index}`}>City</Label>
                    <TextInput id={`employerCity-${index}`} name="employerCity" type="text" value={form.employerCity} onChange={e => handleInputChange(index, 'employerCity', e)} />

                    <Label htmlFor={`employerState-${index}`}>State</Label>
                    <Select id={`employerState-${index}`} name="employerState" value={form.employerState} onChange={e => handleInputChange(index, 'employerState', e)}>
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
                    </Select>

                    <Label htmlFor={`employerZipcode-${index}`}>ZIP Code</Label>
                    <TextInput id={`employerZipcode-${index}`} name="employerZipcode" type="text" value={form.employerZipcode} onChange={e => handleInputChange(index, 'employerZipcode', e)} />


                  </div>
                ))}

<Button type="button" onClick={() => handleDeleteForm}>Delete Form</Button>
              </Fieldset>
              <Button type="button" onClick={handleAddForm}>Add Income Form</Button>

              <ButtonGroup type="default">

                <Link to="/filing-status" className="usa-button usa-button--outline">Back </Link>
                <Button type="button" onClick={handleContinue}>Continue</Button>
                <Link to="/review" className="usa-button usa-button--outline">Go to Review</Link>
              </ButtonGroup>
            </Grid>
          </Grid>
        </GridContainer>


        {/*
<Label htmlFor="w2">1099 Income</Label>
<TextInput id="w2" name="w2" type="text" />

<Label htmlFor="w2">Federal Tax Withheld</Label>
<TextInput id="w2" name="w2" type="text" />

<Label htmlFor="w2">Other Income</Label>
<TextInput id="w2" name="w2" type="text" />


<Label htmlFor="first-name">Employer name</Label>
  <span id="hint-fed-id" className="usa-hint">
    You'll find this in Box c on your 1099.
    </span>
  <TextInput id="first-name" name="first-name" type="text" />
 

  <Label id="first-name" htmlFor="first-name">
      Employer ID Number (EIN)
    </Label>
    <span id="hint-fed-id" className="usa-hint">
    You'll find this in Box b on your 1099.
    </span>
    <TextInputMask id="input-type-ssn" name="input-type-ssn" type="text" aria-labelledby="first-name" aria-describedby="hint-ssn" mask="__ _______" pattern="^(?!(000|666|9))\d{3} (?!00)\d{2} (?!0000)\d{4}$" />
    
</Fieldset>
</Grid>


<Grid col={1}></Grid>

<Grid col={4}>

<Fieldset legend="" legendStyle="large">
  <p>
    Required fields are marked with an asterisk (<RequiredMarker />
    ).
  </p>
  <Label htmlFor="mailing-address-1">Employer Street address</Label>
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






 







     


      <ButtonGroup type="default">

        <Link to="/w2" className="usa-button usa-button--outline">Back </Link>
        <Link to="/deductions" className="usa-button">Continue </Link>

      </ButtonGroup>
      </Grid>
    </Grid>
  </GridContainer>



  */}


  {/*</Form>*/}
</div>

);
};
export default Income1099;