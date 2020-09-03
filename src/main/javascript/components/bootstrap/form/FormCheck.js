import React from 'react';

import { classes } from '../../../styles';

function FormCheck({ children, inline, className, style }) {
  return (
    <div
      className={`${classes.formCheck}${
        inline ? `${classes.formCheck}-inline` : ''
      }${className ? ` ${className}` : ''}`}
      style={style}>
      {children}
    </div>
  );
}

export default FormCheck;
