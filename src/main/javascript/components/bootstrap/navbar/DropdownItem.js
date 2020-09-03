import React from 'react';

import { classes } from '../../../styles';

function DropdownItem({ href, children }) {
  return (
    <a className={classes.dropdownItem} href={href}>
      {children}
    </a>
  );
}

export default DropdownItem;
