import React from 'react';

import { classes } from '../../../styles';

function Thead({ className, children, color }) {
  return (
    <thead
      className={
        className || color
          ? `${className ? `${className}` : ''}${
              color ? `${className ? ' ' : ''}${classes.thead}-color` : ''
            }`
          : null
      }
    >
      {children}
    </thead>
  );
}

export default Thead;
