import React from 'react';

import { classes } from '../../../styles';

function NavItem({ href, children }) {
  return (
    <li className={classes.navItem}>
      <a
        className={`${classes.navLink}${
          window.location.pathname === href ? ' active' : ''
        }
                `}
        href={href}>
        {children}
      </a>
    </li>
  );
}

export default NavItem;
