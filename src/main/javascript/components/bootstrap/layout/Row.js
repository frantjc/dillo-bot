import React from 'react';

import { classes } from '../../../styles';

function Row({ style, children }) {
  return (
    <div className={classes.row} style={style}>
      {children}
    </div>
  );
}

export default Row;
