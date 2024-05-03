import React, { useState } from 'react';
import logoImg from '../assets/logoImg.png';
import { Address, Button, ButtonGroup, Card, CardBody, CardFooter, CardHeader, CardMedia, ExtendedNav, Footer, FooterNav, GovBanner, Grid, GridContainer, Header, Link, Logo, MediaBlockBody, Menu, NavDropDownButton, NavMenuButton, Search, SocialLink, SocialLinks, Title } from '@trussworks/react-uswds';

const LoginHome: React.FC = () => {
  const [mobileNavOpen, setMobileNavOpen] = useState(false);
  const [navDropdownOpen, setNavDropdownOpen] = useState([false, false]);
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
      <a href="#">Return to top</a>
    </GridContainer>;
  const socialLinkItems = [<SocialLink key="facebook" name="Facebook" href="#" />, <SocialLink key="twitter" name="Twitter" href="#" />, <SocialLink key="youtube" name="YouTube" href="#" />, <SocialLink key="instagram" name="Instagram" href="#" />, <SocialLink key="rss" name="RSS" href="#" />];
  const footerPrimary = <FooterNav aria-label="Footer navigation" size="medium" links={Array(5).fill(<a href="javascript:void(0)" className="usa-footer__primary-link">
          Primary link
        </a>)} />;
  const footerSecondary = <>
      <Grid row gap>
        <Logo size="medium" image={<img className="usa-footer__logo-img" src={logoImg} alt="" />} heading={<p className="usa-footer__logo-heading">Name of Agency</p>} />
        <Grid className="usa-footer__contact-links" mobileLg={{
        col: 6
      }}>
          <SocialLinks links={socialLinkItems} />
          <h3 className="usa-footer__contact-heading">Agency Contact Center</h3>
          <Address size="medium" items={[<a key="telephone" href="tel:1-800-555-5555">
                (800) CALL-GOVT
              </a>, <a key="email" href="mailto:info@agency.gov">
                info@agency.gov
              </a>]} />
        </Grid>
      </Grid>
    </>;
  return <>

  
   
  
      <main id="main-content">
      <div className="bg-warning-light" style={{ padding: '8rem',  marginTop: '2rem'}}>
        <section id="test-section-id" className="usa-graphic-list usa-section bg-warning-light">
          <GridContainer  >
            <Grid row>
            <Grid col={12} style={{ marginLeft: 'auto', marginRight: 'auto' }}>
          <div className="usa-hero__heading bg-accent-warm" style={{ padding: '3rem', width: '80%' }}>
         
           
              <h1 className="usa-hero__heading">
                <span className="usa-hero__heading--alt">Welcome Back, Alice!</span>
            
              </h1>
             
           
              <Grid row>
      <Grid col={4}>
        <Button type="button" size = "big" onClick={() => window.location.href = '/personal-form'}>Start Filing</Button>
      </Grid>

      <Grid col={4}>
        <Button type="button" size = "big" onClick={() => window.location.href = '/review'}>My Taxes</Button>
      </Grid>

      <Grid col={4}>
        <Button type="button"  onClick={() => window.location.href = '/my-account'}>My Account</Button>
      </Grid>
    </Grid>
      



   
            </div>
            </Grid>
            </Grid>
      
            
          </GridContainer>
        </section>
       </div>
        <section className="grid-container usa-section">


        <Card layout="flagMediaRight" gridLayout={{
    tablet: {
      col: 12
    }
  }} containerProps={{
    className: 'border-primary-vivid'
  }}>
      <CardHeader>
        <h3 className="usa-card__heading">My friend’s friend</h3>
      </CardHeader>
      <CardMedia className="flex-align-center" imageClass="circle-card margin-x-auto">
        <img src={logoImg} alt="" />
      </CardMedia>
      <CardBody>
        <p>
          He roused up and gave me good-day. I told him a friend of mine had
          commissioned me to make some inquiries about a cherished companion of
          his boyhood named Leonidas W. Smiley.
        </p>
      </CardBody>
      <CardFooter>
        <Link href={'#'} variant="unstyled" allowSpacebarActivation className="usa-button usa-button--outline">
          Give me good-day
        </Link>
      </CardFooter>
    </Card>
          <Grid row gap>
            <Grid tablet={{
            col: 4
          }}>
              <h2 className="font-heading-xl margin-top-0 tablet:margin-bottom-0">
                A tagline highlights your approach
              </h2>
            </Grid>
            <Grid tablet={{
            col: 8
          }} className="usa-prose">
              <p>
                The tagline should inspire confidence and interest, focusing on
                the value that your overall approach offers to your audience.
                Use a heading typeface and keep your tagline to just a few
                words, and don’t confuse or mystify.
              </p>
              <p>
                Use the right side of the grid to explain the tagline a bit
                more. What are your goals? How do you do your work? Write in the
                present tense, and stay brief here. People who are interested
                can find details on internal pages.
              </p>
            </Grid>
          </Grid>
        </section>

        <section className="usa-graphic-list usa-section bg-primary-lighter">
          <GridContainer>
            <Grid row gap className="usa-graphic-list__row">
              <Grid tablet={{
              col: true
            }} className="usa-media-block">
                <img className="usa-media-block__img" src={logoImg} alt="Alt text" />
                <MediaBlockBody>
                  <h2 className="usa-graphic-list__heading">
                    Graphic headings can vary.
                  </h2>
                  <p>
                    Graphic headings can be used a few different ways, depending
                    on what your landing page is for. Highlight your values,
                    specific program areas, or results.
                  </p>
                </MediaBlockBody>
              </Grid>
              <Grid tablet={{
              col: true
            }} className="usa-media-block">
                <img className="usa-media-block__img" src={logoImg} alt="Alt text" />
                <MediaBlockBody>
                  <h2 className="usa-graphic-list__heading">
                    Stick to 6 or fewer words.
                  </h2>
                  <p>
                    Keep body text to about 30 words. They can be shorter, but
                    try to be somewhat balanced across all four. It creates a
                    clean appearance with good spacing.
                  </p>
                </MediaBlockBody>
              </Grid>
            </Grid>
            <Grid row gap className="usa-graphic-list__row">
              <Grid tablet={{
              col: true
            }} className="usa-media-block">
                <img className="usa-media-block__img" src={logoImg} alt="Alt text" />
                <MediaBlockBody>
                  <h2 className="usa-graphic-list__heading">
                    Never highlight anything without a goal.
                  </h2>
                  <p>
                    For anything you want to highlight here, understand what
                    your users know now, and what activity or impression you
                    want from them after they see it.
                  </p>
                </MediaBlockBody>
              </Grid>
              <Grid tablet={{
              col: true
            }} className="usa-media-block">
                <img className="usa-media-block__img" src={logoImg} alt="Alt text" />
                <MediaBlockBody>
                  <h2 className="usa-graphic-list__heading">
                    Could also have 2 or 6.
                  </h2>
                  <p>
                    In addition to your goal, find out your users’ goals. What
                    do they want to know or do that supports your mission? Use
                    these headings to show these.
                  </p>
                </MediaBlockBody>
              </Grid>
            </Grid>
          </GridContainer>
        </section>

        <section id="test-section-id" className="usa-section">
          <GridContainer>
            <h2 className="font-heading-xl margin-y-0">Section heading</h2>
            <p className="usa-intro">
              Everything up to this point should help people understand your
              agency or project: who you are, your goal or mission, and how you
              approach it. Use this section to encourage them to act. Describe
              why they should get in touch here, and use an active verb on the
              button below. “Get in touch,” “Learn more,” and so on.
            </p>
            <a href="#" className="usa-button usa-button--big">
              Call to action
            </a>
          </GridContainer>
        </section>
      </main>

      <Footer returnToTop={returnToTop} primary={footerPrimary} secondary={footerSecondary} />
    </>;
}

export default LoginHome;
