import React, { useState} from "react";
import { Header, NavDropDownButton, Menu, NavMenuButton, Button, Search, ExtendedNav, Footer, FooterNav, GovBanner, GridContainer, Logo, Address, Grid, SocialLinks, SocialLink, PrimaryNav, LanguageSelector, Title, IdentifierLogo, IdentifierLogos } from "@trussworks/react-uswds";
import logoImg from '../assets/logoImg.png';
import './Navbar.css';
import { Link } from 'react-router-dom';
import '@trussworks/react-uswds/lib/index.css';
import '@trussworks/react-uswds/lib/uswds.css';

const Navbar = (): React.ReactElement => {
 
  const [expanded, setExpanded] = useState(false);
  const onClick = (): void => setExpanded(prvExpanded => !prvExpanded);
  const testMenuItems = [<a href="#linkOne" key="one">
      Current link
    </a>, <a href="#linkTwo" key="two">
      Simple link Two
    </a>];
  const [isOpen, setIsOpen] = useState([false, false]);

  const onToggle = (
    index: number,
    setIsOpen: React.Dispatch<React.SetStateAction<boolean[]>>
  ): void => {
    setIsOpen((prevIsOpen) => {
      const newIsOpen = [false, false]
      // eslint-disable-next-line security/detect-object-injection
      newIsOpen[index] = !prevIsOpen[index]
      return newIsOpen
    })
  }

  const primaryPages = [
    
   
    <LanguageSelector
    label="Languages"
    langs={[
      {
        attr: 'ar',
        label: 'العربية',
        label_local: 'Arabic',
        on_click: function Ga(){}
      },
      {
        attr: 'zh',
        label: '简体字',
        label_local: 'Chinese - Simplified',
        on_click: function Ga(){}
      },
      {
        attr: 'en',
        label: 'English',
        on_click: function Ga(){}
      },
      {
        attr: 'es',
        label: 'Español',
        label_local: 'Spanish',
        on_click: function Ga(){}
      },
      {
        attr: 'fr',
        label: 'Français',
        label_local: 'French',
        on_click: function Ga(){}
      },
      {
        attr: 'it',
        label: 'Italiano',
        label_local: 'Italian',
        on_click: function Ga(){}
      },
      {
        attr: 'ru',
        label: 'Pусский',
        label_local: 'Russian',
        on_click: function Ga(){}
      }
    ]}
    />,
     <>
     <NavDropDownButton menuId="testDropDownOne" onToggle={(): void => {
     onToggle(0, setIsOpen);
   }} isOpen={isOpen[0]} label="Languages" isCurrent={true} />
     <Menu key="one" items={testMenuItems} isOpen={isOpen[0]} id="testDropDownOne" />
   </>,
   
   <Link to="/personal-form" key="personal" className="usa-nav__link">
   <span>File Taxes</span>
 </Link>,
  <Link to="/login" key="login" className="usa-nav__link">
  <span>Sign In</span>
</Link>
  ];
 
  /*
  const primaryPages = [
    <Link to="/" style={{ textDecoration: 'none' }}>
      <Button type = {"button"}>Home</Button>
    </Link>,
    <Link to="/login" style={{ textDecoration: 'none' }}>
      <Button type={"button"} >Login</Button>
    </Link>
  ];
*/
  return (
    <>
      <GovBanner />
      <Header basic = {true}>
        <GridContainer containerSize="desktop">
        <div className="usa-nav-container">
        
          <div className="usa-navbar">
          <IdentifierLogos>
              <IdentifierLogo href="/">
                <img className="logo-img" src={logoImg} alt="logo" />
              </IdentifierLogo>
            </IdentifierLogos>
            {/*
          <Title id="extended-logo">
            <a href="/" title="Home" aria-label="Home">
                        <Logo image={<img src={logoImg} alt="Logo" />} />
                        </a>
  </Title>   */}
                        
                        <NavMenuButton onClick={onClick} label="Menu" />
            </div>
            
          
          <PrimaryNav items={primaryPages} >
            </PrimaryNav>
            </div>
        </GridContainer>
      </Header>
    </>
  );
}

export default Navbar;
