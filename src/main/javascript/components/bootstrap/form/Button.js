import React from 'react';

import { classes } from '../../../styles';

function Button({ children, type, onClick, style, className }) {
  return (
    <button
      className={`${classes.button}${className ? ` ${className}` : ''}`}
      style={style}
      type={type}
      onClick={onClick}
    >
      {children}
    </button>
  );
}

export default Button;
