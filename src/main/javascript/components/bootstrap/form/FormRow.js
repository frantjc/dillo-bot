import React from 'react';

import { classes } from '../../../styles';

function FormRow({ children, className, style }) {
  return (
    <div
      className={`${classes.formRow}${className ? ` ${className}` : ''}`}
      style={style}
    >
      {children}
    </div>
  );
}

export default FormRow;
