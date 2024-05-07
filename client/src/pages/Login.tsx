import React from 'react';
import logoImg from '../assets/logoImg.png';
import { Link, useLocation, useNavigate } from "react-router-dom";
import { useDispatch } from 'react-redux';
import { login } from '../redux/slices/authSlice'; 
import { Address, Alert, Button, ButtonGroup, Fieldset, Footer, FooterNav, Form, GovBanner, Grid, GridContainer, Header, Identifier, IdentifierGov, IdentifierIdentity, IdentifierLink, IdentifierLinkItem, IdentifierLinks, IdentifierLogo, IdentifierLogos, IdentifierMasthead, Label, Logo, SocialLinks, TextInput, Title } from '@trussworks/react-uswds';
const Login = (): React.ReactElement => {
   const [showPassword, setShowPassword] = React.useState(false);
   const [errorMessage, setErrorMessage] = React.useState<string | null>(null);
   const [isAuthenticated, setIsAuthenticated] = React.useState(false);
   const location = useLocation(); // Use the useLocation hook
   const [logoutMessage, setLogoutMessage] = React.useState('');
   const [logoutError, setLogoutError] = React.useState('');
   const navigate = useNavigate();
   const dispatch = useDispatch();

   function handleLogin(event: any) {
      event.preventDefault();
      const url = 'http://localhost:8282/login';

      const formData = new URLSearchParams();
      formData.append("username", event.target.email.value);
      formData.append("password", event.target.password.value);

      fetch(url, {
         method: "POST",
         credentials: 'include',
         headers: {
            "Content-Type": "application/x-www-form-urlencoded", // Use form-urlencoded content type
         },
         body: formData.toString(), // Convert form data to string
      })
      .then(response => response.json())  // Assuming the server responds with JSON
      .then(data => {
          if (data.username) {
              // If the login is successful and the username is provided
              dispatch(login({ username: data.username }));
              navigate('/login-home');  // Redirect on successful login
          } else {
              // If login failed but the server responded
              setErrorMessage('Failed to log in. Please check your credentials and try again.');
          }
      })
         .catch((error) => console.error("Error:", error));
         setErrorMessage('An error occurred during login. Please try again later.');
   }
   



   React.useEffect(() => {
      const queryParams = new URLSearchParams(location.search);
      const logoutParam = queryParams.get('logout');
      const errorParam = queryParams.get('error');

      if (logoutParam) {
          setLogoutMessage('You have been successfully logged out.');
      } else if (errorParam) {
          setLogoutError('Failed to log out. Please try again.');
      }
  }, [location]);







   return <>

{logoutMessage && <Alert type="info" headingLevel="h4" noIcon>
                {logoutMessage}
            </Alert>}

            {logoutError && <Alert type="error" heading="Error status" headingLevel="h4">{logoutError}</Alert>}
            


      <main id="main-content">
         <div className="bg-warning-light" style={{ padding: '3rem',  marginTop: '2rem'}}>
            <GridContainer className="usa-section  ">
               <Grid row={true} className="flex-justify-center">
                  <Grid col={12} tablet={{
                     col: 8
                  }} desktop={{
                     col: 6
                  }}>
                     <div className="bg-white padding-y-3 padding-x-5 border border-base-lighter">
                        <h1 className="margin-bottom-0"></h1>
                        <Form onSubmit={handleLogin}>
                           <Fieldset legend="Sign in" legendStyle="large" >
                              <Label htmlFor="email">Email address</Label>
                              <TextInput id="email" name="email" type="email" autoCorrect="off" autoCapitalize="off" required={true} />

                              <Label htmlFor="password-sign-in">Password</Label>
                              <TextInput id="password-sign-in" name="password" type={showPassword ? 'text' : 'password'} autoCorrect="off" autoCapitalize="off" required={true} />

                              <button title="Show password" type="button" className="usa-show-password" aria-controls="password-sign-in" onClick={(): void => setShowPassword(showPassword => !showPassword)}>
                                 {showPassword ? 'Hide password' : 'Show password'}
                              </button>

                              <Button type="submit" >Sign in</Button>


                              <p>
                                 <Link to="/create-account">Forgot password? </Link>
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

      <Footer size="medium" primary={<FooterNav size="medium" links={Array(4).fill(<a className="usa-footer__primary-link" href="#">
          
          </a>)} />} secondary={<div className="grid-row grid-gap">
        <Logo size="big" image={<img className="usa-footer__logo-img" alt="img alt text" src={logoImg} />} heading={<p className="usa-footer__logo-heading"></p>} />
      
         
          <h3 className="usa-footer__contact-heading"></h3>
          <Address size="medium" items={[<a key="telephone" href="tel:1-800-555-5555">
                (800) CALL-GOVT
              </a>, <a key="email" href="mailto:info@agency.gov">
                info@agency.gov
              </a>]} />
        
      </div>} />

   

   </>;
}

export default Login;