import React from 'react';

import { classes } from '../../../styles';

function FormGroup({ children, row, className, style }) {
  return (
    <div
      className={`${classes.formGroup}${row ? ' row' : ''}${
        className ? ` ${className}` : ''
      }`}
      style={style}>
      {children}
    </div>
  );
}

export default FormGroup;
