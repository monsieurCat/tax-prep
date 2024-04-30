import React from 'react';
import logoImg from '../assets/logoImg.png';
import { Link } from "react-router-dom";
import { Button, Fieldset, Footer, Form, GovBanner, Grid, GridContainer, Header, Identifier, IdentifierGov, IdentifierIdentity, IdentifierLink, IdentifierLinkItem, IdentifierLinks, IdentifierLogo, IdentifierLogos, IdentifierMasthead, Label, TextInput, Title } from '@trussworks/react-uswds';

const Login = (): React.ReactElement => {
    const [showPassword, setShowPassword] = React.useState(false);


    function handleLogin(event: any) {
      event.preventDefault();
      const url = 'http://localhost:8282/login';
    
      const formData = new URLSearchParams();
      formData.append("username", event.target.email.value);
      formData.append("password", event.target.password.value);
    
      fetch(url, {
        method: "POST",
        headers: {
          "Content-Type": "application/x-www-form-urlencoded", // Use form-urlencoded content type
        },
        body: formData.toString(), // Convert form data to string
      })
        .then((response) => {
          // Handle the response here
          console.log("Raw response:", response);
          return response.json();
        })
        .catch((error) => console.error("Error:", error));
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
                    <h1 className="margin-bottom-0">Sign in</h1>
                   <Form onSubmit={handleLogin}>
                      <Fieldset legend="Access your account" legendStyle="large">
                        <Label htmlFor="email">Email address</Label>
                        <TextInput id="email" name="email" type="email" autoCorrect="off" autoCapitalize="off" required={true} />
  
                        <Label htmlFor="password-sign-in">Password</Label>
                        <TextInput id="password-sign-in" name="password" type={showPassword ? 'text' : 'password'} autoCorrect="off" autoCapitalize="off" required={true} />
  
                        <button title="Show password" type="button" className="usa-show-password" aria-controls="password-sign-in" onClick={(): void => setShowPassword(showPassword => !showPassword)}>
                          {showPassword ? 'Hide password' : 'Show password'}
                        </button>
  
                        <Button type="submit">Sign in</Button>
  
                        <p>
                        <Link to="/create-account">forgot password? </Link>
                        </p>
                      </Fieldset>
                   </Form>
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