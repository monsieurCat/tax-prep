import React, { useState } from 'react';
import logoImg from '../assets/logoImg.png';
import { Link } from "react-router-dom";
import { Button, Fieldset, Footer, Form, GovBanner, Grid, GridContainer, Header, Identifier, IdentifierGov, IdentifierIdentity, IdentifierLink, IdentifierLinkItem, IdentifierLinks, IdentifierLogo, IdentifierLogos, IdentifierMasthead, Label, TextInput, Title } from '@trussworks/react-uswds';

const Login = (): React.ReactElement => {
    const [showPassword, setShowPassword] = React.useState(false);
    const [privateData, setPrivateData] = useState('');


    function handleLogin(event: any) {
      event.preventDefault();
      const url = 'http://localhost:8282/user/home';
    
      fetch(url, {
        method: "GET",
        credentials: 'include',
        headers: {
          "Content-Type": "application/json", // Use form-urlencoded content type
        }
      })
        .then((response) => {
          // Handle the response here
          console.log("Raw response:", response);
          return response.text();
        })
        .then(data => {
          setPrivateData(data);
        })
        .catch((error) => console.error("Error:", error));
    }

    function handlePrivateData(event : any) {
      event.preventDefault();
      window.location.href = '/personal-form';
    }
 
    return <>
        
  
        
        <Header extended>
          <div className="usa-navbar bg-primary-dark" >
          <GridContainer className="usa-grid-full  ">
          <Grid row={true} className="flex-justify-center">
                <Grid col={12} tablet={{
                col: 8
              }} desktop={{
                col: 6
              }}>
            <Title id="extended-logo">
              <a href="/" title="Home" aria-label="Home">
                Project title
              </a>
            </Title>
            </Grid>
              </Grid>
           </GridContainer>
          </div>
        </Header>
  
        <main id="main-content">
          <div className="bg-primary-lighter">
            <GridContainer className="usa-section">
              <Grid row={true} className="flex-justify-center">
                <Grid col={12} tablet={{
                col: 8
              }} desktop={{
                col: 6
              }}>
                  <div className="bg-white padding-y-3 padding-x-5 border border-base-lighter">
                    <Button type="button" onClick={handleLogin}>private data</Button>
                    <h1>
                      {privateData}
                    </h1>
                  </div>
  
                  <p className="text-center">
                    {"Don't have an account? "}
                    <Link to="/create-account">Create your account now</Link>
                    .
                  </p>
  
                  <div className="border-top border-base-lighter margin-top-3 padding-top-1">
                  
                  </div>
                </Grid>
              </Grid>
            </GridContainer>
          </div>
        </main>
  
       
  
        <Identifier>
          <IdentifierMasthead aria-label="Agency identifier">
            <IdentifierLogos>
              <IdentifierLogo href="#">
                <img className="usa-identifier__logo-img" src={logoImg} alt="<Parent agency> logo" />
              </IdentifierLogo>
            </IdentifierLogos>
            <IdentifierIdentity domain="domain.gov">
              <span aria-hidden="true">An</span> official website of the{' '}
              <Link to="/create-account">{`<Parent agency>`}</Link>
            </IdentifierIdentity>
          </IdentifierMasthead>
         
          <IdentifierGov aria-label="U.S. government information and services">
            <div className="usa-identifier__usagov-description">
              Looking for U.S. government information and services?
            </div>
            &nbsp;
            <Link to="/create-account" className="usa-link">
              Visit USA.gov
            </Link>
          </IdentifierGov>
        </Identifier>
      </>;
  }

  export default Login;