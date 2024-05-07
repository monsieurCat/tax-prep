import { GovBanner, Header, Title, GridContainer, Grid, Fieldset, Form, Label, TextInput, Checkbox, Button, MediaBlockBody, Footer, Identifier, IdentifierMasthead, IdentifierLogos, IdentifierLogo, IdentifierIdentity, IdentifierLinks, IdentifierLinkItem, IdentifierLink, IdentifierGov } from "@trussworks/react-uswds";
import React, { useState } from 'react';
import { Link } from "react-router-dom";
import { TypedUseSelectorHook, useDispatch as reduxUseDispatch} from 'react-redux';
import { registerUser } from '../redux/slices/authSlice';
import {  AppDispatch } from "../redux/store";
import { RootState } from '../redux/storeTypes';




const CreateAccount=(): React.ReactElement => {
  const [email, setEmail] = useState('');
    const [showPassword, setShowPassword] = React.useState(false);
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const useDispatch = () => reduxUseDispatch<AppDispatch>();
  const dispatch = useDispatch();


    
  const handleRegistration = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    if (password !== confirmPassword) {
      alert('Passwords do not match!');
      return;
    }
    dispatch(registerUser({ email, password}));
   
  };

   

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
          <div className="bg-base-lightest">
            <GridContainer className="usa-section">
              <Grid row className="margin-x-neg-205 flex-justify-center">
                <Grid col={12} mobileLg={{
                col: 10
              }} tablet={{
                col: 8
              }} desktop={{
                col: 6
              }} className="padding-x-205 margin-bottom-4">
                  <h1 className="desktop:display-none font-sans-lg margin-bottom-4 tablet:margin-top-neg-3">
                    A tagline that explains the benefit of creating an account.
                  </h1>
  
                  <div className="bg-white padding-y-3 padding-x-5 border border-base-lighter">
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
                          Email address{' '}
                          <abbr title="required" className="usa-label--required">
                            *
                          </abbr>
                        </Label>
                        <TextInput id="email" name="email" type="email" autoCapitalize="off" autoCorrect="off" required={true} />
  
                        <Label htmlFor="password-create-account">
                          Create password{' '}
                          <abbr title="required" className="usa-label--required">
                            *
                          </abbr>
                        </Label>
                        <TextInput id="password-create-account" name="password" type={showPassword ? 'text' : 'password'} autoCapitalize="off" autoCorrect="off" required={true} />
  
                        <button title="Show password" type="button" className="usa-show-password" aria-controls="password-create-account password-create-account-confirm" onClick={(): void => setShowPassword(showPassword => !showPassword)}>
                          {showPassword ? 'Hide password' : 'Show password'}
                        </button>
  
                        <Label htmlFor="password-create-account-confirm">
                          Re-type password{' '}
                          <abbr title="required" className="usa-label--required">
                            *
                          </abbr>
                        </Label>
                        <TextInput id="password-create-account-confirm" name="password-confirm" type={showPassword ? 'text' : 'password'} autoCapitalize="off" autoCorrect="off" required={true} />
  
                        <Checkbox id="terms-and-conditions" name="terms-and-conditions" className="margin-y-3" required={true} label={checkboxLabel} />
  
                        <Button type="submit">Create account</Button>
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