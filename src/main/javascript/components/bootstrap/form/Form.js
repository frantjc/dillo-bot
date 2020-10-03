import React from 'react';

import { classes } from '../../../styles';

function Form({ children, inline, style, className }) {
  return (
    <form
      className={`${inline ? classes.formInline : ''}${
        className ? ` ${className}` : ''
      }`}
      style={style}
    >
      {children}
    </form>
  );
}

export default Form;
