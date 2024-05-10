import { Label, TextInput, Form, FormGroup, ErrorMessage, Textarea, Accordion, Fieldset, Button, Checkbox, Grid, GridContainer, RequiredMarker, Select, DateRangePicker, DatePicker, ButtonGroup, ProcessListHeading, ProcessListItem, StepIndicator, StepIndicatorStep, Radio, TextInputMask } from "@trussworks/react-uswds";
import { Link, useNavigate } from "react-router-dom";
import React, { ChangeEvent, useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { deleteW2Income, fetchTaxInfo, submitFullTaxInfo, updateW2Income } from '../redux/slices/taxSlice';
import { RootState } from "../redux/storeTypes";
import { AppDispatch } from "../redux/store";


export const incomeW2 = (): React.ReactElement => {

  const dispatch = useDispatch<AppDispatch>();
  const navigate = useNavigate();
  const currentTaxInfo = useSelector((state: RootState) => state.taxInfo);






  const [w2Forms, setW2Forms] = useState(currentTaxInfo.incomeW2 || [{
    id: 0, income: 0, withholdings: 0, employerEin: '', employerStreet1: '', employerStreet2: '', employerCity: '', employerState: '', employerZipcode: ''}]);


    useEffect(() => {
      // Fetch tax information when the component mounts
      dispatch(fetchTaxInfo());
  }, [dispatch]);


  






    useEffect(() => {
      console.log("W2 Forms Updated:", w2Forms);
      if (currentTaxInfo.incomeW2) {
        console.log("Initial load of W2 income:", currentTaxInfo.incomeW2);
        setW2Forms(currentTaxInfo.incomeW2);
      }
    }, [currentTaxInfo.incomeW2]);
  
  

    const handleInputChange = (index: number, field: string, event: ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
      const value = event.target.value;
      const newForms = [...w2Forms];
      
      // Type guard to ensure event.target.value is a string
      if (typeof value === 'string') {
        newForms[index] = {
          ...newForms[index],
          [field]: (field === 'income' || field === 'withholdings') ? parseFloat(value) : value
        };
        console.log(`W2 Form ${index} Change:`, newForms[index]);
        setW2Forms(newForms);
      } else {
        // Handle unexpected value type
        console.error('Unexpected value type:', typeof value);
      }
    };
  const handleAddForm = () => {
    setW2Forms([...w2Forms, {
      id:0,
      income: 0,
      withholdings: 0,
      employerEin: '',
      employerStreet1: '',
      employerStreet2: '',
      employerCity: '',
      employerState: '',
      employerZipcode: ''
    }]);
  };

  const handleDeleteForm = (index: number) => {
    const newForms = [...w2Forms];
    newForms.splice(index, 1);
    setW2Forms(newForms);
    // Optionally dispatch a delete action to update the backend
    dispatch(deleteW2Income(index));
  };

  // Updates Redux state and navigates to the next form
  const handleContinue = async (e: { preventDefault: () => void; }) => {
    e.preventDefault();
    console.log("Submitting W2 Forms:", w2Forms);
    await dispatch(updateW2Income({ forms: w2Forms }));
    await dispatch(submitFullTaxInfo(currentTaxInfo));
    
    navigate('/income1099'); // Navigate to the next form
  };
  /*
    const handleSubmit = async () => {
      const formattedData = w2Forms.map(form => ({
          income: parseFloat(form.income) || 0,
          withholdings: parseFloat(form.withholdings) || 0,
          employerEin: form.employerEin,
          employerStreet1: form.employerStreet1,
          employerStreet2: form.employerStreet2,
          employerCity: form.employerCity,
          employerState: form.employerState,
          employerZipcode: form.employerZipcode
      }));
  
      const response = await dispatch(submitFullTaxInfo({ w2Income: formattedData }));
      if (response.error) {
          console.error('Submission failed', response.error);
      } else {
          console.log('Submission successful', response.payload);
          // Update form states with IDs from backend or reset forms if needed
          setW2Forms([...response.payload.w2Income]); // Assuming backend response matches the input format
      }
  };
  
  */

   //accordion opening
   const [openPanelId, setOpenPanelId] = useState<number | null>(null);  // Track the ID of the currently open panel

   // Function to handle accordion changes
   const handleAccordionChange = (panelId: number) => {
     // Toggle panel: close it if it's already open, or open the selected panel
     setOpenPanelId(prevId => prevId === panelId ? null : panelId);
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
              status="current"

            />
            <StepIndicatorStep
              label="Other Income"


            />
            <StepIndicatorStep label="Deductions" />
            <StepIndicatorStep label="Review" />
            <StepIndicatorStep label="Sign and Submit" />
          </StepIndicator>

          <Grid row gap>


            <Grid col={10}>
              {/*
    <Grid col={4} style={{
    marginLeft: '18rem'}}>
    */}

 
<main id="main-content">
              <h1>Other Income Forms</h1>

              <section id="" className="">

                <Grid row>
                  <Grid col={12} >
                    <h1 className="usa-hero__heading">
                      <span className="usa-hero__heading--alt ">
                        Other Income
                      </span>

                    </h1>
                    {/* <Button type="button" onClick={handleAddForm}>Add Income Form</Button>*/}



                    <GridContainer>
                      <Grid row >

                        <Grid col={4}>
                          <ButtonGroup type="default">

                            <Link to="/filing-status" className="usa-button usa-button--outline">Back </Link>


                          </ButtonGroup>
                        </Grid>
                        <Grid col={4}></Grid>



                        <Grid col={4}>
                          <span className="usa-hero__heading--alt  tablet:margin-bottom-3" style={{ fontWeight: 'bold', fontSize: '10px' }}>
                            <Button type="button" secondary onClick={handleAddForm}>Add W2</Button>
                          </span>

                        </Grid>

                      </Grid>

                    </GridContainer>



                    <Accordion bordered={false} items={w2Forms.map((form, index) => ({


                      title: <span style={{ color: '#000', fontWeight: 'bold' }}>W2 Form {index + 1}</span>,
                      content: (
                        <p style={{ color: '#000', width: '80%' }}>



<Fieldset legend="Form W-2" legendStyle="large"  style={{ marginBottom: '20px', marginTop: '30px'}}>
              <Grid row>
              <Grid col={5}>


              
                   <div style={{ marginBottom: '90px', marginTop: '78px'}}>
                  <div key={index}>
                    <Label htmlFor={`income-${index}`}>W-2 Income</Label>
                    <TextInput id={`income-${index}`} name="income" type="number" value={form.income.toString()} onChange={e => handleInputChange(index, 'income', e)} />

                    <Label htmlFor={`withholdings-${index}`}>Federal Tax Withheld</Label>
                    <TextInput id={`withholdings-${index}`} name="withholdings" type="number" value={form.withholdings.toString()} onChange={e => handleInputChange(index, 'withholdings', e)} />

                    <Label htmlFor={`employerEin-${index}`}>Employer EIN</Label>
                    <TextInput id={`employerEin-${index}`} name="employerEin" type="text" value={form.employerEin} onChange={e => handleInputChange(index, 'employerEin', e)} />
                  </div>
                  </div>
                



                              </Grid>





                              <Grid col={2}></Grid>
                              <Grid col={5}>

                              
                     <div style={{ marginBottom: '80px', marginTop: '80px'}}>
                  <div key={index}>
                    <Label htmlFor={`employerStreet1-${index}`}>Employer Street Address</Label>
                    <TextInput id={`employerStreet1-${index}`} name="employerStreet1" type="text" value={form.employerStreet1} onChange={e => handleInputChange(index, 'employerStreet1', e)} />

                    <Label htmlFor={`employerStreet2-${index}`}>Employer Street Address Line 2</Label>
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
                  </div>
                




                                  <GridContainer>
                                    <Grid row >

                                      <Grid col={10}>
                                        <ButtonGroup type="default">






                                          < span className="usa-hero__heading--alt  tablet:margin-top-2" style={{ fontWeight: 'bold', fontSize: '10px' }}>
                                            <Button type="button" onClick={() => handleDeleteForm(index)}>Delete Form</Button>
                                          </span>



                                          <Button type="button" secondary onClick={handleAddForm}>Add W2</Button>
                                        </ButtonGroup>

                                      </Grid>

                                    </Grid>

                                  </GridContainer>

                                



                              </Grid>

                            </Grid>


                          </Fieldset>
















                        </p>
                      ),
                      id: `form-${form.id}`,
                      expanded: openPanelId === form.id,
                      onToggle: () => handleAccordionChange(form.id),
                      headingLevel: 'h3'
                    }))} />






                  </Grid>
                </Grid>

              </section>









              <section id="test-section-id" className="usa-graphic-list usa-section">
                <GridContainer>
                  <Grid row style={{
                    marginLeft: '50rem'
                  }}>
                    <ButtonGroup type="default">
                      <Button type="button" onClick={handleContinue}>Continue</Button>


                      <Link to="/review" className="usa-button usa-button--outline">Skip to Review</Link>

                    </ButtonGroup>


                  </Grid>

                </GridContainer>
              </section>

            </main>

          </Grid>
        </Grid>

      </GridContainer>








    </div>

  );
};
export default incomeW2;