import React, { useState} from "react";
import { Header, NavDropDownButton, Menu, NavMenuButton, Search, ExtendedNav, Footer, FooterNav, GovBanner, GridContainer, Logo, Address, Grid, SocialLinks, SocialLink, PrimaryNav } from "@trussworks/react-uswds";
import logoImg from '../assets/logoImg.png';
import './Navbar.css';
import { Link } from 'react-router-dom';

const Navbar = (): React.ReactElement => {
 
  const [expanded, setExpanded] = useState(false);
  const onClick = (): void => setExpanded(prvExpanded => !prvExpanded);

  const onToggle = (index: number): void => {
    setIsOpen(prevIsOpen => {
      const newState = [...prevIsOpen];
      newState[index] = !newState[index];
      return newState;
    });
  };

  const PrimaryNavLink = ({ href, children }: { href: string; children: React.ReactNode }) => (
    <a href={href} className="usa-nav__link">
      {children}
    </a>
  );
 
  const dropdownItems = [<a href="#linkOne" key="one">
      Current link
    </a>, <a href="#linkTwo" key="two">
      Simple link Two
    </a>];


  const [isOpen, setIsOpen] = useState([false, false]);
  const primaryPages = [
  <PrimaryNavLink href="/" key="home">
    Home
  </PrimaryNavLink>,
  <PrimaryNavLink href="#three" key="parent">
    <span>Login</span>
  </PrimaryNavLink>
    ];
  
   

  return (
    <>
      <GovBanner />
      <Header basic={true} >
      <div className="usa-nav-container">
        <div className="usa-navbar">
          <Logo id="extended-logo" image={<img src={logoImg} alt="Logo" />} />
        
          <PrimaryNav items={primaryPages} />
      
        </div>
       
        </div>
      </Header>
    </>
  );
}

export default Navbar;
