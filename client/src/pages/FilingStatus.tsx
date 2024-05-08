import { Label, TextInput, FormGroup, ErrorMessage, Textarea, Form, Fieldset, Button, Checkbox, Grid, GridContainer, RequiredMarker, Select, DateRangePicker, DatePicker, ButtonGroup, ProcessListHeading, ProcessListItem, StepIndicator, StepIndicatorStep, Radio } from "@trussworks/react-uswds";
import { useNavigate, Link } from "react-router-dom";
import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { updateFilingStatus, fetchFilingStatus, submitFullTaxInfo, fetchTaxInfo, updateLocalTaxInfo } from '../redux/slices/taxSlice';
import { updateFilingStatusApi } from '../api/taxApi';
import { AppDispatch } from '../redux/store';
import { RootState } from "../redux/storeTypes";

const FilingStatus = (): React.ReactElement => {

  //const [filingStatus, setFilingStatus] = useState('');
  //const filingStatus = useSelector((state: RootState) => state.taxInfo.filingStatus);
  const error = useSelector((state: RootState) => state.taxInfo.error);
  const dispatch = useDispatch<AppDispatch>();
  const navigate = useNavigate();
  const taxInfo = useSelector((state: RootState) => state.taxInfo); // Access the whole tax state
  const { filingStatus } = taxInfo;
  const [localFilingStatus, setLocalFilingStatus] = useState(filingStatus);


  /*
  useEffect(() => {
    dispatch(fetchFilingStatus());
}, [dispatch]);*/

useEffect(() => {
  if (!taxInfo.filingStatus) {
    dispatch(fetchTaxInfo());
  }
}, [dispatch, taxInfo.filingStatus]);


  const handleStatusChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    //setFilingStatus(event.target.value);
    setLocalFilingStatus(event.target.value);  // update local state to reflect user's choice
  };

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    try {
      
      //await dispatch(updateFilingStatus({filingStatus})); 
      // Submit the updated filing status to the store before navigating
    //await dispatch(submitFullTaxInfo({ ...taxInfo, filingStatus: localFilingStatus }));
    dispatch(updateLocalTaxInfo());
      navigate('/w2'); 
    } catch (error) {
      console.error('Failed to update filing status:', error);
    }
  };


  return (

    <>
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
              status="current"

            />
            <StepIndicatorStep
              label="W2 Income"
            />
            <StepIndicatorStep
              label="1099 Income"
            />
            <StepIndicatorStep label="Deductions" />
            <StepIndicatorStep label="Review" />
            <StepIndicatorStep label="Sign and Submit" />
          </StepIndicator>

          <Grid row gap>


            <Form onSubmit={handleSubmit}>
              <Fieldset legend="Filing Status" legendStyle="large">
{/*
                <Radio id="single" name="filing-status" label="Single" value="Single" onChange={handleStatusChange} checked={filingStatus === 'Single'} />
                <Radio id="married-jointly" name="filing-status" label="Married filing jointly" value="Married filing jointly" onChange={handleStatusChange} checked={filingStatus === 'Married filing jointly'} />
                <Radio id="married-separately" name="filing-status" label="Married filing separately" value="Married filing separately" onChange={handleStatusChange} checked={filingStatus === 'Married filing separately'} />
                <Radio id="head-of-household" name="filing-status" label="Head of Household" value="Head of Household" onChange={handleStatusChange} checked={filingStatus === 'Head of Household'} />
                <Radio id="surviving-spouse" name="filing-status" label="Qualifying Surviving Spouse" value="Qualifying Surviving Spouse" onChange={handleStatusChange} checked={filingStatus === 'Qualifying Surviving Spouse'} />
*/}
 {['Single', 'Married filing jointly', 'Married filing separately', 'Head of Household', 'Qualifying Surviving Spouse'].map(status => (
                  <Radio key={status} id={status.replace(/\s+/g, '-').toLowerCase()} name="filing-status" label={status} value={status} onChange={handleStatusChange} checked={filingStatus === status} />
                ))}
              </Fieldset>




              <ButtonGroup type="default">

                <Link to="/personal-form" className="usa-button usa-button--outline">Back </Link>
                <Button type="submit" className="usa-button">Continue </Button>

              </ButtonGroup>
            </Form>
          </Grid>
        </GridContainer>




      </div>
    </>

  );
};
export default FilingStatus;