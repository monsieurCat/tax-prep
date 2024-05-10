import { Label, TextInput, FormGroup, Form, ErrorMessage, Textarea, Fieldset, Accordion, Button, Checkbox, Grid, GridContainer, RequiredMarker, Select, DateRangePicker, DatePicker, ButtonGroup, ProcessListHeading, ProcessListItem, StepIndicator, StepIndicatorStep, Radio, TextInputMask } from "@trussworks/react-uswds";
import { Link, useNavigate } from "react-router-dom";
import React, { ChangeEvent, useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { addIncome1099, deleteIncome1099, fetchTaxInfo, submitFullTaxInfo, updateIncome1099 } from '../redux/slices/taxSlice';
import { RootState } from "../redux/storeTypes";
import { AppDispatch } from "../redux/store";

export const Income1099 = (): React.ReactElement => {
  
  const dispatch = useDispatch<AppDispatch>();
  const navigate = useNavigate();
  const currentTaxInfo = useSelector((state: RootState) => state.taxInfo);
  

  const [forms, setForms] = useState(currentTaxInfo.income1099 || [{
   id: 0, income: 0, withholdings: 0, employerEin: '', employerStreet1: '', employerStreet2: '', employerCity: '', employerState: '', employerZipcode: ''}]);

   useEffect(() => {
    // Fetch tax information when the component mounts
    dispatch(fetchTaxInfo());
}, [dispatch]);

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
    setForms([...forms, { id:0, income: 0, withholdings: 0, employerEin: '', employerStreet1: '', employerStreet2: '', employerCity: '',  employerState: '', employerZipcode: '' }]);
  };

  const handleDeleteForm = (index: number) => {
    const newForms = [...forms];
    newForms.splice(index, 1);
    setForms(newForms);
    // Optionally dispatch a delete action to update the backend
    dispatch(deleteIncome1099(index));
  };

  const handleContinue = async (e: { preventDefault: () => void; }) => {
    e.preventDefault();
    dispatch(updateIncome1099({ forms: forms }));
    dispatch(submitFullTaxInfo(currentTaxInfo));
    navigate('/deductions'); // Navigate to the next form
  };



  
//accordion opening
  const [openPanelId, setOpenPanelId] = useState<number | null>(null);  // Track the ID of the currently open panel

  // Function to handle accordion changes
  const handleAccordionChange = (panelId: number) => {
    // Toggle panel: close it if it's already open, or open the selected panel
     setOpenPanelId(prevId => prevId === panelId ? null : panelId);
  };




return (

<div style={{ marginLeft: '1rem' }}>
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

    <Grid col={12} style={{
                        marginLeft: '3rem'
                    }}>
                       




                        <main id="main-content">


                            <section id="" className="">
                                
<Grid row>
    <Grid col={12} >
                                    <h1 className="usa-hero__heading">
                                        <span className="usa-hero__heading--alt ">
                                            Other Income
                                        </span>
                                        
                                    </h1>
                                   {/* <Button type="button" onClick={handleAddForm}>Add Income Form</Button>*/}
                                   <Button type = "button" onClick={handleAddForm}>Add New Accordion Item</Button>
                                    <Accordion bordered={false} items={forms.map((form, index) => ({
                                      
                              
                                                title: <span style={{ color: '#000', fontWeight: 'bold' }}>Form {index + 1}</span>,
                                                content: (
                                                    <p style={{ color: '#000' , width: '80%'}}>



<Fieldset legend="Other Income" legendStyle="large"  className="" >
<Grid row>
              <Grid col={5}>
{/* <Form onSubmit={mockSubmit}>*/}



                  <div key={index} style={{
                    
                }}>

{index > 0 && <hr style={{ height: '5px', backgroundColor: 'black', border: 'none' }}  />} 

<div style={{ marginBottom: '50px', marginTop: '50px'}}>
                    <Label htmlFor={`income-${index}`}>{index === 0 ? "1099 Income" : `Other Income ${index}`}</Label>
                    <TextInput id={`income-${index}`} name="income" type="number" value={form.income.toString()} onChange={e => handleInputChange(index, 'income', e)} />
                    </div>
                    <div style={{ marginBottom: '70px', marginTop: '30px'}}>
                    <Label htmlFor={`withholdings-${index}`}>Federal Tax Withheld</Label>
                    <TextInput id={`withholdings-${index}`} name="withholdings" type="number" value={form.withholdings.toString()} onChange={e => handleInputChange(index, 'withholdings', e)} />
                    </div>
                    <div style={{ marginBottom: '60px', marginTop: '30px'}}>
                    <Label htmlFor={`employerEin-${index}`}>Employer EIN</Label>
                    <TextInput id={`employerEin-${index}`} name="employerEin" type="text" value={form.employerEin} onChange={e => handleInputChange(index, 'employerEin', e)} />

                    
                  </div>
                  </div>
                
              

          </Grid>
          
             
                   
       

              <Grid col={2}></Grid>
              <Grid col={5}>
           
               
               
                  <div key={index} style={{
                    
                }}>
                    
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
                 
                    {index < forms.length - 1 && <hr  style={{ height: '5px', backgroundColor: 'black', border: 'none' }} />} 
                    <Button type="button" onClick={() => handleDeleteForm(index)}>Delete Form</Button>
                    
                  </div>
                   
              

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
                                        marginLeft: '16rem'
                                    }}>
                                        <ButtonGroup type="default">

<Link to="/w2-income" className="usa-button usa-button--outline">Back </Link>
<Button type="button" onClick={handleContinue}>Continue</Button>
<Link to="/review" className="usa-button usa-button--outline">Go to Review</Link>
</ButtonGroup>


                                    </Grid>

                                </GridContainer>
                            </section>

                        </main> 
              
            </Grid>
            </Grid>

              </GridContainer>

            
             

              <ButtonGroup type="default">

                <Link to="/w2-income" className="usa-button usa-button--outline">Back </Link>
                <Button type="button" onClick={handleContinue}>Continue</Button>
                <Link to="/review" className="usa-button usa-button--outline">Go to Review</Link>
              </ButtonGroup>
            


</div>

);
};
export default Income1099;