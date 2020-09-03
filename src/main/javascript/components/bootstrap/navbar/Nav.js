import React from 'react';

import { classes } from '../../../styles';

function Nav({ children, direction = '' }) {
  return <ul className={classes.nav(direction.toLowerCase())}>{children}</ul>;
}

export default Nav;
