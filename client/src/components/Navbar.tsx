import React, { useEffect, useState} from "react";
import { Header, NavDropDownButton, Menu, NavMenuButton, Button, Search, ExtendedNav, Footer, FooterNav, GovBanner, GridContainer, Logo, Address, Grid, SocialLinks, SocialLink, PrimaryNav, LanguageSelector, Title, IdentifierLogo, IdentifierLogos } from "@trussworks/react-uswds";
import logoImg from '../assets/logoImg.png';
import './Navbar.css';
import { Link } from 'react-router-dom';
import '@trussworks/react-uswds/lib/index.css';
import '@trussworks/react-uswds/lib/uswds.css';

const Navbar = (): React.ReactElement => {

  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [username, setUsername] = useState('');
  const [expanded, setExpanded] = useState(false);
  const [isOpen, setIsOpen] = useState([false, false]);




  useEffect(() => {
    // Fetch user session data from the backend
    fetch('http://localhost:8282/api/auth/privateData', {
      method: 'GET',
      credentials: 'include', // To send cookies/session info
    })
      .then(response => response.json())
      .then(data => {
        if (data.username) {
          setIsAuthenticated(true);
          setUsername(data.username);
        }
      })
      .catch(error => console.error("Error fetching session:", error));
  }, []); // Empty dependency array to run only once









  const onClick = (): void => setExpanded(prvExpanded => !prvExpanded);


  const languages = [
    { attr: 'ar', label: 'العربية', label_local: 'Arabic', on_click: function Ga() { } },
    { attr: 'zh', label: '简体字', label_local: 'Chinese - Simplified', on_click: function Ga() { } },
    { attr: 'en', label: 'English', on_click: function Ga() { } },
    { attr: 'es', label: 'Español', label_local: 'Spanish', on_click: function Ga() { } },
    { attr: 'fr', label: 'Français', label_local: 'French', on_click: function Ga() { } },
    { attr: 'it', label: 'Italiano', label_local: 'Italian', on_click: function Ga() { } },
    { attr: 'ru', label: 'Pусский', label_local: 'Russian', on_click: function Ga() { } },
  ];




  const testMenuItems = languages.map((lang, index) => (
    <a href="#" key={index} onClick={lang.on_click}>
      {lang.label}
    </a>
  ));




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


    <>
      <NavDropDownButton menuId="testDropDownOne" onToggle={(): void => {
        onToggle(0, setIsOpen);
      }} isOpen={isOpen[0]} label="Languages" isCurrent={true} />
      <Menu key="one" items={testMenuItems} isOpen={isOpen[0]} id="testDropDownOne" />
    </>,

<Link to="/logout" key="personal" className="usa-nav__link">
<span>TEST LINK!! </span>
</Link>,

    <Link to="/personal-form" key="personal" className="usa-nav__link">
      <span>File Taxes</span>
    </Link>,

    !isAuthenticated && <Link to="/login" key="login" className="usa-nav__link">
      <span>Sign In</span>
    </Link>,

    isAuthenticated && <>
      <Link to="/my-account" className="usa-nav__link">
        <span>Welcome, {username}</span>
      </Link>
      <Link to="/log-out" className="usa-nav__link">
        <span>Logout</span>
      </Link>
    </>

  ].filter(Boolean);
  //removes null values

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
      <Header basic={true}>
        <GridContainer containerSize="widescreen">
          <div className="usa-nav-container" >

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
