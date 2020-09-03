import React from 'react';

import { classes } from '../../../styles';

function Tr({ className, children, color }) {
  return (
    <tr
      className={
        className || color
          ? `${className ? `${className}` : ''}${
              color ? `${className ? ' ' : ''}${classes.table}-${color}` : ''
            }`
          : null
      }>
      {children}
    </tr>
  );
}

export default Tr;
