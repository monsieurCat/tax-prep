import React, { useState } from 'react';
import logoImg from '../assets/logoImg.png';
import { useSelector, useDispatch } from 'react-redux';
import { login, logout , setUsername} from '../redux/slices/authSlice'; 
import { RootState } from '../redux/storeTypes';
import { Link, useNavigate } from "react-router-dom";
import { Address, Button, ButtonGroup, ExtendedNav, Footer, FooterNav, GovBanner, Grid, GridContainer, Header, Logo, MediaBlockBody, Menu, NavDropDownButton, NavMenuButton, Search, SocialLink, SocialLinks, Title } from '@trussworks/react-uswds';

const MyAccount: React.FC = () => {
  const [mobileNavOpen, setMobileNavOpen] = useState(false);
  const [navDropdownOpen, setNavDropdownOpen] = useState([false, false]);
  const username = useSelector((state: RootState) => state.auth.username);




  const handleToggleNavDropdown = (index: number): void => {
    setNavDropdownOpen(prevNavDropdownOpen => {
      const newOpenState = Array(prevNavDropdownOpen.length).fill(false);
      // eslint-disable-next-line security/detect-object-injection
      newOpenState[index] = !prevNavDropdownOpen[index];
      return newOpenState;
    });
  };
  const toggleMobileNav = (): void => {
    setMobileNavOpen(prevOpen => !prevOpen);
  };
  const handleSearch = (): void => {
    /* */
  };
  const primaryNavItems = [<React.Fragment key="primaryNav_0">
      <NavDropDownButton menuId="extended-nav-section-one" isOpen={navDropdownOpen[0]} label="Current section" onToggle={(): void => {
      handleToggleNavDropdown(0);
    }} isCurrent />
      <Menu id="extended-nav-section-one" items={new Array(3).fill(<a href="#">Navigation link</a>)} isOpen={navDropdownOpen[0]} />
    </React.Fragment>, <React.Fragment key="primaryNav_1">
      <NavDropDownButton menuId="extended-nav-section-two" isOpen={navDropdownOpen[1]} label="Section" onToggle={(): void => {
      handleToggleNavDropdown(1);
    }} />
      <Menu id="extended-nav-section-two" items={new Array(3).fill(<a href="#">Navigation link</a>)} isOpen={navDropdownOpen[1]} />
    </React.Fragment>, <a key="primaryNav_2" className="usa-nav__link" href="javascript:void(0)">
      <span>Simple link</span>
    </a>];
  const secondaryNavItems = [<a key="secondaryNav_0" href="">
      Secondary link
    </a>, <a key="secondaryNav_1" href="">
      Another secondary link
    </a>];
  const returnToTop = <GridContainer className="usa-footer__return-to-top">
      <a href="#"></a>
    </GridContainer>;
  const socialLinkItems = [<SocialLink key="facebook" name="Facebook" href="#" />, <SocialLink key="twitter" name="Twitter" href="#" />, <SocialLink key="youtube" name="YouTube" href="#" />, <SocialLink key="instagram" name="Instagram" href="#" />, <SocialLink key="rss" name="RSS" href="#" />];
  const footerPrimary = <FooterNav aria-label="Footer navigation" size="medium" links={Array(5).fill(<a href="javascript:void(0)" className="usa-footer__primary-link">
          
        </a>)} />;
  const footerSecondary = <>
      <Grid row gap>
        <Logo size="medium" image={<img className="usa-footer__logo-img" src={logoImg} alt="" />} heading={<p className="usa-footer__logo-heading"></p>} />
        <Grid className="usa-footer__contact-links" mobileLg={{
        col: 6
      }}>
          <SocialLinks links={socialLinkItems} />
          <h3 className="usa-footer__contact-heading">TAXSTORM Contact Center</h3>
          <Address size="medium" items={[<a key="telephone" href="tel:1-800-555-5555">
                (800) CALL-TAXSTORM
              </a>, <a key="email" href="mailto:info@agency.gov">
                info@agency.gov
              </a>]} />
        </Grid>
      </Grid>
    </>;
  return <>

  
   
  
      <main id="main-content">
      <div className="" style={{ padding: '1rem',  marginTop: '2rem'}}>
        <section id="test-section-id" className="usa-graphic-list usa-section bg-base-lighter">
          <GridContainer  >
            <Grid row>
            <Grid col={12} style={{ marginLeft: 'auto', marginRight: 'auto' }}>

           
         

            <h1 className="font-sans text-black text-center margin-top-0 tablet:margin-bottom-1" style={{ fontSize: '3rem' }}>
         Welcome, {username}
              </h1>
              <h2 className="font-sans text-black text-center margin-top-0" style={{ fontSize: '4rem' , color: '#4B4B4B'}}>
                 Manage your account.
              </h2>
             
             
 


              
      
    <Grid row style={{
                marginLeft: '10rem'
            }}>
      <Grid col={4}>
      <Link to="/profile">
      <Button type="button" size="big">
    Change password
  </Button>
  </Link>
        
      </Grid>

    


      <Grid col={4} style={{
                marginLeft: '5rem'
            }}>
              <Link to="/profile">
      <Button type="button" size="big" bg-info-lighter>
      Edit Personal Information
  </Button>
  </Link>
      
      </Grid>

    

      <Grid col={4} >
      <Link to="/profile">
  <Button type="button" size="big">
   
    Privacy Settings
  </Button>
  </Link>
      </Grid>
    </Grid>


   
            
            </Grid>
            </Grid>
      
            
          </GridContainer>
        </section>
       </div>

     


      </main>

      <Footer returnToTop={returnToTop} primary={footerPrimary} secondary={footerSecondary} />
    </>;
}

export default MyAccount;
