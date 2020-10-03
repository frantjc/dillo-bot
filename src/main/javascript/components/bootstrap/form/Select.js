import React from 'react';

import { classes } from '../../../styles';

function Select({ multiple, children, id, onChange, name, style, className }) {
  return (
    <select
      id={id}
      multiple={multiple}
      className={`${classes.formControl}${className ? ` ${className}` : ''}`}
      onChange={onChange}
      name={name}
      style={style}
    >
      {children}
    </select>
  );
}

export default Select;
