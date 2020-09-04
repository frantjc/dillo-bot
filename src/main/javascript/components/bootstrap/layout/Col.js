import React from 'react';

import { classes } from '../../../styles';

function Col({ width = 'auto', style, children }) {
  return (
    <div className={classes.col(width)} style={style}>
      {children}
    </div>
  );
}

export default Col;
