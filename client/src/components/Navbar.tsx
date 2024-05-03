import React, { useEffect, useState} from "react";
import { Header, NavDropDownButton, Menu, NavMenuButton, Button, Search, ExtendedNav, Footer, FooterNav, GovBanner, GridContainer, Logo, Address, Grid, SocialLinks, SocialLink, PrimaryNav, LanguageSelector, Title, IdentifierLogo, IdentifierLogos } from "@trussworks/react-uswds";
import logoImg from '../assets/logoImg.png';
import './Navbar.css';
import { Link, useNavigate} from 'react-router-dom';
import '@trussworks/react-uswds/lib/index.css';
import '@trussworks/react-uswds/lib/uswds.css';
import { useSelector, useDispatch } from 'react-redux';
import { login, logout , setUsername} from '../redux/slices/authSlice'; 
import { RootState } from '../redux/storeTypes';

const Navbar = (): React.ReactElement => {

 // const [isAuthenticated, setIsAuthenticated] = useState(false);
  //const [username, setUsername] = useState('');
  const [expanded, setExpanded] = useState(false);
  const [isOpen, setIsOpen] = useState([false, false]);
  const navigate = useNavigate();
  const isAuthenticated = useSelector((state: RootState) => state.auth.isAuthenticated);
  const dispatch = useDispatch();
  const username = useSelector((state: RootState) => state.auth.username);



  
  const checkUserSession = () => {
    fetch('http://localhost:8282/user/home', {
        method: 'GET',
        credentials: 'include',
    })
    //.then(response => response.json())
    .then(response => {
      console.log(response);
     return response.json()})
    
    .then(data => {
      if (data.username) {
        dispatch(login({ username: data.username }));  // Pass username to login action
        dispatch(setUsername(data.username as string));  // Set username in the state
    } else {
        dispatch(logout());  // Logout if no username found
    }
})
 .catch(error => {
     console.error("Error fetching session:", error);
     dispatch(logout());
 });
};

useEffect(() => {
 checkUserSession();
}, [dispatch]); 
 



/*
  useEffect(() => {
    // Fetch user session data from the backend
    fetch('http://localhost:8282/user/home', {
      method: 'GET',
      credentials: 'include', // To send cookies/session info
    })
      .then(response => {
        console.log(response);
       return response.json()})
      .then(data => {
        if (data.username) {
          setIsAuthenticated(true);
          setUsername(data.username);
          console.log(data.username);
        } else {
          setIsAuthenticated(false); // Update isAuthenticated if no user data is found
          
        }
      })
      .catch(error => {
        console.error("Error fetching session:", error);
        setIsAuthenticated(false); // Update isAuthenticated if an error occurs
      });
  }, [isAuthenticated]); // Run the effect whenever isAuthenticated changes
  */

  function handleLogout(event: any) {
    event.preventDefault();
    const url = 'http://localhost:8282/logout';

    fetch(url, {
       method: "POST",
       credentials: 'include',
       headers: {
          "Content-Type": "application/x-www-form-urlencoded", // Use form-urlencoded content type
       },
    })
    .then(response => {
       if (response.ok) {
        dispatch(logout());
        console.log('ok');
          // Clear any user-related data stored locally (e.g., localStorage)
          localStorage.removeItem('accessToken');
          sessionStorage.clear();  // Clear all session storage
          // Redirect the user to the login page or any other appropriate page
          navigate('/login');
       } else {
          // Handle error cases
          console.error('Logout failed');
       }
    })
    .catch(error => {
       console.error('Error:', error);
    });
 }

  
/*
  function handleLogout(event: any) {
     event.preventDefault();
     const url = 'http://localhost:8282/logout';

     fetch(url, {
        method: "POST",
        credentials: 'include',
        headers: {
           "Content-Type": "application/x-www-form-urlencoded",
        },
     })
     .then(response => {
        if (response.ok) {
           localStorage.removeItem('accessToken');
           // Redirect to logout page after logout
         
        } else {
           console.error('Logout failed');
        }
     })
     .catch(error => {
        console.error('Error:', error);
     });
  }

*/


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



    <Link to="/personal-form" key="personal" className="usa-nav__link">
      <span>File Taxes</span>
    </Link>,

...(isAuthenticated ? [
    <Link to="/my-account" key="my-account" className="usa-nav__link">
      <span>{username}</span>
    </Link>,
    <Button type="button" onClick={handleLogout} className="usa-nav__link" key="logout">
      Log out
    </Button>
  ] : [
    <Link to="/login" key="login" className="usa-nav__link">
      <span>Sign In</span>
    </Link>
  ]),
];
    {/*!isAuthenticated && <Link to="/login" key="login" className="usa-nav__link">
      <span>Sign In</span>
    </Link>,

    isAuthenticated && <>
      <Link to="/my-account" className="usa-nav__link">
        <span>Welcome, {username}</span>
      </Link>
      <Link onClick={handleLogout} className="usa-nav__link" to={""}>
        Log out
    </Link>
    </>

  ].filter(Boolean);*/}
  

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
