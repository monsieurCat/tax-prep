import React, { useState} from "react";
import { Header, NavDropDownButton, Menu, NavMenuButton, Button, Search, ExtendedNav, Footer, FooterNav, GovBanner, GridContainer, Logo, Address, Grid, SocialLinks, SocialLink, PrimaryNav } from "@trussworks/react-uswds";
import logoImg from '../assets/logoImg.png';
import './Navbar.css';
import { Link } from 'react-router-dom';
import '@trussworks/react-uswds/lib/index.css';
import '@trussworks/react-uswds/lib/uswds.css';

const Navbar = (): React.ReactElement => {
 
  const [expanded, setExpanded] = useState(false);
 
  const mockToggle = (): void => {
    /* mock submit fn */
  }
 
  const primaryPages = [
    <Link to="/" style={{ textDecoration: 'none' }}>
      <Button type = {"button"}>Home</Button>
    </Link>,
    <Link to="/login" style={{ textDecoration: 'none' }}>
      <Button type={"button"} >Login</Button>
    </Link>
  ];

  return (
    <>
      <GovBanner />
      <Header basic={true}>
        <div className="usa-nav-container">
          <div className="usa-navbar">
            <Logo id="extended-logo" image={<img src={logoImg} alt="Logo" />} />
            <PrimaryNav items={primaryPages} onToggleMobileNav={mockToggle}/>
          </div>
        </div>
      </Header>
    </>
  );
}

export default Navbar;
