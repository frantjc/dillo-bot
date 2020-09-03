import React from 'react';

import { classes } from '../../../styles';

function Navbar({ children, brand }) {
  return (
    <nav className={classes.navbar}>
      {brand ? (
        <a className={classes.navBrand} href="/">
          {brand}
        </a>
      ) : null}
      <button
        className={classes.navbarToggler}
        type="button"
        data-toggle="collapse"
        data-target="#navbarNavAltMarkup"
        aria-controls="navbarNavAltMarkup"
        aria-expanded="false"
        aria-label="Toggle navigation">
        <span className={classes.navbarTogglerIcon} />
      </button>
      <div className={classes.navbarCollapse} id="navbarNavAltMarkup">
        {children}
      </div>
    </nav>
  );
}

export default Navbar;
