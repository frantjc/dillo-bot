import React from 'react';

import { classes } from '../../../styles';

function Label({ children, inputId, horizontal, className, style }) {
  return (
    <label
      htmlFor={inputId}
      className={`${horizontal ? classes.formLabel : ''}${
        className ? ` ${className}` : ''
      }`}
      style={style}
    >
      {children}
    </label>
  );
}

export default Label;
