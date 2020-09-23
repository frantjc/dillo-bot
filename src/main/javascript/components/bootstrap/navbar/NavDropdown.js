import React from 'react';

import { classes } from '../../../styles';

function NavDropdown({ href, dropdown, children }) {
  return (
    <li className={`${classes.navLink} dropdown`}>
      <a
        className={`${classes.navLink} dropdown-toggle`}
        href={href}
        id="navbarDropdown"
        role="button"
        data-toggle="dropdown"
        aria-haspopup="true"
        aria-expanded="false"
      >
        {dropdown}
      </a>
      <div className={classes.dropdownMenu} aria-labelledby="navbarDropdown">
        {children}
      </div>
    </li>
  );
}

export default NavDropdown;
