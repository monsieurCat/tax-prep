import { GovBanner, Header, Title, GridContainer, Grid, Fieldset, Form, Label, TextInput, Checkbox, Button, MediaBlockBody, Footer, Identifier, IdentifierMasthead, IdentifierLogos, IdentifierLogo, IdentifierIdentity, IdentifierLinks, IdentifierLinkItem, IdentifierLink, IdentifierGov } from "@trussworks/react-uswds";
import React, { useState } from 'react';
import { Link,  useNavigate } from "react-router-dom";
import { TypedUseSelectorHook, useDispatch as reduxUseDispatch} from 'react-redux';
import {  AppDispatch } from "../redux/store";
import { RootState } from '../redux/storeTypes';




const CreateAccount=(): React.ReactElement => {
  const [email, setEmail] = useState('');
  const [firstName, setFirst] = useState('');
  const [middleName, setMiddle] = useState('');
  const [lastName, setlast] = useState('');
  const [username, setUsername] = useState('');
  const [ssn, setSsn] = useState('');
  const [birthday, setBday] = useState('');
  const [role, setRole] = useState('');
    const [showPassword, setShowPassword] = React.useState(false);
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const useDispatch = () => reduxUseDispatch<AppDispatch>();
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const [errorMessage, setErrorMessage] = React.useState<string | null>(null);


  function handleRegistration(event: { preventDefault: () => void; }) {
    event.preventDefault();
    const userData = {
      
      username,
      password
      
  };
    fetch("http://localhost:8282/api/auth/register", {
        method: "POST",
        credentials: 'include',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(userData)
    })

    .then(response => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      // Assuming response type is JSON, modify this part as needed
      return response.json();
    })
    .then(data => {
      // Assuming response data structure has a different format
      if (data.code === 200) { // Adjust this condition based on your response structure
        console.log('Registration successful');
        navigate('/login');
      } else {
        setErrorMessage(data.message || 'Registration failed. Please try again.');
      }
    })
    .catch(error => {
      console.error("Registration error:", error);
      setErrorMessage('An error occurred during registration.');
    });
  }



/*
    
  const handleRegistration = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    if (password !== confirmPassword) {
      alert('Passwords do not match!');
      return;
    }
    const userData = {
      email, firstName, middleName, lastName, username, ssn, birthday, role, password
    };
    console.log('Dispatching registration with data:', { firstName, middleName, lastName, email, username, ssn, birthday, role });
    dispatch(registerUser({ userData}))
    .unwrap()
    .then(() => {
      console.log('Registration successful, navigating to login-home'); // successful registration
      navigate('/login-home');
    })
    .catch((error) => {
      console.error('Registration failed:', error); // registration fails
      alert('Registration failed: ' + error.message);
    });
   
  };*/

    // Event handlers to update state
 // const handleInputChange = (setter: (arg0: any) => any) => (e: { target: { value: any; }; }) => setter(e.target.value);

      const checkboxLabel = (
        <>
          I agree to the <a href="javascript:void(0);">terms and conditions</a>.{' '}
          <abbr title="required" className="usa-hint usa-hint--required">
            *
          </abbr>
        </>
      )
      
    return <>
        
  
        
  
        <main id="main-content">
          <div className="bg-base-lightest" style={{ padding: '2rem',  marginTop: '2rem'}}>
            <GridContainer className="usa-section">
              <Grid row className="margin-x-neg-205 flex-justify-center">
              <Grid col={12} mobileLg={{ col: 10 }} tablet={{ col: 8 }} desktop={{ col: 6 }} className="padding-x-205 margin-bottom-4">
              <div className="bg-white padding-y-3 padding-x-5 border border-base-lighter" style={{ marginLeft: '1rem', marginRight: '1rem' }}>
                <h1 className="margin-bottom-0">Create account</h1>
                <Form onSubmit={handleRegistration}>
                  <Fieldset legend="Get started with an account.">
                        <p>
                          <abbr title="required" className="usa-hint usa-hint--required">
                            *
                          </abbr>{' '}
                          indicates a required field.
                        </p>
  
                        <Label htmlFor="email">
                          Username{' '}
                          <abbr title="required" className="usa-label--required">
                            *
                          </abbr>
                        </Label>
                        <TextInput id="username" name="username" type="text" autoCapitalize="off" autoCorrect="off" required={true}  value={username}
    onChange={(e) => setUsername(e.target.value)}/>
  
                        <Label htmlFor="password-create-account">
                          Create password{' '}
                          <abbr title="required" className="usa-label--required">
                            *
                          </abbr>
                        </Label>
                        <TextInput id="password-create-account" name="password" type={showPassword ? 'text' : 'password'} autoCapitalize="off" autoCorrect="off" required={true} value={password}
    onChange={(e) => setPassword(e.target.value)} />
  
                        <button title="Show password" type="button" className="usa-show-password" aria-controls="password-create-account password-create-account-confirm" onClick={(): void => setShowPassword(showPassword => !showPassword)}>
                          {showPassword ? 'Hide password' : 'Show password'}
                        </button>
  
                        <Label htmlFor="password-create-account-confirm">
                          Re-type password{' '}
                          <abbr title="required" className="usa-label--required">
                            *
                          </abbr>
                        </Label>
                        <TextInput id="password-create-account-confirm" name="password-confirm" type={showPassword ? 'text' : 'password'} autoCapitalize="off" autoCorrect="off" required={true} value={confirmPassword}
    onChange={(e) => setConfirmPassword(e.target.value)} />
  
                        <Checkbox id="terms-and-conditions" name="terms-and-conditions" className="margin-y-3" required={true} label={checkboxLabel} />
  
                        <Button type="button" onClick={handleRegistration}>Create Account</Button>

                      </Fieldset>
                    </Form>
                  </div>
  
                  <p className="text-center">
                    Already have an account?{' '}
                    <Link to="/login">Sign in</Link>.
                  </p>
                </Grid>
  
                <Grid col={12} mobileLg={{
                col: 10
              }} tablet={{
                col: 8
              }} desktop={{
                col: 6
              }} className="padding-x-205">
                  <div className="border-top border-base-lighter padding-top-4 desktop:border-0 desktop:padding-top-0">
                    
              
  
                    <div className="border-top border-base-lighter margin-top-3 padding-top-1">
                      
                    
                    </div>
                  </div>
                </Grid>
              </Grid>
            </GridContainer>
          </div>
        </main>
  
      </>;
  }
  export default CreateAccount;