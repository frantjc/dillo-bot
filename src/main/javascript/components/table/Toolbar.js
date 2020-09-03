import React from 'react';

import { classes } from '../../styles';

function Toolbar({ children }) {
  return <div className={classes.toolbar}>{children}</div>;
}

export default Toolbar;
