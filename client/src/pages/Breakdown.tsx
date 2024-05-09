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

        <div style={{ marginLeft: '2rem' }}>

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
                        marginLeft: '5rem'
                    }}>
                        {/*
    <Grid col={4} style={{
    marginLeft: '18rem'}}>
    */}



                        {/* <Form onSubmit={mockSubmit}>*/}




                        <main id="main-content">


                            <section id="test-section-id" className="usa-graphic-list usa-section usa-section--dark ">
                                <GridContainer className="">
<Grid row>
    <Grid col={12} >
                                    <h1 className="usa-hero__heading">
                                        <span className="usa-hero__heading--alt ">
                                            Your estimated tax {taxResults!.finalTaxAmount >= 0 ? 'refund' : 'owed'}
                                        </span>
                                        ${Math.abs(taxResults!.finalTaxAmount)}
                                    </h1>
                                    <Accordion bordered={false} items={
                                        [
                                            {
                                                title: <span style={{ color: '#000', fontWeight: 'bold' }}>View Federal Tax Breakdown</span>,
                                                content: (
                                                    <p style={{ color: '#000' }}>
                                                        <Table bordered={false} >
                                                            <thead>
                                                                <tr>
                                                                    <th scope="col">Document title</th>
                                                                    <th scope="col">Year</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>


                                                                <tr>
                                                                    <th scope="row"  >
                                                                        <span className="font-sans-xl text-black margin-top-0 tablet:margin-bottom-0">W-2 Income <br /> + 1099 Income</span>
                                                                    </th>
                                                                    <td>
                                                                        <span className="font-sans-xl text-black margin-top-0 tablet:margin-bottom-0">{taxResults!.incomeW2} <br />{taxResults!.income1099}</span>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <th scope="row">
                                                                        <span className="font-sans-xl text-black margin-top-0 tablet:margin-bottom-0">Total Income <br />- Deductions</span>
                                                                    </th>
                                                                    <span className="font-sans-xl text-black margin-top-0 tablet:margin-bottom-0">{taxResults!.totalIncome}<br />{taxResults!.deductions}</span>
                                                                </tr>




                                                                <tr>
                                                                    <th scope="row"  >
                                                                        <span className="font-sans-xl text-black margin-top-0 tablet:margin-bottom-0">Taxable Income <br /> * Tax Rate</span>
                                                                    </th>
                                                                    <td>
                                                                        <span className="font-sans-xl text-black margin-top-0 tablet:margin-bottom-0">{taxResults!.taxableIncome}<br />{taxResults!.effectiveTaxRate}</span>
                                                                    </td>
                                                                </tr>

                                                                <tr>
                                                                    <th scope="row"  >
                                                                        <span className="font-sans-xl text-black margin-top-0 tablet:margin-bottom-0">Total Federal Tax <br /> -Federal Income Tax Withheld <br /> -Child Tax Credit</span>
                                                                    </th>
                                                                    <td>
                                                                        <span className="font-sans-xl text-black margin-top-0 tablet:margin-bottom-0">{taxResults!.totalTaxAmount}<br />{taxResults!.taxWithheld} <br />{taxResults!.childTaxCredit}</span>
                                                                    </td>
                                                                </tr>

                                                                <tr>
                                                                    <th scope="row"  >
                                                                        <span className="font-sans-xl text-black margin-top-0 tablet:margin-bottom-0">Amount {taxResults!.finalTaxAmount >= 0 ? 'refunded' : 'owed'}</span>
                                                                    </th>
                                                                    <td>
                                                                        <span className="font-sans-xl text-black margin-top-0 tablet:margin-bottom-0"> ${Math.abs(taxResults!.finalTaxAmount)}</span>
                                                                    </td>
                                                                </tr>
                                                               


                                                            </tbody>
                                                        </Table>
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









                            <section id="test-section-id" className="usa-graphic-list usa-section">
                                <GridContainer>
                                    <Grid row style={{
                                        marginLeft: '16rem'
                                    }}>
                                        <a href="#" className="usa-button usa-button--big">
                                            Sign and submit
                                        </a>

                                    </Grid>

                                </GridContainer>
                            </section>

                        </main>

                    </Grid>


                    {/* <Form onSubmit={mockSubmit}>*/}


                    <ButtonGroup type="default">

                        <Link to="/review" className="usa-button usa-button--outline">Back </Link>


                    </ButtonGroup>

                </Grid>

            </GridContainer>



            {/*</Form>*/}
        </div>

    );
};
export default Breakdown;