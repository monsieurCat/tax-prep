import { Label, TextInput, FormGroup, ErrorMessage, Textarea, Fieldset, Button, Checkbox, Grid, GridContainer, RequiredMarker, Select, DateRangePicker, DatePicker, ButtonGroup, ProcessListHeading, ProcessListItem, StepIndicator, StepIndicatorStep, Radio, TextInputMask, MediaBlockBody, Table, Accordion } from "@trussworks/react-uswds";
import { Form, Link } from "react-router-dom";
import '../App.css';
import React,{ useEffect, useState } from "react";
import { fetchTaxResults } from "../api/taxApi";



export const Breakdown = (): React.ReactElement => {


    interface TaxResultsDTO {
        incomeW2: number;
        income1099: number;
        totalIncome: number;
        deductions: number;
        taxableIncome: number;
        marginalTaxRate: number;
        effectiveTaxRate: number;
        taxWithheld: number;
        childTaxCredit: number;
        earnedIncomeTaxCredit: number;
        totalTaxAmount: number;
        finalTaxAmount: number;
    }

    const [taxResults, setTaxResults] = useState<TaxResultsDTO>({ } as TaxResultsDTO);


    useEffect(() => {
        const loadTaxResults = async () => {
            try {
                const results = await fetchTaxResults();
                console.log("Tax Results from API:", results); 
                setTaxResults(results);
            } catch (error) {
                console.error('Failed to load tax results', error);
            }
        };

        loadTaxResults();
    }, []);



    return (

        <div >

            <GridContainer className="usa-section">

                <StepIndicator

                    headingLevel="h4"

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
                    <StepIndicatorStep label="Deductions" status="complete" />
                    <StepIndicatorStep label="Review" status="complete" />
                    <StepIndicatorStep label="Sign and Submit" status="current" />
                </StepIndicator>

                <Grid row gap>


                    <Grid col={12} style={{
                        marginLeft: '3rem'
                    }}>


                        
                        {/*
    <Grid col={4} style={{
    marginLeft: '18rem'}}>
    */}



                        {/* <Form onSubmit={mockSubmit}>*/}

                        
                                <GridContainer>
                                    <Grid row >
                                        
                                        <Grid col={4}>
                                        <ButtonGroup type="default">

<Link to="/review" className="usa-button usa-button--outline">Back </Link>


</ButtonGroup>
                                        </Grid>
                                        <Grid col={4}></Grid>
                                        <span className="usa-hero__heading--alt  tablet:margin-bottom-3"  style={{ fontWeight: 'bold' , fontSize: '10px'}}>
                                        <a href="/submit" className="usa-button usa-button--big">
                                            Sign and submit
                                        </a>
                                        </span>
                                    </Grid>

                                </GridContainer>
                           


                        <main id="main-content">


                            <section id="test-section-id" className="usa-graphic-list usa-section usa-section--dark ">
                                <GridContainer className="" >
<Grid row>
    <Grid col={12} >
        
                                    <h1 className="usa-hero__heading">
                                        <span className="usa-hero__heading--alt  tablet:margin-bottom-4"  style={{ fontWeight: 'bold' }}>
                                            Your Estimated Tax {taxResults!.finalTaxAmount >= 0 ? 'Refund:' : 'Owed:'}
                                        </span>
                                        <span className= " tablet:margin-bottom-6" style={{ fontWeight: 'bold' ,  fontSize: '40px'}}>
                                        ${Math.abs(taxResults!.finalTaxAmount)}
                                        </span>
                                    </h1>
                                    <Accordion bordered={false} items={
                                        [
                                            {
                                                title: <span style={{ color: '#000', fontWeight: 'bold' ,  fontSize: '30px'}}>View Federal Tax Breakdown</span>,
                                                content: (
                                                    <p style={{ color: '#000' }}>


                                                        <GridContainer>

                                                            <Grid row style={{ borderBottom: '', paddingBottom: '1px' }}>
                                                                <Grid col={9} >
                                                                <span className="font-sans-xl text-black margin-top-0 tablet:margin-bottom-0">  W-2 Income  </span>
                                                                </Grid>

                                                                <Grid col={3}>
                                                                <span className="font-sans-xl text-black margin-top-0 tablet:margin-bottom-0" style={{ minWidth: '200px' }}>{taxResults!.incomeW2} <br /></span>
                                                                </Grid>
                                                            </Grid>
                                                            <Grid row style={{ borderBottom: '', paddingBottom: '1px' }}>
                                                                <Grid col={9} >
                                                                <span className="font-sans-xl text-black margin-top-0 tablet:margin-bottom-0" style={{ fontWeight: 'bold' }}>   + </span>
                                                                </Grid>
                                                        

                                                                </Grid>
                                                                <Grid row style={{ borderBottom: '4px solid black', paddingBottom: '20px' }}>
                                                                    <Grid col={9}>
                                                                    <span className="font-sans-xl text-black margin-top-0 tablet:margin-bottom-0">   1099 Income</span>
                                                                    </Grid>
                                                                    <Grid col={3}>
                                                                    <span className="font-sans-xl text-black margin-top-0 tablet:margin-bottom-0"style={{ minWidth: '200px' }}> {taxResults!.income1099}</span>
                                                                    </Grid>
                                                                </Grid>







                                                                <Grid row style={{ borderBottom: '', paddingBottom: '1px', paddingTop: '20px' }}>
                                                                <Grid col={9} >
                                                                <span className="font-sans-xl text-black margin-top-0 tablet:margin-bottom-0" style={{ fontWeight: 'bold' }}>  Total Income  </span>
                                                                </Grid>

                                                                <Grid col={3}>
                                                                <span className="font-sans-xl text-black margin-top-0 tablet:margin-bottom-0" style={{ minWidth: '200px', fontWeight: 'bold'  }}>{taxResults!.totalIncome} <br /></span>
                                                                </Grid>
                                                            </Grid>
                                                            <Grid row style={{ borderBottom: '', paddingBottom: '1px' }}>
                                                                <Grid col={9} >
                                                                <span className="font-sans-xl text-black margin-top-0 tablet:margin-bottom-0" style={{ fontWeight: 'bold' }}>   - </span>
                                                                </Grid>
                                                               

                                                                </Grid>
                                                                <Grid row style={{ borderBottom: '4px solid black', paddingBottom: '20px' }}>
                                                                    <Grid col={9}>
                                                                    <span className="font-sans-xl text-black margin-top-0 tablet:margin-bottom-0">  Deductions</span>
                                                                    </Grid>
                                                                    <Grid col={3}>
                                                                    <span className="font-sans-xl text-black margin-top-0 tablet:margin-bottom-0"style={{ minWidth: '200px' }}> {taxResults!.deductions}</span>
                                                                    </Grid>
                                                                </Grid>






                                                                <Grid row style={{ borderBottom: '', paddingBottom: '2px', paddingTop: '20px' } }>
                                                                <Grid col={9} >
                                                                <span className="font-sans-xl text-black margin-top-0 tablet:margin-bottom-0"  style={{ fontWeight: 'bold' }}>  Taxable Income  </span>
                                                                </Grid>

                                                                <Grid col={3}>
                                                                <span className="font-sans-xl text-black margin-top-0 tablet:margin-bottom-0" style={{ minWidth: '200px' , fontWeight: 'bold'}}>{taxResults!.taxableIncome} <br /></span>
                                                                </Grid>
                                                            </Grid>
                                                            <Grid row style={{ borderBottom: '', paddingBottom: '1px' }}>
                                                                <Grid col={9} >
                                                                <span className="font-sans-xl text-black margin-top-0 tablet:margin-bottom-0" style={{ fontWeight: 'bold' }}>   *  </span>
                                                                </Grid>
                                                               

                                                                </Grid>
                                                                <Grid row style={{ borderBottom: '4px solid black', paddingBottom: '20px' }}>
                                                                    <Grid col={9}>
                                                                    <span className="font-sans-xl text-black margin-top-0 tablet:margin-bottom-0">  Tax Rate</span>
                                                                    </Grid>
                                                                    <Grid col={3}>
                                                                    <span className="font-sans-xl text-black margin-top-0 tablet:margin-bottom-0"style={{ minWidth: '200px' }}> {taxResults!.effectiveTaxRate}</span>
                                                                    </Grid>
                                                                </Grid>







                                                                
                                                                <Grid row style={{ borderBottom: '', paddingBottom: '1px', paddingTop: '20px'}}>
                                                                <Grid col={9} >
                                                                <span className="font-sans-xl text-black margin-top-0 tablet:margin-bottom-0" style={{ fontWeight: 'bold' }}>  Total Federal Tax  </span>
                                                                </Grid>

                                                                <Grid col={3}>
                                                                <span className="font-sans-xl text-black margin-top-0 tablet:margin-bottom-0" style={{ minWidth: '200px' ,fontWeight: 'bold' }}>{taxResults!.totalTaxAmount} <br /></span>
                                                                </Grid>
                                                            </Grid>
                                                            <Grid row style={{ borderBottom: '', paddingBottom: '1px' }}>
                                                                <Grid col={9} >
                                                                <span className="font-sans-xl bold text-black margin-top-0 tablet:margin-bottom-0" style={{ fontWeight: 'bold' }}>   -  </span>
                                                                </Grid>
                                                               

                                                                </Grid>
                                                                <Grid row style={{ borderBottom: '', paddingBottom: '1px' }}>
                                                                    <Grid col={9}>
                                                                    <span className="font-sans-xl text-black margin-top-0 tablet:margin-bottom-0">  Income Tax Withheld </span>
                                                                    </Grid>
                                                                    <Grid col={3}>
                                                                    <span className="font-sans-xl text-black margin-top-0 tablet:margin-bottom-0"style={{ minWidth: '200px' }}> {taxResults!.taxWithheld}</span>
                                                                    </Grid>
                                                                </Grid>
                                                                <Grid row style={{ borderBottom: '', paddingBottom: '1px' }}>
                                                                <Grid col={9} >
                                                                <span className="font-sans-xl bold  text-black margin-top-0 tablet:margin-bottom-0" style={{ fontWeight: 'bold' }}>   -  </span>
                                                                </Grid>
                                                               

                                                                </Grid>
                                                                <Grid row style={{ borderBottom: '10px solid black', paddingBottom: '20px' }}>
                                                                    <Grid col={9}>
                                                                    <span className="font-sans-xl text-black margin-top-0 tablet:margin-bottom-0">  Child Tax Credit </span>
                                                                    </Grid>
                                                                    <Grid col={3}>
                                                                    <span className="font-sans-xl text-black margin-top-0 tablet:margin-bottom-0"style={{ minWidth: '200px' }}> {taxResults!.childTaxCredit}</span>
                                                                    </Grid>
                                                                </Grid>





                                                          






                                                           
                                                    
                                                            <Grid row style={{ borderBottom: '', paddingBottom: '30px', paddingTop: '30px'}}>
                                                                <Grid col={9}>
                                                                <span className="font-sans-xl text-red margin-top-0 tablet:margin-bottom-0" style={{ fontWeight: 'bold' }}>AMOUNT {taxResults!.finalTaxAmount >= 0 ? 'REFUNDED' : 'OWED'}</span>
                                                                </Grid>

                                                                <Grid col={3}>
                                                                <span className="font-sans-xl text-red margin-top-0 tablet:margin-bottom-0" style={{ fontWeight: 'bold' }}>  ${Math.abs(taxResults!.finalTaxAmount)}</span>
                                                                </Grid>

                                                            </Grid>

                                                        </GridContainer>

                                                    </p>
                                                ),
                                                expanded: false,
                                                id: '123',
                                                headingLevel: 'h4',
                                            }]
                                    } />

</Grid>
</Grid>
                                </GridContainer>
                            </section>









                         

                        </main>

                    </Grid>


                    {/* <Form onSubmit={mockSubmit}>*/}


                   

                </Grid>

            </GridContainer>



            {/*</Form>*/}
        </div>

    );
};
export default Breakdown;