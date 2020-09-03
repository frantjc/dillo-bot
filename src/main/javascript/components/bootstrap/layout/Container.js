import React from 'react';

import { classes } from '../../../styles';

function Container({ style, children }) {
  return (
    <div className={classes.container} style={style}>
      {children}
    </div>
  );
}

export default Container;
